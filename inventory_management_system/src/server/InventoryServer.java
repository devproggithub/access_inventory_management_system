package server;

import java.rmi.*;
import java.rmi.server.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

// Define the Product class
class Product {
    private int id;
    private String name;
    private String category;
    private int quantity;
    private double price;

    public Product(int id, String name, String category, int quantity, double price) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.quantity = quantity;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getPrice() {
        return price;
    }
}

// Define the Inventory interface
interface Inventory extends Remote {
    List<Product> getProducts() throws RemoteException;
    boolean addProduct(String name, String category, int quantity, double price) throws RemoteException;
}

// Implement the Inventory interface
class InventoryImpl extends UnicastRemoteObject implements Inventory {
    private final List<Product> products;
    private int productIdCounter;

    protected InventoryImpl() throws RemoteException {
        super();
        this.products = new ArrayList<>();
        this.productIdCounter = 1; // Start product IDs at 1
    }

    @Override
    public synchronized List<Product> getProducts() throws RemoteException {
        return new ArrayList<>(products); // Return a copy to ensure thread safety
    }

    @Override
    public synchronized boolean addProduct(String name, String category, int quantity, double price) throws RemoteException {
        if (name == null || name.isEmpty() || category == null || category.isEmpty() || quantity < 0 || price < 0) {
            return false; // Invalid product details
        }
        Product newProduct = new Product(productIdCounter++, name, category, quantity, price);
        products.add(newProduct);
        return true;
    }
}

// Main server class
public class InventoryServer {
    public static void main(String[] args) {
        try {
            // Create and export the remote object
            Inventory inventory = new InventoryImpl();

            // Create the RMI registry on port 1099 (default)
            Registry registry = LocateRegistry.createRegistry(1099);

            // Bind the remote object in the registry
            registry.rebind("InventoryService", inventory);

            System.out.println("Inventory Server is running...");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
