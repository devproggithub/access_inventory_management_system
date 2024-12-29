package client;

import model.Product;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class InventoryClient {
    public static void main(String[] args) {
        try (
                Socket socket = new Socket("127.0.0.1", 6000);
                ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream input = new ObjectInputStream(socket.getInputStream());
                Scanner scanner = new Scanner(System.in)) {

            boolean running = true;

            while (running) {
                System.out.println("\n--- Menu ---:");
                System.out.println("1. Ajouter un produit");
                System.out.println("2. Modifier un produit");
                System.out.println("3. Supprimer un produit");
                System.out.println("4. Rechercher un produit par nom");
                System.out.println("5. Quitter");
                System.out.print("Votre choix : ");
                int choix = scanner.nextInt();
                scanner.nextLine();

                switch (choix) {
                    case 1 -> addProduct(scanner, output, input);
                    case 2 -> updateProduct(scanner, output, input);
                    case 3 -> deleteProduct(scanner, output, input);
                    case 4 -> searchProduct(scanner, output, input);
                    case 5 -> {
                        running = false;
                        output.writeObject("QUIT");
                        System.out.println("Déconnexion...");
                    }
                    default -> System.out.println("Choix invalide.");
                }
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void addProduct(Scanner scanner, ObjectOutputStream output, ObjectInputStream input) throws IOException, ClassNotFoundException {
        System.out.println("Ajout d'un produit");

        System.out.print("Nom : ");
        String name = scanner.nextLine();

        System.out.print("Catégorie : ");
        String category = scanner.nextLine();

        int quantity = 0;
        while (true) {
            System.out.print("Quantité : ");
            if (scanner.hasNextInt()) {
                quantity = scanner.nextInt();
                scanner.nextLine(); // Consume newline
                break;
            } else {
                System.out.println("Veuillez entrer un nombre valide pour la quantité.");
                scanner.nextLine(); // Clear invalid input
            }
        }

        double price = 0.0;
        while (true) {
            System.out.print("Prix : ");
            if (scanner.hasNextDouble()) {
                price = scanner.nextDouble();
                scanner.nextLine(); // Consume newline
                break;
            } else {
                System.out.println("Veuillez entrer un nombre valide pour le prix.");
                scanner.nextLine(); // Clear invalid input
            }
        }

        Product newProduct = new Product(0, name, category, quantity, price);
        output.writeObject("ADD");
        output.writeObject(newProduct);

        // Wait for server response
        try {
            String response = (String) input.readObject();
            System.out.println("Réponse du serveur : " + response);
        } catch (EOFException e) {
            System.err.println("Server closed connection unexpectedly.");
        }
    }



    private static void updateProduct(Scanner scanner, ObjectOutputStream output, ObjectInputStream input) throws IOException, ClassNotFoundException {
        System.out.print("ID : ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nom : ");
        String name = scanner.nextLine();
        System.out.print("Catégorie : ");
        String category = scanner.nextLine();
        System.out.print("Quantité : ");
        int quantity = scanner.nextInt();
        System.out.print("Prix : ");
        double price = scanner.nextDouble();

        output.writeObject("UPDATE");
        output.writeObject(new Product(id, name, category, quantity, price));
        System.out.println("Réponse du serveur : " + input.readObject());
    }

    private static void deleteProduct(Scanner scanner, ObjectOutputStream output, ObjectInputStream input) throws IOException, ClassNotFoundException {
        System.out.print("ID : ");
        int id = scanner.nextInt();

        output.writeObject("DELETE");
        output.writeObject(id);
        System.out.println("Réponse du serveur : " + input.readObject());
    }

    private static void searchProduct(Scanner scanner, ObjectOutputStream output, ObjectInputStream input) throws IOException, ClassNotFoundException {
        System.out.print("Nom : ");
        String name = scanner.nextLine();

        output.writeObject("SEARCH_BY_NAME");
        output.writeObject(name);
        System.out.println("Réponse du serveur : " + input.readObject());
    }
}


