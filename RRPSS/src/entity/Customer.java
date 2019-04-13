package entity;

public class Customer extends Person {

	private int tableid;
	private int pax;
	private String date;
	private String arrivalTime;
	private String duration;

	public Customer(String name, String contact, int pax, String date, String arrivalTime, String duration) {
		super.name = name;
		super.contact = contact;
		this.pax = pax;
		this.date = date;
		this.arrivalTime = arrivalTime;
		this.setDuration(duration);
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

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}
}