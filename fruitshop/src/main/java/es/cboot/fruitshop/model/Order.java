package es.cboot.fruitshop.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import es.cboot.fruitshop.model.offers.Offer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
public class Order {

	@Getter
	private Map<Product, Integer> orderLines = new HashMap<>();
	
	@Getter
	@Setter
	private double amountSavedFromOffers = 0;
	
	@Getter
	private List<Offer> appliedOffers = new ArrayList<>();
}
