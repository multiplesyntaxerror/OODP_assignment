package controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import entity.Dessert;
import entity.Drinks;
import entity.MainCourse;
import entity.MenuItem;
import utils.Database;

public class MenuController extends Controller {
		
	private static ArrayList<MenuItem> menuList;
	
	public void run(Database db) throws Throwable{

		super.setDb(db);
		super.setGui(db.getGui());
		
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
				break;
			case 6:
				break;
			case 7:
				Database.getGui().displayStrings("Returning ...");
			return;
		}
	
	}
	public void createMenuItem() {

		Scanner sc = new Scanner(System.in);
		
		Database.getGui().displayTitle("Adding New Item To Menu");
		
		while(true) {

			MenuItem item = null;
			Database.getGui().displayStrings("Enter Item Name: ");
			String name = sc.nextLine();
			
			int index[] = super.getDb().getMenu().itemExistReturnIndex(name);

			if (index[1] == -1) {
				Database.getGui().displayStrings("Enter Item Description: ");
				String description = sc.nextLine();

				int choice = 0;
				
				try {

					Database.getGui().displayStrings("Enter Item Price: $");
					double price = sc.nextDouble();
					
					Database.getGui().displayStringsB("Please Choose Type: ");
					String[] type = { "Main Course", "Dessert", "Drinks" };
					choice = Database.getGui().detectChoice(type);
					
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
					
					boolean success = false;
					success = super.getDb().getMenu().createMenuItem(item);
					
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
			else if (index[1] != -1) {
				Database.getGui().displayStringsB("Item Name Already Exist");
			}
		}
	}
	
	public void updateMenuItem() {

		Scanner sc = new Scanner(System.in);
		
		Database.getGui().displayTitle("Updating Menu Item");

		super.getDb().getMenu().printMenuItems();

		while (true) {

			MenuItem item = null;
			Database.getGui().displayStrings("Enter Item Name To Edit: ");
			String name = sc.nextLine();
			
			int index[] = super.getDb().getMenu().itemExistReturnIndex(name);
			
			if (index[1] != -1) {

				Database.getGui().displayStrings("Enter Item New Name: ");
				String newName = sc.nextLine();

				int checkIndex[] = super.getDb().getMenu().itemExistReturnIndex(newName);

				if (checkIndex[1] == -1) {
					
					Database.getGui().displayStrings("Enter Item New Description: ");
					String newDescription = sc.nextLine();

					int choice = 0;
					
					try {
						Database.getGui().displayStrings("Enter Item New Price: $");
						double newPrice = sc.nextDouble();

						Database.getGui().displayStringsB("Choose New Type: ");
						String[] type = { "Main Course", "Dessert", "Drinks" };
						choice = Database.getGui().detectChoice(type);

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
						
						boolean success = false;
						success = super.getDb().getMenu().updateMenuItem(index, item);
						
						if (success) {
							Database.getGui().displayStringsB("SYSTEM NOTICE: Item Updated Successfully");
						} 
						else if (!success) {
							Database.getGui().displayStringsB("SYSTEM ERROR: Item Not Updated");
						}
						return;
						
					} catch (InputMismatchException e) {
						Database.getGui().displayStringsB("ERROR: Your input is invalid.\n");
						sc.nextLine();
						index = null;
					}
				} 
				else if (checkIndex[1] != -1) {
					Database.getGui().displayStringsB("Item Name Already Exist");
				}
			} 
			else if (index[1] == -1) {
				Database.getGui().displayStringsB("Item Does not Exist");
			}
		}
	}
	
	public void deleteMenuItem() {

		Scanner sc = new Scanner(System.in);
		
		Database.getGui().displayTitle("Deleting Menu Item");

		super.getDb().getMenu().printMenuItems();
		
		
		while(true) {
			
			Database.getGui().displayStrings("Enter Item Name To Delete: ");
			String name = sc.nextLine();
			
			int index[] = super.getDb().getMenu().itemExistReturnIndex(name);
			
			if (index[1] != -1) {
				
				boolean success = false;
				success = super.getDb().getMenu().deleteMenuItem(index);
				
				if (success) {
					Database.getGui().displayStringsB("SYSTEM NOTICE: Item Deleted Successfully");
				}
				else if (!success) {
					Database.getGui().displayStringsB("SYSTEM ERROR: Item Not Deleted");
				}
				return;
			}
			else if (index[1] == -1) {
				Database.getGui().displayStringsB("Item Does not Exist");
			}
		}
	}

}
