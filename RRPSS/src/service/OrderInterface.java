package service;

import entity.OrderItem;

/**
 * The Interface OrderInterface.
 */
public interface OrderInterface {
	
	public boolean createOrder(OrderItem order);
	
	public boolean updateOrder(OrderItem order, int orderID);
	
	public boolean deleteOrder(OrderItem order, int orderID);
}
