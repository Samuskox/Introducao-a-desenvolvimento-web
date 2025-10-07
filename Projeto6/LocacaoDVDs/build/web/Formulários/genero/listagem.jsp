<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<c:set var="prefixo" value="processaGenero?acao=preparar"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Gêneros Cadastrados</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="${cp}/css/estilos.css"/>
  </head>

  <body>

    <div class="main-content">

    <h1>Gêneros Cadastrados</h1>

    <table class="table">
      <thead>
        <tr>
          <th>Id</th>
          <th>Descrição</th>
          <th>Alterar</th>
          <th>Excluir</th>
        </tr>
      </thead>
      <tbody>

        <jsp:useBean
            id="servicos"
            scope="page"
            class="locacaodvds.services.GeneroService"/>

        <c:forEach items="${servicos.todos}" var="genero">
          <tr>
            <td>${genero.id}</td>
            <td>${genero.descricao}</td>
            <td>
              <a href="${cp}/${prefixo}Alteracao&id=${genero.id}">
                Alterar
              </a>
            </td>
            <td>
              <a href="${cp}/${prefixo}Exclusao&id=${genero.id}">
                Excluir
              </a>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>

    <p>
      <a class="link" href="${cp}/Formulários/genero/novo.jsp">
        Novo Gênero
      </a>
    </p>

    <div class="sidebar right">
      <a href="${cp}/páginaInicial.html" class="link">Tela Principal</a>
    </div>

    </div>
  
    <footer class="footer">
      “Calote é vida.” — Pitágoras
    </footer>

  </body>

</html>
