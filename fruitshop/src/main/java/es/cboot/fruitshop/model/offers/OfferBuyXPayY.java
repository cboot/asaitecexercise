package es.cboot.fruitshop.model.offers;

import es.cboot.fruitshop.model.Product;
import es.cboot.fruitshop.model.Store;

public class OfferBuyXPayY extends Offer {

	private Product itemToBuy;

	private int amountToBuy;

	private int amountToPay;

	public OfferBuyXPayY(Product youBuyThisProduct, int youBuyThisMany, int youPayThisMany) {
		itemToBuy = youBuyThisProduct;
		amountToBuy = youBuyThisMany;
		amountToPay = youPayThisMany;
	}

	public void init() {
		name = "BXPY";
		product = itemToBuy;
		quantity = Store.getInstance().getCurrentOrder().getOrderLines().get(getProduct());
		applies = quantity >= amountToBuy;
	}

	@Override
	public double getDiscountAmount() {
		if (!applies) {
			throw new IllegalStateException(
					"Can't invoke getDiscount on an offer that doesn't apply the current order");
		}
		return quantity * getProduct().getPrice()
				- ((double) quantity * (double) amountToPay / (double) amountToBuy * getProduct().getPrice());
	}

}
