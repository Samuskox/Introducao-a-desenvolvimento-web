<%-- 
    Document   : “testesJSTL
    Created on : 23 de set. de 2025, 17:54:06
    Author     : Samuel
--%>



<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>>Testes Tags JSTL</title>
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">
        <style>
            .linhaPar{
                background: aqua
            }
            .linhaImpar{
                background: azure
            }
        </style>
            
    </head>
    
    <body>
        
        <div>
            <table>
           
                <c:forEach begin="1" end="10" varStatus="i">
                    <c:choose>
                        <c:when test="${i.count % 2 == 0}">
                            <tr class="linhaPar">
                                <td>Linha ${i.count} JSTL é brabo</td>
                            </tr>
                            
                        </c:when>
                        <c:otherwise>
                            <tr class="linhaImpar">
                                <td>Linha ${i.count} JSTL é bom</td>
                            </tr>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </table>
        </div>
       
        
        
        
    </body>
</html>
