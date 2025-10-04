/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


/**
 *
 * @author Samuel
 */
@WebServlet(name = "EhPrimo", urlPatterns = {"/EhPrimo"})
public class EhPrimo extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
        
        String number = request.getParameter("number");
        int n = Integer.parseInt(number);
        Boolean primo = primo(n);
        
        if(primo == true){
            System.out.println(n + " é primo.");
        } else {
            System.out.println(n + " não é primo.");
        }
    
        
        
    }
    
    
    public Boolean primo(int number){
        if(number == 1){
            return false;
        }
        for(int i = 2; i< Math.sqrt(number);i++){
          if(number % i == 2){
              return false;
            }    
        }
        
        return true;
    }
}
