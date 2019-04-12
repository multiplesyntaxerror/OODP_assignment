package service;

import entity.MenuItem;
import entity.OrderItem;
import entity.PromoSet;

/**
 * The Interface OrderInterface.
 */
public interface OrderInterface {
	
	public boolean createOrder(OrderItem order);
	
	public boolean updateMenuItemOrder(MenuItem item, int orderID);
	
	public boolean removeMenuItemOrder(int orderID, int choice, int qty);
	
	public boolean updatePromoSetOrder(PromoSet set, int orderID);
	
	public boolean removePromoSetOrder(int orderID, int choice, int qty);
	
	public boolean printOrder();
	
	public void printSpecificOrder(int orderID);
	
}
