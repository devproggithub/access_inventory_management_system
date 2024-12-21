package server;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class ProductDAO {
    private static final String URL = "jdbc:mysql://127.0.0.1:3306/inventory_db";  
    private static final String USER = "root"; 
    private static final String PASSWORD = "1234@"; 

   
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

   
    public boolean addProduct(String name, String category, int quantity, double price) {
        String query = "INSERT INTO products (name, category, quantity, price) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, category);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setDouble(4, price);
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;  
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @SuppressWarnings("exports")
	public List<Product> getProducts() {
        String query = "SELECT * FROM products";
        List<Product> products = new ArrayList<>();

        try (Connection connection = getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(query)) {
            
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String category = resultSet.getString("category");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");

                Product product = new Product(id, name, category, quantity, price);
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    
    @SuppressWarnings("exports")
	public Product getProductById(int id) {
        String query = "SELECT * FROM products WHERE id = ?";
        Product product = null;

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            
            if (resultSet.next()) {
                String name = resultSet.getString("name");
                String category = resultSet.getString("category");
                int quantity = resultSet.getInt("quantity");
                double price = resultSet.getDouble("price");

                product = new Product(id, name, category, quantity, price);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    
    public boolean updateProduct(int id, String name, String category, int quantity, double price) {
        String query = "UPDATE products SET name = ?, category = ?, quantity = ?, price = ? WHERE id = ?";
        
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, category);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setDouble(4, price);
            preparedStatement.setInt(5, id);
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;  // Return true if the product was updated successfully
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    
    public boolean deleteProduct(int id) {
        String query = "DELETE FROM products WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0; 
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	@SuppressWarnings("exports")
	public boolean deleteProduct(Product product) {
		
		return false;
	}

	
	

	
}

