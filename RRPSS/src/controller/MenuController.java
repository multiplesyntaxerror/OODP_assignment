package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import entity.Dessert;
import entity.Drinks;
import entity.MainCourse;
import entity.Menu;
import entity.MenuItem;

public class MenuController{

	static Scanner sc = new Scanner(System.in);
	
	Menu menu = new Menu();
	ArrayList<MenuItem> menuList = menu.getMenuList();
	
	public static void main(String[] aArgs) {

		int choice;
		MenuController con = new MenuController();
		
		String[] options = {
				"Add Menu Item" ,
				"Update Menu Item",
				"Delete Menu Item",
				"Add Promo Set" ,
				"Update Promo Set",
				"Delete Promo Set",
				"Exit"
		};
		
		System.out.println("Choose Action: ");
		choice = sc.nextInt();
		
		switch (choice) {
			case 1:
				con.createMenuItem();
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
				break;
			case 7: System.out.println("Returning ...");
		}
	
	}
	
	public void createMenuItem() {
		
		int choice = 0;
		
		String[] type = {
				"Main Course" ,
				"Dessert",
				"Drinks",
				"Exit"
		};
		
		MenuItem item = null;
		
		System.out.println("Please choose Type: ");
		choice = sc.nextInt();
		System.out.println("Enter Item name: ");
		String name = sc.next();
		System.out.println("Enter Item Description: ");
		String description = sc.next();
		System.out.println("Enter Item Price: $");
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
			case 4:
				System.out.println("Exiting...");
		}
		
		menu.createMenuItem(item);
		
	}

}
