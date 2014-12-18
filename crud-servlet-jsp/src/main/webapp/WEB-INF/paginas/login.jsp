<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<h1>Login</h1>
	<c:if test="${not empty mensagem}">
		<strong>${mensagem}</strong>
		<br />
		<br />
	</c:if>
	<form action="login" method="post">
		Usuário: <input type="text" name="usuario" size="12" value="${param.usuario}" /><br/> 
		Senha: <input type="password" name="senha" size="12" style="margin-left: 9px; margin-top: 5px;"/><br/> 
		<input type="submit" value="Login" />
	</form>

</body>
</html>