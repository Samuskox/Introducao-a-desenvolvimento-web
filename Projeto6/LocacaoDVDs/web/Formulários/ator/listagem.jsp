<!-- <%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> -->
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<c:set var="prefixo" value="processaAtor?acao=preparar"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Atores Cadastrados</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="../../css/estilos.css"/>
  </head>

  <body>

    <div class="main-content">
    <h1>Atores Cadastrados</h1>

    <table class="table">
      <thead>
        <tr>
          <th>Id</th>
          <th>Nome</th>
          <th>Sobrenome</th>
          <th>Data de Estreia</th>
          <th>Alterar</th>
          <th>Excluir</th>
        </tr>
      </thead>
      <tbody>

        <jsp:useBean
            id="servicos"
            scope="page"
            class="locacaodvds.services.AtorService"/>

        <c:forEach items="${servicos.todos}" var="ator">
          <tr>
            <td>${ator.id}</td>
            <td>${ator.nome}</td>
            <td>${ator.sobrenome}</td>
            <td>${ator.dataEstreia}</td>
            <td>
              <a href="${cp}/${prefixo}Alteracao&id=${ator.id}">
                Alterar
              </a>
            </td>
            <td>
              <a href="${cp}/${prefixo}Exclusao&id=${ator.id}">
                Excluir
              </a>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>

    <p>
      <a class="link" href="${cp}/Formulários/ator/novo.jsp">
        Novo Ator
      </a>
    </p>

    </div>

    <div class="sidebar right">
            <a class="link" href="../../páginaInicial.html">Tela Principal</a>
        </div>

    <footer class="footer">
      “Calote é vida.” — Pitágoras
    </footer>

  </body>

</html>
