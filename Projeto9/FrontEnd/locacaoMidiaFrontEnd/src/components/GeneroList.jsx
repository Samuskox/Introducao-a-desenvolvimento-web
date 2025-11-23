import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function GeneroList() {
    const [items, setItems] = useState([]);
    const [loading, setLoading] = useState(true);
    const [editingId, setEditingId] = useState(null);
    const [editData, setEditData] = useState({ descricao: '' });
    const navigate = useNavigate();

    useEffect(() => {
        fetch('/api/v1/generos')
            .then(resp => {
                if (!resp.ok) throw new Error('Erro HTTP: ' + resp.status);
                return resp.json();
            })
            .then(data => {
                setItems(data);
                setLoading(false);
            })
            .catch(err => {
                console.error('Erro ao buscar gêneros:', err);
                setLoading(false);
            });
    }, []);

    const handleDelete = (id) => {
        if (!window.confirm(`Tem certeza que deseja excluir o Gênero ID ${id}?`)) return;
        fetch(`/api/v1/generos/${id}`, { method: 'DELETE' })
            .then(resp => {
                if (resp.status === 204 || resp.ok) {
                    setItems(items.filter(i => i.id !== id));
                } else {
                    throw new Error('Falha na exclusão. Status: ' + resp.status);
                }
            })
            .catch(err => {
                alert('Erro ao excluir: ' + err.message);
                console.error(err);
            });
    };

    const handleEditStart = (item) => {
        setEditingId(item.id);
        setEditData({ descricao: item.descricao || '' });
    };

    const handleEditCancel = () => setEditingId(null);

    const handleEditChange = (e) => setEditData({ descricao: e.target.value });

    const handleEditSubmit = (e) => {
        e.preventDefault();
        const updated = { id: editingId, descricao: editData.descricao };
        fetch(`/api/v1/generos/${editingId}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(updated)
        })
        .then(resp => {
            if (!resp.ok) throw new Error('Falha na atualização. Status: ' + resp.status);
            return resp.json();
        })
        .then(data => {
            setItems(items.map(i => i.id === editingId ? data : i));
            setEditingId(null);
        })
        .catch(err => {
            alert('Erro ao atualizar: ' + err.message);
            console.error(err);
        });
    };

    if (loading) return <p>Carregando gêneros...</p>;

    return (
        <div>
            <h2>Lista de Gêneros</h2>
            <button onClick={() => navigate('/genero/novo')}>+ Adicionar Novo Gênero</button>

            {items.length === 0 ? (
                <p>Nenhum gênero encontrado no banco de dados.</p>
            ) : (
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Descrição</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        {items.map(item => (
                            <tr key={item.id}>
                                {editingId === item.id ? (
                                    <td colSpan="3">
                                        <form onSubmit={handleEditSubmit} style={{ display: 'flex', gap: '8px', alignItems: 'center' }}>
                                            <input name="descricao" value={editData.descricao} onChange={handleEditChange} required placeholder="Descrição" />
                                            <button type="submit">Salvar</button>
                                            <button type="button" onClick={handleEditCancel}>Cancelar</button>
                                        </form>
                                    </td>
                                ) : (
                                    <>
                                        <td>{item.id}</td>
                                        <td>{item.descricao}</td>
                                        <td>
                                            <button onClick={() => handleEditStart(item)}>Editar</button>
                                            <button onClick={() => handleDelete(item.id)} style={{ marginLeft: '6px' }}>Excluir</button>
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

export default GeneroList;
