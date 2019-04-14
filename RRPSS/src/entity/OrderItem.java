package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class OrderItem.
 */
public class OrderItem {
	
	/** The order id. */
	private int orderId;
	
	/** The table id. */
	private int tableId;
	
	/** The staff. */
	private Staff staff;
	
	/** The date. */
	private String date;
	
	/** The total price. */
	private double totalPrice;
	
	/** The ala carte. */
	private ArrayList<MenuItem> alaCarte = new ArrayList<MenuItem>();
	
	/** The promo set. */
	private ArrayList<PromoSet> promoSet = new ArrayList<PromoSet>();
	
	/** The printed invoice. */
	private Boolean printedInvoice = false;
	
	
	/**
	 * Instantiates a new order item.
	 *
	 * @param orderId the order id
	 * @param tableId the table id
	 * @param staff the staff
	 * @param date the date
	 * @param totalPrice the total price
	 * @param alaCart the ala cart
	 * @param promoSet the promo set
	 * @param printedInvoice the printed invoice
	 */
	public OrderItem(int orderId, int tableId, Staff staff, String date, double totalPrice, ArrayList<MenuItem> alaCart, ArrayList<PromoSet> promoSet, Boolean printedInvoice) {
		this.orderId = orderId;
		this.tableId = tableId;
		this.staff = staff;
		this.date = date;
		this.totalPrice = totalPrice;
		this.alaCarte = alaCart;
		this.promoSet = promoSet;
		this.printedInvoice = printedInvoice;
	}
	
	/**
	 * Gets the order id.
	 *
	 * @return the order id
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * Sets the order id.
	 *
	 * @param orderId the new order id
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/**
	 * Gets the table id.
	 *
	 * @return the table id
	 */
	public int getTableId() {
		return tableId;
	}

	/**
	 * Sets the table id.
	 *
	 * @param tableId the new table id
	 */
	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	/**
	 * Gets the staff.
	 *
	 * @return the staff
	 */
	public Staff getStaff() {
		return staff;
	}

	/**
	 * Sets the staff.
	 *
	 * @param staff the new staff
	 */
	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
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
	 * Gets the ala carte.
	 *
	 * @return the ala carte
	 */
	public ArrayList<MenuItem> getAlaCarte() {
		return alaCarte;
	}
	
	/**
	 * Sets the ala carte.
	 *
	 * @param order the new ala carte
	 */
	public void setAlaCarte(ArrayList<MenuItem> order) {
		this.alaCarte = order;
	}

	/**
	 * Gets the promo set.
	 *
	 * @return the promo set
	 */
	public ArrayList<PromoSet> getPromoSet() {
		return promoSet;
	}

	/**
	 * Sets the promo set.
	 *
	 * @param set the new promo set
	 */
	public void setPromoSet(ArrayList<PromoSet> set) {
		this.promoSet = set;
	}

	/**
	 * Gets the printed invoice.
	 *
	 * @return the printed invoice
	 */
	public Boolean getPrintedInvoice() {
		return printedInvoice;
	}
	
	/**
	 * Sets the printed invoice.
	 */
	public void setPrintedInvoice() {
		this.printedInvoice = !printedInvoice;
	}
}