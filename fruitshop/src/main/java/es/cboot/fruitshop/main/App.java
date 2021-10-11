package es.cboot.fruitshop.main;

import java.io.File;
import java.util.List;

import org.apache.commons.io.FileUtils;

import es.cboot.fruitshop.model.Store;
import es.cboot.fruitshop.services.AddProductToOrderUseCase;
import es.cboot.fruitshop.services.AddProductToStoreUseCase;
import es.cboot.fruitshop.utils.MyLogger;

public class App {
	
	private static final String CHARSET = "UTF-8";

	private static Store STORE = Store.getInstance();
	
	public static void main(String[] args) {

		for (String anArg: args) {
			MyLogger.log(anArg);
		}
		
		if (args.length != 2) {
			System.out.println("Application expects 2 parameters: path/to/products.txt path/to/order.txt");
		}
		
		
		
		File productsInput = new File(args[0]);
		File orderInput = new File(args[1]);
		
		
		// Setup phase, adding products to the store and creating the order
		try {
			List<String> products = FileUtils.readLines(productsInput, CHARSET);
			List<String> order = FileUtils.readLines(orderInput, CHARSET);
			
			
			for (int i = 1; i < products.size(); i ++) {
				String[] current = products.get(i).split(",");
				AddProductToStoreUseCase.getInstance().addProduct(current[0], Double.parseDouble(current[1].trim()));
			}


			for (int i = 1; i < order.size(); i ++) {
				String[] current = order.get(i).split(",");
				AddProductToOrderUseCase.getInstance().addProduct(current[0], Integer.parseInt(current[1].trim()));
			}

			MyLogger.log(STORE);
			
		} catch (Exception e) {
			System.out.println("Application failed to read input files properly: " + e.getMessage());
			System.exit(-1);
		}
		
		// Setup is complete
		System.out.println(STORE.process());
		 
	}

}
