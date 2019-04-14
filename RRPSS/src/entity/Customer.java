package entity;

// TODO: Auto-generated Javadoc
/**
 * The Class Customer.
 */
public class Customer extends Person {

	/** The tableid. */
	private int tableid;
	
	/** The pax. */
	private int pax;
	
	/** The date. */
	private String date;
	
	/** The arrival time. */
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
	 * Gets the tableid.
	 *
	 * @return the tableid
	 */
	public int getTableid() {
		return tableid;
	}

	/**
	 * Sets the tableid.
	 *
	 * @param tableid the new tableid
	 */
	public void setTableid(int tableid) {
		this.tableid = tableid;
	}

	/**
	 * Gets the pax.
	 *
	 * @return the pax
	 */
	public int getPax() {
		return pax;
	}

	/**
	 * Sets the pax.
	 *
	 * @param pax the new pax
	 */
	public void setPax(int pax) {
		this.pax = pax;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(String date) {
		this.date = date;
	}


	/**
	 * Gets the arrival time.
	 *
	 * @return the arrival time
	 */
	public String getArrivalTime() {
		return arrivalTime;
	}

	/**
	 * Sets the arrival time.
	 *
	 * @param arrivalTime the new arrival time
	 */
	public void setArrivalTime(String arrivalTime) {
		this.arrivalTime = arrivalTime;
	}
}