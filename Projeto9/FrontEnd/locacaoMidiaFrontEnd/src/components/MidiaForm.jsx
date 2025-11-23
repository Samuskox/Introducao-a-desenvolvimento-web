import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function MidiaForm() {
    const [titulo, setTitulo] = useState('');
    const [anoLancamento, setAnoLancamento] = useState('');
    const [ator, setAtor] = useState([]);
    const [atorPrincipal, setAtorPrincipal] = useState([]);
    const [atorPrincipalId, setAtorPrincipalId] = useState('');
    const [atorCoadjuvante, setAtorCoadjuvante] = useState([]);
    const [atorCoadjuvanteId, setAtorCoadjuvanteId] = useState('');
    const [genero, setGenero] = useState([]);
    const [generoId, setGeneroId] = useState('');
    const [classificacaoEtaria, setClassificacaoEtaria] = useState([]);
    const [classificacaoEtariaId, setClassificacaoEtariaId] = useState('');
    const [classificacaoInterna, setClassificacaoInterna] = useState([]);
    const [classificacaoInternaId, setClassificacaoInternaId] = useState('');
    const [tipo, setTipo] = useState([]);
    const [tipoId, setTipoId] = useState('');
    const [codigoBarras, setCodigoBarras] = useState('');
    const [duracao, setDuracao] = useState('');
    const navigate = useNavigate();
    useEffect(() => {
        fetch('/api/v1/atores')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Falha ao buscar atores. Status: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                setAtor(data);
            })
            .catch(err => {
                console.error("Erro ao carregar atores:", err);
            });
    }, []);
    useEffect(() => {
        fetch('/api/v1/generos')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Falha ao buscar generos. Status: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                setGenero(data);
            })
            .catch(err => {
                console.error("Erro ao carregar generos:", err);
            });
    }, []);
    useEffect(() => {
        fetch('/api/v1/classificacao-etaria')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Falha ao buscar classificações etárias. Status: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                setClassificacaoEtaria(data);
            })
            .catch(err => {
                console.error("Erro ao carregar classificações etárias:", err);
            });
    }, []);
    useEffect(() => {
        fetch('/api/v1/classificacao-interna')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Falha ao buscar classificações internas. Status: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                setClassificacaoInterna(data);
            })
            .catch(err => {
                console.error("Erro ao carregar classificações internas:", err);
            });
    }, []);
    useEffect(() => {
        fetch('/api/v1/tipos')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Falha ao buscar tipos. Status: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                setTipo(data);
            })
            .catch(err => {
                console.error("Erro ao carregar tipos:", err);
            });
    }, []);


    const handleSubmit = (e) => {
        e.preventDefault(); 
        const novaMidia = {
            titulo,
            ano_lancamento: anoLancamento,
            codigo_barras: codigoBarras,
            duracao_minutos: duracao,
            ator_principal: { id: atorPrincipalId },
            ator_coadjuvante: { id: atorCoadjuvanteId },
            genero: { id: generoId },
            classificacaoEtaria: { id: classificacaoEtariaId },
            tipo: { id: tipoId },
            classificacaoInterna: { id: classificacaoInternaId }
        };
        fetch('/api/v1/midias', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(novaMidia),
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Falha na criação da mídia. Status: ' + response.status);
            }
            return response.json();
        })
        .then(() => {
            alert('Mídia criada com sucesso!');
            navigate('/midias'); 
        })
        .catch(error => {
            alert('Erro: ' + error.message);
            console.error(error);
        });
    };

    return (
        <div>
            <h2>Nova Mídia</h2>
            <form onSubmit={handleSubmit}>
                <div>
                    <label>Título:</label>
                    <input
                        type="text"
                        value={titulo}
                        onChange={(e) => setTitulo(e.target.value)}
                        required
                    />
                </div>
                <div>
                    <label>Ano de Lançamento:</label>
                    <input
                        type="number"
                        value={anoLancamento}
                        onChange={(e) => setAnoLancamento(e.target.value)}
                        required
                    />
                    </div>
                    <div>
                    <label>Código de Barras:</label>
                    <input
                        type="number"
                        value={codigoBarras}
                        onChange={(e) => setCodigoBarras(e.target.value)}
                        required
                    />
                    </div>   
                    <div>
                    <label>Duração (em minutos):</label>
                    <input
                        type="number"
                        value={duracao}
                        onChange={(e) => setDuracao(e.target.value)}
                        required
                    />
                    </div>                                                 
                <div>
                    <label>Atriz/Ator Principal:</label>
                    <select
                        value={atorPrincipalId}
                        onChange={(e) => setAtorPrincipalId(e.target.value)}
                        required
                    >
                        <option value="">Selecione um ator principal</option>
                        {ator.map(ator => (
                            <option key={ator.id} value={ator.id}>{ator.nome} {ator.sobrenome}</option>
                        ))}
                    </select>
                </div>
                <div>
                    <label>Atriz/Ator Coadjuvante:</label>
                    <select
                        value={atorCoadjuvanteId}
                        onChange={(e) => setAtorCoadjuvanteId(e.target.value)}    
                        required
                    >
                        <option value="">Selecione um ator</option>
                        {ator.map(ator => (
                            <option key={ator.id} value={ator.id}>{ator.nome} {ator.sobrenome}</option>
                        ))}
                    </select>
                </div>
                <div>
                    <label>Gênero:</label>
                    <select
                        value={generoId}
                        onChange={(e) => setGeneroId(e.target.value)}
                        required
                    >
                        <option value="">Selecione um gênero</option>   
                        {genero.map(genero => (
                            <option key={genero.id} value={genero.id}>{genero.descricao}</option>
                        ))}
                    </select>
                </div>
                <div>
                    <label>Classificação Etária:</label>
                    <select
                        value={classificacaoEtariaId}
                        onChange={(e) => setClassificacaoEtariaId(e.target.value)}
                        required
                    >
                        <option value="">Selecione uma classificação etária</option>
                        {classificacaoEtaria.map(classificacao => (
                            <option key={classificacao.id} value={classificacao.id}>{classificacao.descricao}</option>
                        ))}
                    </select>
                </div>
                <div>
                    <label>Classificação Interna:</label>
                    <select
                        value={classificacaoInternaId}
                        onChange={(e) => setClassificacaoInternaId(e.target.value)}
                        required
                    >
                        <option value="">Selecione uma classificação interna</option>
                        {classificacaoInterna.map(classificacao => (
                            <option key={classificacao.id} value={classificacao.id}>{classificacao.descricao}</option>
                        ))}
                    </select>
                </div>
                <div>
                    <label>Tipo:</label>
                    <select
                        value={tipoId}
                        onChange={(e) => setTipoId(e.target.value)}
                        required
                    >
                        <option value="">Selecione um tipo</option>
                        {tipo.map(tipo => (
                            <option key={tipo.id} value={tipo.id}>{tipo.descricao}</option>
                        ))}
                    </select>
                </div>
                <button type="submit">Salvar Mídia</button>
            </form>
        </div>
    );
}

export default MidiaForm;