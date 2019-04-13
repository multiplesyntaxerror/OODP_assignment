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
	
	public OrderItem getBill(int tablenum, ArrayList<OrderItem> orderList);
	
	public List<SalesItem> getSalesReport(int choice,String userdate,ArrayList<OrderItem> orderList);
	
	public ArrayList<String> getSalesDatabase(Menu menu);

}
