package entity;

public abstract class MenuItem {
	
	private String name;
	private String description;
	protected String type;
	private double price;
	private int orderedQuantity;

	public MenuItem(String name, String description, double price, int orderedQuantity) {
		super();
		this.name = name;
		this.description = description;
		this.price = price;
		this.orderedQuantity = orderedQuantity;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}

	public int getOrderedQuantity() {
		return orderedQuantity;
	}

	public void setOrderedQuantity(int orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	}
	
	
	
}
