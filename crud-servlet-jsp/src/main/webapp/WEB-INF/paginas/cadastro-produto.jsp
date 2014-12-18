<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<h1>Cadastro de produto</h1>
	<c:if test="${not empty mensagem}">
		<strong>${mensagem}</strong>
		<br />
		<br />
	</c:if>
	<form action="cadastro-produto" method="post">
	    <input name="id" value="${form.id}" hidden="true"/>
		<label>Nome:</label><input type="text" name="nome" size="20" value="${form.nome}" /><br />
		<label>Preço de custo:</label><input type="text" name="precoCusto" size="8" value="${form.precoCusto}" /><br /> 
		<label>Quantidade em estoque:</label><input type="text" name="quantidadeEstoque" size="8" value="${form.quantidadeEstoque}" /><br />
		<input type="submit" value="Cadastrar">
	</form>
	<c:import url="/WEB-INF/paginas/footer.jsp" />
</body>
</html>