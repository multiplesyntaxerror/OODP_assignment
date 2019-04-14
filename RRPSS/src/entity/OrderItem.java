package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * The Class OrderItem.
 */
public class OrderItem {
	
	/** The order ID. */
	private int orderId;
	
	/** The table ID. */
	private int tableId;
	
	/** The staff's name. */
	private String staffName;
	
	/** The date order was created. */
	private String date;
	
	/** The total price of order. */
	private double totalPrice;
	
	/** The list of ala carte in order. */
	private ArrayList<MenuItem> alaCarte = new ArrayList<MenuItem>();
	
	/** The list of promotional set in order. */
	private ArrayList<PromoSet> promoSet = new ArrayList<PromoSet>();
	
	/** The receipt billed or not. */
	private Boolean printedInvoice = false;
	
	
	/**
	 * Instantiates a new order item.
	 *
	 * @param orderId the order ID
	 * @param tableId the table ID
	 * @param staffName the staff name
	 * @param date the order date
	 * @param totalPrice the total price
	 * @param alaCarte the list of ala carte
	 * @param promoSet the list of promotional set
	 * @param printedInvoice the printed invoice
	 */
	public OrderItem(int orderId, int tableId, String staffName, String date, double totalPrice, ArrayList<MenuItem> alaCarte, ArrayList<PromoSet> promoSet, Boolean printedInvoice) {
		this.orderId = orderId;
		this.tableId = tableId;
		this.staffName = staffName;
		this.date = date;
		this.totalPrice = totalPrice;
		this.alaCarte = alaCarte;
		this.promoSet = promoSet;
		this.printedInvoice = printedInvoice;
	}
	
	/**
	 * Gets the order ID.
	 *
	 * @return the order ID
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * Sets the order ID.
	 *
	 * @param orderId the new order ID
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/**
	 * Gets the table ID.
	 *
	 * @return the table ID
	 */
	public int getTableId() {
		return tableId;
	}

	/**
	 * Sets the table ID.
	 *
	 * @param tableId the new table ID
	 */
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	/**
	 * Gets the staff name.
	 *
	 * @return the staff name
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * Sets the staff name.
	 *
	 * @param staffName the new staff name
	 */
	public void setstaffName(String staffName) {
		this.staffName = staffName;
	}

	/**
	 * Gets the order date.
	 *
	 * @return the order date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the order date.
	 *
	 * @param date the new order date
	 */
	public void setDate(String date) {
		this.date = date;
	}
	
	/**
	 * Gets the total price.
	 *
	 * @return the total price
	 */
	public double getTotalPrice() {
		return totalPrice;
	}
	
	/**
	 * Sets the total price.
	 */
	public void setTotalPrice() {
		for(int i = 0 ; i<alaCarte.size(); i++) {
			totalPrice += (alaCarte.get(i).getPrice() * alaCarte.get(i).getOrderedQuantity());
		}
		for(int j = 0; j<promoSet.size();j++) {
			totalPrice += promoSet.get(j).getSetPrice() * promoSet.get(j).getOrderedQuantity();
		}
	}
	
	/**
	 * Gets the list of ala carte.
	 *
	 * @return the list ala carte
	 */
	public ArrayList<MenuItem> getAlaCarte() {
		return alaCarte;
	}
	
	/**
	 * Sets the list of ala carte.
	 *
	 * @param order the new list of ala carte
	 */
	public void setAlaCarte(ArrayList<MenuItem> order) {
		this.alaCarte = order;
	}

	/**
	 * Gets the list of promotional set.
	 *
	 * @return the list of promotional set
	 */
	public ArrayList<PromoSet> getPromoSet() {
		return promoSet;
	}

	/**
	 * Sets the list of promotional set.
	 *
	 * @param set the new list of promotional set
	 */
	public void setPromoSet(ArrayList<PromoSet> set) {
		this.promoSet = set;
	}

	/**
	 * Gets the value of printed receipt.
	 *
	 * @return the value of printed receipt.
	 */
	public Boolean getPrintedInvoice() {
		return printedInvoice;
	}
	
	/**
	 * Sets the value of printed receipt.
	 */
	public void setPrintedInvoice() {
		this.printedInvoice = !printedInvoice;
	}
}