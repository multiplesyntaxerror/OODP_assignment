package entity;

/**
 * The Class Drinks.
 */
public class Drinks extends MenuItem {

	/**
	 * Instantiates a new drinks and set type to "Drinks".
	 *
	 * @param name the name of the drink
	 * @param description the description of the drink
	 * @param price the price of the drink
	 * @param orderedQuantity the ordered quantity for order
	 */
	public Drinks(String name, String description, double price, int orderedQuantity) {
		super(name, description, price, orderedQuantity);
		this.type = "Drinks";
	}
	
}
