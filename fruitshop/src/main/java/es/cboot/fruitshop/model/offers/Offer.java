package es.cboot.fruitshop.model.offers;

import java.util.HashMap;
import java.util.Map;

import es.cboot.fruitshop.model.Product;
import lombok.Getter;
import lombok.Setter;

public abstract class Offer {
	
	@Getter
	@Setter
	protected Product product;
	
	@Getter
	@Setter
	protected int quantity;
	
	@Getter
	protected String name;
	
	@Getter
	@Setter
	protected boolean applies;

	@Getter
	protected Map<Product, Integer> freeProducts = new HashMap<>();
	
	public abstract void init();
	
	public boolean applies() {
		return applies;
	}
	
	public abstract double getDiscountAmount();
	
	public String getOfferMessage() {
		StringBuilder output = new StringBuilder();
		
		if (getDiscountAmount() > 0) {
			output.append(getName() +": You saved " + getDiscountAmount() + " for buying " + getQuantity() + " x " + getProduct().getName());
		}
		if (!freeProducts.isEmpty()) {
			output.append(getName() +": You got some free items");
		}
		
		return output.toString();
	}
	
 
}
