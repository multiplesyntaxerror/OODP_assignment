package controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

import entity.MenuItem;
import entity.OrderItem;
import entity.PromoSet;
import entity.Staff;
import utils.Database;

/**
 * The Class OrderController.
 */
public class OrderController extends Controller{

	/**
	 * Runs the order options.
	 *
	 * @param db the database
	 */
	public void run(Database db) {

		setDb(db);
		setGui(db.getGui());
		
		int choice;
		
		String[] orderOptions = {
				"View All Order",
				"Create New Order",
				"Add Item To Existing Order",
				"Remove Item From Existing Order",
				"Back"
		};
		
		getGui().displayTitle("Order Option");
		choice = getGui().detectChoice(orderOptions);
		
		switch (choice) {
			case 1: 
				db.getOrder().printOrder();
				break;
			case 2: 
				createOrder();
				break;
			case 3: 
				addItemToExistingOrder();
				break;
			case 4: 
				removeItemFromExistingOrder();
				break;
			case 5:
				getGui().displayStringsB("Returning ...");
			return;
		}
	
	} 
	
	/**
	 * Create an order.
	 */
	public void createOrder() {
		
		Scanner sc = new Scanner(System.in);
		getGui().displayTitle("Creating New Order");
		ArrayList<MenuItem> alaCarteOrder = new ArrayList<MenuItem>();
		ArrayList<PromoSet> promoSetOrder = new ArrayList<PromoSet>();
		
		getGui().displayStrings("Served By: ");
		Staff staff = getDb().getRestaurant().pickStaff("Exit");
		if (staff == null) {
			getGui().displayStringsB("SYSTEM MESSAGE: Returning to System Menu.\n");
			return;
		}
		
		int tableId = 0;
		do {
			try {
				getGui().displayStrings("Enter Table Number: ");
				tableId = sc.nextInt();
				if (tableId <= 0 || tableId > 30) 
					getGui().displayStringsB("Table Only Ranges From 1-30.\n");
			} catch(InputMismatchException e) {
				getGui().displayStringsB("SYSTEM MESSAGE: Your Input Is Invalid.\n");
				sc.nextLine();
			}
		} while (tableId <= 0 || tableId > 30);
		if(getDb().getRestaurant().getTableList().get(tableId - 1).isOccupied() || getDb().getRestaurant().getTableList().get(tableId - 1).isReserved()) {
			getGui().displayStringsB("SYSTEM MESSAGE: Table Is Not Available.");
		}
		else {
		
			OrderItem order = new OrderItem(0, tableId, staff.getName(), null, 0, alaCarteOrder, promoSetOrder, false);

			String pattern = "dd-MM-yyyy hh:mm a";
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
			String date = simpleDateFormat.format(new Date());
			order.setDate(date);
			order.setTableId(tableId);
			int choice;

			do {

				String[] options = { 
						"Ala Carte", 
						"Promo Set", 
						"Done" };

				getGui().displayStringsB("Order Ala Carte Or Promo Set: ");
				choice = getGui().detectChoice(options);
				switch (choice) {
				case 1:
					MenuItem item;
					do {
						getGui().displayStringsB("Enter Choice Of Item To Add Into Order: ");
						item = getDb().getMenu().pickMenuItems("Done");
						if (item != null) {
							getGui().displayStrings("Enter Quantity: ");
							int qty = sc.nextInt();
							item.setOrderedQuantity(qty);

							if (order.getAlaCarte().size() == 0) {
								order.getAlaCarte().add(item);
							} else {
								addItemToNewOrder(order, item);
							}
						}
					} while (item != null);
					break;

				case 2:
					PromoSet set;
					do {
						getGui().displayStringsB("Enter Choice To Add Item Into Order: ");
						set = getDb().getMenu().pickPromoSet("Done");
						if (set != null) {
							getGui().displayStrings("Enter Quantity: ");
							int qty = sc.nextInt();
							set.setOrderedQuantity(qty);

							if (order.getPromoSet().size() == 0) {
								order.getPromoSet().add(set);
							} else {
								addPromoSetToNewOrder(order, set);
							}
						}
					} while (set != null);
					break;
				case 3:
					if (order.getAlaCarte().size() != 0 || order.getPromoSet().size() != 0) {
						boolean created = getDb().getOrder().createOrder(order);
						if (created == true) {
							getGui().displayStringsB("SYSTEM MESSAGE: Order Successfully Created.");
							getDb().getRestaurant().getTableList().get(tableId - 1).setOccupied(true);
							getDb().getRestaurant().updateRestaurantTables();
							
						} else if (created != true) {
							getGui().displayStringsB("SYSTEM MESSAGE: Order not Created.");
						}
					} else {
						getGui().displayStringsB("SYSTEM MESSAGE: Order Is Empty, Returning To System Menu.");
					}
					break;
				default:

				}

			} while (choice == 1 || choice == 2);
		}
	}
	
	/**
	 * Remove items from existing order.
	 */
	public void removeItemFromExistingOrder() {
		Scanner sc = new Scanner(System.in);
		getGui().displayTitle("Update Exisiting Orders");
		getGui().displayStrings("Enter Order ID to Update: ");
		OrderItem order = getDb().getOrder().pickOrderItems("Exit");
		
				
		if(order != null) {
			boolean last = false;
			if(order.getAlaCarte().size() + order.getPromoSet().size() == 1 ) {
				last = true;
			}
			if(order.getPrintedInvoice() == true) {
				getGui().displayStringsB("SYSTEM MESSAGE: Order Is Closed And Cannot Be Edditted.\n");
				return;
			}
			else {
				getGui().displayStringsB("Choose item to update ");
				String[] orderlist = getDb().getOrder().getSpecificOrder(order.getOrderId());
				int choice = getGui().detectChoice(orderlist);
				getGui().displayStrings("Enter quantity to remove: ");
				int qtyToRemove = sc.nextInt();

				if (choice <= getDb().getOrder().getAllOrders().get(order.getOrderId() - 1).getAlaCarte().size()) {
					int qty = order.getAlaCarte().size();
					boolean removed = getDb().getOrder().removeMenuItemOrder(order.getOrderId(), choice, qtyToRemove);
					if(removed == true) {
						getGui().displayStringsB("Item Successfully Removed.");
						if (qty == qtyToRemove && last) {
							getDb().getRestaurant().getTableList().get(order.getTableId() - 1).setOccupied(false);
							getDb().getRestaurant().updateRestaurantTables();
						}
					}
				} 
				else {
					int qty = order.getPromoSet().size();
					choice = choice - getDb().getOrder().getAllOrders().get(order.getOrderId() - 1).getAlaCarte().size();
					boolean removedP = getDb().getOrder().removePromoSetOrder(order.getOrderId(), choice, qtyToRemove);
					if(removedP == true) {
						getGui().displayStringsB("PromoSet Successfully Removed.");
						if (qty == qtyToRemove && last) {
							getDb().getRestaurant().getTableList().get(order.getTableId() - 1).setOccupied(false);
							getDb().getRestaurant().updateRestaurantTables();
						}
					}
				}
			}
		}
  }
	
	/**
	 * Add items to existing order.
	 */
	public void addItemToExistingOrder() {
		Scanner sc = new Scanner(System.in);
		getGui().displayTitle("Adding Item To Existing Orders");
		getGui().displayStringsB("Enter Order ID To Add: ");
		OrderItem order = getDb().getOrder().pickOrderItems("Exit");
					
		MenuItem item;
		PromoSet set;
		if(order != null) {

			if(order.getPrintedInvoice() == true) {
				getGui().displayStringsB("Order Is Closed And Cannot Be Edditted.\n");
				return;
			}
			
			int choice;
			do {
				
				String[] options = {
						"Ala Carte",
						"Promo Set",
						"Done"
				};
				
				getGui().displayStringsB("Add Ala Carte Or Promo Set: ");
				
				choice = getGui().detectChoice(options);
				
				switch (choice) {
				case 1:
					do {
						getGui().displayStringsB("Enter Choice To Add Item Into Order: ");
						item = getDb().getMenu().pickMenuItems("Done");
						if (item != null) {
							getGui().displayStrings("Enter Quantity: ");
							int qty = sc.nextInt();
							item.setOrderedQuantity(qty);
							boolean updated = getDb().getOrder().updateMenuItemOrder(item, order.getOrderId());
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
							boolean updated = getDb().getOrder().updatePromoSetOrder(set, order.getOrderId());
							if(updated == true) {
								getGui().displayStringsB("Order Updated.");
							}
							else {
								getGui().displayStringsB("Order Not Updated.");
							}
						} 
					} while (set != null);
					break; 
				}
			} while (choice == 1 || choice == 2);
		}
		else {
			getGui().displayStringsB("There Are No Orders Yet.");
		}
	}
	
	/**
	 * Add an item to new order.
	 *
	 * @param order the created order
	 * @param item the menu item
	 */
	public void addItemToNewOrder(OrderItem order, MenuItem item) {
		boolean dupe = false;
		for(int i = 0 ; i<order.getAlaCarte().size(); i++) {
			if(order.getAlaCarte().get(i).getName().equals(item.getName())) {
				order.getAlaCarte().get(i).addOrderedQuantity(item.getOrderedQuantity());
				dupe = true;
				break;
			}
		}
		if(dupe == false) {
			order.getAlaCarte().add(item);
		}
	}
	
	/**
	 * Adds the promotional set to new order.
	 *
	 * @param order the created order
	 * @param promoSet the promotional set
	 */
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
}