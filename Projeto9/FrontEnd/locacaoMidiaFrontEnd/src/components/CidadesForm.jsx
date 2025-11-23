import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function CidadeForm() {
    const [nome, setNome] = useState('');
    const [estados, setEstados] = useState([]); // Para armazenar a lista de estados
    const [estadoSelecionadoId, setEstadoSelecionadoId] = useState(''); // Armazena o ID selecionado
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    // 1. EFEITO PARA BUSCAR TODOS OS ESTADOS AO CARREGAR O COMPONENTE
    useEffect(() => {
        fetch('/api/v1/estados')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Falha ao buscar estados. Status: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                setEstados(data);
                // Define o primeiro estado como padrão, se a lista não estiver vazia
                if (data.length > 0) {
                    setEstadoSelecionadoId(data[0].id);
                }
                setLoading(false);
            })
            .catch(err => {
                setError(err.message);
                setLoading(false);
                console.error("Erro ao carregar estados:", err);
            });
    }, []); // Array de dependência vazio: executa apenas uma vez

    // 2. FUNÇÃO PARA ENVIAR O FORMULÁRIO
    const handleSubmit = (e) => {
        e.preventDefault();

        if (!estadoSelecionadoId) {
            alert('Por favor, selecione um estado.');
            return;
        }

        const novaCidade = {
            nome: nome,
            // A API do Spring espera que o objeto relacionado seja enviado como um ID aninhado
            estado: { id: estadoSelecionadoId } 
        };

        fetch('/api/v1/cidades', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(novaCidade),
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Falha na criação da cidade. Status: ' + response.status);
            }
            return response.json();
        })
        .then(() => {
            alert('Cidade criada com sucesso!');
            navigate('/cidades'); // Supondo que você terá uma rota /cidades
        })
        .catch(err => {
            alert('Erro: ' + err.message);
            console.error(err);
        });
    };

    if (loading) return <div>Carregando estados...</div>;
    if (error) return <div>Erro ao carregar dados: {error}</div>;

    return (
        <form onSubmit={handleSubmit}>
            <h2>Nova Cidade</h2>
            
            {/* Campo Nome */}
            <div>
                <label>Nome da Cidade:</label>
                <input 
                    type="text" 
                    value={nome} 
                    onChange={(e) => setNome(e.target.value)} 
                    required 
                />
            </div>

            {/* Campo Dropdown (SELECT) */}
            <div>
                <label>Estado:</label>
                <select 
                    value={estadoSelecionadoId} 
                    onChange={(e) => setEstadoSelecionadoId(Number(e.target.value))} // Converte para Number
                    required
                >
                    {/* Renderiza as opções com base na lista de estados */}
                    {estados.map(estado => (
                        <option key={estado.id} value={estado.id}>
                            {estado.nome} ({estado.sigla})
                        </option>
                    ))}
                </select>
            </div>
            
            <button type="submit">Salvar Cidade</button>
        </form>
    );
}

export default CidadeForm;