package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.Userpostjava;

public class UserPostDao {

	private Connection connection;

	public UserPostDao() {
		connection = SingleConnection.getConnection();
	}

	public void salvar(Userpostjava userpostjava) {
		try {
			String sql = "insert into userpostjava(nome,email) values (?,?)";
			PreparedStatement insert = connection.prepareStatement(sql);
			insert.setString(1, userpostjava.getNome());
			insert.setString(2, userpostjava.getEmail());
			insert.execute();
			connection.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	create SEQUENCE usersequence
//	increment 1
//	minvalue 1
//	maxvalue 981723987129
//	start 5;
//
//	SELECT * from userpostjava 
//
//	alter table userpostjava ALTER column id set default nextval('usersequence'::regclass);
//


	public List<Userpostjava> listar() throws Exception {

		List<Userpostjava> lista = new ArrayList<Userpostjava>();

		String sql = "select * from userpostjava";

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			Userpostjava userpostjava = new Userpostjava();
			userpostjava.setId(resultado.getLong("id"));
			userpostjava.setNome(resultado.getString("nome"));
			userpostjava.setEmail(resultado.getString("email"));
			lista.add(userpostjava);
		}
		return lista;
	}

	public Userpostjava buscar(Long id) throws Exception {

		Userpostjava obj = new Userpostjava();

		String sql = "select * from userpostjava where id = " + id;

		PreparedStatement statement = connection.prepareStatement(sql);

		ResultSet resultado = statement.executeQuery();

		while (resultado.next()) {
			obj.setId(resultado.getLong("id"));
			obj.setNome(resultado.getString("nome"));
			obj.setEmail(resultado.getString("email"));
		}
		return obj;
	}

	public void atualizar(Userpostjava userpostjava) {
		try {
			String sql = "update userpostjava set nome = ? where id = " + userpostjava.getId();

			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setString(1, userpostjava.getNome());

			statement.execute();
			connection.commit();
		} catch (Exception e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
	}

}
