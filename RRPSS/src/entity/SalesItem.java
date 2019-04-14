package entity;

/**
 * The Class SalesItem.
 */
public class SalesItem {

	/** The item name. */
	private String itemName;
	
	/** The quantity sold. */
	private int quantitySold;
	
	/** The price. */
	private double price;
	
	/** Check if promotional. */
	private boolean isPromo;

	/**
	 * Checks if is promotional.
	 *
	 * @return true, if is promotional
	 */
	public boolean isPromo() {
		return isPromo;
	}

	/**
	 * Sets the promotional.
	 *
	 * @param isPromo the new promotional
	 */
	public void setPromo(boolean isPromo) {
		this.isPromo = isPromo;
	}

	/**
	 * Gets the item name.
	 *
	 * @return the item name
	 */
	public String getItemname() {
		return itemName;
	}

	/**
	 * Sets the item name.
	 *
	 * @param itemname the new item name
	 */
	public void setItemname(String itemname) {
		this.itemName = itemname;
	}

	/**
	 * Gets the quantity sold.
	 *
	 * @return the quantity sold
	 */
	public int getQuantitySold() {
		return quantitySold;
	}

	/**
	 * Sets the quantity sold.
	 *
	 * @param quantitySold the new quantity sold
	 */
	public void setQuantitySold(int quantitySold) {
		this.quantitySold = quantitySold;
	}

	/**
	 * Gets the price.
	 *
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Sets the price.
	 *
	 * @param price the new price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

}
