package server;

import java.rmi.*;
import java.rmi.registry.*;
import java.util.HashMap;

public class InventoryServer {
    private static final String URL = "rmi://127.0.0.1:1099/InventoryService";
    
    
    private static final HashMap<String, String> userCredentials = new HashMap<>();
    
    static {
        userCredentials.put("admin", "admin123"); 
        userCredentials.put("user", "user123");
    }

    public static void main(String[] args) {
        try {
          
            InventoryImpl inventory = new InventoryImpl();

            
            LocateRegistry.createRegistry(3306);

           
            Naming.bind(URL, inventory);

            System.out.println("Inventory Service is ready...");

           
            String username = "user"; 
            String password = "user123"; 

            if (authenticate(username, password)) {
                System.out.println("Authentication successful. Access granted.");
                
            } else {
                System.out.println("Authentication failed. Access denied.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static boolean authenticate(String username, String password) {
        String storedPassword = userCredentials.get(username);
        return storedPassword != null && storedPassword.equals(password);
    }
}


