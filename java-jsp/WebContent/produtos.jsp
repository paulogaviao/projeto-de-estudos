<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="myprefix" uri="WEB-INF/testetag.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">

<title>cadastro de Produtos</title>
<link rel="stylesheet" href="resource/css/cadastro.css">

</head>
<body>
<a href="acessoliberado.jsp"><img src="resource/img/inicioo.png"  width="50px" height="50px" title="inicio"></a>
	<a href="index.jsp"><img src="resource/img/sair.png"  width="50px" height="50px" title="sair"></a>
	<center>
		<h1>Cadastro de Produtos</h1>
		<h3>${msg }</h3>
	</center>
	<ul class="form-style-1">
		<li>
			<form action="salvarProduto" method="post" id="formproduto"onsubmit="return validarCampos() ? true : false">

				<table>
					<tr>
						<td>codigo do Produto</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${produto.id}" class="field-long"></td>
					</tr>
					<tr>
						<td>Nome</td>
						<td><input type="text" id="nome" name="nome"
							value="${produto.nome}"></td>
					</tr>
					<tr>
						<td>Quantidade</td>
						<td><input type="text" id="quantidade" name="quantidade"
							value="${produto.quantidade}"></td>
					</tr>
					<tr>
						<td>Valor R$</td>
						<td><input type="text" id="valor" name="valor"
							value="${produto.valor}"></td>
					</tr>
					
					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"> <input type="submit" value="Cancelar" onclick="document.getElementById('formprodutor').action='salvarProduto?acao=reset'"></td>
					</tr>
				</table>

			</form>
		</li>
	</ul>
	<center>
		<h1>Produtos Cadastrados</h1>
	</center>
	<div class="container">
		<table class="responsive-table">

 
			<tr>
				<th>CODIGO DO PRODUTO</th>
				<th>PRODUTO</th>
				<th>QUANTIDADE</th>
				<th>VALOR</th>
				<th>DELETAR</th>
				<th>EDITAR</th>
			</tr>
			
			<c:forEach items="${produtos}" var="produto">

				<tr>
					<td><c:out value="${produto.id}"></c:out></td>

					<td><c:out value="${produto.nome}"></c:out></td>

					<td><c:out value="${produto.quantidade}"></c:out></td>
					
					<td><c:out value="${produto.valor}"></c:out></td>
					
				

					<td><a href="salvarProduto?acao=delete&produto=${produto.id}"><img
							src="resource/img/delete.png" width="20px" height="20 px"
							title="Excluir"></a></td>
					<td><a href="salvarProduto?acao=editar&produto=${produto.id}"><img
							src="resource/img/editar.jpg" width="20px" height="20 px"
							title="Editar"></a></td>
				</tr>

			</c:forEach>
			<tr></tr>
		</table>
	</div>
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("nome").value == '') {
				alert('informe o Nome')
				return false;
			} else if (document.getElementById("quantidade").value == '') {
				alert('informe a Quantidade')
				return false;
			} else if (document.getElementById("valor").value == '') {
				alert('informe o Valor')
				return false;
			} 
			return true;
		}
	</script>
	
</body>
</html>