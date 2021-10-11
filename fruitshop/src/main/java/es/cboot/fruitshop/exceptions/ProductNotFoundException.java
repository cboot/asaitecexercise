package es.cboot.fruitshop.exceptions;

public class ProductNotFoundException extends Exception {

	private static final long serialVersionUID = -4428299763095220553L;

	public ProductNotFoundException(String msg) {
		super(msg);
	}
}
