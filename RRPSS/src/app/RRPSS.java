package app;

import controller.MainController;
import utils.Database;

public class RRPSS {
	public static void main(String args[]) throws Throwable  {
		
		Database db = new Database();
		db.initializeData();
		final MainController mainController = new MainController(db);
		mainController.run();
		System.exit(0);
		//hello
	}
}
