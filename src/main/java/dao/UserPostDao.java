package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
	
	public List<Userpostjava> listar() throws Exception{
		
		List<Userpostjava> lista = new ArrayList<Userpostjava>();
		
		String sql = "select * from userpostjava";
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {
			Userpostjava userpostjava = new Userpostjava();
			userpostjava.setId(resultado.getLong("id"));
			userpostjava.setNome(resultado.getString("nome"));
			userpostjava.setEmail(resultado.getString("email"));	
			lista.add(userpostjava);
		}
		return lista;
	}
	
	public Userpostjava buscar(Long id) throws Exception{
		
		Userpostjava obj = new Userpostjava();
		
		String sql = "select * from userpostjava where id = " + id;
		
		PreparedStatement statement = connection.prepareStatement(sql);
		
		ResultSet resultado = statement.executeQuery();
		
		while(resultado.next()) {
			obj.setId(resultado.getLong("id"));
			obj.setNome(resultado.getString("nome"));
			obj.setEmail(resultado.getString("email"));	
		}
		return obj;
	}

}
