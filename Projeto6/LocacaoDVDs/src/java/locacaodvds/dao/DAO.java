/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaodvds.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import locacaodvds.jdbc.ConnectionFactory;
/**
 *
 * @author Samuel
 */
public abstract class DAO<Tipo> {
    private Connection connection;
    
    public DAO() throws SQLException{
        connection = ConnectionFactory.getConnection();
    }
    
    public Connection getConnection(){
        return connection;
    }
    
    public void closeConnection() throws SQLException{
        connection.close();
    }
    
    public abstract void salvar( Tipo obj ) throws SQLException;
    public abstract void atualizar( Tipo obj ) throws SQLException;
    public abstract void excluir( Tipo obj ) throws SQLException;
    public abstract List<Tipo> listarTodos() throws SQLException;
    public abstract Tipo obterPorId( int id ) throws SQLException;
}
