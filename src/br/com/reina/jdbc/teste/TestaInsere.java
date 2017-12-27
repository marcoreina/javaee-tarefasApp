package br.com.reina.jdbc.teste;

import java.util.Calendar;

import br.com.reina.jdbc.dao.ContatoDao;
import br.com.reina.jdbc.modelo.Contato;

public class TestaInsere {
	
	public static void main(String[] args) {
		// pronto para gravar
		Contato contato = new Contato();
		contato.setNome("John");
		contato.setEmail("john@email.com.br");
		contato.setEndereco("R. abc 3185 cj57");
		contato.setDataNascimento(Calendar.getInstance());
		
		// grave nessa conex�o!!!
	    ContatoDao dao = new ContatoDao();
	    
	    // m�todo elegante
	    dao.adiciona(contato);
	    
	    System.out.println("Gravado!");
	}
}
