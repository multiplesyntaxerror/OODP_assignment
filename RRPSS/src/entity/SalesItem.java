package entity;

public class SalesItem {
	
	private String itemname;
	private int quantitySold;
	private double price;
	private boolean isPromo;
	public boolean isPromo() {
		return isPromo;
	}
	public void setPromo(boolean isPromo) {
		this.isPromo = isPromo;
	}
	public String getItemname() {
		return itemname;
	}
	public void setItemname(String itemname) {
		this.itemname = itemname;
	}
	public int getQuantitySold() {
		return quantitySold;
	}
	public void setQuantitySold(int quantitySold) {
		this.quantitySold = quantitySold;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	
}
