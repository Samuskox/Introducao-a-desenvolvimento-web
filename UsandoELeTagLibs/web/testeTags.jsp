<%-- 
    Document   : testeTags
    Created on : 23 de set. de 2025, 17:06:59
    Author     : Samuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Testes tags JSP</title>
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0">
    </head>
    <body>
            
            <jsp:useBean id="meuProduto"
                         class="entidades.Produto"
                         scope="page"/>
            <jsp:setProperty name="meuProduto"
                             property="codigo"
                             value="4"/>
            <jsp:setProperty name="meuProduto"
                             property="descricao"
                             value="Arroz"/>
            <jsp:setProperty name="meuProduto"
                             property="unidade"
                             value="kg"/>
            <jsp:setProperty name="meuProduto"
                             property="quantidade"
                             value="100"/>
            
            <h1>
                Produto Criado:
            </h1>
            ${pageScope.meuProduto.codigo}
            ${pageScope.meuProduto.descricao}
            ${pageScope.meuProduto.unidade}
            ${pageScope.meuProduto.quantidade}
              
            
    </body>
</html>
