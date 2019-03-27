package utils;

import entity.Menu;
import view.GUI;

public class Database {
	
	private static ReadWriteFile rwFile = new ReadWriteFile();
	
	private static final String SEPARATOR = "|";

	private static GUI gui;
	
	private Menu menu;
	
	public void initializeData() {
		gui = new GUI();
		menu = new Menu();
	}

	public static ReadWriteFile getRwFile() {
		return rwFile;
	}

	public static String getSeparator() {
		return SEPARATOR;
	}
	
	public static GUI getGui() {
		return gui;
	}

	public Menu getMenu() {
		return menu;
	}

	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	
}
