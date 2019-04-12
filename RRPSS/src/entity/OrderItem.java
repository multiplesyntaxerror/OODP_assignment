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
	private ArrayList<MenuItem> alaCart = new ArrayList<MenuItem>();
	private ArrayList<PromoSet> promoSet = new ArrayList<PromoSet>();
	private String printedInvoice = "false";
	
	public OrderItem(int orderId, String date, ArrayList<MenuItem> order, ArrayList<PromoSet> promoSet, Staff staff){
		this.orderId = orderId;
		this.date = date;
		this.alaCart = order;
		this.promoSet = promoSet;
		this.staff = staff;
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
		for(int i = 0 ; i<alaCart.size(); i++) {
			totalPrice += (alaCart.get(i).getPrice() * alaCart.get(i).getOrderedQuantity());
		}
		for(int j = 0; j<promoSet.size();j++) {
			totalPrice += promoSet.get(j).getSetPrice() * promoSet.get(j).getOrderedQuantity();
		}
	}
	
	public ArrayList<MenuItem> getAlaCart() {
		return alaCart;
	}
	
	public void setAlaCart(ArrayList<MenuItem> order) {
		this.alaCart = order;
	}

	public ArrayList<PromoSet> getPromoSet() {
		return promoSet;
	}

	public void setPromoSet(ArrayList<PromoSet> set) {
		this.promoSet = set;
	}

	public String getPrintedInvoice() {
		return printedInvoice;
	}
	
	public void setPrintedInvoice(String printedInvoice) {
		this.printedInvoice = printedInvoice;
	}
}