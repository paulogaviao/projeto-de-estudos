package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.beanProduto;

import connection.singleConnection;

public class DaoProduto {

	private Connection connection;

	public DaoProduto() {
		connection = singleConnection.getConnection();
	}

	// metodo para inserir logine senha no banco e salvar os dados.

	public void salvar(beanProduto produto) {

		try {
			String sql = "insert into produto(nome, quantidade , valor)values(?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, produto.getNome());
			insert.setDouble(2, produto.getQuantidade());
			insert.setDouble(3, produto.getValor());
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

	public List<beanProduto> listar() throws Exception {
		List<beanProduto> listar = new ArrayList<beanProduto>();

		String sql = "select * from produto";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			beanProduto beanVal = new beanProduto();
			beanVal.setId(resultSet.getLong("id"));
			beanVal.setNome(resultSet.getString("nome"));
			beanVal.setQuantidade(resultSet.getDouble("quantidade"));
			beanVal.setValor(resultSet.getDouble("valor"));

			listar.add(beanVal);
		}
		return listar;
	}

	public void delete(String id) { // metodo para excluir usuario.
		try {
			String sql = "delete from produto where id='" + id + "'";
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

	public beanProduto consultar(String id) throws Exception {

		String sql = "select * from produto where id='" + id + "'";
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		if (resultado.next()) {
			beanProduto bean = new beanProduto();
			bean.setId(resultado.getLong("id"));
			bean.setNome(resultado.getString("nome"));
			bean.setQuantidade(resultado.getDouble("quantidade"));
			bean.setValor(resultado.getDouble("valor"));

			return bean;
		}
		return null;
	}

	/* verifica se existe algum login e valida ele como unico no sistema.. */
	public boolean validarNome(String nome) throws Exception {
		if (nome != null && !nome.trim().isEmpty()) {
			String sql = "select count(1) as qtd from produto where nome='" + nome + "'";
			PreparedStatement statement = connection.prepareStatement(sql);
			ResultSet resultado = statement.executeQuery();
			if (resultado.next()) {

				return resultado.getInt("qtd") <= 0;
			}
		}
		return false;
	}

	/*
	 * verifica o login apartir do atualizar e e valida ele sendo unico no sistema
	 */
	public boolean validarNomeUpdate(String nome, String id) throws Exception {
     if(nome!=null && !nome.trim().isEmpty()) {
		String sql = "select count(1) as qtd from produto where nome='" + nome + "'and id <>" + id;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultado = statement.executeQuery();
		if (resultado.next()) {

			return resultado.getInt("qtd") <= 0;
		}
     }
		return false;
	}

// metodo para atualizar um cadastro jah existente
	public void atualizar(beanProduto produto) {
		try {

			String sql = "update produto set nome= ?,quantidade=?,valor=? where id =" + produto.getId();
			PreparedStatement statement = connection.prepareStatement(sql);

			statement.setString(1, produto.getNome());
			statement.setDouble(2, produto.getQuantidade());
			statement.setDouble(3, produto.getValor());

			statement.execute();
			connection.commit();

		}

		catch (Exception e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}

	}

}
