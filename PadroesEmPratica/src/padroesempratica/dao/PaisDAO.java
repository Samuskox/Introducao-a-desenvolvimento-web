/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package padroesempratica.dao;
import java.sql.SQLException;
import java.util.List;
import padroesempratica.entidades.Pais;
import java.sql.PreparedStatement;

/**
 *
 * @author Samuel
 */
public class PaisDAO extends DAO<Pais> {
    
    public PaisDAO() throws SQLException{
        super();
    }

    @Override
    public void create(Pais obj) throws SQLException {
        String sql = """
                     INSERT INTO pais (nome, sigla)
                     VALUES( ?, ? );
                     """;
        
        PreparedStatement stmt = getConnection().prepareStatement( sql );
        stmt.setString( 1, obj.getNome() );
        stmt.setString( 2, obj.getSigla() );
        
        stmt.executeUpdate();
        stmt.close();
        
    }

    @Override
    public void atualizar(Pais obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Pais> listarTodos() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Pais obterporId(Pais obj) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
