package service;

import entity.MenuItem;
import entity.OrderItem;
import entity.PromoSet;

/**
 * The Interface OrderInterface.
 */
public interface OrderInterface {
	
	/**
	 * Creates an order.
	 *
	 * @param order the order
	 * @return true, if successful
	 */
	public boolean createOrder(OrderItem order);
	
	/**
	 * Update menu item order.
	 *
	 * @param item the item
	 * @param orderIs the order is
	 * @return true, if successful
	 */
	public boolean updateMenuItemOrder(MenuItem item, int orderIs);
	
	/**
	 * Removes a menu item order.
	 *
	 * @param orderId the order id
	 * @param choice the choice
	 * @param qty the quantity
	 * @return true, if successful
	 */
	public boolean removeMenuItemOrder(int orderId, int choice, int qty);
	
	/**
	 * Update promotional set order.
	 *
	 * @param set the set
	 * @param orderID the order ID
	 * @return true, if successful
	 */
	public boolean updatePromoSetOrder(PromoSet set, int orderID);
	
	/**
	 * Removes a promotional set order.
	 *
	 * @param orderId the order id
	 * @param choice the choice
	 * @param qty the quantity
	 * @return true, if successful
	 */
	public boolean removePromoSetOrder(int orderId, int choice, int qty);

}
