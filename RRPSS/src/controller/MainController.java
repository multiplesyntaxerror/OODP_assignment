package controller;

import utils.Database;

/**
 * 
 * Main Application.
 */
public class MainController {
		
	/** The database. */
	private Database db;
	
	/** The menu controller. */
	private MenuController mc;
	private OrderController oc;
	private BookingController boc;
	private BillController bic;
	
	/**
	 * Instantiates a new main controller.
	 *
	 * @param db the database
	 */
	public MainController(Database db) {
		this.db = db;
		this.mc = new MenuController();
		this.oc = new OrderController();
		this.boc= new BookingController();
		this.bic = new BillController();
	}
	
	/**
	 * Runs the main system menu.
	 * @throws Exception 
	 * 
	 */
	public void run() {

		int choice;
		
		String[] mainOption = {
				"Menu Options",
				"Order Options",
				"Booking Options",
				"Billing Options",
				"Print Sales Revenue",
				"Exit System"
		};

		db.getGui().displayTitle("---------------------Welcome to NOM-NOM Restaruant---------------------");
		
		do {

			db.getGui().displayTitle("System Menu");
			
			
			choice = db.getGui().detectChoice(mainOption);
//			choice = 1;
//			choice = 2;
//			choice = 3;
//			choice = 4;
//			choice = 5;
			
			switch(choice) {
				case 1:
					mc.run(db);
					break;
				case 2:
					oc.run(db);
					break;
				case 3:
					bc.run(db);
					break;
				case 4:
					bc.run(db);
					break;
				case 5:
					break;
				case 6:
					db.getGui().displayTitle("Exiting System...");
					return;
			}
		} while(choice < mainOption.length);
				
	}
	
}
