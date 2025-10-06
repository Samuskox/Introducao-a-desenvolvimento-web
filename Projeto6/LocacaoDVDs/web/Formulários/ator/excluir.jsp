<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Excluir Ator</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="../../css/estilos.css"/>
  </head>

  <body>

    <div class="main-content">

    <h1>Excluir Ator</h1>

    <form method="post" action="${cp}/processaAtor">

      <input name="acao" type="hidden" value="excluir"/>
      <input name="id" type="hidden" value="${requestScope.ator.id}"/>

      <table class="table">
        <tr>
          <td class="alinharDireita">Nome:</td>
          <td>${requestScope.ator.nome}</td>
        </tr>
        
        <tr>
          <td class="alinharDireita">Sobrenome:</td>
          <td>${requestScope.ator.sobrenome}</td>
        </tr>

        <tr>
          <td class="alinharDireita">Data de Estreia:</td>
          <td>${requestScope.ator.dataEstreia}</td>
        </tr>

        <tr>
          <td>
            <a class="link" href="${cp}/Formulários/ator/listagem.jsp">Voltar</a>
          </td>
          <td class="alinharDireita">
            <input type="submit" value="Excluir"/>
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
