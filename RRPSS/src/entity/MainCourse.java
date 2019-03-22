package entity;

public class MainCourse extends MenuItem {
	
	public MainCourse(String name, String description, double price) {
		super(name, description, price);
		super.type = "Main Course";
	}

}
