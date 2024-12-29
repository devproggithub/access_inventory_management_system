package server;

import dao.ProductDAO;
import jdbc.DatabaseConnection;

import java.io.IOException;
import java.net.ServerSocket;
import java.sql.Connection;
import java.sql.SQLException;

public class InventoryServer {
    public static void main(String[] args) {
        try (
                ServerSocket serverSocket = new ServerSocket(6000);
                Connection connection = DatabaseConnection.getConnection()) {

            ProductDAO productDAO = new ProductDAO(connection);
            System.out.println("Serveur démarré sur le port 6000...");

            while (true) {
                new Thread(new ClientHandler(serverSocket.accept(), productDAO)).start();
            }

        } catch (IOException | RuntimeException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}