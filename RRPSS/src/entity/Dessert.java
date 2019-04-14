package entity;

// TODO: Auto-generated Javadoc
/**
 * The Class Dessert.
 */
public class Dessert extends MenuItem {
	
	/**
	 * Instantiates a new dessert.
	 *
	 * @param name the name of the dessert
	 * @param description the description of the dessert
	 * @param price the price of the dessert
	 * @param orderedQuantity the ordered quantity for order
	 */
	public Dessert(String name, String description, double price, int orderedQuantity) {
		super(name, description, price, orderedQuantity);
		this.type = "Dessert";
	}
}
