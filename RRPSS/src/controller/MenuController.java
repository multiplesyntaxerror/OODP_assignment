package controller;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import entity.Dessert;
import entity.Drinks;
import entity.MainCourse;
import entity.MenuItem;
import utils.Database;
import view.GUI;

public class MenuController{
		
	private static ArrayList<MenuItem> menuList;
	
	private static GUI gui;
	
	private static Scanner sc;
	
	private static Database db;
	
	public static void run(Database db) throws Throwable{
		
		gui = db.getGui();
		
		sc = gui.getScanner();
		
		MenuController.db = db;
		
		menuList = db.getMenu().getMenuList();
		
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
	
	public static void createMenuItem() {
		
		int choice = 0;
		
		String[] type = {
				"Main Course" ,
				"Dessert",
				"Drinks"
		};
		

		db.getGui().displayTitle("Adding New Item to Menu");
		
		while(true) {
			try {
				MenuItem item = null;
				gui.displayStringsB("Please choose Type: ");
				choice = db.getGui().detectChoice(type);
				gui.displayStrings("Enter Item name: ");
				String name = sc.next();
				gui.displayStrings("Enter Item Description: ");
				String description = sc.next();
				gui.displayStrings("Enter Item Price: $");
				double price = sc.nextDouble();
				
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
				
				db.getMenu().createMenuItem(item);
				return;
			} catch(InputMismatchException e) {
				gui.displayStringsB("ERROR: Your input is invalid.\n");
			}
		}
		
		
	}

}
