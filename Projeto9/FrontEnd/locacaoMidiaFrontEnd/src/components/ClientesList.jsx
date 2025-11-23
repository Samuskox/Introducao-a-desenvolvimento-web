import React, { useState, useEffect } from 'react';
import { useNavigate } from "react-router";


function ClientesList() {

    const [clientes, setClientes] = useState([]);
    const [loading, setLoading] = useState(true);
    const [cidades, setCidades] = useState([]);
    const [editingId, setEditingId] = useState(null);
    const [editData, setEditData] = useState({
        nome: '', sobrenome: '', data_nascimento: '', cpf: '', email: '',
        logradouro: '', numero: '', bairro: '', cep: '', cidadeId: ''
    });
    const Navigate = useNavigate();

    useEffect(() => {
        fetch('/api/v1/clientes') 
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Erro HTTP: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                setClientes(data);
                setLoading(false);
            })
            .catch(error => {
                console.error("Erro ao buscar clientes:", error);
                setLoading(false);
            });
    }, []);

    useEffect(() => {
        // fetch cidades to populate select when editing
        fetch('/api/v1/cidades')
            .then(resp => {
                if (!resp.ok) throw new Error('Erro ao carregar cidades');
                return resp.json();
            })
            .then(data => setCidades(data))
            .catch(err => console.error(err));
    }, []);

    if (loading) {
        return <p>Carregando clientes da API Spring Boot...</p>;
    }

    const handleDelete = (id) => {
        if (!window.confirm(`Tem certeza que deseja excluir o Cliente ID ${id}?`)) return;
        fetch(`/api/v1/clientes/${id}`, { method: 'DELETE' })
            .then(response => {
                if (response.status === 204 || response.ok) {
                    setClientes(clientes.filter(c => c.id !== id));
                } else {
                    throw new Error('Falha na exclusão. Status: ' + response.status);
                }
            })
            .catch(err => {
                alert('Erro ao excluir: ' + err.message);
                console.error(err);
            });
    };

    const handleEditStart = (cliente) => {
        setEditingId(cliente.id);
        setEditData({
            nome: cliente.nome,
            sobrenome: cliente.sobrenome,
            data_nascimento: cliente.data_nascimento,
            cpf: cliente.cpf,
            email: cliente.email,
            logradouro: cliente.logradouro,
            numero: cliente.numero,
            bairro: cliente.bairro,
            cep: cliente.cep,
            cidadeId: cliente.cidade?.id
        });
    };

    const handleEditCancel = () => setEditingId(null);

    const handleEditChange = (e) => {
        const { name, value } = e.target;
        setEditData(prev => ({ ...prev, [name]: value }));
    };

    const handleEditSubmit = (e) => {
        e.preventDefault();
        const cliente = {
            id: editingId,
            nome: editData.nome,
            sobrenome: editData.sobrenome,
            data_nascimento: editData.data_nascimento,
            cpf: editData.cpf,
            email: editData.email,
            logradouro: editData.logradouro,
            numero: editData.numero,
            bairro: editData.bairro,
            cep: editData.cep,
            cidade: { id: editData.cidadeId }
        };

        fetch(`/api/v1/clientes/${editingId}`, {
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(cliente)
        })
        .then(response => {
            if (!response.ok) throw new Error('Falha na atualização. Status: ' + resp.status);
            return response.json();
        })
        .then(data => {
            setClientes(clientes.map(c => c.id === editingId ? data : c));
            setEditingId(null);
        })
        .catch(err => {
            alert('Erro ao atualizar: ' + err.message);
            console.error(err);
        });
    };

    return (
        
        <div>
            <h2>Lista de Clientes</h2>
            <button onClick={() =>  Navigate("/clientes/novo")}>
            + Adicionar Novo Cliente
            </button>
            {clientes.length === 0 ? (
                <p>Nenhum cliente encontrado no banco de dados.</p>
            ) : (

                <table>
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Nome</th>
                            <th>Sobrenome</th>
                            <th>Data de Nascimento</th>
                            <th>CPF</th>
                            <th>Email</th>
                            <th>Logradouro</th>
                            <th>Número</th>
                            <th>Bairro</th>
                            <th>CEP</th>
                            <th>Cidade</th>
                            <th>Estado</th>
                            <th>Ações</th>
                        </tr>
                    </thead>
                    <tbody>
                        
                        {clientes.map(cliente => (
                            
                            <tr key={cliente.id}>
                                <tr>{cliente.id}</tr>
                                {editingId === cliente.id ? (
                                    <td colSpan="12">
                                        <form onSubmit={handleEditSubmit} style={{ display: 'flex', gap: '8px', alignItems: 'center', flexWrap: 'wrap' }}>
                                            <input name="nome" value={editData.nome} onChange={handleEditChange} required placeholder="Nome" />
                                            <input name="sobrenome" value={editData.sobrenome} onChange={handleEditChange} required placeholder="Sobrenome" />
                                            <input type="date" name="data_nascimento" value={editData.data_nascimento} onChange={handleEditChange} required />
                                            <input name="cpf" value={editData.cpf} onChange={handleEditChange} required placeholder="CPF" />
                                            <input type="email" name="email" value={editData.email} onChange={handleEditChange} required placeholder="Email" />
                                            <input name="logradouro" value={editData.logradouro} onChange={handleEditChange} placeholder="Logradouro" />
                                            <input name="numero" value={editData.numero} onChange={handleEditChange} placeholder="Número" />
                                            <input name="bairro" value={editData.bairro} onChange={handleEditChange} placeholder="Bairro" />
                                            <input name="cep" value={editData.cep} onChange={handleEditChange} placeholder="CEP" />
                                            <select name="cidadeId" value={editData.cidadeId} onChange={handleEditChange} required>
                                                <option value="">Selecione uma cidade</option>
                                                {cidades.map(c => (
                                                    <option key={c.id} value={c.id}>{c.nome} ({c.estado?.sigla ?? ''})</option>
                                                ))}
                                            </select>
                                            <button type="submit">Salvar</button>
                                            <button type="button" onClick={handleEditCancel}>Cancelar</button>
                                        </form>
                                    </td>
                                ) : (
                                    <>
                                        <td>{cliente.nome}</td>
                                        <td>{cliente.sobrenome}</td>
                                        <td>{cliente.data_nascimento}</td>
                                        <td>{cliente.cpf}</td>
                                        <td>{cliente.email}</td>
                                        <td>{cliente.logradouro}</td>
                                        <td>{cliente.numero}</td>
                                        <td>{cliente.bairro}</td>
                                        <td>{cliente.cep}</td>
                                        <td>{cliente.cidade?.nome ?? ''}</td>
                                        <td>{cliente.cidade?.estado?.sigla ?? ''}</td>
                                        <td>
                                            <button onClick={() => handleEditStart(cliente)}>Editar</button>
                                            <button onClick={() => handleDelete(cliente.id)} style={{ marginLeft: '6px' }}>Excluir</button>
                                        </td>
                                    </>
                                )}
                            </tr>
                        ))}
                    </tbody>
                </table>
            )}
        </div>
    );
}
export default ClientesList;



                                    

                                    