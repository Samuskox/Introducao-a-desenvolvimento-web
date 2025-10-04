/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import entidades.Produto;
import jakarta.servlet.RequestDispatcher;
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
@WebServlet(name = "ProcessaDadosProdutos", urlPatterns = {"/ProcessaDadosProdutos"})
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
        
        int codigo = 0;
        int quantidade = 0;
        
        String descricao = request.getParameter("descricao");
        String unidadeMedida = request.getParameter("unidade");
        
        try {
            codigo = Integer.parseInt(request.getParameter("descricao"));
        } catch (NumberFormatException exc) {
            System.out.println("Erro ao converter o codigo.");
        }
        
        try {
            quantidade = Integer.parseInt(request.getParameter("quantidade"));
        } catch (NumberFormatException exc) {
            System.out.println("Erro ao converter quantidade.");
        }
        
        Produto prod = new Produto();
        prod.setCodigo( codigo );
        prod.setDescricao(descricao);
        prod.setQuantidade(quantidade);
        prod.setUnidade(unidadeMedida);
        
        request.setAttribute( "produtoObtido", prod );

        RequestDispatcher disp = request.getRequestDispatcher("exibeDados.jsp");
        
        disp.forward( request, response );
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
