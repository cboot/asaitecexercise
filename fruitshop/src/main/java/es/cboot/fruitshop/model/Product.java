package es.cboot.fruitshop.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@ToString
public class Product {

	@Getter
	@Setter
	private String name;
	
	@Getter
	@Setter
	private double price;
	
	
}
