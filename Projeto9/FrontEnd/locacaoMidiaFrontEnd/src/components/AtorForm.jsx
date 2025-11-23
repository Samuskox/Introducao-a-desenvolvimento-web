import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function AtorForm() {
    const [nome, setNome] = useState('');
    const [sobrenome, setSobrenome] = useState('');
    const [dataEstreia, setDataEstreia] = useState('');
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault();

        const novoAtor = {
            nome,
            sobrenome,
            data_estreia: dataEstreia
        };

        fetch('/api/v1/atores', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(novoAtor),
        })
        .then(response => {
            if (!response.ok) throw new Error('Falha na criação do ator. Status: ' + response.status);
            return response.json();
        })
        .then(() => {
            alert('Ator criado com sucesso!');
            navigate('/ator');
        })
        .catch(err => {
            alert('Erro: ' + err.message);
            console.error(err);
        });
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>Novo Ator</h2>
            <div>
                <label>Nome:</label>
                <input type="text" value={nome} onChange={(e) => setNome(e.target.value)} required />
            </div>
            <div>
                <label>Sobrenome:</label>
                <input type="text" value={sobrenome} onChange={(e) => setSobrenome(e.target.value)} required />
            </div>
            <div>
                <label>Data de estreia:</label>
                <input type="date" value={dataEstreia} onChange={(e) => setDataEstreia(e.target.value)} />
            </div>
            <button type="submit">Salvar</button>
        </form>
    );
}

export default AtorForm;
