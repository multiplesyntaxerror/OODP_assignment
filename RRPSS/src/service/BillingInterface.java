package service;

import java.util.ArrayList;

import entity.OrderItem;

/**
 * The Interface BillingInterface.
 */
public interface BillingInterface {
	
	public int printBillInvoice(int tablenum, ArrayList<OrderItem> orderList);
	
	public boolean printSalesReport(int choice,String userdate,ArrayList<OrderItem> orderList);

}
