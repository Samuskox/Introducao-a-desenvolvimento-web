/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaodvds.dao;

import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import locacaodvds.entidades.Ator;
/**
 *
 * @author Samuel
 */
public class AtorDAO extends DAO<Ator>{
    
    public AtorDAO() throws SQLException{}

    @Override
    public void salvar(Ator obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                INSERT INTO  
                ator( nome, sobrenome, data_estreia )  
                VALUES( ?, ?, ? );
                """ );
        
        stmt.setString( 1, obj.getNome() );
        stmt.setString( 2, obj.getSobrenome() );
        stmt.setString(3, obj.getDataEstreia().toString());

        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public void atualizar(Ator obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                UPDATE ator  
                SET 
                    nome = ?, 
                    sobrenome = ?,
                    data_estreia = ?
                WHERE 
                    id = ?;
                """ );
        
        stmt.setString( 1, obj.getNome() );
        stmt.setString( 2, obj.getSobrenome() );
        stmt.setString(3, obj.getDataEstreia().toString());
        stmt.setInt( 3, obj.getId() );

        stmt.executeUpdate();
        stmt.close();
        
    }

    @Override
    public void excluir(Ator obj) throws SQLException {
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                DELETE FROM cidade  
                WHERE 
                    id = ?;
                """ );
        
        stmt.setInt( 3, obj.getId() );

        stmt.executeUpdate();
        stmt.close();
    }

    @Override
    public List<Ator> listarTodos() throws SQLException {
        List<Ator> lista = new ArrayList<>();
        PreparedStatement stmt = getConnection().prepareStatement(
                """
                SELECT 
                    * 
                FROM
                    locacao_dvds.ator;
                """ );
        
        ResultSet resultSet = stmt.executeQuery();
        
        while ( resultSet.next() ) {


            Ator ator = new Ator;

            ator.set
            c.setId( rs.getInt( "idCidade" ) );
            c.setNome( rs.getString( "nomeCidade" ) );
            c.setEstado( e );

            e.setId( rs.getInt( "idEstado" ) );
            e.setNome( rs.getString( "nomeEstado" ) );
            e.setSigla( rs.getString( "siglaEstado" ) );

            lista.add( c );

        }
    }

    @Override
    public Ator obterPorId(int id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
