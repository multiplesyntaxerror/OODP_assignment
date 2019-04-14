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

/**
 * The Class MenuController.
 */
public class MenuController extends Controller {
		
	/**
	 * Runs the menu options.
	 *
	 * @param db the database
	 */
	public void run(Database db) {

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
		
		getGui().displayTitle("Menu Option");
		choice = getGui().detectChoice(menuOptions);
		
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
				getGui().displayStringsB("Returning ...");
			return;
		}
	
	}
	
	/**
	 * Create a menu item.
	 */
	public void createMenuItem() {

		Scanner sc = new Scanner(System.in);
		getGui().displayTitle("Adding New Item To Menu");
		MenuItem item = null;
		while(true) {
			 
			String name;
			do {
				getGui().displayStrings("Enter Item Name: ");
				name = sc.nextLine();
				if (name.isEmpty())
					getGui().displayStringsB("Please Enter Something.\n");
			} while (name.isEmpty());
			
			item = getDb().getMenu().getMenuItem(name);
			if (item == null) {
				
				String description;
				do {
					getGui().displayStrings("Enter Item Description: ");
					description = sc.nextLine();
					if (description.isEmpty())
						getGui().displayStringsB("Please Enter Something.\n");
				} while (description.isEmpty());

				double price = 0;
				do {
					try {
						getGui().displayStrings("Enter Item Price: $");
						price = sc.nextDouble();
						if (price <= 0) 
							getGui().displayStringsB("Price Cannot Be Zero Or Negative.\n");
					} catch(InputMismatchException e) {
						getGui().displayStringsB("ERROR: Your Input Is Invalid.\n");
						sc.nextLine();
					}
				} while (price <= 0 );
				
				getGui().displayStringsB("Please Choose Type: ");
				String[] type = { "Main Course", "Dessert", "Drinks" };
				int choice = 0;
				choice = getGui().detectChoice(type);
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
				if (success)
					getGui().displayStringsB("SYSTEM NOTICE: Item Successfully Added");
				else if (!success)
					getGui().displayStringsB("SYSTEM ERROR: Item Not Added");
				return;
				
			}
			else
				getGui().displayStringsB("Item Name Already Exist.\n");
		}
	}
	
	/**
	 * Updates a menu item.
	 */
	public void updateMenuItem() {

		Scanner sc = new Scanner(System.in);
		getGui().displayTitle("Updating Menu Item");
		getGui().displayStrings("Enter Item Number To Edit: ");
		MenuItem item = getDb().getMenu().pickMenuItems("Exit");
		if (item != null) {
			
			String newName;
			do {
				getGui().displayStrings("Enter Item New Name: ");
				newName = sc.nextLine();
				if (newName.isEmpty())
					getGui().displayStringsB("Please Enter Something.\n");
			} while (newName.isEmpty());
			
			String newDescription;
			do {
				getGui().displayStrings("Enter Item New Description: ");
				newDescription = sc.nextLine();
				if (newDescription.isEmpty())
					getGui().displayStringsB("Please Enter Something.\n");
			} while (newDescription.isEmpty());
			
			double newPrice = 0;
			do {
				try {
					getGui().displayStrings("Enter Item New Price: $");
					newPrice = sc.nextDouble();
					if (newPrice <= 0)
						getGui().displayStringsB("Price Cannot Be Zero Or Negative.\n");
				} catch (InputMismatchException e) {
					getGui().displayStringsB("ERROR: Your Input Is Invalid.\n"); 
					sc.nextLine();
				}
			} while (newPrice <= 0 );
			
			boolean success = getDb().getMenu().updateMenuItem(item, newName, newDescription, newPrice);
			if (success)
				getGui().displayStringsB("SYSTEM NOTICE: Item Updated Successfully");
			else if (!success)
				getGui().displayStringsB("SYSTEM ERROR: Item Not Updated");
		}
	}
	
	/**
	 * Deletes a menu item.
	 */
	public void deleteMenuItem() {
		
		getGui().displayTitle("Deleting Menu Item");
		getGui().displayStringsB("Choose Item To Delete: ");
		MenuItem item = getDb().getMenu().pickMenuItems("Exit");
		
		if (item != null) {
			boolean success = getDb().getMenu().deleteMenuItem(item);
			if (success) 
				getGui().displayStringsB("SYSTEM NOTICE: Item Deleted Successfully");
			else if (!success)
				getGui().displayStringsB("SYSTEM ERROR: Item Not Deleted");
		}
	}
	
	/**
	 * Creates a promotional set item.
	 */
	public void createPromoItem() {
		
		Scanner sc = new Scanner(System.in);
		getGui().displayTitle("Creating Promotional Set Package");
		PromoSet set = null;
		MenuItem item;
		
		ArrayList<MenuItem> createdItemsArray = new ArrayList<MenuItem>();
		do {
			getGui().displayStringsB("Enter Choice To Add Into Set: ");
			item = getDb().getMenu().pickMenuItems("Done");
			if (item != null)
				createdItemsArray.add(item);
		} while(item != null);
		
		if (createdItemsArray.size() > 1) {
			
			String description;
			do {
				getGui().displayStrings("Enter Set Description: ");
				description = sc.nextLine();
				if (description.isEmpty())
					getGui().displayStringsB("Please Enter Something.\n");
			} while (description.isEmpty());

			double price = 0;
			do {
				try {
					getGui().displayStrings("Enter Set Price: $");
					price = sc.nextDouble();
					if (price <= 0)
						getGui().displayStringsB("Price Cannot Be Zero Or Negative.\n");
				} catch (InputMismatchException e) {
					getGui().displayStringsB("ERROR: Your Input Is Invalid.\n");
					sc.nextLine();
				}
			} while (price <= 0 );
				
			set = new PromoSet(description, createdItemsArray, price);
			boolean success = getDb().getMenu().createPromoItem(set);
			if (success)
				getGui().displayStringsB("SYSTEM NOTICE: Set Successfully Added");
			else if (!success)
				getGui().displayStringsB("SYSTEM ERROR: Set Already Exist");
		}
		else
			getGui().displayStringsB("SYSTEM ERROR: Pleace Choose 2 Or More Items To Add Create a Set");
	}
	
	/**
	 * Updates a promotional set item.
	 */
	public void updatePromoItem() {
		
		Scanner sc = new Scanner(System.in);
		getGui().displayTitle("Updating Promotional Set Package");
		getGui().displayStrings("Enter Item Number To Edit: ");
		PromoSet set = getDb().getMenu().pickPromoSet("Exit");		
		if (set != null) {

			String newDescription;
			do {
				getGui().displayStrings("Enter Promotional Set New Description: ");
				newDescription = sc.nextLine();
				if (newDescription.isEmpty())
					getGui().displayStringsB("Please Enter Something.\n");
			} while (newDescription.isEmpty());
			
			double newPrice = 0;
			do {
				try {getGui().displayStrings("Enter Promotional Set New Price: $");
					newPrice = sc.nextDouble();
					if (newPrice <= 0)
						getGui().displayStringsB("Price Cannot Be Negative.\n");
				} catch (InputMismatchException e) {
					getGui().displayStringsB("ERROR: Your Input Is Invalid.\n");
					sc.nextLine();
				}
			} while (newPrice <= 0 );
			
			boolean success = getDb().getMenu().updatePromoItem(set, newDescription, newPrice);
			if (success)
				getGui().displayStringsB("SYSTEM NOTICE: Item Updated Successfully");
			else if (!success)
				getGui().displayStringsB("SYSTEM ERROR: Item Not Updated");
		}
	}
	
	/**
	 * Delete a promotional set item.
	 */
	public void deletePromoItem() {
		
		getGui().displayTitle("Deleting Promotional Set");
		getGui().displayStringsB("Choose Promotional Set To Delete: ");
		PromoSet set = getDb().getMenu().pickPromoSet("Exit");
		
		if (set != null) {
			boolean success = getDb().getMenu().deletePromoItem(set);
			if (success) 
				getGui().displayStringsB("SYSTEM NOTICE: Set Deleted Successfully");
			else if (!success)
				getGui().displayStringsB("SYSTEM ERROR: Set Not Deleted");
		}	
	}

}
