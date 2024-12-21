package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
	

	public class DatabaseMysql {
	  
	    private static final String URL = "jdbc:MySQL://localhost:3306/inventory_db"; // Remplace par le nom de ta base de données
	    private static final String USER = "root"; // Remplace par ton nom d'utilisateur MySQL
	  private static final String PASSWORD = ""; // Remplace par ton mot de passe MySQL

	    public static void main(String[] args) {
	    	
	    		 
	    	
	        Connection connection = null;
	        Statement statement = null;
	        ResultSet resultSet = null;

	        try {
	           
	            connection = DriverManager.getConnection(URL,USER,PASSWORD);
	            System.out.println("Connexion réussie à la base de données.");

	            // Créer une instruction pour exécuter des requêtes
	            statement = connection.createStatement();

	            // Exécuter une requête SQL
	            String sql = "SELECT * FROM inventory_db"; // Remplace par le nom de ta table
	            resultSet = statement.executeQuery(sql);

	            // Traiter les résultats
	            while (resultSet.next()) {
	                int id = resultSet.getInt("id"); // Remplace "id" par le nom de ta colonne
	                String name = resultSet.getString("TYPE_EPREUVE"); // Remplace "name" par le nom de ta colonne
	                System.out.println("ID: " + id + ", TYPE_EPREUVE: " + name);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            // Fermer les ressources
	            try {
	                if (resultSet != null) resultSet.close();
	                if (statement != null) statement.close();
	                if (connection != null) connection.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	    }
	}
