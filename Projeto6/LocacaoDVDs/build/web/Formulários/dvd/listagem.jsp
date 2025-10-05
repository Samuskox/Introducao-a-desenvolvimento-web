<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<c:set var="prefixo" value="TODO?acao=preparar"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Cidades Cadastradas</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="${cp}/css/estilos.css"/>
  </head>

  <body>

    <h1>Cidades Cadastradas</h1>

    <p>
      <a href="${cp}/TODO">
        Nova Cidade
      </a>
    </p>

    <table class="tabelaListagem">
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
            class="TODO"/>

        <c:forEach items="${servicos.todos}" var="dvd">
          <tr>
            <td>${dvd.id}</td>
            <td>${dvd.titulo}</td>
            <td>${dvd.ano_lancamento}</td>
            <td>${dvd.ator_principal_id}</td>
            <td>${dvd.ator_coadjuvante_id}</td>
            <td>${dvd.data_lancamento}</td>
            <td>${dvd.duracao_minutos}</td>
            <td>${dvd.classificacao_etaria_id}</td>
            <td>${dvd.genero_id}</td>
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

    <p>
      <a href="${cp}/TODO">
        Novo DVD
      </a>
    </p>

    <p><a href="${cp}/TODO">Tela Principal</a></p>

  </body>

</html>
