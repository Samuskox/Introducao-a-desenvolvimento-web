import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';

function EstadoForm() {
    const [nome, setNome] = useState('');
    const [sigla, setSigla] = useState('');
    const navigate = useNavigate();

    const handleSubmit = (e) => {
        e.preventDefault(); // Impede o recarregamento padrão da página

        const novoEstado = { nome, sigla };

        fetch('/api/v1/estados', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' }, // 👈 CRÍTICO para POST
            body: JSON.stringify(novoEstado),
        })
        .then(response => {
            if (!response.ok) {
                // Se o Spring falhar (ex: erro de unicidade), lança um erro
                throw new Error('Falha na criação do estado. Status: ' + response.status);
            }
            return response.json(); // Retorna o objeto Estado criado
        })
        .then(() => {
            alert('Estado criado com sucesso!');
            navigate('/estados'); // Redireciona de volta para a lista
        })
        .catch(error => {
            alert('Erro: ' + error.message);
            console.error(error);
        });
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>Novo Estado</h2>
            <div>
                <label>Nome:</label>
                <input type="text" value={nome} onChange={(e) => setNome(e.target.value)} required />
            </div>
            <div>
                <label>Sigla:</label>
                <input type="text" value={sigla} onChange={(e) => setSigla(e.target.value)} maxLength="2" required />
            </div>
            <button type="submit">Salvar</button>
        </form>
    );
}

export default EstadoForm;