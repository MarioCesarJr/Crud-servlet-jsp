<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>

	<h1>Consulta de produtos</h1>

	<form action="consulta-produtos" method="get">
		Nome: <input type="text" name="nome" value="${param.nome}" /> <input
			type="submit" value="Consultar" />
	</form><br/>

	<c:if test="${empty produtos}">
		<strong>Nenhum produto encontrado.</strong>
	</c:if>

	<c:if test="${not empty produtos}">
		<table border="1">
			<tr>
				<th>Nome</th>
				<th>Preço</th>
				<th>Quantidade</th>
				<th colspan="2">Opções</th>
			</tr>
			<c:forEach items="${produtos}" var="produto">
				<tr>
					<td>${produto.nome}</td>
					<td>
						<fmt:setLocale value="pt_BR" /> 
						<fmt:formatNumber type="currency" value="${produto.precoCusto}" />
					</td>
					<td>${produto.quantidadeEstoque}</td>
					<td><a href="atualiza-produto?id=${produto.id}">Atualizar</a></td>
					<td><a href="remove-produto?id=${produto.id}">Deletar</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<c:import url="/WEB-INF/paginas/footer.jsp" />
</body>
</html>