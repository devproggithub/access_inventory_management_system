package server;

import dao.ProductDAO;
import model.Product;

import java.io.*;
import java.net.Socket;
import java.sql.SQLException;

public class ClientHandler implements Runnable {
    private final Socket clientSocket;
    private final ProductDAO productDAO;

    public ClientHandler(Socket clientSocket, ProductDAO productDAO) {
        this.clientSocket = clientSocket;
        this.productDAO = productDAO;
    }

    @Override
    public void run() {
        try (
                ObjectInputStream input = new ObjectInputStream(clientSocket.getInputStream());
                ObjectOutputStream output = new ObjectOutputStream(clientSocket.getOutputStream())
        ) {
            while (true) {
                try {
                    String command = (String) input.readObject();

                    switch (command) {
                        case "ADD":
                            handleAdd(input, output);
                            break;

                        case "UPDATE":
                            handleUpdate(input, output);
                            break;

                        case "DELETE":
                            handleDelete(input, output);
                            break;

                        case "SEARCH_BY_NAME":
                            handleSearchByName(input, output);
                            break;

                        case "QUIT":
                            output.writeObject("Déconnexion réussie.");
                            return;

                        default:
                            output.writeObject("Commande inconnue.");
                            break;
                    }
                } catch (ClassNotFoundException | SQLException | IOException e) {
                    output.writeObject("An error occurred: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            System.err.println("Error handling client connection: " + e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                System.err.println("Failed to close client socket: " + e.getMessage());
            }
        }
    }

    private void handleAdd(ObjectInputStream input, ObjectOutputStream output) throws IOException, ClassNotFoundException, SQLException {
        Product newProduct = (Product) input.readObject();
        boolean success = productDAO.addProduct(newProduct);
        output.writeObject(success ? "Product added successfully." : "Failed to add product.");
    }

    private void handleUpdate(ObjectInputStream input, ObjectOutputStream output) throws IOException, ClassNotFoundException, SQLException {
        Product product = (Product) input.readObject();
        boolean success = productDAO.updateProduct(product);
        output.writeObject(success ? "Product successfully updated." : "Product update failed.");
    }

    private void handleDelete(ObjectInputStream input, ObjectOutputStream output) throws IOException, ClassNotFoundException, SQLException {
        int productId = (Integer) input.readObject();
        boolean success = productDAO.deleteProduct(productId);
        output.writeObject(success ? "Product successfully removed." : "Product deletion failed.");
    }

    private void handleSearchByName(ObjectInputStream input, ObjectOutputStream output) throws IOException, ClassNotFoundException, SQLException {
        String name = (String) input.readObject();
        Product[] products = productDAO.searchByName(name).toArray(new Product[0]);
        output.writeObject(products);
    }
}
