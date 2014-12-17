<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Tabela de Contatos</title>
</head>
<body>
<jsp:useBean id="dao" class="agenda.dao.ContatoDAO" />
  <div style="font-family:Consolas;">
	<table border="1" cellpadding="10">
       
       	<tr>
		    <th>id</th>
			<th>Nome</th>
			<th>e-mail</th>
			<th>Endereço</th>
			<th>Data Nascimento</th>
			<th colspan=2>Opções</th>
		</tr>
		
	<c:forEach var="contato" items="${dao.listarTodos()}" >
		<tr>
		    <td><c:out value="${contato.id}" /></td>
		    <td><c:out value="${contato.nome}" /></td>
		    <td><c:out value="${contato.email}" /></td>
		    <td><c:out value="${contato.endereco}" /></td>
		    <td><fmt:formatDate value="${contato.dataNascimento}" pattern="dd/MM/yyyy" /></td>
		    <td><a href="adicionaContato?action=editar&Id=${contato.id}">Atualizar</a></td>
            <td><a href="adicionaContato?action=deletar&Id=${contato.id}">Deletar</a></td>
		</tr>
	</c:forEach>	
	
	</table>
	<p><a href="adicionaContato?action=inserir">Add Contato</a></p>
  </div>	
</body>
</html>