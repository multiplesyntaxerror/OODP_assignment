package app;

import controller.MainController;
import utils.Database;

/**
 * RRPSS Application.
 */
public class RRPSS {
	
	/**
	 * Runs the main program.
	 *
	 * @param args the arguments
	 */
	public static void main(String args[]) {
		
		Database db = new Database();
		db.initializeData();
		final MainController mainController = new MainController(db);
		mainController.run();
		System.exit(0);
	}
}
