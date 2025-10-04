package cadastroclientes.testes;

import cadastroclientes.jdbc.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Testes para o mecanismo de persistência.
 *
 * @author Prof. Dr. David Buzatto
 */
public class Testes {

    public static void main( String[] args ) throws Exception {

        // seu código aqui
           try{
            Connection connection = ConnectionFactory.getConnection();
            System.out.println("Conexão criada com sucesso!!");
        } catch(SQLException esc){
            System.err.println("Erro ao tentar criar uma conexão");
        }
    }
    
}
