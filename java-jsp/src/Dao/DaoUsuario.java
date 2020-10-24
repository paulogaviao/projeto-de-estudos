package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.beanValidacao;
import connection.singleConnection;

public class DaoUsuario {

	private Connection connection;

	public DaoUsuario() {
		connection = singleConnection.getConnection();
	}

	// metodo para inserir logine senha no banco e salvar os dados.

	public void salvar(beanValidacao usuario) {

		try {
			String sql = "insert into usuario(login, senha, nome, fone, cep, rua, bairro, cidade, estado, ibge , fotobase64,contenttype, curriculoBase64, contentTypeCurriculo  )values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, usuario.getLogin());
			insert.setString(2, usuario.getSenha());
			insert.setString(3, usuario.getNome());
			insert.setString(4, usuario.getFone());
			
			insert.setString(5, usuario.getCep());
			insert.setString(6, usuario.getRua());
			insert.setString(7, usuario.getBairro());
			insert.setString(8, usuario.getCidade());
			insert.setString(9, usuario.getEstado());
			insert.setString(10, usuario.getIbge());
			insert.setString(11, usuario.getFotoBase64());
			insert.setString(12, usuario.getContentType());
			insert.setString(13, usuario.getCurriculoBase64());
			insert.setString(14, usuario.getContentTypeCurriculo());
			insert.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}
//esse metodo lista os usuarios logo abaixo na tela de cadastro

	public List<beanValidacao> listar() throws Exception {
		List<beanValidacao> listar = new ArrayList<beanValidacao>();

		String sql = "select * from usuario";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			beanValidacao beanVal = new beanValidacao();
			beanVal.setId(resultSet.getLong("id"));
			beanVal.setLogin(resultSet.getString("login"));
			beanVal.setSenha(resultSet.getString("senha"));
			beanVal.setNome(resultSet.getString("nome"));
			beanVal.setFone(resultSet.getString("fone"));
			
			beanVal.setCep(resultSet.getString("cep"));
			beanVal.setRua(resultSet.getString("rua"));
			beanVal.setBairro(resultSet.getString("bairro"));
			beanVal.setCidade(resultSet.getString("cidade"));
			beanVal.setEstado(resultSet.getString("estado"));
			beanVal.setIbge(resultSet.getString("ibge"));
			
			beanVal.setContentType(resultSet.getString("contentType"));
			beanVal.setFotoBase64(resultSet.getString("fotoBase64"));
			beanVal.setCurriculoBase64(resultSet.getString("curriculoBase64"));
			beanVal.setContentTypeCurriculo(resultSet.getString("contentTypeCurriculo"));
			listar.add(beanVal);
		}
		return listar;
	}

	public void delete(String id) { // metodo para excluir usuario.
		try {
			String sql = "delete from usuario where id='" + id + "'";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {

				e1.printStackTrace();
			}
		}
	}

//faz uma consulta no banco de dados antes de executar uma atualizaçao

	public beanValidacao consultar(String id) throws Exception {

		String sql = "select * from usuario where id='" + id + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		if (resultado.next()) {
			beanValidacao bean = new beanValidacao();
			bean.setId(resultado.getLong("id"));
			bean.setLogin(resultado.getString("login"));
			bean.setSenha(resultado.getString("senha"));
			bean.setNome(resultado.getString("nome"));
			bean.setFone(resultado.getString("fone"));
			
			bean.setCep(resultado.getString("cep"));
			bean.setRua(resultado.getString("rua"));
			bean.setBairro(resultado.getString("bairro"));
			bean.setCidade(resultado.getString("cidade"));
			bean.setEstado(resultado.getString("estado"));
			bean.setIbge(resultado.getString("ibge"));
			bean.setContentType(resultado.getString("contentType"));
			bean.setFotoBase64(resultado.getString("fotoBase64"));
			bean.setCurriculoBase64(resultado.getString("curriculoBase64"));
			bean.setContentTypeCurriculo(resultado.getString("contentTypeCurriculo"));
			return bean;
		}
		return null;
	}
/*verifica se existe algum login e valida ele como unico no sistema..*/
	public boolean validarLogin(String login) throws Exception {

		String sql = "select count(1) as qtd from usuario where login='" + login + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		if (resultado.next()) {

			return resultado.getInt("qtd") <= 0;
		}
		return false;
	}
/*verifica o login apartir do atualizar e e valida ele sendo unico no sistema*/
	public boolean validarLoginUpdate(String login, String id) throws Exception {

		String sql = "select count(1) as qtd from usuario where login='" + login + "'and id <>"+id;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		if (resultado.next()) {

			return resultado.getInt("qtd") <= 0;
		}
		return false;
	}
	/*valida a senha sendo unica dentro do sistema*/
	public boolean validarSenha(String senha) throws Exception {

		String sql = "select count(1) as qtd from usuario where senha='" + senha + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		if (resultado.next()) {

			return resultado.getInt("qtd") <= 0;
		}
		return false;
	}
// metodo para atualizar um cadastro jah existente
	public void atualizar(beanValidacao usuario) {
		try {
			String sql = "update usuario set login= ?,senha=?,nome=?, fone=?, cep=?, rua=?, bairro=?, cidade=?, estado=?, ibge=? ,contenttype=? ,fotobase64=? , curriculoBase64=?, contentTypeCurriculo=? where id =" + usuario.getId();
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, usuario.getLogin());
			statement.setString(2, usuario.getSenha());
			statement.setString(3, usuario.getNome());
			statement.setString(4, usuario.getFone());
			
			statement.setString(5, usuario.getCep());
			statement.setString(6, usuario.getRua());
			statement.setString(7, usuario.getBairro());
			statement.setString(8, usuario.getCidade());
			statement.setString(9, usuario.getEstado());
			statement.setString(10, usuario.getIbge());
			statement.setString(11, usuario.getContentType());
			statement.setString(12, usuario.getFotoBase64());
			statement.setString(13, usuario.getCurriculoBase64());
			statement.setString(14, usuario.getContentTypeCurriculo());
			statement.execute();
			connection.commit();

		} catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}
	
}
