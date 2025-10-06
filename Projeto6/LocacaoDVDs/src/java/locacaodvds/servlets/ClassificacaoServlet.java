/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package locacaodvds.servlets;

import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import locacaodvds.dao.ClassificacaoDAO;
import locacaodvds.entidades.ClassificacaoEtaria;



/**
 *
 * @author Samuel
 */
@WebServlet(name = "ClassificacaoServlet", urlPatterns = {"/processaClassificacao"})
public class ClassificacaoServlet extends HttpServlet {

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
        ClassificacaoDAO dao = null;
        RequestDispatcher disp = null;
        //ystem.out.println("cebol oiaaaa");
        
        try {

            dao = new ClassificacaoDAO();

            if ( acao.equals( "inserir" ) ) {

                String descricao = request.getParameter( "descricao" );
                ClassificacaoEtaria genero = new ClassificacaoEtaria();
                genero.setDescricao(descricao);

                dao.salvar( genero );

                disp = request.getRequestDispatcher(
                        "/Formulários/classificacaoetaria/listagem.jsp" );

            } else if ( acao.equals( "alterar" ) ) {
                //System.out.println("cebol oifdfadsgdfsgd");
                int id = Integer.parseInt(request.getParameter( "id" ));
                
                
                //System.out.println("esse é o id -> " + id);
               
                String descricao = request.getParameter( "descricao" );
                
                System.out.println("esse é descricao -> " + descricao);
                ClassificacaoEtaria classificacaoEtaria = new ClassificacaoEtaria();
                
                classificacaoEtaria.setId(id);
                classificacaoEtaria.setDescricao(descricao);
                //System.out.println("esse é o id -> " + id);
                //System.out.println("esse é descricao -> " + descricao);
                dao.atualizar( classificacaoEtaria );

                disp = request.getRequestDispatcher(
                        "/Formulários/classificacaoetaria/listagem.jsp" );

            } else if ( acao.equals( "excluir" ) ) {
                //System.out.println("daj ldanjasdlkdsa");
                int id = Integer.parseInt(request.getParameter( "id" ));
                //System.out.println("id -> "+ id);

                ClassificacaoEtaria classificacaoEtaria = new ClassificacaoEtaria();
                classificacaoEtaria.setId( id );
                //System.out.println("id ->" +id);

                dao.excluir( classificacaoEtaria );

                disp = request.getRequestDispatcher(
                        "/Formulários/classificacaoetaria/listagem.jsp" );

            } else {
                
                int id = Integer.parseInt(request.getParameter( "id" ));
                ClassificacaoEtaria classificacao = dao.obterPorId(id);
      
                request.setAttribute( "classificacaoEtaria", classificacao );
                        
                if ( acao.equals( "prepararAlteracao" ) ) {

                    disp = request.getRequestDispatcher( 
                            "/Formulários/classificacaoetaria/alterar.jsp" );
                } else if ( acao.equals( "prepararExclusao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/Formulários/classificacaoetaria/excluir.jsp" );
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
