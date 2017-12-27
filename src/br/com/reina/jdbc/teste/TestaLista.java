package br.com.reina.jdbc.teste;

import java.util.Calendar;
import java.util.List;

import br.com.reina.jdbc.dao.ContatoDao;
import br.com.reina.jdbc.modelo.Contato;

public class TestaLista {

	public static void main(String[] args) {
		ContatoDao dao = new ContatoDao();
		
		Contato contato = new Contato();
		contato.setNome("Maria");
		contato.setEmail("Maria@email.com.br");
		contato.setEndereco("R. xyz 1234");
		contato.setDataNascimento(Calendar.getInstance());
		
		// Adiciona um novo contato
		System.out.println("Checkpoint 1:\n");
		dao.adiciona(contato);
		printContatos(dao.getLista());
		
		// Altera o endereço do contato
		System.out.println("Checkpoint 2:\n");
		contato.setNome("Maria do Bairro");
		contato.setEndereco("R. sem nome");
		dao.altera(contato);
		printContatos(dao.getLista());
		
		// Remove o contato
		System.out.println("Checkpoint 3:\n");
		dao.remove(contato);
		printContatos(dao.getLista());
	}
	
	public static void printContatos(List<Contato> contatos) {
		
		for (Contato contato : contatos) {
			System.out.println("Nome: " + contato.getNome());
		    System.out.println("Email: " + contato.getEmail());
		    System.out.println("Endereço: " + contato.getEndereco());
		    System.out.println("Data de Nascimento: " + 
		              contato.getDataNascimento().getTime() + "\n");
		}
	}
}
