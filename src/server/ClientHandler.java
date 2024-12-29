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
                            try {
                                Product newProduct = (Product) input.readObject();
                                boolean success = productDAO.addProduct(newProduct);
                                output.writeObject(success ? "Product added successfully." : "Failed to add product.");
                            } catch (Exception e) {
                                e.printStackTrace();
                                output.writeObject("Error processing ADD command: " + e.getMessage());
                            }
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
        output.writeObject(success ? "Produit mis à jour avec succès." : "Échec de la mise à jour du produit.");
    }

    private void handleDelete(ObjectInputStream input, ObjectOutputStream output) throws IOException, ClassNotFoundException, SQLException {
        int productId = (Integer) input.readObject();
        boolean success = productDAO.deleteProduct(productId);
        output.writeObject(success ? "Produit supprimé avec succès." : "Échec de la suppression du produit.");
    }

    private void handleSearchByName(ObjectInputStream input, ObjectOutputStream output) throws IOException, ClassNotFoundException, SQLException {
        String name = (String) input.readObject();
        Product[] products = productDAO.searchByName(name).toArray(new Product[0]);
        output.writeObject(products);
    }
}
