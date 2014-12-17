<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<link rel="stylesheet" href="http://code.jquery.com/ui/1.9.0/themes/base/jquery-ui.css" />
<script src="http://code.jquery.com/jquery-1.8.2.js"></script>
<script src="http://code.jquery.com/ui/1.9.0/jquery-ui.js" /></script>

<script>
$(function() {
    $( "#calendario" ).datepicker({dateFormat: 'dd/mm/yy',changeMonth: true,changeYear: true});
});
</script>

<title>Salvar Contato</title>
</head>
<body>
     
     <c:if test="${not empty mensagem}">
		<strong>${mensagem}</strong>
		<br />
		<br />
	</c:if>
     
   	<form action="adicionaContato" method="post" id="frm">
	
	    <label>Id:</label>
	    <input type="text" readonly="readonly" name="idContato" value="${contato.id}" /> <br />
	   
		<label>Nome:</label>
		<input type="text" name="nome" value="${contato.nome}" /><br/>
		
		<label>e-mail:</label>
		<input type="text" name="email" value="${contato.email}" /><br/>
		
		<label>Endereço:</label>
		<input type="text" name="endereco" value="${contato.endereco}" /><br/>
		
		<label>Data Nascimento:</label>
		<input type="text" id="calendario" name="dataNascimento" 
		value="<fmt:formatDate pattern="MM/dd/yyyy"  value="${contato.dataNascimento}" />" /><br/>
	
	    <input type="submit" value="Gravar" />
	</form>
	
	<p><a href="listar_contatos.jsp">Listar Contato</a></p>
</body>
</html>