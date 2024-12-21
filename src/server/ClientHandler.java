package server;

import java.rmi.*;
import java.rmi.server.*;
import java.util.List;

import model.Product;

public class ClientHandler extends UnicastRemoteObject implements Inventory {

    
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;

    
    public ClientHandler() throws RemoteException {
        super();
        this.productDAO = new ProductDAO();  
    }

    @SuppressWarnings("exports")
	@Override
    public List<Product> getProducts() throws RemoteException {
        System.out.println("Client requested all products.");
        return productDAO.getProducts();  
    }

    @Override
    public boolean addProduct(String name, String category, int quantity, double price) throws RemoteException {
        System.out.println("Client wants to add a new product: " + name);
        Product product = new Product(quantity, name, category, quantity, price);
        return productDAO.deleteProduct(product); 
    }

	@Override
	public boolean updateProduct(int productId, String name, String category, int quantity, double price)
			throws RemoteException {
		
		return false;
	}

	@Override
	public boolean deleteProduct(int productId) throws RemoteException {
	
		return false;
	}

	@SuppressWarnings("exports")
	@Override
	public Product getProductById(int productId) throws RemoteException {
	
		return null;
	}
}

