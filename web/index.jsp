<%-- 
    Document   : index
    Created on : Dec 2, 2022, 11:59:38 AM
    Author     : gusta
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
    </head>
    <body>
        <table class="tabelaListagem">
      <thead>
        <tr>
          <th>Id</th>
          <th>Nome</th>
          <th>Estado</th>
          <th>Alterar</th>
          <th>Excluir</th>
        </tr>
      </thead>
      <tbody>

        <jsp:useBean
            id="servicos"
            scope="page"
            class="locacaomidias.servicos.ClienteServices"/>

        <c:forEach items="${servicos.todos}" var="cliente">
          <tr>
            <td>${cliente.id}</td>
            <td>${cliente.nome}</td>
            <td>${cliente.sobrenome}</td>
            <td>${cliente.email}</td>
            <td>${cliente.cpf}</td>
            <td>${cliente.logradouro}</td>
            <td>${cliente.numero}</td>
            <td>${cliente.bairro}</td>
            <td>${cliente.cep}</td>
            <td>${cliente.cidade.nome}</td>
          </tr>
        </c:forEach>
      </tbody>
    </table>
        
    <form>
        
        
        
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
        
    </form>
        
        
    </body>
</html>
