package entity;

public class Customer extends Person {

	private int tableid;
	private int pax;
	private String date;
	private String arrivalTime;

	public Customer(String name, String contact, int tableId, int pax, String date, String arrivalTime) {
		super.name = name;
		super.contact = contact;
		this.tableid = tableId;
		this.pax = pax;
		this.date = date;
		this.arrivalTime = arrivalTime;
	}

	public int getTableid() {
		return tableid;
	}

	public void setTableid(int tableid) {
		this.tableid = tableid;
	}

	public int getPax() {
		return pax;
	}

	public void setPax(int pax) {
		this.pax = pax;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}


	public String getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
}