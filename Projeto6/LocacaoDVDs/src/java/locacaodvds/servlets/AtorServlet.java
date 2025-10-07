/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package locacaodvds.servlets;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import locacaodvds.dao.AtorDAO;
import locacaodvds.entidades.Ator;

/**
 *
 * @author Samuel
 */
@WebServlet(name = "AtorServlet", urlPatterns = {"/processaAtor"})
public class AtorServlet extends HttpServlet {

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
       

        String acao = request.getParameter( "acao" );
        AtorDAO dao = null;
        RequestDispatcher disp = null;

        try {

            dao = new AtorDAO();

            if ( acao.equals( "inserir" ) ) {

                String nome = request.getParameter( "nome" );
                String sobrenome = request.getParameter("sobrenome");
                String dataEstreia = request.getParameter("data_estreia");

                Ator ator = new Ator();
                ator.setNome(nome);
                ator.setSobrenome(sobrenome);
                ator.setDataEstreia(dataEstreia);

                dao.salvar( ator );

                disp = request.getRequestDispatcher(
                        "/Formulários/ator/listagem.jsp" );

            } else if ( acao.equals( "alterar" ) ) {

                int id = Integer.parseInt(request.getParameter( "id" ));

                String nome = request.getParameter( "nome" );
                String sobrenome = request.getParameter("sobrenome");
                String dataEstreia = request.getParameter("data_estreia");


                Ator ator = new Ator();
                ator.setId(id);
                ator.setNome(nome);
                ator.setSobrenome(sobrenome);
                ator.setDataEstreia(dataEstreia);

                dao.atualizar( ator );

                disp = request.getRequestDispatcher(
                        "/Formulários/ator/listagem.jsp" );

            } else if ( acao.equals( "excluir" ) ) {

                int id = Integer.parseInt(request.getParameter( "id" ));

                Ator ator = new Ator();
                ator.setId( id );
                try {
                    dao.excluir( ator );
                    disp = request.getRequestDispatcher(
                        "/Formulários/ator/listagem.jsp" );
                } catch (Exception e) {
                    request.setAttribute("erro", e.getMessage());
                    disp = request.getRequestDispatcher("/Formulários/ator/erro.jsp");
                    disp.forward(request, response);
                }

            } else {

                int id = Integer.parseInt(request.getParameter( "id" ));
                Ator ator = dao.obterPorId( id );
                request.setAttribute( "ator", ator );

                //System.out.println("coiso aq  -> " + acao);

                if ( acao.equals( "prepararAlteracao" ) ) {

                    //System.out.println("oi cebola");

                    disp = request.getRequestDispatcher( 
                            "/Formulários/ator/alterar.jsp" );
                } else if ( acao.equals( "prepararExclusao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/Formulários/ator/excluir.jsp" );
                }

            }

        } catch ( SQLException exc ) {
            exc.printStackTrace();
        } finally {
            if ( dao != null ) {
                try {
                    dao.closeConnection();
                } catch ( SQLException exc ) {
                    exc.printStackTrace();
                }
            }
        }

        if ( disp != null ) {
            disp.forward( request, response );
        }
        
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
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
