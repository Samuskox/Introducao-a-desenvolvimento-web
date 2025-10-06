<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Alterar Genero</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="../../css/estilos.css"/>
  </head>

  <body>

    <div class="main-content">

    <h1>Alterar Gênero</h1>

    <form method="post" action="${cp}/processaGenero">

      <input name="acao" type="hidden" value="alterar"/>
      <input name="id" type="hidden" value="${requestScope.genero.id}"/>

      <table class="table">
        <tr>
          <td class="alinharDireita">Descrição:</td>
          <td>
            <input name="descricao"
                   type="text"
                   size="20"
                   maxlength="30"
                   required
                   value="${requestScope.genero.descricao}"/>
          </td>
        </tr>
        
        <tr>
          <td>
            <a class="link" href="${cp}/Formulários/genero/listagem.jsp">Voltar</a>
          </td>
          <td class="alinharDireita">
            <input type="submit" value="Alterar"/>
          </td>
        </tr>
      </table>

    </form>

    </div>

    <footer class="footer">
      “Calote é vida.” — Pitágoras
    </footer>

  </body>

</html>
