<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="myprefix" uri="WEB-INF/testetag.tld"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>cadastro de usuário</title>
<link rel="stylesheet" href="resource/css/cadastro.css">
<!-- Adicionando JQuery -->
<script src="https://code.jquery.com/jquery-3.4.1.min.js"
	integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
	crossorigin="anonymous"></script>


</head>
<body>
	<a href="acessoliberado.jsp"><img src="resource/img/inicioo.png"
		width="50px" height="50px" title="inicio"></a>
	<a href="index.jsp"><img src="resource/img/sair.png" width="50px"
		height="50px" title="sair"></a>
	<center>
		<h1>Cadastro de usuário</h1>
		<h3 color="red">${msg }</h3>
	</center>
	<ul class="form-style-1">
		<li>
			<form action="salvarUsuario" method="post" id="formuser"
				onsubmit="return validarCampos() ? true : false"
				enctype="multipart/form-data">

				<table>
					<tr>
						<td>codigo</td>
						<td><input type="text" readonly="readonly" id="id" name="id"
							value="${user.id}" class="field-long" placeholder="Automático"></td>

						<td>Cep</td>
						<td><input type="text" id="cep" name="cep"
							onblur="consultaCep()" value="${user.cep}"
							placeholder="Digite o Cep"></td>
					</tr>

					<tr>
						<td>login</td>
						<td><input type="text" id="login" name="login"
							value="${user.login}" placeholder="Digite o usuario"></td>

						<td>Rua</td>
						<td><input type="text" id="rua" name="rua"
							value="${user.rua}" placeholder="Digite a rua"></td>
					</tr>

					<tr>
						<td>senha</td>
						<td><input type="password" id="senha" name="senha"
							value="${user.senha}" placeholder="Escolha uma senha"></td>

						<td>Bairro</td>
						<td><input type="text" id="bairro" name="bairro"
							value="${user.bairro}" placeholder="Digite o bairro"></td>
					</tr>

					<tr>
						<td>Nome</td>
						<td><input type="text" id="nome" name="nome"
							value="${user.nome}" placeholder=" Digite o nome"></td>

						<td>cidade</td>
						<td><input type="text" id="cidade" name="cidade"
							value="${user.cidade}" placeholder="Digite a cidade"></td>
					</tr>

					<tr>
						<td>Telefone</td>
						<td><input type="text" id="fone" name="fone"
							value="${user.fone}" placeholder="Insira um telefone"></td>

						<td>Estado</td>
						<td><input type="text" id="estado" name="estado"
							value="${user.estado}" placeholder="Digite o Estado"></td>

						<td>IBGE</td>
						<td><input type="text" id="ibge" name="ibge"
							value="${user.ibge}" placeholder="Codigo do IBGE"></td>
					</tr>

					<tr>
						<td>Foto:</td>
						<td><input type="file" name="foto" value="foto"><input
							type="text" style="display: none" name="fotoTemp"
							value="${user.fotoBase64}" readonly="readonly"> <input
							type="text" style="display: none" name="contentTypeTemp"
							value="${user.contentType}" readonly="readonly"></td>
					</tr>
					<tr>
						<td>Curriculo:</td>
						<td><input type="file" name="curriculo" value="curriculo">
							<input type="text" style="display: none" name="fotoTempPdf"
							value="${user.curriculoBase64}" readonly="readonly"> <input
							type="text" style="display: none" name="contentTypeTempPdf"
							value="${user.contentTypeCurriculo}" readonly="readonly"></td>
					</tr>

					<tr>
						<td></td>
						<td><input type="submit" value="Salvar"> <input
							type="submit" value="Cancelar"
							onclick="document.getElementById('formuser').action='salvarUsuario?acao=reset'"></td>
					</tr>
				</table>

			</form>
		</li>
	</ul>
	<center>
		<h1>Usuarios Cadastrados</h1>
	</center>
	<div class="container">
		<table class="responsive-table">


			<tr>
				<th>ID</th>
				<th>FOTO</th>
				<th>CURRICULO</th>
				<th>NOME</th>
				<th>CEP</th>
				<th>RUA</th>
				<th>BAIRRO</th>
				<th>CIDADE</th>
				<th>ESTADO</th>
				<th>IBGE</th>
				<th>DELETAR</th>
				<th>EDITAR</th>
				<th>PHONES</th>
			</tr>

			<c:forEach items="${usuario}" var="user">

				<tr>
					<td><c:out value="${user.id}"></c:out></td>

					<td><a
						href="salvarUsuario?acao=download&tipo=imagem&user=${user.id}"><img
							src="<c:out value="${user.tempFotoUser}"/>" width=" 40px"
							height="40 px" title="Foto Usuario"></a></td>
					<td><a
						href="salvarUsuario?acao=download&tipo=curriculo&user=${user.id}"><img
							title="curriculo" src="resource/img/curriculo.png" width="40px"
							height="40 px"></a></td>

					<td><c:out value="${user.nome}"></c:out></td>

					<td><c:out value="${user.cep}"></c:out></td>

					<td><c:out value="${user.rua}"></c:out></td>

					<td><c:out value="${user.bairro}"></c:out></td>

					<td><c:out value="${user.cidade}"></c:out></td>

					<td><c:out value="${user.estado}"></c:out></td>

					<td><c:out value="${user.ibge}"></c:out></td>

					<td><a href="salvarUsuario?acao=delete&user=${user.id}"><img
							src="resource/img/delete.png" width="20px" height="20 px"
							title="Excluir"></a></td>
					<td><a href="salvarUsuario?acao=editar&user=${user.id}"><img
							src="resource/img/editar.jpg" width="20px" height="20 px"
							title="Editar"></a></td>

					<td><a href="salvarTelefones?acao=addfone&user=${user.id}"><img
							src="resource/img/telefone.png" width="25px" height="25 px"
							title="Telefones"></a></td>
				</tr>

			</c:forEach>
			<tr></tr>
		</table>
	</div>
	<script type="text/javascript">
		function validarCampos() {
			if (document.getElementById("login").value == '') {
				alert('informe o login')
				return false;
			} else if (document.getElementById("senha").value == '') {
				alert('informe a senha')
				return false;
			} else if (document.getElementById("nome").value == '') {
				alert('informe o nome')
				return false;
			} else if (document.getElementById("fone").value == '') {
				alert('informe o telefone')
				return false;
			}
			return true;
		}
		function consultaCep() {
			var cep = $("#cep").val();

			//Consulta o webservice viacep.com.br/
			$.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?",
					function(dados) {

						$("#rua").val(dados.logradouro);
						$("#bairro").val(dados.bairro);
						$("#cidade").val(dados.localidade);
						$("#estado").val(dados.uf);
						$("#ibge").val(dados.ibge);

						if (!("erro" in dados)) {

						} //end if.
						else {

							alert("CEP não encontrado.");
							$("#cep").val('');
							$("#rua").val('');
							$("#bairro").val('');
							$("#cidade").val('');
							$("#estado").val('');
							$("#ibge").val('');
						}
					});

		}
	</script>

</body>
</html>