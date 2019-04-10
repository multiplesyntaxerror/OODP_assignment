package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

import entity.Dessert;
import entity.Drinks;
import entity.MainCourse;
import entity.MenuItem;
import entity.Order;
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
				"Update Order",
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
			case 2: updateOrder();
				break;
			case 3:
				break;
			case 4: 
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
							addItemToOrder(order,item);						
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
							addPromoSetToOrder(order,set);
						}
					}
				} while(set != null);
				break;
			case 3:
				if(order.getOrder().size() !=0 || order.getPromoSet().size()!=0) {
					
//					Order od = new Order();
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
	
	public void updateOrder() {
		Scanner sc = new Scanner(System.in);
		getGui().displayTitle("Update Exisiting Orders");
		getGui().displayStringsB("Enter Order ID: ");
		int orderId = sc.nextInt();
		getGui().displayStringsB("Choose item to update ");
		String[] orderlist = getDb().getOrder().printSpecificOrder(orderId);
		int choice = getGui().detectChoice(orderlist);
		
		
	}
	
	public void addItemToOrder(OrderItem order, MenuItem item) {
		boolean dupe = false;
		for(int i = 0 ; i<order.getOrder().size(); i++) {
			if(order.getOrder().get(i).getName().equals(item.getName())) {
				order.getOrder().get(i).setOrderedQuantity(item.getOrderedQuantity());
				dupe = true;
				break;
			}
		}
		if(dupe == false) {
			order.getOrder().add(item);
		}
	}
	public void removeItemFromOrder(OrderItem order, MenuItem item) {
		boolean dupe= false;
		for(int i = 0 ; i<order.getOrder().size(); i++) {
			if(order.getOrder().get(i).getName().equals(item.getName())) {
				order.getOrder().get(i).setOrderedQuantity(item.getOrderedQuantity());
				dupe = true;
				break;
			}
		}
		if(dupe == false) {
			System.out.println("The item has not been ordered!");
		}
	}
	
	public void addPromoSetToOrder(OrderItem order, PromoSet promoSet){
		boolean dupe = false;
		for(int i = 0 ; i<order.getPromoSet().size(); i++) {
			if(order.getPromoSet().get(i).getSetID()==(promoSet.getSetID())) {
				order.getPromoSet().get(i).setOrderedQuantity(promoSet.getOrderedQuantity());
				dupe = true;
				break;
			}
		}
		if(dupe == false) {
			order.getPromoSet().add(promoSet);
		}
	}
	
	public void removePromoSetFromOrder(OrderItem order, PromoSet promoSet) {
		boolean dupe = false;
		for(int i = 0 ;  i<order.getPromoSet().size(); i++) {
			if(order.getPromoSet().get(i).getSetID()==(promoSet.getSetID())){
				order.getPromoSet().get(i).setOrderedQuantity(promoSet.getOrderedQuantity());
				dupe = true; 
				break;
			} 
		}
		if(dupe==false) {
			System.out.println("The item has not been ordered!");
		}
	}
	
	public void viewOrder(){
		Order od = new Order();
		boolean printed = od.printOrder();
	}
}