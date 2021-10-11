package es.cboot.fruitshop.services;

import java.util.Map;

import es.cboot.fruitshop.exceptions.ProductNotFoundException;
import es.cboot.fruitshop.model.Product;
import es.cboot.fruitshop.model.Store;

public class AddProductToOrderUseCase {

	public void addProduct(String productName, int quantity) {
		try {
			Product product = GetProductFromStoreUseCase.getInstance().getProduct(productName);
			Map<Product, Integer> orderLines = Store.getInstance().getCurrentOrder().getOrderLines();
			orderLines.putIfAbsent(product, 0);
			orderLines.replace(product, orderLines.get(product) + quantity);
		} catch (ProductNotFoundException e) {
			e.printStackTrace();
			System.out.println("Can't go on, you ordered something that i dont have");
			System.exit(-1);
		}
		
	}
	
	
	private static AddProductToOrderUseCase instance;
	
	public static AddProductToOrderUseCase getInstance() {
		if (instance == null) {
			instance = new AddProductToOrderUseCase();
		}
		
		return instance;
	}
	
	private AddProductToOrderUseCase() {
		
	};
}
