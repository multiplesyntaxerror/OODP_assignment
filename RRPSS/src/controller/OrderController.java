package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import entity.MenuItem;
import entity.OrderItem;
import entity.PromoSet;
import entity.Staff;
import utils.Database;

public class OrderController extends Controller{

	public void run(Database db) throws Exception {

		setDb(db);
		setGui(db.getGui());
		
		int choice;
		
		String[] orderOptions = {
				"Create New Order",
				"Add Item To Existing Order",
				"Remove Item From Existing Order",
				"View Specific Order",
				"View Existing Order",
				"Back"
		};
		
		getGui().displayTitle("Menu Option");
		choice = getGui().detectChoice(orderOptions);
		
		switch (choice) {
			case 1: createOrder();
				break;
			case 2: addItemToExistingOrder();
				break;
			case 3: removeItemFromExistingOrder();
				break;
			case 4: viewSpecificOrder();
				break;
			case 5: viewOrder();
				break;
			case 6:
				getGui().displayStringsB("Returning ...");
			return;
		}
	
	} 
	
	public void createOrder() {
		Scanner sc = new Scanner(System.in);
		getGui().displayTitle("Creating New Order");
		ArrayList<MenuItem> alaCarteOrder = new ArrayList<MenuItem>();
		ArrayList<PromoSet> promoSetOrder = new ArrayList<PromoSet>();
		Staff server = new Staff();
		getGui().displayStringsB("Enter server name: ");
		server.setName(sc.nextLine());
		OrderItem order = new OrderItem(0, null, alaCarteOrder, promoSetOrder,server);
		MenuItem item;
		PromoSet set;
		String pattern = "dd-MM-yyyy hh:mm a";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String date = simpleDateFormat.format(new Date());
		order.setDate(date);
		int choice;
		
		do {
			
			getGui().displayStringsB("Order Ala Carte Or Promo Set : ");
			System.out.println("1 : Ala Carte");
			System.out.println("2 : Promo Set");
			System.out.println("3 : Done");
			choice = sc.nextInt();
			
			switch(choice) {
			case 1:
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
							addItemToNewOrder(order,item);						
						}
					}
				} while(item != null);
				break;
				
			case 2: 
				do {
					getGui().displayStringsB("Enter Choice To Add Item Into Order: ");
					set = getDb().getMenu().pickPromoSet("Done");
					if (set != null) {
						getGui().displayStrings("Enter Quantity: ");
						int qty = sc.nextInt();
						set.setOrderedQuantity(qty);
					
						if(order.getPromoSet().size() == 0) {
							order.getPromoSet().add(set);
						}
						else {
							addPromoSetToNewOrder(order,set);
						}
					}
				} while(set != null);
				break;
			case 3:
				if(order.getOrder().size() !=0 || order.getPromoSet().size()!=0) {
					boolean created = getDb().getOrder().createOrder(order);
					if(created == true) {
						getGui().displayStringsB("Order Created!");
					}
					else if(created != true){
						getGui().displayStringsB("Order not Created!");
					}
				}
				else {
					getGui().displayStringsB("Order is empty, returning to system Menu!");					
				}
				break;
			}
		}while(choice == 1 || choice == 2);
	}
	
	public void removeItemFromExistingOrder() {
		Scanner sc = new Scanner(System.in);
		getGui().displayTitle("Update Exisiting Orders");
		getGui().displayStringsB("Enter Order ID: ");
		int orderId = sc.nextInt();
		if(orderId <= getDb().getOrder().findOrderID()) {
			getGui().displayStringsB("Choose item to update ");
			String[] orderlist = getDb().getOrder().getSpecificOrder(orderId);
			int choice = getGui().detectChoice(orderlist);
			getGui().displayTitle("Enter quantity to remove: ");
			int qtyToRemove = sc.nextInt();

			if (choice <= getDb().getOrder().getAllOrders().get(orderId - 1).getOrder().size()) {
				boolean removed = getDb().getOrder().removeMenuItemOrder(orderId, choice, qtyToRemove);
				if(removed == true) {
					getGui().displayStringsB("Item Successfully Removed!");
				}
			} else {
				choice = choice - getDb().getOrder().getAllOrders().get(orderId - 1).getOrder().size();
				boolean removedP = getDb().getOrder().removePromoSetOrder(orderId, choice, qtyToRemove);
				if(removedP == true) {
					getGui().displayStringsB("PromoSet Successfully Removed!");
				}
			}
		}
		else {
			getGui().displayStringsB("Order Does not exist!");
		}
		
	}
	
	public void addItemToExistingOrder() {
		Scanner sc = new Scanner(System.in);
		MenuItem item;
		PromoSet set;
		getGui().displayTitle("Adding Item To Exisiting Orders");
		getGui().displayStringsB("Enter Order ID: ");
		int orderId = sc.nextInt();
		if(orderId <= getDb().getOrder().findOrderID()) {
			int choice;
			do {
				getGui().displayStringsB("Add Ala Carte Or Promo Set : ");
				System.out.println("1 : Ala Carte");
				System.out.println("2 : Promo Set");
				System.out.println("3 : Done");
				choice = sc.nextInt();
				switch (choice) {
				case 1:
					do {
						getGui().displayStringsB("Enter Choice To Add Item Into Order: ");
						item = getDb().getMenu().pickMenuItems("Done");
						if (item != null) {
							getGui().displayStrings("Enter Quantity: ");
							int qty = sc.nextInt();
							item.setOrderedQuantity(qty);
							boolean updated = getDb().getOrder().updateMenuItemOrder(item, orderId);
							if(updated == true) {
								getGui().displayStringsB("Order Updated!");
							}
							else {
								getGui().displayStringsB("Order Not Updated!");
							}
						}
					} while (item != null);
					break;

				case 2:
					do { 
						getGui().displayStringsB("Enter Choice To Add Item Into Order: ");
						set = getDb().getMenu().pickPromoSet("Done");
						if (set != null) {
							getGui().displayStrings("Enter Quantity: ");
							int qty = sc.nextInt();
							set.setOrderedQuantity(qty);
							boolean updated = getDb().getOrder().updatePromoSetOrder(set, orderId);
							if(updated == true) {
								getGui().displayStringsB("Order Updated!");
							}
							else {
								getGui().displayStringsB("Order Not Updated!");
							}
						} 
					} while (set != null);
					break; 
				}
			} while (choice == 1 || choice == 2);
		}
		else {
			getGui().displayStringsB("Order Does not exist!");
		}
	}
	
	public void addItemToNewOrder(OrderItem order, MenuItem item) {
		boolean dupe = false;
		for(int i = 0 ; i<order.getOrder().size(); i++) {
			if(order.getOrder().get(i).getName().equals(item.getName())) {
				order.getOrder().get(i).addOrderedQuantity(item.getOrderedQuantity());
				dupe = true;
				break;
			}
		}
		if(dupe == false) {
			order.getOrder().add(item);
		}
	}
	
	public void addPromoSetToNewOrder(OrderItem order, PromoSet promoSet){
		boolean dupe = false;
		for(int i = 0 ; i<order.getPromoSet().size(); i++) {
			if(order.getPromoSet().get(i).getSetID()==(promoSet.getSetID())) {
				order.getPromoSet().get(i).addOrderedQuantity(promoSet.getOrderedQuantity());
				dupe = true;
				break;
			}
		}
		if(dupe == false) {
			order.getPromoSet().add(promoSet);
		}
	}
	
	
	public void viewOrder(){
		boolean updated = getDb().getOrder().printOrder();
	}
	
	public void viewSpecificOrder() {
		Scanner sc = new Scanner(System.in);
		getGui().displayStringsB("Enter OrderID: ");
		int orderId = sc.nextInt();
		if(orderId <= getDb().getOrder().findOrderID()) {
			getDb().getOrder().printSpecificOrder(orderId);
		}
		else {
			getGui().displayStringsB("Order Does not exist!");
		}
	}
}