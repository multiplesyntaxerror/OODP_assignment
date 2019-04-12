package entity;

public class Customer {

	private String name;
	private String contact;
	private int pax;
	private String date;
	private String atime;
	private int tableid;

	public Customer(String name,String contact,int pax,String date,String atime) {
		this.name=name;
		this.contact=contact;
		this.pax=pax;
		this.date=date;
		this.atime=atime;
	}

	public int getTableid() {
		return tableid;
	}

	public void setTableid(int tableid) {
		this.tableid = tableid;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
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
	public String getATime() {
		return atime;
	}
	public void setATime(String atime) {
		this.atime = atime;
	}	
}