package entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class OrderItem {
	private int orderId;
	private Date date;
	private double totalPrice;
	private List<MenuItem> order = new LinkedList<MenuItem>();
	private List<PromoSet> set = new LinkedList<PromoSet>();
	//needs a staff class
	
	public OrderItem(int orderId, Date date, List<MenuItem> order, List<PromoSet> promoSet){
		this.orderId = orderId;
		this.date = date;
		this.order = order;
		this.set = promoSet;
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
	}
	
	public List<MenuItem> getOrder(){
		return order;
	}
	public void setOrder(List<MenuItem> order){
		this.order = order;
	}
	
	public List<PromoSet> getPromoSet() {
		return this.set;
	}
	public void setPromoSet(List<PromoSet> promoSet) {
		this.set = promoSet;
	}
}