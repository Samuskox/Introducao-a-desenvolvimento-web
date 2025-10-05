<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<c:set var="prefixo" value="TODO?acao=preparar"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Classificações Etárias Cadastrados</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="${cp}/css/estilos.css"/>
  </head>

  <body>

    <h1>Classificações Etárias Cadastrados</h1>

    <p>
      <a href="${cp}/TODO">
        Nova Classificação Etária
      </a>
    </p>

    <table class="tabelaListagem">
      <thead>
        <tr>
          <th>Id</th>
          <th>Descrição</th>
        </tr>
      </thead>
      <tbody>

        <jsp:useBean
            id="servicos"
            scope="page"
            class="TODO"/>

        <c:forEach items="${servicos.todos}" var="classificacao_etaria">
          <tr>
            <td>${classificacao_etaria.id}</td>
            <td>${classificacao_etaria.descricao}</td>
            <td>
              <a href="${cp}/${prefixo}Alteracao&id=${classificacao_etaria.id}">
                Alterar
              </a>
            </td>
            <td>
              <a href="${cp}/${prefixo}Exclusao&id=${classificacao_etaria.id}">
                Excluir
              </a>
            </td>
          </tr>
        </c:forEach>
      </tbody>
    </table>

    <p>
      <a href="${cp}/TODO">
        Nova Classificação Etária
      </a>
    </p>

    <p><a href="${cp}/TODO">Tela Principal</a></p>

  </body>

</html>
