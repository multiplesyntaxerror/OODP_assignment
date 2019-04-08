package entity;

import service.OrderInterface;

public class Order implements OrderInterface{
	
	public boolean createOrder(OrderItem order) {
		return false;
	}
	
	public boolean updateOrder(OrderItem order, int orderID) {
		return false;
	}
	
	public boolean deleteOrder(OrderItem order, int orderID) {
		return false;
	}
}
