package server;

import java.rmi.*;
import java.rmi.server.*;
import java.util.List;

import dao.ProductDAO;

public class ClientHandler extends UnicastRemoteObject implements Inventory {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ProductDAO productDAO;

    // Constructor for initializing ProductDAO
    public ClientHandler() throws RemoteException {
        super();
        this.productDAO = new ProductDAO();  // Initialize the DAO to interact with the database
    }

    @Override
    public List<Product> getProducts() throws RemoteException {
        System.out.println("Client requested all products.");
        return productDAO.getProducts();  // Fetch products from the database using ProductDAO
    }

    @Override
    public boolean addProduct(String name, String category, int quantity, double price) throws RemoteException {
        System.out.println("Client wants to add a new product: " + name);
        Product product = new Product(quantity, name, category, quantity, price);
        return productDAO.deleteProduct(product);  // Add the product to the database
    }

	@Override
	public boolean updateProduct(int productId, String name, String category, int quantity, double price)
			throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteProduct(int productId) throws RemoteException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Product getProductById(int productId) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}
}

