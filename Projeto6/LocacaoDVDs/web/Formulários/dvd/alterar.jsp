<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Alterar DVD</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="${cp}/css/estilos.css"/>
  </head>

  <body>

    <h1>Alterar DVD</h1>

    <form method="post" action="${cp}/processaDVD">

      <input name="acao" type="hidden" value="alterar"/>
      <input name="id" type="hidden" value="${requestScope.dvd.id}"/>

      <table>
        <tr>
          <td class="alinharDireita">Título: </td>
          <td>
            <input name="titulo"
                   type="text"
                   size="20"
                   maxlength="30"
                   required
                   value="${requestScope.dvd.titulo}"/>
          </td>
        </tr>

        <tr>
          <td class="alinharDireita">Ano de Lançamento: </td>
          <td>
            <input name="ano_lancamento"
                   type="text"
                   size="20"
                   maxlength="30"
                   required
                   value="${requestScope.dvd.anoLancamento}"/>
          </td>
        </tr>

        <tr>
          <td class="alinharDireita">Ator Principal:</td>
          <td>

            <jsp:useBean 
                id="servicos1"
                scope="page"
                class="locacaodvds.services.AtorService"/>

            <select name="idAtorPrincipal" required>
              <c:forEach items="${servicos1.todos}" var="ator">
                <c:choose>
                  <c:when test="${requestScope.dvd.atorPrincipal.id eq ator.id}">
                    <option value="${atorPrincipal.id}" selected>
                      ${ator.nome}
                    </option>
                  </c:when>
                  <c:otherwise>
                    <option value="${ator.id}">
                      ${ator.nome}
                    </option>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
            </select>

          </td>
        </tr>

        <tr>
          <td class="alinharDireita">Ator Coadjuvante:</td>
          <td>

            <jsp:useBean 
                id="servicos2"
                scope="page"
                class="locacaodvds.services.AtorService"/>

            <select name="idAtorCoadjuvante" required>
              <c:forEach items="${servicos2.todos}" var="ator">
                <c:choose>
                  <c:when test="${requestScope.dvd.atorCoadjuvante.id eq ator.id}">
                    <option value="${ator.id}" selected>
                      ${ator.nome}
                    </option>
                  </c:when>
                  <c:otherwise>
                    <option value="${ator.id}">
                      ${ator.nome}
                    </option>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
            </select>

          </td>
        </tr>

        <tr>
          <td class="alinharDireita">Data de Lançamento: </td>
          <td>
            <input name="data_lancamento"
                   type="text"
                   size="20"
                   maxlength="30"
                   required
                   value="${requestScope.dvd.dataLancamento}"/>
          </td>
        </tr>

        <tr>
          <td class="alinharDireita">Duração em Minutos: </td>
          <td>
            <input name="duracao_minutos"
                   type="text"
                   size="20"
                   maxlength="30"
                   required
                   value="${requestScope.dvd.duracaoMinutos}"/>
          </td>
        </tr>

        <tr>
          <td class="alinharDireita">Classificação Etária:</td>
          <td>

            <jsp:useBean 
                id="servicos3"
                scope="page"
                class="locacaodvds.services.ClassificacaoService"/>

            <select name="idClassificacaoEtaria" required>
              <c:forEach items="${servicos3.todos}" var="classificacao_etaria">
                <c:choose>
                  <c:when test="${requestScope.dvd.classificacaoEtaria.id eq classificacao_etaria.id}">
                    <option value="${classificacao_etaria.id}" selected>
                      ${classificacao_etaria.descricao}
                    </option>
                  </c:when>
                  <c:otherwise>
                    <option value="${classificacao_etaria.id}">
                      ${classificacao_etaria.descricao}
                    </option>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
            </select>

          </td>
        </tr>

        <tr>
          <td class="alinharDireita">Gênero:</td>
          <td>

            <jsp:useBean 
                id="servicos4"
                scope="page"
                class="locacaodvds.services.GeneroService"/>

            <select name="idGenero" required>
              <c:forEach items="${servicos4.todos}" var="genero">
                <c:choose>
                  <c:when test="${requestScope.dvd.genero.id eq genero.id}">
                    <option value="${genero.id}" selected>
                      ${genero.descricao}
                    </option>
                  </c:when>
                  <c:otherwise>
                    <option value="${genero.id}">
                      ${genero.descricao}
                    </option>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
            </select>

          </td>
        </tr>

        <tr>
          <td>
            <a href="${cp}/Formulários/dvd/listagem.jsp">Voltar</a>
          </td>
          <td class="alinharDireita">
            <input type="submit" value="Alterar"/>
          </td>
        </tr>
      </table>

    </form>

  </body>

</html>
