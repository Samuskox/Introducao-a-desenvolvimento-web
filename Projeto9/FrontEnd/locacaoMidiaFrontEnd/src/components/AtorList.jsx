import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function AtorList() {
    const [atores, setAtores] = useState([]);
    const [loading, setLoading] = useState(true);
    const [editingId, setEditingId] = useState(null);
    const [editData, setEditData] = useState({ nome: '', sobrenome: '', data_nascimento: '' });
    const navigate = useNavigate();

    useEffect(() => {
        fetch('/api/v1/atores')
            .then(resp => {
                if (!resp.ok) throw new Error('Erro HTTP: ' + resp.status);
                return resp.json();
            })
            .then(data => {
                setAtores(data);
                setLoading(false);
            })
            .catch(err => {
                console.error('Erro ao buscar atores:', err);
                setLoading(false);
            });
    }, []);

    const handleDelete = (id) => {
        if (!window.confirm(`Tem certeza que deseja excluir o Ator ID ${id}?`)) return;
        fetch(`/api/v1/atores/${id}`, { method: 'DELETE' })
            .then(resp => {
                if (resp.status === 204 || resp.ok) {
                    setAtores(atores.filter(a => a.id !== id));
                } else {
                    throw new Error('Falha na exclusão. Status: ' + resp.status);
                }
            })
            .catch(err => {
                alert('Erro ao excluir: ' + err.message);
                console.error(err);
            });
    };

    const handleEditStart = (ator) => {
        setEditingId(ator.id);
        setEditData({
            nome: ator.nome || '',
            sobrenome: ator.sobrenome || '',
            data_estreia: ator.data_estreia || ''
        });
    };

    const handleEditCancel = () => setEditingId(null);

    const handleEditChange = (e) => {
        const { name, value } = e.target;
        setEditData(prev => ({ ...prev, [name]: value }));
    };

    const handleEditSubmit = (e) => {
        e.preventDefault();
        const updated = {
            id: editingId,
            nome: editData.nome,
            sobrenome: editData.sobrenome,
            data_estreia: editData.data_estreia
        };

        fetch(`/api/v1/atores/${editingId}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(updated)
        })
        .then(resp => {
            if (!resp.ok) throw new Error('Falha na atualização. Status: ' + resp.status);
            return resp.json();
        })
        .then(data => {
            setAtores(atores.map(a => a.id === editingId ? data : a));
            setEditingId(null);
        })
        .catch(err => {
            alert('Erro ao atualizar: ' + err.message);
            console.error(err);
        });
    };

    if (loading) return <p>Carregando atores...</p>;

    return (
        <div>
            <h2>Lista de Atores</h2>
            <button onClick={() => navigate('/ator/novo')}>+ Adicionar Novo Ator</button>

            {atores.length === 0 ? (
                <p>Nenhum ator encontrado no banco de dados.</p>
            ) : (
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Sobrenome</th>
                            <th>Data de Estreia</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        {atores.map(ator => (
                            <tr key={ator.id}>
                                {editingId === ator.id ? (
                                    <td colSpan="5">
                                        <form onSubmit={handleEditSubmit} style={{ display: 'flex', gap: '8px', alignItems: 'center', flexWrap: 'wrap' }}>
                                            <input name="nome" value={editData.nome} onChange={handleEditChange} required placeholder="Nome" />
                                            <input name="sobrenome" value={editData.sobrenome} onChange={handleEditChange} required placeholder="Sobrenome" />
                                            <input type="date" name="data_estreia" value={editData.data_estreia} onChange={handleEditChange} />
                                            <button type="submit">Salvar</button>
                                            <button type="button" onClick={handleEditCancel}>Cancelar</button>
                                        </form>
                                    </td>
                                ) : (
                                    <>
                                        <td>{ator.id}</td>
                                        <td>{ator.nome}</td>
                                        <td>{ator.sobrenome}</td>
                                        <td>{ator.data_estreia}</td>
                                        <td>
                                            <button onClick={() => handleEditStart(ator)}>Editar</button>
                                            <button onClick={() => handleDelete(ator.id)} style={{ marginLeft: '6px' }}>Excluir</button>
                                        </td>
                                    </>
                                )}
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
}

export default AtorList;
