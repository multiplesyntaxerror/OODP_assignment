package entity;

public class Table {

	private int tableId;
	private int noOfSeat;
	private boolean occupied;
	private boolean reserved;

	public Table() {
		occupied = false;
		reserved = false;
	}

	public boolean getOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public boolean getReserved() {
		return reserved;
	}

	public void setReserved(boolean reserved) {
		this.reserved = reserved;
	}

	public int getNoOfSeat() {
		return noOfSeat;
	}

	public void setNoOfSeat(int noOfSeat) {
		this.noOfSeat = noOfSeat;
	}

	public int getTableId() {
		return tableId;
	}

	public void setTableId(int tableid) {
		this.tableId = tableid;
	}

}