// Em src/components/LocacaoForm.jsx
import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function LocacaoForm() {
    const [dataInicio, setDataInicio] = useState('');
    const [dataFim, setDataFim] = useState('');

    const [clientes, setClientes] = useState([]);
    const [clienteId, setClienteId] = useState('');

    const [exemplar, setExemplar] = useState([]);
    const [exemplarId, setExemplarId] = useState('');

    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);

    const [locacao, setLocacao] = useState([]);
    const [locacaoId, setLocacaoId] = useState('');

    const [itemLocacao, setItemLocacao] = useState([]);
    const [itemLocacaoId, setItemLocacaoId] = useState('');

    const [valor, setValor] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        fetch('/api/v1/clientes')
            .then(response => {
                if (!response.ok) throw new Error('Falha ao buscar clientes. Status: ' + response.status);
                return response.json();
            })
            .then(data => {
                setClientes(data);
                setLoading(false);
            })
            .catch(err => {
                setError(err.message);
                setLoading(false);
                console.error("Erro ao carregar exemplares:", err);
            });
    }, []);

    useEffect(() => {
        fetch('/api/v1/exemplares?disponivel=true')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Falha ao buscar exemplares. Status: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                setExemplar(data);
            })
            .catch(err => {
                console.error("Erro ao carregar exemplares:", err);
            });
    }, []);


    const handleSubmit = async (e) => {
        e.preventDefault();

        const novaLocacao = {
            data_inicio: dataInicio,
            data_fim: dataFim,
            cancelada: false,
            cliente: { id: clienteId }
        };

        let locacaoId = null;

        try{
            const response = await fetch('/api/v1/locacoes', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(novaLocacao),
        })

        if(!response.ok) throw new Error('Falha ao criar Locação principal.')

        const locacaoCriada = await response.json();

        locacaoId = locacaoCriada.id;
        } catch(error){
            alert(`Erro 1: Locação principal não registrada. ${error.message}`);
            return;
        }

        //const exemplarCodigoInterno = exemplarId;
        //se precisar eu descomento
        const itemLocacao = {
            id: {
            locacaoId: locacaoId,
            exemplarCodigoInterno: exemplarId
        },
        valor: Number(valor)
        }

        try{
            const itemResponse = await fetch('/api/v1/item-locacoes', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(itemLocacao),
        })
        if(!itemResponse.ok) throw new Error(`Erro 2: ItemLocacao falhou. Locação ID ${locacaoId} criada, mas não conectada.`);

        }catch (error) {
        alert(error.message);
        console.error("Erro Passo 2:", error);
        return;
    }

    try{
        const exemplarResponse = await fetch(`/api/v1/exemplares/${exemplarId}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            // Envia apenas o campo que muda (disponivel: false)
            body: JSON.stringify({ disponivel: false })
        })

        if(!exemplarResponse.ok) console.warn('Alerta: Exemplar não foi marcado como indisponível.');

    } catch (error) {
        console.error('Erro Passo 3: Falha ao atualizar exemplar:', error);
    }

    alert('Locação registrada com sucesso!');
    navigate('/locacao');
            
    };

    if (loading) return <div>Carregando dados...</div>;
    if (error) return <div>Erro ao carregar dados: {error}</div>;

    return (
        <form onSubmit={handleSubmit}>
            <h2>Registrar Nova Locação</h2>

            {/* Campo Dropdown (SELECT) para Cliente */}
            <div>
                <label>Cliente:</label>
                <select
                    value={clienteId}
                    onChange={(e) => setClienteId(Number(e.target.value))}
                    required
                >
                    <option value="">Selecione um cliente</option>
                    {clientes.map(cliente => (
                        <option key={cliente.id} value={cliente.id}>
                            {cliente.nome} {cliente.sobrenome} (CPF: {cliente.cpf})
                        </option>
                    ))}
                </select>
            </div>

            {/* Campo Data de Início */}
            <div>
                <label>Data de Início:</label>
                <input
                    type="date"
                    value={dataInicio}
                    onChange={(e) => setDataInicio(e.target.value)}
                    required
                />
            </div>

            <div>
                <label>Data de Fim (Previsão):</label>
                <input
                    type="date"
                    value={dataFim}
                    onChange={(e) => setDataFim(e.target.value)}
                    required
                />
            </div>

            <div>
                <label>Exemplar:</label>
                <select
                    value={exemplarId}
                    onChange={(e) => setExemplarId(Number(e.target.value))}
                    required
                >
                    <option value="">Selecione um exemplar disponível</option>
                    {exemplar.map(exemplar => (
                        <option key={exemplar.codigo_interno} value={exemplar.codigo_interno}>
                        {exemplar.codigo_interno} - {exemplar.midia.titulo} ({exemplar.midia.duracao_minutos} min)
                        </option>
                    ))}
                </select>
            </div>

            <div>
                <label>Valor da locacao:</label>
                <input type="number" value={valor} onChange={(e) => setValor(e.target.value)} required />
            </div>
                    
            <button type="submit">Registrar Locação</button>
        </form>
    );
}

export default LocacaoForm;