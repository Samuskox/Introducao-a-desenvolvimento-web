/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package processRequest;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *
 * @author samue
 */

@WebServlet(name="RequestContador", urlPatterns = {"/contar30"})
public class Servlets extends HttpServlet {
    private void processRequest(){
        for(int i = 1; i < 31;i++){
            System.out.println("Contador:" +  i);
                    
        }
    }
    
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException{
        processRequest();
    }
}
