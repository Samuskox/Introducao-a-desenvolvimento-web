import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function LocacaoList() {
    const [locacaoId, setLocacaoId] = useState([]);
    const [itemLocacao, setItemLocacao] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const fetchItemLocacao = () => {
        setLoading(true)
        setError(null)

        fetch('/api/v1/item-locacoes')
               .then(response => {
                   if (!response.ok) {
                       throw new Error(`Erro HTTP: ${response.status}`);
                   }
                   return response.json();
               })
               .then(data => {
                   setItemLocacao(data);
                   setLoading(false);
               })
               .catch(error => {
                   console.error("Erro ao buscar locacoes:", error);
                   setLoading(false);
               });
    }

   useEffect(()  => {
           fetchItemLocacao()
       }, []);

       const handleCancel = async (locacao) => {
        if (!window.confirm(`Tem certeza que deseja cancelar a Locação ID ${locacao.id}?`)) {
            return;
        }

        // Criar o objeto de atualização
        const locacaoCancelada = {
            id: locacao.id,
            data_inicio: locacao.data_inicio, 
            data_fim: locacao.data_fim, 
            cancelada: true,
            cliente: { id: locacao.cliente.id } 
        };
        console.log(locacaoCancelada)
        fetch(`/api/v1/locacoes/${locacao.id}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(locacaoCancelada),
            
        })
        .then(async response => {
            if (!response.ok) {
                const err = await response.json();
                throw new Error(err.message || 'Falha ao cancelar locação.');
            }
            alert(`Locação ${locacao.id} cancelada com sucesso!`);
            fetchItemLocacao()
        })
        .catch(err => {
            alert('Erro ao cancelar: ' + err.message);
            console.error("Erro no cancelamento:", err);
        });
    };

    if (loading) return <div>Carregando Locações...</div>;
    if (error) return <div>Erro ao carregar dados: {error}</div>;
    return(
        <div>
            <h2>Lista de Locações</h2>
            <button onClick={() => navigate('/locacao/novo')}>
                + Nova Locação
            </button>
            
            {itemLocacao.length === 0 ? (
                <p>Nenhuma locação registrada.</p>
            ) : (
                <table>
                    <thead>
                        <tr>
                            <th>Cliente</th>
                            <th>Período</th>
                            <th>Exemplar</th>
                            <th>Valor</th>
                            <th>Status</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        {itemLocacao.map(itemLocacao => {
                            const isCancelled = itemLocacao.locacao.cancelada;
                            return (
                                <tr 
                                    style={isCancelled ? tableStyles.cancelledRow : tableStyles.tr}
                                >
                                    <td>{itemLocacao.locacao.cliente?.nome ?? 'N/A'}</td>
                                    <td>{itemLocacao.locacao.data_inicio} até {itemLocacao.locacao.data_fim}</td>
                                    <td>{itemLocacao.exemplar.midia.titulo}</td>
                                    <td>{itemLocacao.valor}</td>
                                    <td style={{ fontWeight: 'bold', color: isCancelled ? 'red' : 'green' }}>
                                        {isCancelled ? 'CANCELADA' : 'ATIVA'}
                                    </td>
                                    <td>
                                        {!isCancelled && (
                                            <button 
                                                onClick={() => handleCancel(itemLocacao.locacao)} 
                                                style={{ background: 'red', color: 'white' }}
                                            >
                                                Cancelar
                                            </button>
                                        )}
                                    </td>
                                </tr>
                            );
                        })}
                    </tbody>
                </table>
            )}
        </div>
    )
}

const tableStyles = {
    table: { width: '100%', borderCollapse: 'collapse', marginTop: '15px' },
    th: { border: '1px solid #000000ff', padding: '10px', textAlign: 'left', backgroundColor: '#474747ff' },
    tr: { borderBottom: '1px solid #eee' },
    cancelledRow: { backgroundColor: '#c56b6bff', textDecoration: 'line-through' }
};


export default LocacaoList;