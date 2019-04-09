package entity;


/**
 * The Class MenuItem.
 */
public abstract class MenuItem {
	
	/** The name of the item. */
	private String name;
	
	/** The description of the item. */
	private String description;
	
	/** The type of the item. */
	protected String type;
	
	/** The price of the item. */
	private double price;
	
	/** The ordered quantity for order. */
	private int orderedQuantity;

	/**
	 * Instantiates a new menu item.
	 *
	 * @param name the name of the item
	 * @param description the description of the item
	 * @param price the price of the item
	 * @param orderedQuantity the ordered quantity for order
	 */
	public MenuItem(String name, String description, double price, int orderedQuantity) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.orderedQuantity = orderedQuantity;
	}

	/**
	 * Gets the item's name.
	 *
	 * @return item's name
	 */ 
	public String getName() {
		return name;
	}
	
	/**
	 * Sets the name of the item..
	 *
	 * @param name item's new name
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Gets the item's description.
	 *
	 * @return item's description.
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the item's description.
	 *
	 * @param description item's new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the item's type.
	 *
	 * @return item's type
	 */
	public String getType() {
		return type;
	}

	/**
	 * Sets the item's type.
	 *
	 * @param type item's new type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Gets the item's price.
	 *
	 * @return item's price
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Sets the item's price.
	 *
	 * @param price item's new price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Gets the ordered quantity.
	 *
	 * @return the ordered quantity
	 */
	public int getOrderedQuantity() {
		return orderedQuantity;
	}

	/**
	 * Sets the ordered quantity.
	 *
	 * @param orderedQuantity the new ordered quantity
	 */
	public void setOrderedQuantity(int orderedQuantity) {
		this.orderedQuantity += orderedQuantity;
	}
	
	
	
}
