package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import entity.Dessert;
import entity.Drinks;
import entity.MainCourse;
import entity.MenuItem;
import service.Menu;
import utils.InitData;

public class MenuController implements Menu{

	Scanner sc = new Scanner(System.in);
	static ArrayList al = null;
	
	public static void main(String[] aArgs) {

		InitData txtDB = new InitData();
		String filename = "res/MenuItems.txt";
		MenuController menu = new MenuController();
		
		try {
			// read file containing Professor records.
			al = InitData.readMenuItem(filename);
			for (int i = 0; i < al.size(); i++) {
				MenuItem item = (MenuItem) al.get(i);
				System.out.println("Name: " + item.getName());
				System.out.println("Description: " + item.getDescription());
				System.out.println("Type: " + item.getType());
				System.out.println("Price: $" + item.getPrice());
				System.out.println();

			}
			menu.createMenuItem();
			
			InitData.saveMenuItem(filename, al);
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
	}
	
	@Override
	public void createMenuItem() {
		
		int choice = 0;
		
		String[] type = {
				"Main Course" ,
				"Dessert",
				"Drinks",
				"Exit"
		};
		
		MenuItem item = null;
		System.out.println("Adding Menu Item..");
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
				System.out.println("here");
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
		al.add(item);
	}

	@Override
	public void updateMenuItem() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteMenuItem() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createPromoItem() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updatePromoItem() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deletePromoItem() {
		// TODO Auto-generated method stub
		
	}

}
