import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function GeneroForm() {
    const [descricao, setDescricao] = useState('');
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();
        const payload = { descricao };
        fetch('/api/v1/generos', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(payload),
        })
        .then(resp => {
            if (!resp.ok) throw new Error('Falha ao criar. Status: ' + resp.status);
            return resp.json();
        })
        .then(() => {
            alert('Gênero criado com sucesso!');
            navigate('/genero');
        })
        .catch(err => {
            alert('Erro: ' + err.message);
            console.error(err);
        });
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>Novo Gênero</h2>
            <div>
                <label>Descrição:</label>
                <input type="text" value={descricao} onChange={(e) => setDescricao(e.target.value)} required />
            </div>
            <button type="submit">Salvar</button>
        </form>
    );
}

export default GeneroForm;
