<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	Agora são :
	<%=new SimpleDateFormat("hh:mm:ss").format(new Date())%>

	<center>
		<a href="app/salvarContato.jsp">add Contato</a><br/> 
		<a href="app/listar_contatos.jsp">Listar Contato</a>
	</center>
</body>
</html>