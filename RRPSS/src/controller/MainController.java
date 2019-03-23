package controller;

import utils.Database;

/**
 * Main Application
 */
public class MainController {
		
	private Database db;
	
	public MainController(Database db) {
		this.db = db;
	}
	
	public void run() throws Throwable{

		int choice;
		
		String[] mainOption = {
				"Menu Options",
				"Order Options",
				"Booking Options",
				"Billing Options",
				"Print Sales Revenue",
				"Exit"
		};

		db.getGui().displayTitle("Welcome to NOM-NOM Restaruant");
		
		do {
			choice = db.getGui().detectChoice(mainOption);
			switch(choice) {
				case 1:
					MenuController.run(db);
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
					db.getGui().displayStrings("Exiting System...");
					return;
			}
		} while(choice < mainOption.length);
				
	}
	
}
