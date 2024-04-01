package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexaojdbc.SingleConnection;
import model.TelUserBean;
import model.Telefone;
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

	public void deletar(Long id) {
		try {
			String sql = "delete from userpostjava where id = " + id;
			PreparedStatement statement = connection.prepareStatement(sql);
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

	public void salvarTelefone(Telefone telefone) {
		try {
			String sql = "INSERT INTO public.telefoneuser(numero, tipo, usuariopessoa) VALUES (?, ?, ?);";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, telefone.getNumero());
			preparedStatement.setString(2, telefone.getTipo());
			preparedStatement.setLong(3, telefone.getUsuariopessoa());
			preparedStatement.execute();
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
	
	public List<TelUserBean> listarTelefoneuser(Long id) {
		List<TelUserBean> list = new ArrayList<TelUserBean>();
		
		String sql = " select nome,numero,email from telefoneuser as fone ";
		sql += " inner join userpostjava as userp ";
		sql += " on fone.usuariopessoa = userp.id ";
		sql += " where userp.id = " + id;
		
		try {
		PreparedStatement statement = connection.prepareStatement(sql);		
		ResultSet resultSet = statement.executeQuery();
		
		while(resultSet.next()) {
			TelUserBean bean = new TelUserBean();
			bean.setEmail(resultSet.getString("email"));
			bean.setNome(resultSet.getString("nome"));
			bean.setNumero(resultSet.getString("numero"));
			list.add(bean);
		}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public void deletarUserFone(Long id) {
		String sqlFone = "delete from telefoneuser where usuariopessoa = " + id;
		String sqlUser = "delete from userpostjava where id = " + id;
		
		try {
			PreparedStatement statement = connection.prepareStatement(sqlFone);
			statement.executeUpdate();			
			connection.commit();
			
			statement = connection.prepareStatement(sqlUser);
			statement.executeUpdate();
			connection.commit();
			
		} catch (SQLException e) {
			try {
				connection.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		
	}

}
