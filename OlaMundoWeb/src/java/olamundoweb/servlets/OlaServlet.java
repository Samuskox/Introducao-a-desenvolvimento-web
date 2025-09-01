/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package olamundoweb.servlets;

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
@WebServlet(name = "OlaServlet", urlPatterns = {"/ola"})
public class OlaServlet extends HttpServlet{

    @Override
    protected void doGet(
            HttpServletRequest req,
            HttpServletResponse resp) 
            throws ServletException, IOException {
        System.out.println("olamundoweb.servlets.OlaServlet.doGet()");
    }
    
}
