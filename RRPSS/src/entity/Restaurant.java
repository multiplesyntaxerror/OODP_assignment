package entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import utils.Database;

public class Restaurant {
	
	/** The Constant ITEMSFILENAME for menu item data path. */
	private static final String TABLEFILENAME = "res/Table.txt";
	
	/** The Constant SETSFILENAME for promotional set data path. */
	private static final String STAFFFILENAME = "res/Staff.txt";
	
	/** The menu items list of all 3 types. */
	private ArrayList<Table> tableList = new ArrayList<Table>();
	
	public Restaurant() {
		callRead();
	}
	
	public ArrayList<Table> getTableList() {
		return tableList;
	}

	public void updateRestaurantTables() {
		callWrite();
		callRead();
	}
	
	public void showAvailableTable() {
		int previous = 0;
        for (int i = 0 ; i < tableList.size() ; i++) {
        	Table table = tableList.get(i);

        	if (table.getNoOfSeat() != previous) {
        		Database.getGui().displayStringsB("");
        		Database.getGui().displayStrings(table.getNoOfSeat() + " Seater :");
        	}
        	if (!table.isOccupied() && !table.isReserved()) {
        		Database.getGui().displayStrings("(" + table.getTableId() + ")");
        	}
        	previous = table.getNoOfSeat();
        }
	}
	
	private void readTable() throws IOException {

		ArrayList<String> stringArray = (ArrayList<String>) Database.getRwFile().read(TABLEFILENAME);
		
		tableList.clear();
		
        for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st, Database.getSeparator());	
			int tableId = Integer.parseInt(star.nextToken().trim());
			int noOfSeat = Integer.parseInt(star.nextToken().trim());
			Boolean occupied = Boolean.parseBoolean(star.nextToken().trim());
			Boolean reserved = Boolean.parseBoolean(star.nextToken().trim()); 
								
	        Table table = new Table(tableId, noOfSeat, occupied, reserved);
	        tableList.add(table);
		}
	}
	
	private void writeTable() throws IOException {
		List alw = new ArrayList();

		for (int i = 0; i < tableList.size(); i++) {
			Table table = (Table) tableList.get(i);
			StringBuilder st = new StringBuilder();
			st.append(table.getTableId());
			st.append(Database.getSeparator());
			st.append(table.getNoOfSeat());
			st.append(Database.getSeparator());
			st.append(table.isOccupied());
			st.append(Database.getSeparator());
			st.append(table.isReserved());
			alw.add(st.toString());
		}
		Database.getRwFile().write(TABLEFILENAME, alw);
	}
	
	private boolean callRead() {
		try {
			readTable();
			return true;
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
		return false;

	}

	private boolean callWrite() {
		try {
			writeTable();
			return true;
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
		return false;
	}

}
