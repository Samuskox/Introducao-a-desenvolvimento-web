/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package padroesempratica.testes;

import java.sql.SQLException;
import padroesempratica.dao.PaisDAO;
import padroesempratica.entidades.Pais;

/**
 *
 * @author Samuel
 */
public class TestesPaisDAO {
    public static void main(String[] args) {
        Pais pais = new Pais();
        pais.setNome("Brasil");
        pais.setSigla("BR");
        
        PaisDAO dao = null;
        
        try{
            dao = new PaisDAO();
            dao.create(pais);
            
        } catch(SQLException exc){
            
            exc.printStackTrace();
            
        } finally {
            
            if (dao != null){
                try {
                    dao.fecharConexao();
                } catch (SQLException e) {
                    System.out.println("Erro ao fechar a conexao!");
                    e.printStackTrace();
                }
            }
            
        }
    }
}
