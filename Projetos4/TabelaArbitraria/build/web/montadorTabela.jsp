<%-- 
    Document   : montadorTabela
    Created on : 29 de set. de 2025, 17:03:38
    Author     : Samuel
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Montador de Tabela Ultra Profissional</title>
        
         <style>
        table, th, td {
            border: 1px solid black;
            border-collapse: collapse;
            padding: 8px;
            text-align: center;
        }
        </style>
    </head>
    <body>
        <c:choose>
                    <c:when test="${(param.linha gt 0) and (param.colunas gt 0)}">
                        <table>
                            <c:forEach begin="1" end="${param.linha}" var="i">
                                <tr>
                                <c:forEach begin="1" end="${param.colunas}" var="j">
                                    <td style="background-color: blueviolet">POR FAVOR NÃO SIGA O CAMINHO DO ROXO BRILHANTE</td> 
                                    <td style="background-color: red">ISTO NÃO É UM TESTE</td>
                                </c:forEach>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:when>
                    <c:otherwise>
                        <p style="color:red">O numero de linhas e colunas está inconsistente</p>
                    </c:otherwise>
                </c:choose>
    </body>
</html>
