package entity;

public class MainCourse extends MenuItem {
	
	public MainCourse(String name, String description, double price, int orderedQuantity) {
		super(name, description, price, orderedQuantity);
		super.type = "Main Course";
	}

}
