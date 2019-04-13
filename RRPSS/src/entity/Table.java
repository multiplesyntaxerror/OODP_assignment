package entity;

public class Table {

	private int tableId;
	private int noOfSeat;
	private boolean occupied;
	private boolean reserved;

	public Table(int tableId, int noOfSeat, boolean occupied, boolean reserved) {
		this.tableId = tableId;
		this.noOfSeat = noOfSeat;
		this.occupied = occupied;
		this.reserved = reserved;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableid) {
		this.tableId = tableid;
	}

	public int getNoOfSeat() {
		return noOfSeat;
	}

	public void setNoOfSeat(int noOfSeat) {
		this.noOfSeat = noOfSeat;
	}

	public boolean isOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public boolean isReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}
	

}