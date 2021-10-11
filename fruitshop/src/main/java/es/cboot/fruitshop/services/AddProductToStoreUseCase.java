package es.cboot.fruitshop.services;

import es.cboot.fruitshop.exceptions.ProductNotFoundException;
import es.cboot.fruitshop.model.Product;
import es.cboot.fruitshop.model.Store;
import es.cboot.fruitshop.utils.MyLogger;

public class AddProductToStoreUseCase {

	public void addProduct(String productName, double productPrice) {

		try {
			GetProductFromStoreUseCase.getInstance().getProduct(productName);
			throw new IllegalArgumentException("Product " + productName + " is already in the store");
		} catch (ProductNotFoundException e) {
			MyLogger.log("Adding product "  + productName);
			Product product = new Product(productName, productPrice);
			Store.getInstance().getAvailableProducts().add(product);
		}
	}

	private static AddProductToStoreUseCase instance;

	public static AddProductToStoreUseCase getInstance() {
		if (instance == null) {
			instance = new AddProductToStoreUseCase();
		}

		return instance;
	}

	private AddProductToStoreUseCase() {

	};
}
