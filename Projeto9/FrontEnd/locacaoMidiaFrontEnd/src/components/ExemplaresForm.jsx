import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function ExemplaresForm() {
    const [disponivel, setDisponivel] = useState('');
    const [midia, setMidia] = useState([]);
    const [midiaId, setMidiaId] = useState('');
    const navigate = useNavigate();


useEffect(() => {
    fetch('/api/v1/midias')
        .then(response => {
            if (!response.ok) {
                throw new Error('Falha ao buscar midias. Status: ' + response.status);
            }
            return response.json();
        })
        .then(data => {
            setMidia(data);
        })
        .catch(err => {
            console.error("Erro ao carregar midias:", err);
        });
}, []);

const handleSubmit = (e) => {
    e.preventDefault();
    const exemplar = {
        disponivel,
        midia: { id: midiaId }
    };
    fetch('/api/v1/exemplares', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(exemplar),
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Falha na criação de um exemplar. Status: ' + response.status);
            }
            return response.json();
        })
        .then(() => {
            alert('Exemplar criado com sucesso!');
            navigate('/exemplares');
        })
        .catch(error => {
            alert('Erro: ' + error.message);
            console.error(error);
        });
};

return (
    <div>
        <h2>Novo Exemplar</h2>
        <form onSubmit={handleSubmit}>
            <div>
                <label>Disponível</label>
                <select
                    name="disponivel"
                    value={disponivel}
                    onChange={(e) => setDisponivel(e.target.value)}
                    required
                >
                    <option value="true">Sim</option>
                    <option value="false">Não</option>
                </select>
            </div>
            <div>
                <label>Mídia</label>
                <select type="number"
                    value={midiaId}     
                    onChange={(e) => setMidiaId(e.target.value)}
                    required           
                >
                    <option value="">Selecione uma mídia</option>
                        {midia.map(midia => (
                            <option key={midia.id} value={midia.id}>{midia.titulo}({midia.ano_lancamento})</option>
                        ))}
                </select>

            </div>
            <button type="submit">Salvar</button>
        </form>
    </div>
    )
}

export default ExemplaresForm