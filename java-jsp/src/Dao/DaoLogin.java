package Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import connection.singleConnection;

public class DaoLogin {

	private Connection connection;
	
	public DaoLogin() {
		connection=singleConnection.getConnection();
	}
	public boolean validarLogin(String login, String senha)throws Exception {
		String sql="select *from usuario where login='"+login+"'and senha='"+senha+"'";
		PreparedStatement statemant=connection.prepareStatement(sql);
		ResultSet resultset=statemant.executeQuery();
		if(resultset.next()) {
			return true;
		}else {
			return false;
		}
		
	}
}
