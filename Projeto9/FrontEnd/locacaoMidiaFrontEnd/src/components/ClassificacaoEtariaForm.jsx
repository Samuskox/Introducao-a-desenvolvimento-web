import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function ClassificacaoEtariaForm() {
    const [descricao, setDescricao] = useState('');
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        const nova = { descricao };
        fetch('/api/v1/classificacao-etaria', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(nova),
        })
        .then(resp => {
            if (!resp.ok) throw new Error('Falha ao criar. Status: ' + resp.status);
            return resp.json();
        })
        .then(() => {
            alert('Classificação etária criada com sucesso!');
            navigate('/classificacaoEtaria');
        })
        .catch(err => {
            alert('Erro: ' + err.message);
            console.error(err);
        });
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>Nova Classificação Etária</h2>
            <div>
                <label>Descrição:</label>
                <input type="text" value={descricao} onChange={(e) => setDescricao(e.target.value)} required />
            </div>
            <button type="submit">Salvar</button>
        </form>
    );
}

export default ClassificacaoEtariaForm;
