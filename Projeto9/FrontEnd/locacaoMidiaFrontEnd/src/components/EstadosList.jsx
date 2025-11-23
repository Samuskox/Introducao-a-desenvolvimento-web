import React, { useState, useEffect } from 'react';
import { useNavigate } from "react-router";

function EstadoList() {
    // Estado que guarda a listade estados
    const [estados, setEstados] = useState([]);
    const [loading, setLoading] = useState(true);
    //Estado para rastrear o ID que está sendo editado
    const [editingId, setEditingId] = useState(null);
    //Estado para armazenar os dados do formulário de edição
    const [editData, setEditData] = useState({ nome: '', sigla: '' });
    const Navigate = useNavigate();

    // useEffect roda o código uma vez, após a montagem do componente
    useEffect(() => {
        // O proxy configurado no vite.config.js redireciona para http://localhost:8080
        fetch('/api/v1/estados') 
            .then(response => {
                if (!response.ok) {
                    throw new Error(`Erro HTTP: ${response.status}`);
                }
                return response.json();
            })
            .then(data => {
                setEstados(data);
                setLoading(false);
            })
            .catch(error => {
                console.error("Erro ao buscar estados:", error);
                setLoading(false);
                // 💡 Se o seu DB estiver vazio, o Spring pode retornar [], que é OK.
            });
    }, []); 

    if (loading) {
        return <p>Carregando estados da API Spring Boot...</p>;
    }

    // Função para excluir um estado
    const handleDelete = (id) => {
    // Confirmação para evitar exclusão acidental
    if (!window.confirm(`Tem certeza que deseja excluir o Estado ID ${id}?`)) {
        return;
        }

        fetch(`/api/v1/estados/${id}`, {
        method: 'DELETE',
    })
    .then(response => {
        // O status 204 No Content ou 200 OK são esperados para DELETE bem-sucedido
        if (response.status === 204 || response.ok) { 
            // 💡 Atualiza a lista no frontend, filtrando o estado excluído
            setEstados(estados.filter(estado => estado.id !== id)); 
            console.log('Estado excluído com sucesso!');
        } else {
            // Se o backend enviar 404 ou 500, trata o erro
            throw new Error(`Falha na exclusão. Status: ${response.status}`);
        }
    })
    .catch(error => {
        alert('Erro ao excluir: ' + error.message);
        console.error(error);
    });
    }

    // Função para iniciar a edição de um estado
    const handleEditStart = (estado) => {
        setEditingId(estado.id);
        setEditData({ nome: estado.nome,
                    sigla: estado.sigla });
    };

    // Função para cancelar a edição
    const handleEditCancel = () => {
        setEditingId(null);
    };

    // Função para lidar com mudanças nos campos de edição
    const handleEditChange = (e) => {
        // Atualiza os dados de edição conforme o usuário digita
        const { name, value } = e.target;
        setEditData(prev => ({ ...prev, [name]: value }));
    };

    // Função para enviar o formulário de edição
    const handleEditSubmit = (e) => {
        e.preventDefault();
        
        // Dados a serem enviados no PUT
        const estadoAtualizado = { 
            id: editingId, 
            nome: editData.nome, 
            sigla: editData.sigla 
        };

        fetch(`/api/v1/estados/${editingId}`, { // 👈 URL com o ID
            method: 'PUT',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify(estadoAtualizado),
        })
        .then(response => {
            if (!response.ok) {
                throw new Error('Falha na atualização. Status: ' + response.status);
            }
            return response.json();
        })
        .then(data => {
            // 💡 Atualiza a lista no frontend com os novos dados recebidos (data)
            setEstados(estados.map(estado => 
                estado.id === editingId ? data : estado
            ));
            setEditingId(null); // Sai do modo de edição
            console.log('Estado atualizado com sucesso!');
        })
        .catch(error => {
            alert('Erro ao atualizar: ' + error.message);
            console.error(error);
        });
    }

    

    

// Renderização do componente
return (
    <div>
        <h2>Lista de Estados</h2>
        
        <button onClick={() =>  Navigate("/estados/novo")}>
            + Adicionar Novo Estado
        </button>

        {estados.length === 0 ? (
            <p>Nenhum estado encontrado no banco de dados.</p>
        ) : (
            <table style={{ width: '100%', borderCollapse: 'collapse', marginTop: '15px' }}>
                
                <thead>
                    <tr style={{ backgroundColor: '#5f5050ff' }}>
                        <th>ID</th>
                        <th>Nome</th>
                        <th>Sigla</th>
                        <th>Ações</th>
                    </tr>
                </thead>

                <tbody>
                    {/* 2. Mapeia o array de estados UMA VEZ para criar linhas (<tr>) */}
                    {estados.map(estado => (
                        <tr key={estado.id}>
                            

                            <td>{estado.id}</td>

                            {/* Colunas Nome e Sigla (Modo de Visualização ou Edição) */}
                            {editingId === estado.id ? (
                                
                                // --- MODO DE EDIÇÃO (Ocupa as 3 colunas de dados) ---
                                <td colSpan="3">
                                    <form onSubmit={handleEditSubmit} style={{ display: 'flex', gap: '10px' }}>
                                        <input 
                                            type="text" 
                                            name="nome" 
                                            value={editData.nome} 
                                            onChange={handleEditChange}
                                            required 
                                        />
                                        <input 
                                            type="text" 
                                            name="sigla" 
                                            value={editData.sigla} 
                                            onChange={handleEditChange} 
                                            style={{ width: '40px' }}
                                            required 
                                        />
                                        <button type="submit">Salvar</button>
                                        <button type="button" onClick={handleEditCancel}>Cancelar</button>
                                    </form>
                                </td>
                                
                            ) : (
                                
                                // --- MODO DE VISUALIZAÇÃO ---
                                <>
                                    <td>{estado.nome}</td>
                                    <td>{estado.sigla}</td>
                                    
                                    <td>
                                        <button onClick={() => handleEditStart(estado)}>
                                            Editar
                                        </button>
                                        <button onClick={() => handleDelete(estado.id)} style={{ marginLeft: '5px' }}>
                                            Excluir
                                        </button>
                                    </td>
                                </>
                            )}
                        </tr>
                    ))}
                </tbody>
            </table>
        )}
    </div>
)
}

export default EstadoList;