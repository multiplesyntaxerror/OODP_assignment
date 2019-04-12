package entity;

public class Table {
	private int tableid;
	private int seatno;
	private boolean occupied;
	private boolean reserved;

	public Table(){
		occupied=false;
		reserved=false;
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
	
	public void setSeatno(int i) {
		seatno=i;
	}
	public int getSeatno() {
		return seatno;
	}
	public int getTableid() {
		return tableid;
	}
	public void setTableid(int tableid) {
		this.tableid = tableid;
	}

}