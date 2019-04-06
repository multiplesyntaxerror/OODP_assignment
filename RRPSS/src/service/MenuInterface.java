package service;

import entity.MenuItem;
import entity.PromoSet;

/**
 * The Interface For Menu.
 */
public interface MenuInterface {
	
	/**
	 * Creates a menu item.
	 *
	 * @param item new menu item
	 * @return true, if creation successful
	 */
	public boolean createMenuItem(MenuItem item);
	
	/**
	 * Update name, description and price of a menu item.
	 *
	 * @param item the selected item
	 * @param newName item's new name
	 * @param newDescription item's new description
	 * @param newPrice item's new price
	 * @return true, if update successful
	 */
	public boolean updateMenuItem(MenuItem item, String newName, String newDescription, double newPrice);
	
	/**
	 * Delete a menu item.
	 *
	 * @param item the selected item
	 * @return true, if deletion successful
	 */
	public boolean deleteMenuItem(MenuItem item);
	
	/**
	 * Creates a promotional set.
	 *
	 * @param set new promotional set
	 * @return true, if creation successful
	 */
	public boolean createPromoItem(PromoSet set);
	
	/**
	 * Update description and price of a promotional set.
	 *
	 * @param set the selected set
	 * @param newDescription promotional set's new description
	 * @param newPrice promotional set's new price
	 * @return true, if update successful
	 */
	public boolean updatePromoItem(PromoSet set, String newDescription, double newPrice);
	
	/**
	 * Delete a promotional set.
	 *
	 * @param set the selected set
	 * @return true, if deletion successful
	 */
	public boolean deletePromoItem(PromoSet set);

}
