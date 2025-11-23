/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.locacaomidia.locacaoMidia;

import com.locacaomidia.jdbc.ConnectionFactory;
import java.sql.SQLException;

/**
 *
 * @author Samuel
 */
public class ConnectionFactoryTest {
    
    public static void main(String[] args) {
        ConnectionFactory connection = new ConnectionFactory();
        
        try {
            connection.getConnection();
            System.out.println("CONEXÃO FEITA COM SUCESSO");
        } catch (SQLException e) {
            System.out.println("CONEXÃO FALHA");
            e.printStackTrace();
        }
    }
}
