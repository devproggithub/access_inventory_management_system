package server;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class InventoryImpl extends UnicastRemoteObject implements Inventory {

    
	private static final long serialVersionUID = 1L;
	private List<Product> products;

    public InventoryImpl() throws RemoteException {
        products = new ArrayList<>();
    }

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


