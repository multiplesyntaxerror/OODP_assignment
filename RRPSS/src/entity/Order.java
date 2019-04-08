package entity;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class Order {
	private int orderId;
	private Date date;
	private double totalPrice;
	private List<MenuItem> order = new LinkedList<MenuItem>();
	
	public Order(int orderId, Date date, List<MenuItem> order){
		this.orderId = orderId;
		this.date = date;
		this.order = order;
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
}