package controller;

import java.util.Scanner;

import utils.Database;
import view.GUI;

public abstract class Controller {
	
	private GUI gui;
	private Database db;
	
	public GUI getGui() {
		return gui;
	}
	
	public void setGui(GUI gui) {
		this.gui = gui;
	}
	
	public Database getDb() {
		return db;
	}
	
	public void setDb(Database db) {
		this.db = db;
	}

		
}
