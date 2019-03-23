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
				break;
			case 4: 
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
		

		super.getDb().getGui().displayTitle("Adding New Item to Menu");
		
		while(true) {
			try {
				MenuItem item = null;
				super.getGui().displayStringsB("Please choose Type: ");
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
				
				super.getDb().getMenu().createMenuItem(item);
				return;
			} catch(InputMismatchException e) {
				super.getGui().displayStringsB("ERROR: Your input is invalid.\n");
			}
		}
		
		
	}

}
