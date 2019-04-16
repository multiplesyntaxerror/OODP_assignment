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
	
	/** The order controller */
	private OrderController oc;
	
	/** The booking controller. */
	private BookingController boc;
	
	/** The billing controller. */
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
	 */
	public void run() {
		

		db.getBooking().checkAndClearReservation();
		
		int choice;
		
		String[] mainOption = {
				"Menu Options",
				"Order Options",
				"Booking Options",
				"Billing Options",
				"Exit System"
		};

		db.getGui().displayTitle("---------------------Welcome to NOM-NOM Restaurant---------------------");
		
		do {

			db.getGui().displayTitle("System Menu");
			
			
			choice = db.getGui().detectChoice(mainOption);
			
			switch(choice) {
				case 1:
					mc.run(db);
					break;
				case 2:
					oc.run(db);
					break;
				case 3:
					boc.run(db);
					break;
				case 4:
					bic.run(db);
					break;
				case 5:
					db.getGui().displayTitle("Exiting System...");
					return;
			}
		} while(choice < mainOption.length);
				
	}
	
}
