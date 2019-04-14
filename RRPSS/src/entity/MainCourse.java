package entity;

/**
 * The Class MainCourse.
 */
public class MainCourse extends MenuItem {
	
	/**
	 * Instantiates a new main course and set type to "Main Course".
	 *
	 * @param name the name of the main course
	 * @param description the description of the main course
	 * @param price the price of the main course
	 * @param orderedQuantity the ordered quantity for order
	 */
	public MainCourse(String name, String description, double price, int orderedQuantity) {
		super(name, description, price, orderedQuantity);
		super.type = "Main Course";
	}

}
