package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import server.Product;

public class ProductDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/Tennis";  // Replace with your DB details
    private static final String USER = "root"; // Replace with your DB username
    private static final String PASSWORD = ""; // Replace with your DB password

    // Method to establish a database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Method to add a new product
    public boolean addProduct(String name, String category, int quantity, double price) {
        String query = "INSERT INTO products (name, category, quantity, price) VALUES (?, ?, ?, ?)";
        
        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, category);
            preparedStatement.setInt(3, quantity);
            preparedStatement.setDouble(4, price);
            
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;  // Return true if the product was added successfully
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Method to get a list of all products
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

    // Method to get a single product by ID
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

    // Method to update a product
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

    // Method to delete a product by ID
    public boolean deleteProduct(int id) {
        String query = "DELETE FROM products WHERE id = ?";

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            
            preparedStatement.setInt(1, id);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;  // Return true if the product was deleted successfully
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

	public boolean deleteProduct(Product product) {
		// TODO Auto-generated method stub
		return false;
	}

	
	

	
}

