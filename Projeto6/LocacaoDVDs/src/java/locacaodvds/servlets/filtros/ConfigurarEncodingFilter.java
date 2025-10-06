/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package locacaodvds.servlets.filtros;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import java.io.IOException;

@WebFilter( filterName = "ConfigurarEncodingFilter", 
            urlPatterns = { "/*" } )
public class ConfigurarEncodingFilter implements Filter {
    
    @Override
    public void doFilter( 
            ServletRequest request,
            ServletResponse response,
            FilterChain chain )
            throws IOException, ServletException {
        
        request.setCharacterEncoding( "UTF-8" );
        chain.doFilter( request, response );
        
    }
    
}

