package Servlets;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

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
@WebServlet(name = "ProcessaDadosProdutos",urlPatterns = {"/ProcessaDadosProdutos"})
public class ProcessaDadosProdutos extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
    }

    
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        System.out.println("Dados do Produto");
        String codigoBarras = request.getParameter("codigoBarras");
        String medida = request.getParameter("medida");
        String qntEmbalagem = request.getParameter("qntEmbalagem");
        String nomeFabricante = request.getParameter("nomeFabricante");
        
        
        System.out.println("Código de Barras: " + codigoBarras);
        System.out.println("Medida: " + medida);
        System.out.println("Quantidade por Embalagem: " + qntEmbalagem);
        System.out.println("Nome do Fabricante: " + nomeFabricante);
    }
}
