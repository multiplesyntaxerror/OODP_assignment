package controller;

import java.util.Scanner;

import utils.Database;
import view.GUI;

public abstract class Controller {
	
	private GUI gui;
	private Scanner sc;
	private Database db;
	
	public GUI getGui() {
		return gui;
	}
	
	public void setGui(GUI gui) {
		this.gui = gui;
	}
	
	public Scanner getSc() {
		return sc;
	}
	
	public void setSc(Scanner sc) {
		this.sc = sc;
	}
	
	public Database getDb() {
		return db;
	}
	
	public void setDb(Database db) {
		this.db = db;
	}

		
}
