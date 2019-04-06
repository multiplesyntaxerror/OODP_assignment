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
	 * @throws FileNotFoundException if file does not exist.
	 * @throws IOException if stream to file cannot be written to or closed.
	 */
	public static void main(String args[]) throws Throwable  {
		
		Database db = new Database();
		db.initializeData();
		final MainController mainController = new MainController(db);
		mainController.run();
		System.exit(0);
	}
}
