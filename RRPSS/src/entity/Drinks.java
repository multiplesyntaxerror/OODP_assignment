package entity;

public class Drinks extends MenuItem {

	public Drinks(String name, String description, double price) {
		super(name, description, price);
		this.type = "Drinks";
	}
	
}
