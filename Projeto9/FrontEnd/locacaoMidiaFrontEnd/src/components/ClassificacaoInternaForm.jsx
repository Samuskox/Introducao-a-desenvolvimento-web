import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function ClassificacaoInternaForm() {
    const [descricao, setDescricao] = useState('');
    const [valorAluguel, setValorAluguel] = useState('');
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        const payload = { descricao, valor_aluguel: Number(valorAluguel) };
        fetch('/api/v1/classificacao-interna', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload),
        })
        .then(resp => {
            if (!resp.ok) throw new Error('Falha ao criar. Status: ' + resp.status);
            return resp.json();
        })
        .then(() => {
            alert('Classificação interna criada com sucesso!');
            navigate('/classificacaoInterna');
        })
        .catch(err => {
            alert('Erro: ' + err.message);
            console.error(err);
        });
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>Nova Classificação Interna</h2>
            <div>
                <label>Descrição:</label>
                <input type="text" value={descricao} onChange={(e) => setDescricao(e.target.value)} required />
            </div>
            <div>
                <label>Valor Aluguel:</label>
                <input type="number" step="0.01" value={valorAluguel} onChange={(e) => setValorAluguel(e.target.value)} required />
            </div>
            <button type="submit">Salvar</button>
        </form>
    );
}

export default ClassificacaoInternaForm;
