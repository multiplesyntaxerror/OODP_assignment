package service;

import java.util.ArrayList;
import java.util.List;

import entity.Menu;
import entity.OrderItem;
import entity.SalesItem;

/**
 * The Interface BillingInterface.
 */
public interface BillingInterface {
	
	/**
	 * Gets the bill.
	 *
	 * @param tableNum the table number
	 * @param orderList the order list
	 * @return the bill
	 */
	public OrderItem getBill(int tableNum, ArrayList<OrderItem> orderList);
	
	/**
	 * Gets the sales report.
	 *
	 * @param choice the choice
	 * @param userDate the input date
	 * @param orderList the order list
	 * @return the sales report
	 */
	public List<SalesItem> getSalesReport(int choice, String userDate, ArrayList<OrderItem> orderList);
	
	/**
	 * Gets the sales database.
	 *
	 * @param menu the menu
	 * @return the sales database
	 */
	public ArrayList<String> getSalesDatabase(Menu menu);

}
