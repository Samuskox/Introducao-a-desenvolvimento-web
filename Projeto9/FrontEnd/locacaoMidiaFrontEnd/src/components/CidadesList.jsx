import React, { useState, useEffect } from 'react';
import { useNavigate } from "react-router";

function CidadesList() {
    // Estado para armazenar a lista de cidades
    const [cidades, setCidades] = useState([]);
    const [loading, setLoading] = useState(true);
    const [estados, setEstados] = useState([]);
    const [editingId, setEditingId] = useState(null);
    const [editData, setEditData] = useState({ nome: '', estado: '' });
    const Navigate = useNavigate();

    useEffect(() => {
        fetch('/api/v1/cidades') 
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Erro HTTP: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                setCidades(data);
                setLoading(false);
            })
            .catch(error => {
                console.error("Erro ao buscar cidades:", error);
                setLoading(false);
            });
    }, []); 

    useEffect(() => {
        // Buscar estados para popular o select ao editar
        fetch('/api/v1/estados')
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Erro HTTP: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                setEstados(data);
            })
            .catch(error => {
                console.error('Erro ao buscar estados:', error);
            });
    }, []);

    if (loading) {
        return <p>Carregando cidades da API Spring Boot...</p>;
    }

    const handleDelete = (id) => {
        if (!window.confirm(`Tem certeza que deseja excluir a Cidade ID ${id}?`)) {
            return;
        }
        fetch(`/api/v1/cidades/${id}`, {
            method: 'DELETE',
        })
        .then(response => {
            if (response.status === 204 || response.ok) { 
                setCidades(cidades.filter(cidade => cidade.id !== id));
                console.log('Cidade excluída com sucesso!');
            } else {
                throw new Error(`Falha na exclusão. Status: ${response.status}`);
            }
        })
        .catch(error => {
            console.error("Erro ao excluir cidade:", error);
        })
    }

    const handleEditStart = (cidade) => {
        setEditingId(cidade.id);
        setEditData({ nome: cidade.nome, estadoId: cidade.estado.id })
    }

    const handleEditCancel = () => {
        setEditingId(null);
    }

    const handleEditChange = (e) => {
        const { name, value } = e.target;
        setEditData(prev => ({ ...prev, [name]: value }))
    }

    const handleEditSubmit = (e) => {
        e.preventDefault();

        const cidadeAtualizada = {
                    id: editingId,
                    nome: editData.nome, 
                    estado: { 
                        id: editData.estadoId 
                    } 
    };

        fetch(`/api/v1/cidades/${editingId}`, {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(cidadeAtualizada),
        })
        .then(response => {
            if (!response.ok) {
                throw new Error(`Erro HTTP: ${response.status}`);
            }
            return response.json();
        })
        .then(updatedCidade => {
            setCidades(cidades.map(cidade => 
                cidade.id === editingId ? updatedCidade : cidade
            ));
            setEditingId(null);
        })
        .catch(error => {
            console.error("Erro ao atualizar cidade:", error);
        });
    }
    



    return (
        <div>
            <h2>Lista de Cidades</h2>

            <button onClick={() =>  Navigate("/cidades/novo")}>
            + Adicionar Nova Cidade
            </button>

            {cidades.length === 0 ? (
                <p>Nenhuma cidade encontrada no banco de dados.</p>
            ) : (
                <table>
                    <thead>
                        <tr >
                            <th>ID</th>
                            <th>Nome da Cidade</th>
                            <th>Estado</th>
                            <th>Ações</th>
                        </tr>
                    </thead>

                    <tbody>
                    {cidades.map(cidade => (
                            <tr key={cidade.id}>
                                <td>{cidade.id}</td>
                                
                                {editingId === cidade.id ? (
                                    <td colSpan="3">
                                    <form onSubmit={handleEditSubmit} style={{ display: 'flex', gap: '10px' }}>
                                        <input 
                                            type="text" 
                                            name="nome" 
                                            value={editData.nome} 
                                            onChange={handleEditChange}
                                            required 
                                        />
                                        <select
                                            name="estadoId"
                                            value={editData.estadoId}
                                            onChange={handleEditChange}
                                            required
                                            style={{ width: '180px' }}
                                        >
                                            <option value="">Selecione um estado</option>
                                            {estados.map(estado => (
                                                <option key={estado.id} value={estado.id}>
                                                    {estado.nome} ({estado.sigla})
                                                </option>
                                            ))}
                                        </select>
                                        <button type="submit">Salvar</button>
                                        <button type="button" onClick={handleEditCancel}>Cancelar</button>
                                    </form>
                                </td>
                                ) : (
                                <>
                                <td>{cidade.nome}</td>
                                <td>{cidade.estado?.sigla ?? ''}</td>
                                <td>
                                        <button onClick={() => handleEditStart(cidade)}>
                                            Editar
                                        </button>
                                        <button onClick={() => handleDelete(cidade.id)} style={{ marginLeft: '5px' }}>
                                            Excluir
                                        </button>
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



export default CidadesList;