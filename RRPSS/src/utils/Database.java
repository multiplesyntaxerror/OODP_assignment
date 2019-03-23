package utils;

import entity.Menu;
import view.GUI;

public class Database {
	
	private ReadWriteFile rwFile = new ReadWriteFile();
	
	private static final String SEPARATOR = "|";

	private GUI gui;
	
	private static Menu menu;
	
	public void initializeData() {
		gui = new GUI();
		menu = new Menu(rwFile, SEPARATOR);
	}

	public ReadWriteFile getRwFile() {
		return rwFile;
	}
	
	public GUI getGui() {
		return gui;
	}

	public Menu getMenu() {
		return menu;
	}

	public static void setMenu(Menu menu) {
		Database.menu = menu;
	}

	public static String getSeparator() {
		return SEPARATOR;
	}
	
}
