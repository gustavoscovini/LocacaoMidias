<%@page contentType="text/html" pageEncoding="UTF-8" %>
  <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="cp" value="${pageContext.request.contextPath}" />
    <!DOCTYPE html>

    <html>

    <head>
      <title>Sistema para Locação de Mídias</title>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <link rel="stylesheet" href="${cp}/css/estilos.css" />
    </head>

    <body class="index">
      <nav>
        <a href="${cp}/formularios/clientes/listagem.jsp">
          Cliente
        </a>

        <a href="${cp}/formularios/cidades/listagem.jsp">
          Cidade
        </a>

        <a href="${cp}/formularios/locacao/listagem.jsp">
          Locacao
        </a>

        <a href="${cp}/formularios/estados/listagem.jsp">
          Estado
        </a>

        <a href="${cp}/formularios/exemplar/listagem.jsp">
          Exemplar
        </a>

        <a href="${cp}/formularios/tipo/listagem.jsp">
          Tipo
        </a>

        <a href="${cp}/formularios/midias/listagem.jsp">
          Midia
        </a>

        <a href="${cp}/formularios/classificacaoInterna/listagem.jsp">
          Classificacao Interna
        </a>

        <a href="${cp}/formularios/ator/listagem.jsp">
          Ator/Atriz
        </a>

        <a href="${cp}/formularios/genero/listagem.jsp">
          Genero
        </a>

        <a href="${cp}/formularios/classificacaoEtaria/listagem.jsp">
          Classificacao Etaria
        </a>
      </nav>
      <h1>Sistema para Locação de Mídias</h1>
    </body>

    </html>