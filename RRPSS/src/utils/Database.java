package utils;

import entity.Menu;
import entity.Order;
import entity.Table;
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
	
	private static final String TXTSEPARATOR = "**";

	/** The GUI. */
	private static GUI gui;
	
	/** The menu. */
	private Menu menu;
	private Order order;
	
	private Table[] table;
	
	/** 
	 * Initialize data.
	 */
	public void initializeData() {
		gui = new GUI();
		menu = new Menu();
		order = new Order();
		table = new Table[30];
		for(int i = 0; i<table.length; i++) {
			table[i] = new Table();
			table[i].setTableid(i + 1);
			if(i<10) {
				table[i].setSeatno(2);
			}
			if(i>=10 && i<20) {
				table[i].setSeatno(4);
			}
			if(i>=20 && i<25) { 
				table[i].setSeatno(8);
			}
			if(i>=25 && i<30) {
				table[i].setSeatno(10);
			}
		}
	}

	public Table[] getTable() {
		return table;
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

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public static String getTXTSeparator() {
		return TXTSEPARATOR;
	}
}
