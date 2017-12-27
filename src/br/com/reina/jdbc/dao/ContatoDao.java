package br.com.reina.jdbc.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.reina.jdbc.ConnectionFactory;
import br.com.reina.jdbc.modelo.Contato;

public class ContatoDao {
	
	// a conexão com o banco de dados
    private Connection connection;
    
    public ContatoDao() {
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void adiciona(Contato contato) {
    	String sql = "insert into contatos " +
    				 "(nome, email, endereco, dataNascimento) " +
    				 "values (?,?,?,?)";
    	
    	try {
    		// prepared statement para inserção
    		PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
    		
    		// seta os valores
            stmt.setString(1,contato.getNome());
            stmt.setString(2,contato.getEmail());
            stmt.setString(3,contato.getEndereco());
            stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
            
            // executa
            stmt.execute();
            
            // Recupera o id gerado no momento da inserção
            ResultSet generatedKeys = stmt.getGeneratedKeys();
            if(generatedKeys.next()) {
            	contato.setId(generatedKeys.getLong(1));
            }
            
            stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }
    
    public void altera(Contato contato) {
    	String sql = "update contatos set nome=?, email=?, endereco=?, " +
    				 "dataNascimento=? where id=?";
    	
    	try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			// seta os valores
            stmt.setString(1,contato.getNome());
            stmt.setString(2,contato.getEmail());
            stmt.setString(3,contato.getEndereco());
            stmt.setDate(4, new Date(contato.getDataNascimento().getTimeInMillis()));
            stmt.setLong(5,contato.getId());
            stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }
    
    public void remove(Contato contato) {
    	String sql = "delete from contatos where id=?";
    
    	try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setLong(1, contato.getId());
            stmt.execute();
            stmt.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    
    public List<Contato> getLista() {
    	String sql = "select * from contatos";
    	
    	try {
			List<Contato> contatos = new ArrayList<>();
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			
			while(rs.next()) {
				// criando o objeto Contato
				Contato contato = new Contato();
                contato.setId(rs.getLong("id"));
                contato.setNome(rs.getString("nome"));
                contato.setEmail(rs.getString("email"));
                contato.setEndereco(rs.getString("endereco"));
                
                // montando a data através do Calendar
                Calendar data = Calendar.getInstance();
                data.setTime(rs.getDate("dataNascimento"));
                contato.setDataNascimento(data);
                
                // adicionando o objeto à lista
                contatos.add(contato);
			}
			
			rs.close();
			stmt.close();
			
			return contatos;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
    }
}
