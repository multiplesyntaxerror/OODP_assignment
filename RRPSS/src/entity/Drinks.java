package entity;

public class Drinks extends MenuItem {

	public Drinks(String name, String description, double price, int orderedQuantity) {
		super(name, description, price, orderedQuantity);
		this.type = "Drinks";
	}
	
}
