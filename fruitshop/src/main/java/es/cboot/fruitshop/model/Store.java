package es.cboot.fruitshop.model;

import java.util.ArrayList;
import java.util.List;

import es.cboot.fruitshop.model.offers.Offer;
import es.cboot.fruitshop.model.offers.OfferBuy3PearTake2;
import es.cboot.fruitshop.model.offers.OfferDirectDiscountForEvery4inPears;
import es.cboot.fruitshop.model.offers.OfferFreeOrangeEvery2Pears;
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
			
			output.append("You save " + totalDiscount + " from applied offers\n");
		}
		
		
		
		output.append("Grand total: " + (subTotal - totalDiscount));
		return output.toString();
	}

	private static Store instance;
	
	public static Store getInstance() {
		if (instance == null) {
			instance = new Store();
		}
		return instance;
	}
	
	private Store() {
		availableOffers.add(new OfferBuy3PearTake2());
		availableOffers.add(new OfferFreeOrangeEvery2Pears());
		availableOffers.add(new OfferDirectDiscountForEvery4inPears());
	}
}
