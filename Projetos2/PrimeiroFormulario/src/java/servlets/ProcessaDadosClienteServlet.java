/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import entidades.Cadastro;
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
 * @author samue
 */
@WebServlet(name = "ProcessaDadosClienteServlet", urlPatterns = {"/processaDadosCliente"})
public class ProcessaDadosClienteServlet extends HttpServlet {

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
        String nome = request.getParameter("nome");
        String sobrenome = request.getParameter("sobrenome");
        String cpf = request.getParameter("cpf");
        String dataNasc = request.getParameter("data");
        String sexo = request.getParameter("sexo");
        String logradouro = request.getParameter("logradouro");
        int numero = Integer.parseInt(request.getParameter("numero"));
        String complemento = request.getParameter("complemento");
        String cidade = request.getParameter("cidade");
        String cep = request.getParameter("cep");
        String filhos = request.getParameter("filhos");
        String obs = request.getParameter("obs");
        
        Cadastro cadastro = new Cadastro();
        
        cadastro.setNome(nome);
        cadastro.setSobrenome(sobrenome);
        cadastro.setCPF(cpf);
        cadastro.setData(dataNasc);
        cadastro.setSexo(sexo);
        cadastro.setLogradouro(logradouro);
        cadastro.setNumero(numero);
        cadastro.setComplemento(complemento);
        cadastro.setCidade(cidade);
        cadastro.setCEP(cep);
        boolean temOuN = filhos.equalsIgnoreCase("S");
        cadastro.setFilhos(temOuN);
        cadastro.setObs(obs);
        
        request.setAttribute("CadastroObtido", cadastro);
        
        RequestDispatcher disp = request.getRequestDispatcher("formulario.jsp");
        disp.forward(request, response);
        
        
        
        System.out.println("Dados do Cliente:");
        System.out.println("Nome: " + nome);
        System.out.println("Sobrenome: " + sobrenome);
        System.out.println("CPF: " + cpf);
        System.out.println("Data: " + dataNasc);
        if(sexo.equals("M")){
            System.out.println("Sexo Masculino");
        } else {
            System.out.println("Sexo Feminino");
        }
        System.out.println("Logradouro: " + logradouro);
        System.out.println("Numero: " + numero);
        System.out.println("Complemento: " + complemento);
        System.out.println("Cidade: " + cidade);
        System.out.println("CEP: " + cep);
        if(filhos.equals("S")){
            System.out.println("Tem filhos");
        } else {
            System.out.println("Não Filhos");
        }
        System.out.println("Observações: " + obs);
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
