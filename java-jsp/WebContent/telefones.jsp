<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="myprefix" uri="WEB-INF/testetag.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>cadastro de Telefones</title>
<link rel="stylesheet" href="resource/css/cadastro.css">
</head>
<body>
	<a href="acessoliberado.jsp"><img src="resource/img/inicioo.png"
		width="50px" height="50px" title="inicio"></a>
	<a href="index.jsp"><img src="resource/img/sair.png" width="50px"
		height="50px" title="sair"></a>
	<center>
		<h1>Cadastro de Telefones</h1>
		<h3 color="red">${msg }</h3>
	</center>
	<ul class="form-style-1">
		<li>
			<form action="salvarTelefones" method="post" id="formuser"
				onsubmit="return validarCampos() ? true : false">

				<table>
					<tr>
						<td>User</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${userEscolhido.id}" class="field-long"></td>

						<td><input type="text" readonly="readonly" id="nome"
							name="nome" value="${userEscolhido.nome}" class="field-long"></td>
					</tr>
					<tr>
						<td>Número</td>
						<td><input type="text" id="numero" name="numero"></td>
					</tr>
					<tr>
						<td>Tipo</td>
						<td><select name="tipo" id="tipo">
								<option>Casa</option>
								<option>Contato</option>
								<option>Celular</option>
						</select></td>
					</tr>

					<tr>
						<td></td>

						<td><input type="submit" value="Salvar"></td>
					</tr>
				</table>

			</form>
		</li>
	</ul>
	<center>
		<h1>Telefones Cadastrados</h1>
	</center>
	<div class="container">
		<table class="responsive-table">


			<tr>
				<th>ID</th>
				<th>NUMERO</th>
				<th>TIPO</th>
				<th>EXCLUIR</th>

			</tr>

			<c:forEach items="${telefones}" var="fone">

				<tr>
					<td><c:out value="${fone.id}"></c:out></td>

					<td><c:out value="${fone.numero}"></c:out></td>

					<td><c:out value="${fone.tipo}"></c:out></td>


					<td><a href="salvarTelefones?acao=deletefone&foneId=${fone.id}"><img
							src="resource/img/delete.png" width="20px" height="20 px"
							title="Excluir"></a></td>


				</tr>

			</c:forEach>
			<tr></tr>
		</table>
	</div>
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("numero").value == '') {
				alert('informe o numero')
				return false;
			}else if (document.getElementById("tipo").value == '') {
				alert('informe o tipo')
				return false;
			}
			return true;
		}
	</script>

</body>
</html>