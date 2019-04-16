/*
 * 
 */
package entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import utils.Database;

/**
 * The Class Restaurant.
 */
public class Restaurant {
	
	/** The Constant TABLEFILENAME for table data path. */
	private static final String TABLEFILENAME = "res/Table.txt";
	
	/** The Constant STAFFFILENAME for staff data path. */
	private static final String STAFFFILENAME = "res/Staff.txt";

	/** The Constant value of TABLE. */
	private static final int TABLE = 1;

	/** The Constant value of STAFF. */
	private static final int STAFF = 2;

	/** The list tables in restaurant. */
	private ArrayList<Table> tableList = new ArrayList<Table>();

	/** The list staff that are allowed to use the system. */
	private ArrayList<Staff> staffList = new ArrayList<Staff>();
	
	/**
	 * Instantiates a new restaurant.
	 */
	public Restaurant() {
		callRead(TABLE+STAFF);
	}
	
	/**
	 * Gets the table list.
	 *
	 * @return the table list
	 */
	public ArrayList<Table> getTableList() {
		return tableList;
	}
	
	/**
	 * Update restaurant tables.
	 */
	public void updateRestaurantTables() {
		callWrite(TABLE);
		callRead(TABLE);
	}
	
	/**
	 * Gets the table ID by number of people.
	 *
	 * @param pax the number of people 
	 * @return the table ID
	 */
	public int getTableIdByPax(int pax) {
		
        for (int i = 0 ; i < tableList.size() ; i++) {
        	Table table = tableList.get(i);
        	if (table.getNoOfSeat() >= pax && !table.isReserved() && !table.isOccupied()) {
        		return table.getTableId();
        	}
        }
		return 0;
	}
	
	/**
	 * Shows the available table for booking.
	 */
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
        Database.getGui().displayStringsB("");
	} 
	
	/**
	  *Check if there are available tables for booking
	  *
	  */
	public int countAvailableTable() {
		int availableTables = tableList.size();
        for (int i = 0 ; i < tableList.size() ; i++) {
        	Table table = tableList.get(i);

        	if (table.isOccupied() || table.isReserved()) {
        		availableTables--;
        	}
        }
        return availableTables;
	} 
	
	/**
	 * Reads the table from the database Table.txt file in resource folder and store them in system.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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

	/**
	 * Writes the data from system into the database Table.txt file in resource folder
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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

	/**
	 * Gets the staff list.
	 *
	 * @return the staff list
	 */
	public ArrayList<Staff> getStaffList() {
		return staffList;
	}
	
	/**
	 * Allows staff to identify themselves from the list.
	 *
	 * @param text string for the last option (back/exit)
	 * @return the staff
	 */
	public Staff pickStaff(String text) {

		Database.getGui().displayStringsB("");
		Staff staff = null;
		String listOfStaff[] = new String[countStaff() + 1];
				
		for (int i = 0; i < staffList.size(); i++) {
			Staff st = (Staff) staffList.get(i);
			listOfStaff[i] = "\t" + st.getName() + " (" + st.getJobTitle() + ")";
		}
		
		listOfStaff[listOfStaff.length - 1] = "\t" + text;

		int choice = Database.getGui().detectChoice(listOfStaff);
		
		int count = 1;
		for (int i = 0; i < staffList.size(); i++) {
			if (choice == count) {
				staff = (Staff) staffList.get(i);
				return staff;
			}
			count++;
		}
		return staff;
	}
	
	/**
	 * Count the total number of staff.
	 *
	 * @return the total number of staff
	 */
	public int countStaff() {
		return staffList.size();
	}
	
	/**
	 * Reads the staff from the database Staff.txt file in resource folder and store them in system.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void readStaff() throws IOException {

		ArrayList<String> stringArray = (ArrayList<String>) Database.getRwFile().read(STAFFFILENAME);
		
		staffList.clear();
		
        for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st, Database.getSeparator());	
			String name = star.nextToken().trim();
			String contact = star.nextToken().trim();
			int employeeId = Integer.parseInt(star.nextToken().trim());
			String jobTitle = star.nextToken().trim();
			double salary = Double.parseDouble(star.nextToken().trim());
								
	        Staff staff = new Staff(name, contact, employeeId, jobTitle, salary);
	        staffList.add(staff);
		}
	}
	
	/**
	 * Writes the data from system into the database Table.txt file in resource folder
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void writeStaff() throws IOException {
		List alw = new ArrayList();

		for (int i = 0; i < staffList.size(); i++) {
			Staff staff = (Staff) staffList.get(i);
			StringBuilder st = new StringBuilder();
			st.append(staff.getName());
			st.append(Database.getSeparator());
			st.append(staff.getContact());
			st.append(Database.getSeparator());
			st.append(staff.getEmployeeId());
			st.append(Database.getSeparator());
			st.append(staff.getJobTitle());
			st.append(Database.getSeparator());
			st.append(staff.getSalary());
			alw.add(st.toString());
		}
		Database.getRwFile().write(STAFFFILENAME, alw);
	}
	
	/**
	 * Reads the database in resource.
	 *
	 * @return true, if read successful
	 */
	private boolean callRead(int choice) {
		try {
			switch(choice) {
				case 1:
					readTable();
				case 2:
					readStaff();
				case 3:
					readTable();
					readStaff();
			}
			return true;
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
		return false;

	}

	/**
	 * Writes the database with new data.
	 *
	 * @return true, if write successful
	 */
	private boolean callWrite(int choice) {
		try {
			switch(choice) {
			case 1:
				writeTable();
			case 2:
				writeStaff();
			case 3:
				writeTable();
				writeStaff();
		}
			return true;
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
		return false;
	}

}
