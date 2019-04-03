package entity;

public class Dessert extends MenuItem {
	
	public Dessert(String name, String description, double price, int orderedQuantity) {
		super(name, description, price, orderedQuantity);
		this.type = "Dessert";
	}
}
