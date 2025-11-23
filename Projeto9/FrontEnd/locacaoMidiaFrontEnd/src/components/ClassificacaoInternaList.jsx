import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function ClassificacaoInternaList() {
    const [items, setItems] = useState([]);
    const [loading, setLoading] = useState(true);
    const [editingId, setEditingId] = useState(null);
    const [editData, setEditData] = useState({ descricao: '', valor_aluguel: '' });
    const navigate = useNavigate();

    useEffect(() => {
        fetch('/api/v1/classificacao-interna')
            .then(resp => {
                if (!resp.ok) throw new Error('Erro HTTP: ' + resp.status);
                return resp.json();
            })
            .then(data => {
                setItems(data);
                setLoading(false);
            })
            .catch(err => {
                console.error('Erro ao buscar classificações internas:', err);
                setLoading(false);
            });
    }, []);

    const handleDelete = (id) => {
        if (!window.confirm(`Tem certeza que deseja excluir a Classificação Interna ID ${id}?`)) return;
        fetch(`/api/v1/classificacao-interna/${id}`, { method: 'DELETE' })
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
        setEditData({ descricao: item.descricao || '', valor_aluguel: item.valor_aluguel ?? '' });
    };

    const handleEditCancel = () => setEditingId(null);

    const handleEditChange = (e) => {
        const { name, value } = e.target;
        setEditData(prev => ({ ...prev, [name]: value }));
    };

    const handleEditSubmit = (e) => {
        e.preventDefault();
        const updated = { id: editingId, descricao: editData.descricao, valor_aluguel: Number(editData.valor_aluguel) };
        fetch(`/api/v1/classificacao-interna/${editingId}`, {
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

    if (loading) return <p>Carregando classificações internas...</p>;

    return (
        <div>
            <h2>Lista de Classificações Internas</h2>
            <button onClick={() => navigate('/classificacaoInterna/novo')}>+ Adicionar Nova Classificação Interna</button>

            {items.length === 0 ? (
                <p>Nenhuma classificação encontrada no banco de dados.</p>
            ) : (
                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Descrição</th>
                            <th>Valor Aluguel</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        {items.map(item => (
                            <tr key={item.id}>
                                {editingId === item.id ? (
                                    <td colSpan="4">
                                        <form onSubmit={handleEditSubmit} style={{ display: 'flex', gap: '8px', alignItems: 'center' }}>
                                            <input name="descricao" value={editData.descricao} onChange={handleEditChange} required placeholder="Descrição" />
                                            <input name="valor_aluguel" type="number" step="0.01" value={editData.valor_aluguel} onChange={handleEditChange} required placeholder="Valor Aluguel" />
                                            <button type="submit">Salvar</button>
                                            <button type="button" onClick={handleEditCancel}>Cancelar</button>
                                        </form>
                                    </td>
                                ) : (
                                    <>
                                        <td>{item.id}</td>
                                        <td>{item.descricao}</td>
                                        <td>{item.valor_aluguel}</td>
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

export default ClassificacaoInternaList;
