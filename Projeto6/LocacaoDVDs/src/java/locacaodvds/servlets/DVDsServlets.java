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
import locacaodvds.dao.DVDsDAO;
import locacaodvds.entidades.Ator;
import locacaodvds.entidades.ClassificacaoEtaria;
import locacaodvds.entidades.DVDs;
import locacaodvds.entidades.Genero;

/**
 *
 * @author Samuel
 */
@WebServlet(name = "DVDsServlets", urlPatterns = {"/processaDVD"})
public class DVDsServlets extends HttpServlet {

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
        DVDsDAO dao = null;
        RequestDispatcher disp = null;

        try {

            dao = new DVDsDAO();

            if ( acao.equals( "inserir" ) ) {

                String titulo = request.getParameter( "titulo" );
                int anoLancamento = Integer.parseInt(request.getParameter("ano_lancamento"));
                int idAtorPrincipal = Integer.parseInt(request.getParameter("idAtorPrincipal"));
                int idAtorCoadjuvante = Integer.parseInt(request.getParameter("idAtorCoadjuvante"));
                String dataLancamento = request.getParameter("data_lancamento");
                int duracao = Integer.parseInt(request.getParameter("duracao_minutos"));
                int idClassificacao = Integer.parseInt(request.getParameter("idClassificacaoEtaria"));
                int idGenero = Integer.parseInt(request.getParameter("idGenero"));
                
                Ator atorPrincipal = new Ator();
                atorPrincipal.setId(idAtorPrincipal);
                
                Ator atorCoadjuvante = new Ator();
                atorCoadjuvante.setId(idAtorCoadjuvante);
                
                ClassificacaoEtaria classificacao = new ClassificacaoEtaria();
                classificacao.setId(idClassificacao);
                
                Genero genero = new Genero();
                genero.setId(idGenero);
                
                DVDs dvd = new DVDs();
                dvd.setTitulo(titulo);
                dvd.setAnoLancamento(anoLancamento);
                dvd.setAtorPrincipal(atorPrincipal);
                dvd.setAtorCoadjuvante(atorCoadjuvante);
                dvd.setDataLancamento(dataLancamento);
                dvd.setDuracaoMinutos(duracao);
                dvd.setClassificacaoEtaria(classificacao);
                dvd.setGenero(genero);

                dao.salvar( dvd );

                disp = request.getRequestDispatcher(
                        "/Formulários/dvd/listagem.jsp" );

            } else if ( acao.equals( "alterar" ) ) {

                int id = Integer.parseInt(request.getParameter( "id" ));
                System.out.println("id ->" + id);
                int idAtorPrincipal = Integer.parseInt(request.getParameter("idAtorPrincipal"));
                 System.out.println("id qdkj djadsmj->" + idAtorPrincipal);
                int idAtorCoadjuvante = Integer.parseInt(request.getParameter("idAtorCoadjuvante"));
                int idClassificacao = Integer.parseInt(request.getParameter("idClassificacaoEtaria"));
                int idGenero = Integer.parseInt(request.getParameter("idGenero"));
               
                String titulo = request.getParameter( "titulo" );
                int anoLancamento = Integer.parseInt(request.getParameter("ano_lancamento"));
                String dataLancamento = request.getParameter("data_lancamento");
                int duracao = Integer.parseInt(request.getParameter("duracao_minutos"));
                
                String nome = request.getParameter( "nome" );
                String sobrenome = request.getParameter("sobrenome");
                String dataEstreia = request.getParameter("data_estreia");


                Ator atorPrincipal = new Ator();
                atorPrincipal.setId(idAtorPrincipal);
                
                Ator atorCoadjuvante = new Ator();
                atorCoadjuvante.setId(idAtorCoadjuvante);
                
                ClassificacaoEtaria classificacao = new ClassificacaoEtaria();
                classificacao.setId(idClassificacao);
                
                Genero genero = new Genero();
                genero.setId(idGenero);
                
                DVDs dvd = new DVDs();
                dvd.setId(id);
                dvd.setTitulo(titulo);
                dvd.setAnoLancamento(anoLancamento);
                dvd.setAtorPrincipal(atorPrincipal);
                dvd.setAtorCoadjuvante(atorCoadjuvante);
                dvd.setDataLancamento(dataLancamento);
                dvd.setDuracaoMinutos(duracao);
                dvd.setClassificacaoEtaria(classificacao);
                dvd.setGenero(genero);
                //System.out.println("Olá bebe");
                dao.atualizar( dvd );
                //System.out.println("Olá ksklmsdaklmsad");
                disp = request.getRequestDispatcher(
                        "/Formulários/dvd/listagem.jsp" );

            } else if ( acao.equals( "excluir" ) ) {

                int id = Integer.parseInt(request.getParameter( "id" ));

                DVDs dvd = new DVDs();
                dvd.setId( id );

                dao.excluir( dvd );

                disp = request.getRequestDispatcher(
                        "/Formulários/dvd/listagem.jsp" );

            } else {

                int id = Integer.parseInt(request.getParameter( "id" ));
                DVDs dvd = dao.obterPorId( id );
                request.setAttribute( "dvd", dvd );

                //System.out.println("coiso aq  -> " + acao);

                if ( acao.equals( "prepararAlteracao" ) ) {

                    //System.out.println("oi cebola");

                    disp = request.getRequestDispatcher( 
                            "/Formulários/dvd/alterar.jsp" );
                } else if ( acao.equals( "prepararExclusao" ) ) {
                    disp = request.getRequestDispatcher( 
                            "/Formulários/dvd/excluir.jsp" );
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
