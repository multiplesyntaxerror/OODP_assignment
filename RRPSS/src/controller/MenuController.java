package controller;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import entity.Dessert;
import entity.Drinks;
import entity.MainCourse;
import entity.MenuItem;
import utils.Database;
import view.GUI;

public class MenuController extends Controller {
		
	private static ArrayList<MenuItem> menuList;
	
	public void run(Database db) throws Throwable{

		super.setDb(db);
		super.setGui(db.getGui());
		super.setSc(db.getGui().getScanner());
		
		menuList = super.getDb().getMenu().getMenuList();
		
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
				"Exit"
		};
		
		//toRemove
		db.getGui().displayTitle("Menu Option");
		choice = db.getGui().detectChoice(menuOptions);
		
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
				break;
			case 6:
				break;
			case 7:
				db.getGui().displayStrings("Returning ...");
			return;
		}
	
	}
	
	public void createMenuItem() {
		
		int choice = 0;
		
		String[] type = {
				"Main Course" ,
				"Dessert",
				"Drinks"
		};
		
		boolean success = false;
		
		while(true) {

			super.getDb().getGui().displayTitle("Adding New Item To Menu");
			
			try {
				MenuItem item = null;
				super.getGui().displayStringsB("Please Choose Type: ");
				super.getSc().nextLine();
				choice = super.getDb().getGui().detectChoice(type);
				super.getGui().displayStrings("Enter Item name: ");
				String name = super.getSc().next();
				super.getGui().displayStrings("Enter Item Description: ");
				String description = super.getSc().next();
				super.getGui().displayStrings("Enter Item Price: $");
				double price = super.getSc().nextDouble();
				
				switch(choice) {
					case 1:
						item = new MainCourse(name, description, price);
						break;
					case 2:
						item = new Dessert(name, description, price);
						break;
					case 3:
						item = new Drinks(name, description, price);
						break;
				}

				success = super.getDb().getMenu().createMenuItem(item);
				if (success) {
					super.getGui().displayStringsB("SYSTEM NOTICE: Item Successfully Added");
				}
				else if (!success) {
					super.getGui().displayStringsB("SYSTEM ERROR: Item Not Added");
				}
				return;
			} catch(InputMismatchException e) {
				super.getGui().displayStringsB("ERROR: Your Input Is Invalid.\n");
			}
		}
	}
	
	public void updateMenuItem() {

		int choice = 0;

		super.getDb().getGui().displayTitle("Updating Menu Item");

		super.getDb().getMenu().printMenuItems();

		boolean success = false;

		while (true) {

			MenuItem item = null;
			super.getGui().displayStrings("Enter Item Name To Edit: ");
			super.getSc().nextLine();
			String name = super.getSc().next();
			int index = super.getDb().getMenu().itemExistReturnIndex(name);

			if (index != -1) {

				super.getGui().displayStrings("Enter Item New Name: ");
				String newName = super.getSc().next();

				int checkIndex = super.getDb().getMenu().itemExistReturnIndex(newName);

				if (checkIndex == -1) {
					super.getGui().displayStrings("Enter Item New Description: ");
					String newDescription = super.getSc().next();

					try {
						super.getGui().displayStrings("Enter Item New Price: $");
						double newPrice = super.getSc().nextDouble();

						super.getGui().displayStringsB("Choose New Type: ");

						String[] type = { "Main Course", "Dessert", "Drinks" };

						choice = super.getDb().getGui().detectChoice(type);

						switch (choice) {
							case 1:
								item = new MainCourse(newName, newDescription, newPrice);
								break;
							case 2:
								item = new Dessert(newName, newDescription, newPrice);
								break;
							case 3:
								item = new Drinks(newName, newDescription, newPrice);
								break;
						}
						success = super.getDb().getMenu().updateMenuItem(index, item);
						if (success) {
							super.getGui().displayStringsB("SYSTEM NOTICE: Item Updated Successfully");
						} 
						else if (!success) {
							super.getGui().displayStringsB("SYSTEM ERROR: Item Not Updated");
						}
						return;
					} catch (InputMismatchException e) {
						super.getGui().displayStringsB("ERROR: Your input is invalid.\n");
						index = 0;
					}
				} 
				else if (checkIndex != -1) {
					super.getGui().displayStringsB("Item Name Already Exist");
				}
			} 
			else if (index == -1) {
				super.getGui().displayStringsB("Item Does not Exist");
			}
		}
	}
	
	public void deleteMenuItem() {
		
		super.getDb().getGui().displayTitle("Deleting Menu Item");

		super.getDb().getMenu().printMenuItems();
		
		boolean success = false;
		
		while(true) {
			
			super.getGui().displayStrings("Enter Item Name To Delete: ");
			super.getSc().nextLine();
			String name = super.getSc().next();
			int index = super.getDb().getMenu().itemExistReturnIndex(name);
			
			if (index != -1) {
				success = super.getDb().getMenu().deleteMenuItem(index);
				if (success) {
					super.getGui().displayStringsB("SYSTEM NOTICE: Item Deleted Successfully");
				}
				else if (!success) {
					super.getGui().displayStringsB("SYSTEM ERROR: Item Not Deleted");
				}
				return;
			}
			else if (index == -1) {
				super.getGui().displayStringsB("Item Does not Exist");
			}
		}
	}

}
