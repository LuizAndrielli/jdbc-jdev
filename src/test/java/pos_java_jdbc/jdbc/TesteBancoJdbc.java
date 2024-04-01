package pos_java_jdbc.jdbc;

import java.util.List;

import org.junit.Test;

import dao.UserPostDao;
import model.Telefone;
import model.Userpostjava;

public class TesteBancoJdbc {

	@Test
	public void initBanco() {
		UserPostDao userPostDao = new UserPostDao();
		Userpostjava userpostjava = new Userpostjava();

//		userpostjava.setId(4L);
		userpostjava.setNome("Junit Teste");
		userpostjava.setEmail("teste@junit.com");

		userPostDao.salvar(userpostjava);
	}

	@Test
	public void initListar() {
		UserPostDao dao = new UserPostDao();
		try {
			List<Userpostjava> list = dao.listar();
			for (Userpostjava userpostjava : list) {
				System.out.println(userpostjava);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void buscar() {
		UserPostDao dao = new UserPostDao();
		try {
			Userpostjava obj = dao.buscar(3L);
			System.out.println(obj);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void initAtualizar() {
		try {
			UserPostDao dao = new UserPostDao();
			Userpostjava objBanco = dao.buscar(4L);
			objBanco.setNome("Nome initAtualizar");
			dao.atualizar(objBanco);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void initDelete() {
		try {
			UserPostDao dao = new UserPostDao();
			dao.deletar(6L);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void adicionarTelefone() {
		Telefone telefone = new Telefone();
		telefone.setNumero("555555555555");
		telefone.setTipo("Celular");
		telefone.setUsuariopessoa(3L);
		
		UserPostDao dao = new UserPostDao();
		dao.salvarTelefone(telefone);
	}
}
