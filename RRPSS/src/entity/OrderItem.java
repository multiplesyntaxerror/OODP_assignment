package entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class OrderItem {
	private int orderId;
	private Date date;
	private double totalPrice;
	private ArrayList<MenuItem> order = new ArrayList<MenuItem>();
	private ArrayList<PromoSet> set = new ArrayList<PromoSet>();
	private Staff staff;
	
	public OrderItem(int orderId, Date date, ArrayList<MenuItem> order, ArrayList<PromoSet> promoSet, Staff staff){
		this.orderId = orderId;
		this.date = date;
		this.order = order;
		this.set = promoSet;
		this.staff = staff;
	} 
	
	public int getOrderId() {
		return orderId;
	}
	public void setOrderId(int n) {
		orderId = n;
	}
	
	public Date getDate() { 
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice() {
		for(int i = 0 ; i<order.size(); i++) {
			totalPrice += (order.get(i).getPrice() * order.get(i).getOrderedQuantity());
		}
		for(int j = 0; j<set.size();j++) {
			totalPrice += set.get(j).getSetPrice() * set.get(j).getOrderedQuantity();
		}
	}
	
	public ArrayList<MenuItem> getOrder(){
		return order;
	}
	public void setOrder(ArrayList<MenuItem> order){
		this.order = order;
	}
	
	public ArrayList<PromoSet> getPromoSet() {
		return this.set;
	}
	public void setPromoSet(ArrayList<PromoSet> promoSet) {
		this.set = promoSet;
	}
	
	public Staff getStaff() {
		return staff;
	}
	public void setStaff(Staff staff) {
		this.staff = staff;
	}
}