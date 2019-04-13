package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class OrderItem {
	
	private int orderId;
	private int tableId;
	private Staff staff;
	private String date;
	private double totalPrice;
	private ArrayList<MenuItem> alaCarte = new ArrayList<MenuItem>();
	private ArrayList<PromoSet> promoSet = new ArrayList<PromoSet>();
	private Boolean printedInvoice = false;
	
	
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
	
	public int getOrderId() {
		return orderId;
	}

	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableId) {
		this.tableId = tableId;
	}

	public Staff getStaff() {
		return staff;
	}

	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public void setTotalPrice() {
		for(int i = 0 ; i<alaCarte.size(); i++) {
			totalPrice += (alaCarte.get(i).getPrice() * alaCarte.get(i).getOrderedQuantity());
		}
		for(int j = 0; j<promoSet.size();j++) {
			totalPrice += promoSet.get(j).getSetPrice() * promoSet.get(j).getOrderedQuantity();
		}
	}
	
	public ArrayList<MenuItem> getAlaCarte() {
		return alaCarte;
	}
	
	public void setAlaCarte(ArrayList<MenuItem> order) {
		this.alaCarte = order;
	}

	public ArrayList<PromoSet> getPromoSet() {
		return promoSet;
	}

	public void setPromoSet(ArrayList<PromoSet> set) {
		this.promoSet = set;
	}

	public Boolean getPrintedInvoice() {
		return printedInvoice;
	}
	
	public void setPrintedInvoice() {
		this.printedInvoice = !printedInvoice;
	}
}