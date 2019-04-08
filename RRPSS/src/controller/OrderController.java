package controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import entity.MenuItem;
import entity.Order;
import utils.Database;

public class OrderController extends Controller{

	public void run(Database db) {

		setDb(db);
		setGui(db.getGui());
		
		int choice;
		
		String[] orderOptions = {
				"Create New Order",
				"Add Item To Existing Order",
				"Remove Item From Existing Order",
				"Delete Existing Order",
				"Back"
		};
		
		getGui().displayTitle("Menu Option");
		choice = getGui().detectChoice(orderOptions);
		
		switch (choice) {
			case 1: createOrder();
				break;
			case 2: 
				break;
			case 3:
				break;
			case 4: 
				break;
			case 5:
				getGui().displayStringsB("Returning ...");
			return;
		}
	
	}
	
	public void createOrder() {
		Scanner sc = new Scanner(System.in);
		getGui().displayTitle("Creating New Order");
		List<MenuItem> newOrder = new LinkedList<MenuItem>();
		Order order = new Order(0, null, newOrder);
		MenuItem item;
		Date date = new Date();
		order.setDate(date);
		boolean dupe = false;
		//need to set orderId
		
		do {
			getGui().displayStringsB("Enter Choice To Add Item Into Order: ");
			item = getDb().getMenu().pickMenuItems("Done");
			if (item != null) {
				getGui().displayStrings("Enter Quantity: ");
				int qty = sc.nextInt();
				item.setOrderedQuantity(qty);
				
				if(order.getOrder().size() == 0) {
					order.getOrder().add(item);
				}
				else {
					for(int i = 0 ; i< order.getOrder().size(); i++) {
						if(order.getOrder().get(i).getName().equals(item.getName())) {
							order.getOrder().get(i).setOrderedQuantity(item.getOrderedQuantity());
							dupe = true;
							break;
						}
					}
					if(!dupe) {
						order.getOrder().add(item);
					}
				}
			}
		} while(item != null);
		
		order.setTotalPrice();
		/*System.out.println(order.getDate());
		System.out.println(order.getTotalPrice());
		for(int i = 0 ;i < order.getOrder().size(); i++) {
			System.out.println(order.getOrder().get(i).getName());
			System.out.println(order.getOrder().get(i).getPrice());
		}*/
		//need to save order into database
	}
	
	public void addItemToOrder(Order order, MenuItem item) {
		for(int i = 0 ; i<order.getOrder().size(); i++) {
			if(order.getOrder().get(i).getName().equals(item.getName())) {
				order.getOrder().get(i).setOrderedQuantity(item.getOrderedQuantity());
			}
			else {
				order.getOrder().add(item);
			}
		}
	}
	public void removeItemFromOrder(Order order, MenuItem item) {
		for(int i = 0 ; i<order.getOrder().size(); i++) {
			if(order.getOrder().get(i).getName().equals(item.getName())) {
				order.getOrder().get(i).setOrderedQuantity(item.getOrderedQuantity());
			}
			else {
				System.out.println("The item has not been ordered!");
			}
		}
	}
	public void deleteOrder() {
	}
}