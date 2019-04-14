package entity;

// TODO: Auto-generated Javadoc
/**
 * The Class SalesItem.
 */
public class SalesItem {

	/** The itemname. */
	private String itemname;
	
	/** The quantity sold. */
	private int quantitySold;
	
	/** The price. */
	private double price;
	
	/** The is promo. */
	private boolean isPromo;

	/**
	 * Checks if is promo.
	 *
	 * @return true, if is promo
	 */
	public boolean isPromo() {
		return isPromo;
	}

	/**
	 * Sets the promo.
	 *
	 * @param isPromo the new promo
	 */
	public void setPromo(boolean isPromo) {
		this.isPromo = isPromo;
	}

	/**
	 * Gets the itemname.
	 *
	 * @return the itemname
	 */
	public String getItemname() {
		return itemname;
	}

	/**
	 * Sets the itemname.
	 *
	 * @param itemname the new itemname
	 */
	public void setItemname(String itemname) {
		this.itemname = itemname;
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
