package es.cboot.fruitshop.model;

import java.util.ArrayList;
import java.util.List;

import es.cboot.fruitshop.exceptions.ProductNotFoundException;
import es.cboot.fruitshop.model.offers.Offer;
import es.cboot.fruitshop.model.offers.OfferBuyXPayY;
import es.cboot.fruitshop.model.offers.OfferDirectDiscountForEvery4inPears;
import es.cboot.fruitshop.model.offers.OfferFreeOrangeEvery2Pears;
import es.cboot.fruitshop.services.GetProductFromStoreUseCase;
import es.cboot.fruitshop.utils.Utils;
import lombok.Getter;
import lombok.ToString;

@ToString
public class Store {
	
	@Getter
	private List<Product> availableProducts = new ArrayList<>();;
	
	@Getter
	private Order currentOrder = new Order();
	
	@Getter
	private List<Offer> availableOffers = new ArrayList<>();
	

    
	public String process() {
		initOffers();
		
		double subTotal = 0;
		double totalDiscount = 0;
		List<String> appliedOffersMessage = new ArrayList<>();
		StringBuilder output = new StringBuilder();
		
		for (Product aProductInOrder : currentOrder.getOrderLines().keySet()) {
			int quantity = currentOrder.getOrderLines().get(aProductInOrder);
			subTotal+= aProductInOrder.getPrice() * quantity;
			output.append(aProductInOrder.getName() + " x " + quantity + "\n");
		}
		
		output.append("Subtotal: " + subTotal + "\n");
		
		
		for (Offer anOffer: availableOffers) {
			anOffer.init();
			if (anOffer.applies()) {
				totalDiscount+=anOffer.getDiscountAmount();
				appliedOffersMessage.add(anOffer.getOfferMessage());
				for (Product aFreeProduct: anOffer.getFreeProducts().keySet()) {
					output.append("For free: " + anOffer.getFreeProducts().get(aFreeProduct) + " x " + aFreeProduct.getName() +"\n");
				}
			}
		}
		
		if (totalDiscount > 0) {
			for (String anOfferMessage: appliedOffersMessage) {
				output.append(anOfferMessage + "\n");
			}
			
			output.append("You save " + Utils.df.format(totalDiscount) + " from applied offers\n");
		}
		
		
		
		output.append("Grand total: " + Utils.df.format((subTotal - totalDiscount)));
		return output.toString();
	}

	private void initOffers() {
		try {
			availableOffers.add(new OfferBuyXPayY(GetProductFromStoreUseCase.getInstance().getProduct("Apple"), 3, 2));
		} catch (ProductNotFoundException e) {
			e.printStackTrace();
			System.err.println("Disabling offer due to unavailable product");
		}
		availableOffers.add(new OfferFreeOrangeEvery2Pears());
		availableOffers.add(new OfferDirectDiscountForEvery4inPears());
	}
	
	private static Store instance;
	
	public static Store getInstance() {
		if (instance == null) {
			instance = new Store();
		}
		return instance;
	}
	
	private Store() {

	}
	
}
