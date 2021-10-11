package es.cboot.fruitshop.model.offers;

import es.cboot.fruitshop.exceptions.ProductNotFoundException;
import es.cboot.fruitshop.model.Store;
import es.cboot.fruitshop.services.GetProductFromStoreUseCase;

public class OfferDirectDiscountForEvery4inPears extends Offer {


	public void init() {
		name = "1EDDFE4OP";
		try {
			product = GetProductFromStoreUseCase.getInstance().getProduct("Pear");
		} catch (ProductNotFoundException e) {
			applies = false;
			return;
		}
		quantity = Store.getInstance().getCurrentOrder().getOrderLines().get(getProduct());
		applies = quantity * product.getPrice() >= 4; 
	}

	@Override
	public double getDiscountAmount() {
		if (!applies) {
			throw new IllegalStateException("Can't invoke getDiscount on an offer that doesn't apply the current order");
		}
		return Math.floor(quantity * product.getPrice() / 4) * 1;
	}	
}
