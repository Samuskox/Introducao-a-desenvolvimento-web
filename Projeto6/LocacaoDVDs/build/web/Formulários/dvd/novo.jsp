<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Novo DVD</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="${cp}/css/estilos.css"/>
  </head>

  <body>

    <h1>Novo DVD</h1>

    <form method="post" action="${cp}/processaDVD">

      <input name="acao" type="hidden" value="inserir"/>

      <table>
        <tr>
          <td class="alinharDireita">Título:</td>
          <td>
            <input name="titulo"
                   type="text"
                   size="20"
                   maxlength="30"
                   required/>
          </td>
        </tr>

        <tr>
          <td class="alinharDireita">Ano de Lançamento:</td>
          <td>
            <input name="ano_lancamento"
                   type="text"
                   size="20"
                   maxlength="30"
                   required/>
          </td>
        </tr>

        <tr>
          <td class="alinharDireita">Ator Principal:</td>
          <td>

            <jsp:useBean 
                id="servicos"
                scope="page"
                class="locacaodvds.services.AtorService"/>

            <select name="idAtorPrincipal" required>
              <c:forEach items="${servicos.todos}" var="atorP">
                <option value="${atorP.id}">
                  ${atorP.nome}
                </option>
              </c:forEach>
            </select>

          </td>
        </tr>

        <tr>
          <td class="alinharDireita">Ator Coadjuvante:</td>
          <td>

            <jsp:useBean 
                id="servicos1"
                scope="page"
                class="locacaodvds.services.AtorService"/>

            <select name="idAtorCoadjuvante" required>
              <c:forEach items="${servicos1.todos}" var="atorC">
                <option value="${atorC.id}">
                  ${atorC.nome}
                </option>
              </c:forEach>
            </select>

          </td>
        </tr>

        <tr>
          <td class="alinharDireita">Data de Lançamento:</td>
          <td>
            <input name="data_lancamento"
                   type="date"
                   size="20"
                   maxlength="30"
                   required/>
          </td>
        </tr>

        <tr>
          <td class="alinharDireita">Duração em Minutos:</td>
          <td>
            <input name="duracao_minutos"
                   type="text"
                   size="20"
                   maxlength="30"
                   required/>
          </td>
        </tr>

        <tr>
          <td class="alinharDireita">Classificação Etária:</td>
          <td>

            <jsp:useBean 
                id="servicos2"
                scope="page"
                class="locacaodvds.services.ClassificacaoService"/>

            <select name="idClassificacaoEtaria" required>
              <c:forEach items="${servicos2.todos}" var="classificacao_etaria">
                <option value="${classificacao_etaria.id}">
                  ${classificacao_etaria.descricao}
                </option>
              </c:forEach>
            </select>

          </td>
        </tr>

        <tr>
          <td class="alinharDireita">Gênero:</td>
          <td>

            <jsp:useBean 
                id="servicos3"
                scope="page"
                class="locacaodvds.services.GeneroService"/>

            <select name="idGenero" required>
              <c:forEach items="${servicos3.todos}" var="genero">
                <option value="${genero.id}">
                  ${genero.descricao}
                </option>
              </c:forEach>
            </select>

          </td>
        </tr>

        <tr>
          <td>
            <a href="${cp}/Formulários/dvd/listagem.jsp">Voltar</a>
          </td>
          <td class="alinharDireita">
            <input type="submit" value="Salvar"/>
          </td>
        </tr>
      </table>

    </form>

  </body>

</html>
