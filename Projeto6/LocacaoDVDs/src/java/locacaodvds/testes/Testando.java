/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaodvds.testes;

import locacaodvds.jdbc.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Samuel
 */
public class Testando {
    public static void main(String[] args) {
        Connection connection;
        
        try {
            connection = ConnectionFactory.getConnection();
            System.out.println("foi");
        } catch (SQLException e) {
            System.out.println("n foi D:");
            e.printStackTrace();
        }
    }
}
