<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Nova Classificação Etária</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="../../css/estilos.css"/>
  </head>

  <body>

    <div class="main-content">

    <h1>Nova Classificação Etária</h1>

    <form method="post" action="${cp}/processaClassificacao">

      <input name="acao" type="hidden" value="inserir"/>

      <table class="table">
        <tr>
          <td class="alinharDireita">Descrição:</td>
          <td>
            <input name="descricao"
                   type="text"
                   size="20"
                   maxlength="30"
                   required/>
          </td>
        </tr>

        <tr>
          <td>
            <a class="link" href="${cp}/Formulários/classificacaoetaria/listagem.jsp">Voltar</a>
          </td>
          <td class="alinharDireita">
            <input type="submit" value="Salvar"/>
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
