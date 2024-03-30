package dao;

import java.sql.Connection;

import conexaojdbc.SingleConnection;

public class UserPostDao {
	
	private Connection connection;
	
	public UserPostDao() {
		connection = SingleConnection.getConnection();
	}

}
