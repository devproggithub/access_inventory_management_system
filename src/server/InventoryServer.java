package server;

import java.rmi.*;
import java.rmi.registry.*;
import java.util.HashMap;

public class InventoryServer {
    private static final String URL = "rmi://localhost:1099/InventoryService";
    
    // Hardcoded users (for demonstration)
    private static final HashMap<String, String> userCredentials = new HashMap<>();
    
    static {
        userCredentials.put("root", "1234@"); // Username : admin, Password : admin123
        userCredentials.put("user", "user123"); // Username : user, Password : user123
    }

    public static void main(String[] args) {
        try {
            // Initialize the server and bind it
            InventoryImpl inventory = new InventoryImpl();

            // Create registry
            LocateRegistry.createRegistry(1099);

            // Bind service to the registry
            Naming.bind(URL, inventory);

            System.out.println("Inventory Service is ready...");

            // Simple login check
            String username = "root"; // This will come from client input
            String password = "1234@"; // This will come from client input

            if (authenticate(username, password)) {
                System.out.println("Authentication successful. Access granted.");
                // Proceed to manage inventory...
            } else {
                System.out.println("Authentication failed. Access denied.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Authentication method
    public static boolean authenticate(String username, String password) {
        String storedPassword = userCredentials.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }
}


