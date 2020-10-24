package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import bean.beanTelefones;
import connection.singleConnection;

public class DaoTelefones {

	private Connection connection;

	public DaoTelefones() {
		connection = singleConnection.getConnection();
	}

	// metodo para inserir logine senha no banco e salvar os dados.

	public void salvar(beanTelefones telefones) {

		try {
			String sql = "insert into telefone(numero, tipo , usuario )values(?, ?, ?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, telefones.getNumero());
			insert.setString(2, telefones.getTipo());
			insert.setLong(3, telefones.getUsuario());
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

	public List<beanTelefones> listar(Long user) throws Exception {
		List<beanTelefones> listar = new ArrayList<beanTelefones>();

		String sql = "select * from telefone where usuario="+user;
		PreparedStatement statement = connection.prepareStatement(sql);
		ResultSet resultSet = statement.executeQuery();
		while (resultSet.next()) {
			beanTelefones beanVal = new beanTelefones();
			beanVal.setId(resultSet.getLong("id"));
			beanVal.setNumero(resultSet.getString("numero"));
			beanVal.setTipo(resultSet.getString("tipo"));
			beanVal.setUsuario(resultSet.getLong("usuario"));

			listar.add(beanVal);
		}
		return listar;
	}

	public void delete(String id) { // metodo para excluir usuario.
		try {
			String sql = "delete from telefone where id='" + id + "'";
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

}
