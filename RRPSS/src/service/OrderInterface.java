package service;

import entity.MenuItem;
import entity.OrderItem;
import entity.PromoSet;

/**
 * The Interface OrderInterface.
 */
public interface OrderInterface {
	
	public boolean createOrder(OrderItem order);
	
	public boolean updateMenuItemOrder(MenuItem item, int orderIs);
	
	public boolean removeMenuItemOrder(int orderId, int choice, int qty);
	
	public boolean updatePromoSetOrder(PromoSet set, int orderID);
	
	public boolean removePromoSetOrder(int orderId, int choice, int qty);

}
