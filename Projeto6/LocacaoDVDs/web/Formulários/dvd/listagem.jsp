<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<c:set var="prefixo" value="processaDVD?acao=preparar"/>
<!DOCTYPE html>

<html>
  <head>
    <title>DVDs Cadastrados</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="../../css/estilos.css"/>
  </head>

  <body>

    <div class="main-content">

    <h1>Cidades Cadastradas</h1>


    <div class="table-wrapper">
    <table class="table">
      <thead>
        <tr>
          <th>Id</th>
          <th>Título</th>
          <th>Ano de Lançamento</th>
          <th>Ator Principal</th>
          <th>Ator Coadjuvante</th>
          <th>Data de Lançamento</th>
          <th>Duração em Minutos</th>
          <th>Classificação Etária</th>
          <th>Gênero</th>
          <th>Alterar</th>
          <th>Excluir</th>
        </tr>
      </thead>
      <tbody>

        <jsp:useBean
            id="servicos"
            scope="page"
            class="locacaodvds.services.DVDsService"/>

        <c:forEach items="${servicos.todos}" var="dvd">
          <tr>
            <td>${dvd.id}</td>
            <td>${dvd.titulo}</td>
            <td>${dvd.anoLancamento}</td>
            <td>${dvd.atorPrincipal.nome}</td>
            <td>${dvd.atorCoadjuvante.nome}</td>
            <td>${dvd.dataLancamento}</td>
            <td>${dvd.duracaoMinutos}</td>
            <td>${dvd.classificacaoEtaria.descricao}</td>
            <td>${dvd.genero.descricao}</td>
            <td>
              <a href="${cp}/${prefixo}Alteracao&id=${dvd.id}">
                Alterar
              </a>
            </td>
            <td>
              <a href="${cp}/${prefixo}Exclusao&id=${dvd.id}">
                Excluir
              </a>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
    </div>

    </div>

    <p>
      <a class="link" href="${cp}/Formulários/dvd/novo.jsp">
        Novo DVD
      </a>
    </p>

    <div class="sidebar right">
      <a href="../../páginaInicial.html" class="link">Tela Principal</a>
    </div>

    <footer class="footer">
    “Calote é vida.” — Pitágoras
  </footer>

  </body>

</html>
