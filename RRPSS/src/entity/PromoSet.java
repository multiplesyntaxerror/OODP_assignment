package entity;

import java.util.ArrayList;

public class PromoSet {
	
	private int setID;
	private String setDescription;
	private ArrayList<MenuItem> setItems;
	private double setPrice;
	
	public PromoSet(String setDescription, ArrayList<MenuItem> setItems, double setPrice) {
		super();
		this.setDescription = setDescription;
		this.setItems = setItems;
		this.setPrice = setPrice;
	}

	public int getSetID() {
		return setID;
	}
	
	public void setSetID(int setID) {
		this.setID = setID;
	}
	
	public String getSetDescription() {
		return setDescription;
	}
	
	public void setSetDescription(String setDescription) {
		this.setDescription = setDescription;
	}
	
	public ArrayList<MenuItem> getSetItems() {
		return setItems;
	}
	
	public void setSetItems(ArrayList<MenuItem> setItems) {
		this.setItems = setItems;
	}
	
	public double getSetPrice() {
		return setPrice;
	}
	
	public void setSetPrice(double setPrice) {
		this.setPrice = setPrice;
	}
	
	
}
