package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import model.Product;

public class InventoryImpl extends UnicastRemoteObject implements Inventory {

    
	private static final long serialVersionUID = 1L;
	private List<Product> products;

    public InventoryImpl() throws RemoteException {
        products = new ArrayList<>();
    }

    @SuppressWarnings("exports")
	@Override
    public List<Product> getProducts() throws RemoteException {
        return products;
    }

    @Override
    public boolean addProduct(String name, String category, int quantity, double price) throws RemoteException {
        Product product = new Product(quantity, name, category, quantity, price);
        return products.add(product);
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


