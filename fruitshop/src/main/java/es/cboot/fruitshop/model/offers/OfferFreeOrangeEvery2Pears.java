package es.cboot.fruitshop.model.offers;

import es.cboot.fruitshop.exceptions.ProductNotFoundException;
import es.cboot.fruitshop.model.Store;
import es.cboot.fruitshop.services.GetProductFromStoreUseCase;

public class OfferFreeOrangeEvery2Pears extends Offer {

	@Override
	public void init() {
		name = "1FOE2P";
		try {
			product = GetProductFromStoreUseCase.getInstance().getProduct("Pear");
		} catch (ProductNotFoundException e) {
			applies = false;
			return;
		}
		quantity = Store.getInstance().getCurrentOrder().getOrderLines().get(getProduct());
		applies = quantity >= 2;
		
		try {
			freeProducts.put(GetProductFromStoreUseCase.getInstance().getProduct("Orange"), quantity / 2);
		} catch (ProductNotFoundException e) {
			e.printStackTrace();
			System.out.println("We have no oranges today, cant apply this offer");
			applies = false;
		}
	}


	@Override
	public double getDiscountAmount() {
		if (!applies) {
			throw new IllegalStateException("Can't invoke getDiscount on an offer that doesn't apply the current order");
		}
		return 0;
	}
	
}
