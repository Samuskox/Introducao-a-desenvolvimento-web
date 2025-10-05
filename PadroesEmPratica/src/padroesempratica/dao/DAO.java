/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package padroesempratica.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import padroesempratica.jdbc.ConnectionFactory;

/**
 *
 * @author Samuel
 */
public abstract class DAO<Tipo> {
    
    private Connection conexao;
    public DAO() throws SQLException{
        conexao = ConnectionFactory.getConnection();
    }
    
    public Connection getConnection(){
        return conexao;
    }
    
    public void fecharConexao() throws SQLException{
        conexao.close();
    }
    
    public abstract void create(Tipo obj) throws SQLException;
    public abstract void atualizar(Tipo obj) throws SQLException;
    public abstract List<Tipo> listarTodos() throws SQLException;
    public abstract Tipo obterporId(Tipo obj) throws SQLException;
    
    
}
