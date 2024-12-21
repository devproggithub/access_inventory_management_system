package jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
	

	public class DatabaseMysql {
	  
	    private static final String URL = "jdbc:MySQL://127.0.0.1:3306/inventory_db"; 
	  private static final String PASSWORD = "1234@";
	private static final String USER = "root";

	    public static void main(String[] args) {
	    	
	    		 
	    	
	        Connection connection = null;
	        Statement statement = null;
	        ResultSet resultSet = null;

	        try {
	           
	            connection = DriverManager.getConnection(URL,USER,PASSWORD);
	            System.out.println("Connexion réussie à la base de données.");

	           
	            statement = connection.createStatement();

	          
	            String sql = "SELECT * FROM inventory_db"; 
	            resultSet = statement.executeQuery(sql);

	          
	            while (resultSet.next()) {
	                int id = resultSet.getInt("id"); 
	                String name = resultSet.getString("TYPE_EPREUVE"); 
	                System.out.println("ID: " + id + ", TYPE_EPREUVE: " + name);
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	           
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
