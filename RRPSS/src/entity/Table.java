package entity;

/**
 * The Class Table.
 */
public class Table {

	/** The table id. */
	private int tableId;
	
	/** The no of seat. */
	private int noOfSeat;
	
	/** The occupied. */
	private boolean occupied;
	
	/** The reserved. */
	private boolean reserved;

	/**
	 * Instantiates a new table.
	 *
	 * @param tableId the table id
	 * @param noOfSeat the no of seat
	 * @param occupied the occupied
	 * @param reserved the reserved
	 */
	public Table(int tableId, int noOfSeat, boolean occupied, boolean reserved) {
		this.tableId = tableId;
		this.noOfSeat = noOfSeat;
		this.occupied = occupied;
		this.reserved = reserved;
	}

	/**
	 * Gets the table id.
	 *
	 * @return the table id
	 */
	public int getTableId() {
		return tableId;
	}

	/**
	 * Sets the table id.
	 *
	 * @param tableid the new table id
	 */
	public void setTableId(int tableid) {
		this.tableId = tableid;
	}

	/**
	 * Gets the no of seat.
	 *
	 * @return the no of seat
	 */
	public int getNoOfSeat() {
		return noOfSeat;
	}

	/**
	 * Sets the no of seat.
	 *
	 * @param noOfSeat the new no of seat
	 */
	public void setNoOfSeat(int noOfSeat) {
		this.noOfSeat = noOfSeat;
	}

	/**
	 * Checks if is occupied.
	 *
	 * @return true, if is occupied
	 */
	public boolean isOccupied() {
		return occupied;
	}

	/**
	 * Sets the occupied.
	 *
	 * @param occupied the new occupied
	 */
	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	/**
	 * Checks if is reserved.
	 *
	 * @return true, if is reserved
	 */
	public boolean isReserved() {
		return reserved;
	}

	/**
	 * Sets the reserved.
	 *
	 * @param reserved the new reserved
	 */
	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}
	

}