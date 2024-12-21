package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import model.Product;

public interface Inventory extends Remote {
    @SuppressWarnings("exports")
	List<Product> getProducts() throws RemoteException;
    boolean addProduct(String name, String category, int quantity, double price) throws RemoteException;

    boolean updateProduct(int productId, String name, String category, int quantity, double price) throws RemoteException;
    boolean deleteProduct(int productId) throws RemoteException;
    @SuppressWarnings("exports")
	Product getProductById(int productId) throws RemoteException;
}
