package entity;

/**
 * The Class Customer.
 */
public class Customer extends Person {

	/** The table ID. */
	private int tableid;
	
	/** The number of people at table. */
	private int pax;
	
	/** The reservation date. */
	private String date;
	
	/** The reservation time. */
	private String arrivalTime;

	/**
	 * Instantiates a new customer.
	 *
	 * @param name the name
	 * @param contact the contact
	 * @param tableId the table id
	 * @param pax the pax
	 * @param date the date
	 * @param arrivalTime the arrival time
	 */
	public Customer(String name, String contact, int tableId, int pax, String date, String arrivalTime) {
		super.name = name;
		super.contact = contact;
		this.tableid = tableId;
		this.pax = pax;
		this.date = date;
		this.arrivalTime = arrivalTime;
	}

	/**
	 *
	 * @return the table ID
	 */
	public int getTableid() {
		return tableid;
	}

	/**
	 * Sets the table ID.
	 *
	 * @param tableid the new table ID
	 */
	public void setTableid(int tableid) {
		this.tableid = tableid;
	}

	/**
	 * Gets number of pax.
	 *
	 * @return the number of pax
	 */
	public int getPax() {
		return pax;
	}

	/**
	 * Sets the number of pax.
	 *
	 * @param pax the new number of pax
	 */
	public void setPax(int pax) {
		this.pax = pax;
	}

	/**
	 * Gets the reservation date.
	 *
	 * @return the reservation date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the reservation date.
	 *
	 * @param date the new reservation date
	 */
	public void setDate(String date) {
		this.date = date;
	}


	/**
	 * Gets the reservation time.
	 *
	 * @return the reservation time
	 */
	public String getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * Sets the reservation time.
	 *
	 * @param arrivalTime the new reservation time
	 */
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
}