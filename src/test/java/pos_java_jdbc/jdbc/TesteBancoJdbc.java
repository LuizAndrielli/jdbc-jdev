package pos_java_jdbc.jdbc;

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

}
