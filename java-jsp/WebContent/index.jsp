<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="myprefix" uri="WEB-INF/testetag.tld"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="mystyle.css">
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="resource/css/estilo.css">
</head>
<body>
	<div class="login-page">
		<div class="form">
			<form action="loginUsuario" method="post" class="login-form">
				<h3>usuario</h3>
				<input type="text" id="login" name="login"
					placeholder="login usuario">
				<h3>senha</h3>
				<input type="password" id="senha" name="senha"
					placeholder="senha usuario"> <br /> <button type="submit"
					value="conectar">Logar</button>
			</form>
		</div>
	</div>
</body>
</html>