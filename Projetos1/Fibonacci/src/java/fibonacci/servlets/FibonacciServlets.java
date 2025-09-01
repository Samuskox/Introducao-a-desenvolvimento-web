/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package fibonacci.servlets;

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

@WebServlet(name="Fibonacci", urlPatterns = {"/fibonacci40"})
public class FibonacciServlets extends HttpServlet{

    @Override
    protected void doGet(HttpServletRequest req,
            HttpServletResponse resp) throws ServletException, IOException {
            processRequest();
    }
    
    public void processRequest(){
        fibonacci();
    }
    
    public void fibonacci(){
        int primeiro = 0;
        int segundo = 1;
        
        for(int i = 0; i < 40;i++){
            System.out.println(primeiro + " ");
            int terceiroN = primeiro + segundo;
            primeiro = segundo;
            segundo = terceiroN;
        }
    }
    
}
