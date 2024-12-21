package client;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.List;

import model.Product;
import server.Inventory;

public class InventoryClient {
    public static void main(String[] args) {
        try {
            
            Registry registry = LocateRegistry.getRegistry("127.0.0.1");

     
            Inventory inventoryService = (Inventory) registry.lookup("InventoryService");

         
            System.out.println("Fetching product list...");
            List<Product> products = inventoryService.getProducts();
            if (products != null && !products.isEmpty()) {
                System.out.println("ID | Name | Category | Quantity | Price");
                for (Product product : products) {
                    System.out.printf("%d | %s | %s | %d | %.2f%n",
                            product.getId(), product.getName(),
                            product.getCategory(), product.getQuantity(),
                            product.getPrice());
                }
            } else {
                System.out.println("No products available.");
            }

           
            System.out.println("\nAdding a new product...");
            String name = "Laptop";
            String category = "Electronics";
            int quantity = 10;
            double price = 1200.50;

            boolean isAdded = inventoryService.addProduct(name, category, quantity, price);
            if (isAdded) {
                System.out.println("Product added successfully!");
            } else {
                System.out.println("Failed to add product.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
