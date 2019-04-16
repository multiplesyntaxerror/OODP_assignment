package controller;

import utils.Database;
import view.GUI;

/**
 * The Class Controller.
 */
public class Controller {
	
	/** The GUI for displaying. */
	private GUI gui;
	
	/** The database. */
	private Database db;
	
	/**
	 * Gets the GUI.
	 *
	 * @return the GUI
	 */
	public GUI getGui() {
		return gui;
	}
	
	/**
	 * Sets the GUI.
	 *
	 * @param gui the new GUI
	 */
	public void setGui(GUI gui) {
		this.gui = gui;
	}
	
	/**
	 * Gets the database.
	 *
	 * @return the database
	 */
	public Database getDb() {
		return db;
	}
	
	/**
	 * Sets the database.
	 *
	 * @param db the new database
	 */
	public void setDb(Database db) {
		this.db = db;
	}

		
}
