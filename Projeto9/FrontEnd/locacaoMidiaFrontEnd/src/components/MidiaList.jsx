import React, { useState, useEffect, use } from 'react';
import { useNavigate } from "react-router";

function MidiaList() {
    // Estado que guarda a lista de mídias
    const [midias, setMidias] = useState([]);
    const [ator, setAtor] = useState([]);
    const [genero, setGenero] = useState([]);
    const [classificacaoEtaria, setClassificacaoEtaria] = useState([]);
    const [classificacaoInterna, setClassificacaoInterna] = useState([]);
    const [tipo, setTipo] = useState([]);
    const [editingId, setEditingId] = useState(null);
    const [editData, setEditData] = useState({ titulo: '', ano_lancamento: '',
        ator_principalId: '', ator_coadjuvanteId: '', generoId: '',
        classificacaoEtariaId: '', classificacaoInternaId: '', tipoId: '' });
    const [loading, setLoading] = useState(true);

    const Navigate = useNavigate();

    useEffect(() => {
        fetch('/api/v1/midias')
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Erro HTTP: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                setMidias(data);
                setLoading(false);
            })
            .catch(error => {
                console.error("Erro ao buscar mídias:", error);
                setLoading(false);
            })
    }, [])

    useEffect(() => {
        fetch('/api/v1/atores')
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Erro HTTP: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                setAtor(data);
            })
            .catch(error => {
                console.error("Erro ao buscar atores:", error);
            })
    }, [])

    useEffect(() => {
        fetch('/api/v1/generos')
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Erro HTTP: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                setGenero(data);
            })
            .catch(error => {
                console.error("Erro ao buscar generos:", error);
            })
    }, [])

    useEffect(() => {
        fetch('/api/v1/classificacao-etaria')
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Erro HTTP: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                setClassificacaoEtaria(data);
            })
            .catch(error => {
                console.error("Erro ao buscar classificações etarias:", error);
            })
    }, [])

    useEffect(() => {
        fetch('/api/v1/classificacao-interna')
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Erro HTTP: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                setClassificacaoInterna(data);
            })
            .catch(error => {
                console.error("Erro ao buscar classificações internas:", error);
            })
    }, [])

    useEffect(() => {
        fetch('/api/v1/tipos')
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Erro HTTP: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                setTipo(data);
            })
            .catch(error => {
                console.error("Erro ao buscar tipos:", error);
            })
    }, [])

    const handleDelete = (id) => {
        if (!window.confirm(`Tem certeza que deseja excluir a Mídia ID ${id}?`)) {
            return;
        }
        fetch(`/api/v1/midias/${id}`, {
            method: 'DELETE',
        })
            .then(response => {
                if (response.status === 204 || response.ok) {
                    setMidias(midias.filter(midia => midia.id !== id));
                    console.log('Mídia excluída com sucesso!');
                } else {
                    throw new Error(`Falha na exclusão. Status: ${response.status}`);
                }
            })
            .catch(error => {
                console.error("Erro ao excluir mídia:", error);
            })
    }

    const handleEditStart = (midia) => {
        setEditingId(midia.id);
        setEditData({
            titulo: midia.titulo,
            ano_lancamento: midia.ano_lancamento,
            ator_principalId: midia.ator_principal?.id,
            ator_coadjuvanteId: midia.ator_coadjuvante?.id,
            generoId: midia.genero?.id,
            classificacaoEtariaId: midia.classificacaoEtaria?.id,
            classificacaoInternaId: midia.classificacaoInterna?.id,
            tipoId: midia.tipo?.id
        });
    };

    const handleEditCancel = () => setEditingId(null);

    const handleEditChange = (e) => {
        const { name, value } = e.target;
        setEditData(prevData => ({
            ...prevData,
            [name]: value
        }));
    }

    const handleEditSubmit = (e) => {
        e.preventDefault();
        const midia = {
            id: editingId,
            titulo: editData.titulo,
            ano_lancamento: editData.ano_lancamento,
            ator_principal: { id: editData.ator_principalId },
            ator_coadjuvante: { id: editData.ator_coadjuvanteId },
            genero: { id: editData.generoId },
            classificacaoEtaria: { id: editData.classificacaoEtariaId },
            classificacaoInterna: { id: editData.classificacaoInternaId },
            tipo: { id: editData.tipoId }
        };

        fetch(`/api/v1/midias/${editingId}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(midia)
        })
        .then(response => {
            if (!response.ok) throw new Error('Falha na atualização. Status: ' + resp.status);
            return response.json();
        })
        .then(data => {
            setMidias(midias.map(m => m.id === editingId ? data : m));
            setEditingId(null);
        })
        .catch(err => {
            alert('Erro ao atualizar mídia: ' + err.message);
            console.error(err);
        });

    }

    if (loading) {
        return <p>Carregando mídias da API Spring Boot...</p>;
    }
    return (
        <div>
            <h2>Lista de Mídias</h2>
            <button onClick={() => Navigate("/midias/novo")}>
                + Adicionar Nova Mídia
            </button>
            {midias.length === 0 ? (
                <p>Nenhuma mídia encontrada no banco de dados.</p>
            ) : (
                <table>
                    <thead>
                        <tr >
                            <th>ID</th>
                            <th>Título</th>
                            <th>Ano de Lançamento</th>
                            <th>Ator</th>
                            <th>Gênero</th>
                            <th>Classificação Etária</th>
                            <th>Classificação Interna</th>
                            <th>Tipo</th>
                        </tr>
                    </thead>
                    <tbody>
                        {midias.map(midia => (
                            <tr key={midia.id}>

                                {
                                    editingId === midia.id ? (
                                        <td>
                                            <form onSubmit={handleEditSubmit}>
                                                <input name="titulo" value={editData.titulo} onChange={handleEditChange} required placeholder="titulo" />
                                                <input name="ano_lancamento" value={editData.ano_lancamento} onChange={handleEditChange} required placeholder="ano_lancamento" />
                                                <select name="ator_principalId" value={editData.ator_principalId} onChange={handleEditChange} required>
                                                    <option value="">Selecione o Ator Principal</option>
                                                    {ator.map(ator => (
                                                        <option key={ator.id} value={ator.id}>{ator.nome}</option>
                                                    ))}
                                                </select>
                                                <select name="ator_coadjuvanteId" value={editData.ator_coadjuvanteId} onChange={handleEditChange} required>
                                                    <option value="">Selecione o Ator Coadjuvante</option>
                                                    {ator.map(ator => (
                                                        <option key={ator.id} value={ator.id}>{ator.nome}</option>
                                                    ))}
                                                </select>
                                                <select name="generoId" value={editData.generoId} onChange={handleEditChange} required>
                                                    <option value="">Selecione o Gênero</option>
                                                    {genero.map(genero => ( 
                                                        <option key={genero.id} value={genero.id}>{genero.descricao}</option>
                                                    ))}
                                                </select>
                                                <select name="classificacaoEtariaId" value={editData.classificacaoEtariaId} onChange={handleEditChange} required>
                                                    <option value="">Selecione a Classificação Etária</option>
                                                    {classificacaoEtaria.map(classificacaoEtaria => (
                                                        <option key={classificacaoEtaria.id} value={classificacaoEtaria.id}>{classificacaoEtaria.descricao}</option>
                                                    ))}
                                                </select>
                                                <select name="classificacaoInternaId" value={editData.classificacaoInternaId} onChange={handleEditChange} required>
                                                    <option value="">Selecione a Classificação Interna</option>
                                                    {classificacaoInterna.map(classificacaoInterna => (
                                                        <option key={classificacaoInterna.id} value={classificacaoInterna.id}>{classificacaoInterna.descricao}</option>
                                                    ))}
                                                </select>
                                                <select name="tipoId" value={editData.tipoId} onChange={handleEditChange} required>
                                                    <option value="">Selecione o Tipo</option>
                                                    {tipo.map(tipo => (
                                                        <option key={tipo.id} value={tipo.id}>{tipo.descricao}</option>
                                                    ))}
                                                </select>
                                                <button type="submit">Salvar</button>
                                                <button type="button" onClick={handleEditCancel}>Cancelar</button>
                                            </form>
                                        </td>
                                    ) : (
                                        <>
                                            <td>{midia.id}</td>
                                            <td>{midia.titulo}</td>
                                            <td>{midia.ano_lancamento}</td>
                                            <td>{midia.ator_principal.nome}</td>
                                            <td>{midia.ator_coadjuvante.nome}</td>
                                            <td>{midia.genero.descricao}</td>
                                            <td>{midia.classificacaoEtaria.descricao}</td>
                                            <td>{midia.classificacaoInterna.descricao}</td>
                                            <td>{midia.tipo.descricao}</td>
                                            <td>
                                            <button onClick={() => handleEditStart(midia)}>Editar</button>
                                            <button onClick={() => handleDelete(midia.id)} style={{ marginLeft: '6px' }}>Excluir</button>
                                            </td>
                                        </>
                                    )
                                }
                                
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
}

export default MidiaList;
