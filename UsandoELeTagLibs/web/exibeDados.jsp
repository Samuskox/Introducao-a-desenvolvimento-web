<%-- 
    Document   : exibeDados
    Created on : 22 de set. de 2025, 16:14:52
    Author     : Samuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Produto obtido</title>
    </head>
    
    <style>
        .alinharDireita{
            text-align: right
        }
    </style>
    <body>
        <h1>Produto obtido</h1>
        <table>
            <tr>
                <th class="alinharDireita">Código: </th>
                <th>${requestScope.produtoObtido.codigo}</th>
            </tr>
            <tr>
                <th class="alinharDireita">Descrição:< </th>
                <th>${requestScope.produtoObtido.descricao}</th>
            </tr>
            <tr>
                <td class="alinharDireita">Unidade de Medida:</td>
                <td>${requestScope.produtoObtido.unidade}</td>
            </tr>
            <tr>
                <td class="alinharDireita">Quant. em Estoque:</td>
                <td>${requestScope.produtoObtido.quantidade}</td>
            </tr>
            
            <tr>
                <td colspan="2">
                    <a href="index.html">Voltar</a>
                </td>
            </tr>

        </table>
    </body>
</html>
