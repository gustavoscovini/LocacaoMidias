<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>

<html>
  <head>
    
    <title>Nova Venda</title>
    
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0">
    
    <link rel="stylesheet"
          href="${cp}/css/estilos.css"/>
    
    <script src="${cp}/js/libs/jquery/jquery.min.js"></script>
    <script src="${cp}/js/libs/decimal.js/decimal.min.js"></script>
    <script src="${cp}/js/formularios/vendas/novo.js"></script>
    
  </head>

  <body>

    <h1>Nova Locação</h1>

    <form id="formNovaVenda" method="post" action="${cp}/processaLocacao">

      <input name="acao" type="hidden" value="inserir"/>
      <input id="hiddenItensLocacao" name="itensLocacao" type="hidden"/>

      <div id="divCliente">
        <jsp:useBean 
            id="servicosC" 
            scope="page" 
            class="locacaomidias.servicos.ClienteServices"/>

        Cliente:
        <br>
        <select id="selectCliente" name="idCliente" required>
          <c:forEach items="${servicosC.todos}" var="cliente">
            <option value="${cliente.id}">
              ${cliente.nome} ${cliente.sobrenome}
            </option>
          </c:forEach>
        </select>
      </div>
      
      <div id="divItensVenda">
        <table>
          <tr>
            <td>

              <jsp:useBean 
                  id="servicosP" 
                  scope="page" 
                  class="locacaomidias.servicos.MidiaServices"/>

              <p>
                Mídia:
                <br>
                <select id="selectProduto">
                  <c:forEach items="${servicosP.todos}" var="midia">
                    <option value="${midia.id}">   
                        ${midia.titulo}
                    </option>
                  </c:forEach>
                </select>
              </p>

              <p>
              

            </td>
            
        </table>
      </div>
            
              <button type="submit">Cadastrar<button/>
      <a href="${cp}/formularios/locacoes/listagem.jsp">
        Voltar
      </a>

    </form>

  </body>

</html>
