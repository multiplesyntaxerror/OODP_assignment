package controller;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import entity.Dessert;
import entity.Drinks;
import entity.MainCourse;
import entity.MenuItem;
import entity.OrderItem;
import entity.PromoSet;
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
				"View Existing Order",
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
				break;
			case 6:
				getGui().displayStringsB("Returning ...");
			return;
		}
	
	}
	
	public void createOrder() {
		Scanner sc = new Scanner(System.in);
		getGui().displayTitle("Creating New Order");
		List<MenuItem> alaCarteOrder = new LinkedList<MenuItem>();
		List<PromoSet> promoSetOrder = new LinkedList<PromoSet>();
		OrderItem order = new OrderItem(0, null, alaCarteOrder, promoSetOrder);
		MenuItem item;
		PromoSet set = null;
		Date date = new Date();
		order.setDate(date);
		boolean dupe = false;
		//need to set orderId
		
		getGui().displayStringsB("Order Ala Carte Or Promo Set : ");
		System.out.println("1 : Ala Carte");
		System.out.println("2 : Promo Set");
		int choice = sc.nextInt();
		
		switch(choice) {
		case 1:
			do {
				getGui().displayStringsB("Enter Choice To Add Item Into Order: ");
				item = getDb().getMenu().pickMenuItems("Done");
				if (item != null) {
					getGui().displayStrings("Enter Quantity: ");
					int qty = sc.nextInt();
					item.setOrderedQuantity(qty);
					int qtyOrdered = item.getOrderedQuantity();
					
					if(order.getOrder().size() == 0) {
						order.getOrder().add(item);
					}
					else {
						for(int i = 0 ; i< order.getOrder().size(); i++) {
							if(order.getOrder().get(i).getName().equals(item.getName())) {
								order.getOrder().get(i).setOrderedQuantity(qtyOrdered);
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
			break;
		case 2: 
			do {
				getGui().displayStringsB("Enter Choice To Add Item Into Order: ");
				set = getDb().getMenu().pickPromoSet("Done");
				if (set != null) {
					getGui().displayStrings("Enter Quantity: ");
					int qty = sc.nextInt();
					//set.setOrderedQuantity(qty);
					//int setQtyOrdered = set.getOrderedQuantity();
					
					if(order.getPromoSet().size() == 0) {
						order.getPromoSet().add(set);
					}
					else {
						for(int i = 0 ; i< order.getPromoSet().size(); i++) {
							if(order.getPromoSet().get(i).getSetID() == set.getSetID()) {
								//order.getOPromoSet().get(i).setOrderedQuantity(setQtyOrdered);
								dupe = true;
								break;
							}
						}
						if(!dupe) {
							order.getPromoSet().add(set);
						}
					}
				}
			} while(set != null);
			
			order.setTotalPrice();
			break;
		}
	}
	
	public void addItemToOrder(OrderItem order, MenuItem item) {
		for(int i = 0 ; i<order.getOrder().size(); i++) {
			if(order.getOrder().get(i).getName().equals(item.getName())) {
				order.getOrder().get(i).setOrderedQuantity(item.getOrderedQuantity());
			}
			else {
				order.getOrder().add(item);
			}
		}
	}
	public void removeItemFromOrder(OrderItem order, MenuItem item) {
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
	
	public void viewOrder() {
		
	}
}