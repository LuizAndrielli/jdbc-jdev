package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

import conexaojdbc.SingleConnection;
import model.Userpostjava;

public class UserPostDao {
	
	private Connection connection;
	
	public UserPostDao() {
		connection = SingleConnection.getConnection();
	}
	
	public void salvar(Userpostjava userpostjava) {
		try {
			String sql = "insert into userpostjava(id,nome,email) values (?,?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setLong(1, userpostjava.getId());
			insert.setString(2, userpostjava.getNome());
			insert.setString(3, userpostjava.getEmail());
			insert.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
