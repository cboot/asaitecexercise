package es.cboot.fruitshop.services;

import es.cboot.fruitshop.exceptions.ProductNotFoundException;
import es.cboot.fruitshop.model.Product;
import es.cboot.fruitshop.model.Store;

public class GetProductFromStoreUseCase {

	public Product getProduct(String name) throws ProductNotFoundException {
		
		for (Product aProduct: Store.getInstance().getAvailableProducts()) {
			if ( aProduct.getName().equals(name)) {
				return aProduct;
			}
		}
		
		throw new ProductNotFoundException("The product " + name + " in the store!");
	}
	
	private static GetProductFromStoreUseCase instance;
	
	public static GetProductFromStoreUseCase getInstance() {
		if (instance == null) {
			instance = new GetProductFromStoreUseCase();
		}
		
		return instance;
	}
	
	private GetProductFromStoreUseCase() {
		
	};
}
