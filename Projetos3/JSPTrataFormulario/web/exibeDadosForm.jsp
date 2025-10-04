<%-- 
    Document   : exibeDadosForm
    Created on : 29 de set. de 2025, 16:42:45
    Author     : Samuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <style>
            .right{
                text-align: right;
            }
        </style>
    </head>
    <body>
        <h1 class="right">Dados do aluno:</h1>
        <table class="right">
            <tr>
                <th>Nome</th>
                <th>${param.nome}</th>
            </tr>
            <tr>
                <td>Idade</td>
                <td>${param.idade}</td>
            </tr>
        </table>
        
    </body>
</html>
