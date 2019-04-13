package app;

import java.util.Timer;
import java.util.TimerTask;

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
		
		TimerTask task = new TimerTask() {
			@Override	
			public void run() {
				db.getBooking().checkAndClearReservation();
			}
		};
		Timer timer = new Timer();	    
		long delay = 0;	    
		//30sec interval
		long intevalPeriod = 1 * 30000;
		timer.scheduleAtFixedRate(task, delay, intevalPeriod);
		
		mainController.run();
		
		System.exit(0);
	}
}
