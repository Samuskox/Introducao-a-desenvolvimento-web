/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package padroesempratica.testes;

import java.sql.Connection;
import java.sql.SQLException;
import padroesempratica.jdbc.ConnectionFactory;

/**
 *
 * @author Samuel
 */
public class TesteConnectionFactory {
    public static void main(String[] args) {
        
        try{
            Connection connection = ConnectionFactory.getConnection();
            System.out.println("Conexão criada com sucesso!!");
        } catch(SQLException esc){
            System.err.println("Erro ao tentar criar uma conexão");
        }
    }
}  
