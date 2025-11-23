import React, { useState, useEffect } from 'react';
import { useNavigate } from "react-router";

function ExemplaresList() {
    const [exemplares, setExemplares] = useState([]);
    const [loading, setLoading] = useState(true);
    const [midias, setMidias] = useState([]);
    const [editingId, setEditingId] = useState(null);
    const [editData, setEditData] = useState({ midiaId: '', locacaoId: '', data_devolucao: '' });
    const Navigate = useNavigate();
    useEffect(() => {
        fetch('/api/v1/exemplares')
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Erro HTTP: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                setExemplares(data);
                setLoading(false);
            })
            .catch(error => {
                console.error("Erro ao buscar exemplares:", error);
                setLoading(false);
            });
    }, []);
    useEffect(() => {
        fetch('/api/v1/midias')
            .then(resp => {
                if (!resp.ok) throw new Error('Erro ao carregar midias');
                return resp.json();
            }
            ).then(data => setMidias(data))
            .catch(err => console.error(err));
    }, []);

    const handleDelete = (id) => {
        if (!window.confirm(`Tem certeza que deseja excluir o Exemplar ID ${id}?`)) {
            return;
        }
        fetch(`/api/v1/exemplares/${id}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.status === 204 || response.ok) {
                    setExemplares(exemplares.filter(exemplar => exemplar.codigo_interno !== id));
                } else {
                    throw new Error(`Falha na exclusão. Status: ${response.status}`);
                }
            })
            .catch(error => {
                console.error('Erro ao excluir exemplar:', error);
                console.error(error)
            });
    };

    const handleEditStart = (exemplar) => {
        setEditingId(exemplar.codigo_interno);
        setEditData({
            disponivel: exemplar.disponivel,
            midiaId: exemplar.midia.id
        });
    }

    const handleEditChange = (e) => {
        const { name, value } = e.target;
        setEditData(prevData => ({
            ...prevData,
            [name]: value
        }));
    }

    const handleEditCancel = () => {
        setEditingId(null);
    }

    const handleEditSubmit = (e) => {
        e.preventDefault();
        const updatedExemplar = {
            codigoInterno: editingId,
            disponivel: (editData.disponivel === 'true' || editData.disponivel === true),
            midia: { id: editData.midiaId },
        };
        fetch(`/api/v1/exemplares/${editingId}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(updatedExemplar),
        })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Erro HTTP: ${response.status}`);
                }
                return response.json();
            }
            ).then(data => {
                setExemplares(exemplares.map(exemplar => exemplar.codigo_interno === editingId ? data : exemplar));
                setEditingId(null);
            })
            .catch(error => {
                console.error('Erro ao atualizar exemplar:', error);
            });
    };

    if (loading) {
        return <p>Carregando exemplares da API Spring Boot...</p>;
    }
    return (
        <div>
    <h2>Lista de Exemplares</h2>

    <button onClick={() => Navigate('/exemplares/novo')}>+ Adicionar Novo Exemplar</button>

    {exemplares.length === 0 ? (
        <p>Nenhum exemplar encontrado no banco de dados.</p>
    ) : (
        <table>
            <thead>
                <tr>
                    <th>Código Interno</th>
                    <th>Disponível</th>
                    <th>Mídia</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
                {exemplares.map((exemplar) => (
                    <tr key={exemplar.codigo_interno}>
                        {editingId === exemplar.codigo_interno ? (
                            <td colSpan={4}>
                                <form onSubmit={handleEditSubmit}>
                                    <select
                                        name="disponivel"
                                        value={editData.disponivel}
                                        onChange={handleEditChange}
                                        required
                                    >
                                        <option value="true">Sim</option>
                                        <option value="false">Não</option>
                                    </select>

                                    <select
                                        name="midiaId"
                                        value={editData.midiaId}
                                        onChange={handleEditChange}
                                        required
                                    >
                                        <option value="">Selecione uma mídia</option>
                                        {midias.map((midia) => (
                                            <option key={midia.id} value={midia.id}>
                                                {midia.titulo} ({midia.ano_lancamento})
                                            </option>
                                        ))}
                                    </select>

                                    <button type="submit">Salvar</button>
                                    <button type="button" onClick={handleEditCancel}>
                                        Cancelar
                                    </button>
                                </form>
                            </td>
                        ) : (
                            <>
                                <td>{exemplar.codigo_interno}</td>
                                <td>{exemplar.disponivel ? "Sim" : "Não"}</td>
                                <td>{exemplar.midia?.titulo ?? ""}</td>
                                <td>
                                    <button onClick={() => handleEditStart(exemplar)}>Editar</button>
                                    <button
                                        onClick={() => handleDelete(exemplar.codigo_interno)}
                                        style={{ marginLeft: "6px" }}
                                    >
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

export default ExemplaresList;
