package pos_java_jdbc.jdbc;

import java.util.List;

import org.junit.Test;


import dao.UserPostDao;
import model.Userpostjava;

public class TesteBancoJdbc {
	
	@Test
	public void initBanco() {
		UserPostDao userPostDao = new UserPostDao();
		Userpostjava userpostjava = new Userpostjava();
		
		userpostjava.setId(4L);
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
}
