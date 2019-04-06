package utils;

import entity.Menu;
import view.GUI;

/**
 * The Class Database.
 */
public class Database {
	
	/** The ReadWriteFile. */
	private static ReadWriteFile rwFile = new ReadWriteFile();
	
	/** The Constant SEPARATOR. */
	private static final String SEPARATOR = "|";
	
	/** The Constant SETSEPARATOR for promotional set. */
	private static final String SETSEPARATOR = "+";

	/** The GUI. */
	private static GUI gui;
	
	/** The menu. */
	private Menu menu;
	
	/**
	 * Initialize data.
	 */
	public void initializeData() {
		gui = new GUI();
		menu = new Menu();
	}

	/**
	 * Gets the rw file.
	 *
	 * @return the rw file
	 */
	public static ReadWriteFile getRwFile() {
		return rwFile;
	}

	/**
	 * Gets the separator.
	 *
	 * @return the separator
	 */
	public static String getSeparator() {
		return SEPARATOR;
	}
	
	/**
	 * Gets the set separator.
	 *
	 * @return the set separator
	 */
	public static String getSetSeparator() {
		return SETSEPARATOR;
	}
	
	/**
	 * Gets the GUI.
	 *
	 * @return the GUI
	 */
	public static GUI getGui() {
		return gui;
	}

	/**
	 * Gets the menu.
	 *
	 * @return the menu
	 */
	public Menu getMenu() {
		return menu;
	}

	/**
	 * Sets the menu.
	 *
	 * @param menu the new menu
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	
}
