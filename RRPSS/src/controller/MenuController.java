package controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import entity.Dessert;
import entity.Drinks;
import entity.MainCourse;
import entity.MenuItem;
import entity.PromoSet;
import utils.Database;

public class MenuController extends Controller {
		
	public void run(Database db) throws Throwable{

		setDb(db);
		setGui(db.getGui());
		
		int choice;
		
		String[] menuOptions = {
				"Display All Menu Item",
				"Add Menu Item",
				"Update Menu Item",
				"Delete Menu Item",
				"Display All Promo Set",
				"Add Promo Set",
				"Update Promo Set",
				"Delete Promo Set",
				"Back"
		};
		
		Database.getGui().displayTitle("Menu Option");
		choice = Database.getGui().detectChoice(menuOptions);
		
		switch (choice) {
			case 1:
				db.getMenu().printMenuItems();
				break;
			case 2: 
				createMenuItem();
				break;
			case 3:
				updateMenuItem();
				break;
			case 4: 
				deleteMenuItem();
				break;
			case 5:
				db.getMenu().printPromoSets();
				break;
			case 6:
				createPromoItem();
				break;
			case 7:
				updatePromoItem();
				break;
			case 8:
				deletePromoItem();
				break;
			case 9:
				Database.getGui().displayStringsB("Returning ...");
			return;
		}
	
	}
	
	public void createMenuItem() {

		Scanner sc = new Scanner(System.in);
		
		Database.getGui().displayTitle("Adding New Item To Menu");

		MenuItem item = null;
		
		while(true) {
			
			Database.getGui().displayStrings("Enter Item Name: ");
			String name = sc.nextLine();
			
			item = getDb().getMenu().getMenuItem(name);

			if (item == null) {
				
				Database.getGui().displayStrings("Enter Item Description: ");
				String description = sc.nextLine();

				int choice = 0;
				
				try {
					
					double price;
					do {
						
						Database.getGui().displayStrings("Enter Item Price: $");
						price = sc.nextDouble();
						
						if (price < 0) {
							Database.getGui().displayStringsB("Price Cannot Be Negative.\n");
						}
						
					} while (price < 0 );
					
					Database.getGui().displayStringsB("Please Choose Type: ");
					String[] type = { "Main Course", "Dessert", "Drinks" };
					choice = Database.getGui().detectChoice(type);
					
					switch(choice) {
						case 1:
							item = new MainCourse(name, description, price, 0);
							break;
						case 2:
							item = new Dessert(name, description, price, 0);
							break;
						case 3:
							item = new Drinks(name, description, price, 0);
							break;
					}
					
					boolean success = getDb().getMenu().createMenuItem(item);
					
					if (success) {
						Database.getGui().displayStringsB("SYSTEM NOTICE: Item Successfully Added");
					}
					else if (!success) {
						Database.getGui().displayStringsB("SYSTEM ERROR: Item Not Added");
					}
					return;
					
				} catch(InputMismatchException e) {
					Database.getGui().displayStringsB("ERROR: Your Input Is Invalid.\n");
					sc.nextLine();
				}
			}
			else {
				Database.getGui().displayStringsB("Item Name Already Exist");
			}
		}
	}
	
	public void updateMenuItem() {

		Scanner sc = new Scanner(System.in);
		
		Database.getGui().displayTitle("Updating Menu Item");
		
		Database.getGui().displayStrings("Enter Item Number To Edit: ");

		MenuItem item = getDb().getMenu().pickMenuItems("Exit");
		
		if (item != null) {
			
			Database.getGui().displayStrings("Enter Item New Description: ");
			String newDescription = sc.nextLine();
			
			try {
				
				double newPrice;
				do {
					Database.getGui().displayStrings("Enter Item New Price: $");
					newPrice = sc.nextDouble();
					
					if (newPrice < 0) {
						Database.getGui().displayStringsB("Price Cannot Be Negative.\n");
					}
					
				} while (newPrice < 0 );
			
				boolean success = getDb().getMenu().updateMenuItem(item, newDescription, newPrice);
				
				if (success) {
					Database.getGui().displayStringsB("SYSTEM NOTICE: Item Updated Successfully");
				} 
				else if (!success) {
					Database.getGui().displayStringsB("SYSTEM ERROR: Item Not Updated");
				}
				return;
				
			} catch (InputMismatchException e) {
				Database.getGui().displayStringsB("ERROR: Your input is invalid.");
			}
		}
	}
	
	public void deleteMenuItem() {
		
		Database.getGui().displayTitle("Deleting Menu Item");

		Database.getGui().displayStringsB("Choose Item To Delete: ");

		MenuItem item = getDb().getMenu().pickMenuItems("Exit");
		
		if (item != null) {
			
			boolean success = getDb().getMenu().deleteMenuItem(item);
			
			if (success) {
				Database.getGui().displayStringsB("SYSTEM NOTICE: Item Deleted Successfully");
			}
			else if (!success) {
				Database.getGui().displayStringsB("SYSTEM ERROR: Item Not Deleted");
			}
		}
	}
	
	public void createPromoItem() {
		
		Scanner sc = new Scanner(System.in);
		
		Database.getGui().displayTitle("Creating Promotional Set Package");

		PromoSet set = null;
		
		MenuItem item;
		
		ArrayList<MenuItem> createdItemsArray = new ArrayList<MenuItem>();
		
		do {
			Database.getGui().displayStringsB("");
			Database.getGui().displayStringsB("Enter Choice To Add Into Set: ");
			
			item = getDb().getMenu().pickMenuItems("Done");
			if (item != null) {
				createdItemsArray.add(item);
			}

		} while(item != null);
		
		if (createdItemsArray.size() > 1) {
			
			Database.getGui().displayStrings("Enter Set Description: ");
			String description = sc.nextLine();
			
			try {
				
				double price;
				do {
					
					Database.getGui().displayStrings("Enter Set Price: $");
					price = sc.nextDouble();
					
					if (price < 0) {
						Database.getGui().displayStringsB("Price Cannot Be Negative.\n");
					}
					
				} while (price < 0 );
				
				set = new PromoSet(description, createdItemsArray, price);
				boolean success = getDb().getMenu().createPromoItem(set);
				
				if (success) {
					Database.getGui().displayStringsB("SYSTEM NOTICE: Set Successfully Added");
				}
				else if (!success) {
					Database.getGui().displayStringsB("SYSTEM ERROR: Set Already Exist");
				}
				return;
				
			} catch (InputMismatchException e) {
				Database.getGui().displayStringsB("ERROR: Your input is invalid.\n");
				sc.nextLine();
			}
		}
		else {
			Database.getGui().displayStringsB("SYSTEM ERROR: Pleace Choose 2 Or More Options");
		}
	}
	
	private void updatePromoItem() {
		
		Scanner sc = new Scanner(System.in);
		
		Database.getGui().displayTitle("Updating Promotional Set Package");

		Database.getGui().displayStrings("Enter Item Number To Edit: ");
		
		PromoSet set = getDb().getMenu().pickPromoSet("Exit");
		
		if (set != null) {

			Database.getGui().displayStrings("Enter Promotional Set New Description: ");
			String newDescription = sc.nextLine();
			
			try {
				
				double newPrice;
				do {
					
					Database.getGui().displayStrings("Enter Promotional Set New Price: $");
					newPrice = sc.nextDouble();
					
					if (newPrice < 0) {
						Database.getGui().displayStringsB("Price Cannot Be Negative.\n");
					}
					
				} while (newPrice < 0 );
			
				boolean success = getDb().getMenu().updatePromoItem(set, newDescription, newPrice);
				
				if (success) {
					Database.getGui().displayStringsB("SYSTEM NOTICE: Item Updated Successfully");
				} 
				else if (!success) {
					Database.getGui().displayStringsB("SYSTEM ERROR: Item Not Updated");
				}
				return;
				
			} catch (InputMismatchException e) {
				Database.getGui().displayStringsB("ERROR: Your input is invalid.");
			}
		}
	}
	
	private void deletePromoItem() {
		
		Database.getGui().displayTitle("Deleting Promotional Set");

		Database.getGui().displayStringsB("Choose Promotional Set To Delete: ");
		
		PromoSet set = getDb().getMenu().pickPromoSet("Exit");
		
		if (set != null) {
			boolean success = getDb().getMenu().deletePromoItem(set);
			
			if (success) {
				Database.getGui().displayStringsB("SYSTEM NOTICE: Set Deleted Successfully");
			}
			else if (!success) {
				Database.getGui().displayStringsB("SYSTEM ERROR: Set Not Deleted");
			}
		}	
	}

}
