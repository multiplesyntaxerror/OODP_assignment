package entity;

public class Dessert extends MenuItem {
	
	public Dessert(String name, String description, double price) {
		super(name, description, price);
		this.type = "Dessert";
	}
}
