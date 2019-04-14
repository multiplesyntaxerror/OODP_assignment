package entity;

import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/**
 * The Class PromoSet.
 */
public class PromoSet {
	
	/** The set ID. */
	private int setID;
	
	/** The set description. */
	private String setDescription;
	
	/** The set items. */
	private ArrayList<MenuItem> setItems;
	
	/** The set price. */
	private double setPrice;
	
	/** The ordered quantity for order. */
	private int orderedQuantity;
	
	/**
	 * Instantiates a new promotional set.
	 *
	 * @param setDescription promotional set's description
	 * @param setItems promotional set's items
	 * @param setPrice promotional set's price
	 */
	public PromoSet(String setDescription, ArrayList<MenuItem> setItems, double setPrice) {
		super();
		this.setDescription = setDescription;
		this.setItems = setItems;
		this.setPrice = setPrice;
	}

	/**
	 * Gets the promotional set's ID.
	 *
	 * @return promotional set's ID
	 */
	public int getSetID() {
		return setID;
	}
	
	/**
	 * Sets the promotional set's ID.
	 *
	 * @param setID promotional set's new ID
	 */
	public void setSetID(int setID) {
		this.setID = setID;
	}
	
	/**
	 * Gets the promotional set's description.
	 *
	 * @return promotional set's description
	 */
	public String getSetDescription() {
		return setDescription;
	}
	
	/**
	 * Sets the promotional set's description.
	 *
	 * @param setDescription promotional set's new description
	 */
	public void setSetDescription(String setDescription) {
		this.setDescription = setDescription;
	}
	
	/**
	 * Gets the promotional set's items.
	 *
	 * @return promotional set's items
	 */
	public ArrayList<MenuItem> getSetItems() {
		return setItems;
	}
	
	/**
	 * Sets the sets the items.
	 *
	 * @param setItems promotional set's new items
	 */
	public void setSetItems(ArrayList<MenuItem> setItems) {
		this.setItems = setItems;
	}
	
	/**
	 * Gets the promotional set's price.
	 *
	 * @return promotional set's price
	 */
	public double getSetPrice() {
		return setPrice;
	}
	
	/**
	 * Sets the promotional set's price.
	 *
	 * @param setPrice promotional set's new price
	 */
	public void setSetPrice(double setPrice) {
		this.setPrice = setPrice;
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
		this.orderedQuantity = orderedQuantity;
	}
	
	/**
	 * Adds the ordered quantity.
	 *
	 * @param orderedQuantity the ordered quantity
	 */
	public void addOrderedQuantity(int orderedQuantity) {
		this.orderedQuantity += orderedQuantity;
	}
	
}
