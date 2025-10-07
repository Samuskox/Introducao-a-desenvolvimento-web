<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
  <head>
    <title>Página de Erro - Excluir ator</title>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"
          href="${cp}/css/estilos.css"/>
  </head>

  <body>
    <div class="main-content">
    <h1>Deu um erro ao excluir o ator</h1>
    <h2>${erro}</h2>
    <a class="link" href="${cp}/Formulários/ator/listagem.jsp">Voltar</a>
    </div>

  </body>

</html>
