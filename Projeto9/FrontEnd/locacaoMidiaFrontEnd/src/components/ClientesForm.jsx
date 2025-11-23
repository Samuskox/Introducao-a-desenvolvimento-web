import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function ClientesForm() {
    const [nome, setNome] = useState('');
    const [sobrenome, setSobrenome] = useState('');
    const [dataNascimento, setDataNascimento] = useState('');
    const [cpf, setCpf] = useState('');
    const [email, setEmail] = useState('');
    const [logradouro, setLogradouro] = useState('');
    const [numero, setNumero] = useState('');
    const [bairro, setBairro] = useState('');
    const [cep, setCep] = useState('');
    const [cidadeId, setCidadeId] = useState('');
    const [cidades, setCidades] = useState([]);
    const navigate = useNavigate();

    useEffect(() => {
        fetch('/api/v1/cidades')
            .then(response => {
                if (!response.ok) {
                    throw new Error('Falha ao buscar cidades. Status: ' + response.status);
                }
                return response.json();
            })
            .then(data => {
                setCidades(data);
            })
            .catch(err => {
                console.error("Erro ao carregar cidades:", err);
            });
    }, []);

    const handleSubmit = (e) => {
        e.preventDefault(); 
        const novoCliente = {
            nome,
            sobrenome,
            data_nascimento: dataNascimento,
            cpf,
            email,
            logradouro,
            numero,
            bairro,
            cep,
            cidade: { id: cidadeId }
        };
        fetch('/api/v1/clientes', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(novoCliente),
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Falha na criação do cliente. Status: ' + response.status);
            }
            return response.json();
        })
        .then(() => {
            alert('Cliente criado com sucesso!');
            navigate('/clientes'); 
        })
        .catch(error => {
            alert('Erro: ' + error.message);
            console.error(error);
        });
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>Novo Cliente</h2>
            <div>
                <label>Nome:</label>
                <input type="text" value={nome} onChange={(e) => setNome(e.target.value)} required />
            </div>
            <div>
                <label>Sobrenome:</label>
                <input type="text" value={sobrenome} onChange={(e) => setSobrenome(e.target.value)} required />
            </div>
            <div>
                <label>Data de Nascimento:</label>
                <input type="date" value={dataNascimento} onChange={(e) => setDataNascimento(e.target.value)} required />
            </div>
            <div>
                <label>CPF:</label>
                <input type="text" value={cpf} onChange={(e) => setCpf(e.target.value)} required />
            </div>
            <div>
                <label>Email:</label>
                <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
            </div>
            <div>
                <label>Logradouro:</label>
                <input type="text" value={logradouro} onChange={(e) => setLogradouro(e.target.value)} required />
            </div>
            <div>
                <label>Número:</label>
                <input type="text" value={numero} onChange={(e) => setNumero(e.target.value)} required />
            </div>
            <div>
                <label>Bairro:</label>
                <input type="text" value={bairro} onChange={(e) => setBairro(e.target.value)} required />
            </div>
            <div>
                <label>CEP:</label>
                <input type="text" value={cep} onChange={(e) => setCep(e.target.value)} required />
            </div>
            <div>
                <label>Cidade:</label>
                <select value={cidadeId} onChange={(e) => setCidadeId(e.target.value)} required>
                    <option value="">Selecione uma cidade</option>
                    {cidades.map(cidade => (
                        <option key={cidade.id} value={cidade.id}>
                            {cidade.nome} ({cidade.estado.sigla})
                        </option>
                    ))}
                </select>
            </div>
            <button type="submit">Salvar Cliente</button>
        </form>
    );
}

export default ClientesForm;