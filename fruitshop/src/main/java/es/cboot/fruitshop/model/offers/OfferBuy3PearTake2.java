package es.cboot.fruitshop.model.offers;

import es.cboot.fruitshop.exceptions.ProductNotFoundException;
import es.cboot.fruitshop.model.Store;
import es.cboot.fruitshop.services.GetProductFromStoreUseCase;

public class OfferBuy3PearTake2 extends OfferBuyXPayY {

	public void init() {
		name = "1FPE3";
		try {
			product = GetProductFromStoreUseCase.getInstance().getProduct("Apple");
		} catch (ProductNotFoundException e) {
			applies = false;
			return;
		}
		quantity = Store.getInstance().getCurrentOrder().getOrderLines().get(getProduct());
		applies = quantity >= 3; 
	}

	@Override
	public double getDiscountAmount() {
		if (!applies) {
			throw new IllegalStateException("Can't invoke getDiscount on an offer that doesn't apply the current order");
		}
		return Math.floor(quantity / 3) * getProduct().getPrice();
	}
}
