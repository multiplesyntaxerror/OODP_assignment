package entity;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.time.*;

import service.BookingInterface;
import utils.Database;
import utils.ReadWriteFile;

/**
 * The Class Booking.
 */
public class Booking implements BookingInterface {

	/** The Constant BOOKFILENAME for booking data path.  */
	private static final String BOOKFILENAME = "res/Booking.txt";

	/** The list of bookings. */ 
	private ArrayList<Customer> bookingList = new ArrayList<Customer>();

	/**
	 * Instantiates a new booking.
	 */
	public Booking() {
		callRead();
	}

	/**
	 * Gets the booking list.
	 *
	 * @return the booking list
	 */
	public ArrayList<Customer> getBooking() {
		return bookingList;
	}

	/* (non-Javadoc)
	 * @see service.BookingInterface#createBooking(entity.Customer)
	 */
	@Override
	public boolean createBooking(Customer customer) {
		int index = customerExistReturnIndex(customer.getContact());
		if (index == -1) {
			bookingList.add(customer);
			callWrite();
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see service.BookingInterface#deleteBooking(entity.Customer)
	 */
	@Override
	public boolean deleteBooking(Customer customer) {
		bookingList.remove(customer);
		callWrite();
		callRead();
		return true;
	}

	/**
	 * Checks existence of customer and return its index.
	 *
	 * @param contact the customer
	 * @return the index of the customer
	 */
	public int customerExistReturnIndex(String contact) {
		for (Customer i : bookingList) {
			if (i.getContact().equalsIgnoreCase(contact)) {
				return bookingList.indexOf(i);
			}
		}
		return -1;
	}
	
	/**
	 * Check and clear reservation if reservation exceed 30 minutes.
	 */
	public void checkAndClearReservation() {

		Date curDate = new Date();
		for (int i = 0; i < bookingList.size(); i++) {
			Customer cus = (Customer) bookingList.get(i);
			SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm a");
			Date time;
			try {
				time = simpleTimeFormat.parse(cus.getDate() + " " + cus.getArrivalTime());
				long mins = TimeUnit.MINUTES.convert((curDate.getTime() - time.getTime()), TimeUnit.MILLISECONDS);
				if (mins >= 30) {
					deleteBooking(cus);
//					Database.getGui().displayStringsB("sth deleted");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Gets the customer by name.
	 *
	 * @param name the name of the customer
	 * @return the customer
	 */
	public Customer getCustomer(String name) {
		Customer customer = null;
		for (int i = 0; i < bookingList.size(); i++) {
			if (bookingList.get(i).getName().equalsIgnoreCase(name)) {
				return customer;
			} else {
				customer = null;
			}
		}
		return customer;
	}

	/**
	 * Prints all the reservations.
	 */
	public void printBookings() {
		Database.getGui().displayStringsB("");

		if(bookingList.size() == 0) {
			Database.getGui().displayStringsB("There Are No Reservation In The System.");
		}
		for (int i = 0; i < bookingList.size(); i++) {
			Customer customer = (Customer) bookingList.get(i);
			Database.getGui().displayStringsB("Name: " + customer.getName() + "\tPax: " + customer.getPax());
			Database.getGui().displayStringsB("Contact: " + customer.getContact());
			Database.getGui().displayStringsB("Date/Time: " + customer.getDate() + " " + customer.getArrivalTime());
			Database.getGui().displayStringsB("");
		}
	}

	/**
	 * Allows user to pick a reservations from the list.
	 *
	 * @param text string for the last option (back/exit)
	 * @return a customer
	 */
	public Customer pickBooking(String text) {

		Database.getGui().displayStringsB("");
		
		Customer customer = null;
		
		if(bookingList.size() == 0) {
			return customer;
		}
		
		String listOfBooking[] = new String[countBooking() + 1];
		
		for (int i = 0; i < bookingList.size(); i++) {
			Customer cus = (Customer) bookingList.get(i);
			listOfBooking[i] = "\tName: " + cus.getName() + "(" + cus.getContact() + ")\tPax: " + cus.getPax() + "\n\tDate: " + cus.getDate() + " " + cus.getArrivalTime() + "\n";
		}
		
		listOfBooking[listOfBooking.length - 1] = "\t" + text;

		int choice = Database.getGui().detectChoice(listOfBooking);
		
		int count = 1;
		for (int i = 0; i < bookingList.size(); i++) {
			if (choice == count) {
				customer = (Customer) bookingList.get(i);
				return customer;
			}
			count++;
		}
		return customer;
	}
	
	/**
	 * Count the number of total reservations.
	 *
	 * @return the total number of reservations
	 */
	public int countBooking() {
		return bookingList.size();
	}
	
	/**
	 * Reads the bookings from the database Booking.txt file in resource folder and store them in system.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void readBooking() throws IOException {

		ArrayList<String> stringArray = (ArrayList<String>) Database.getRwFile().read(BOOKFILENAME);

		bookingList.clear();
		
		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st, Database.getSeparator());
			String name = star.nextToken().trim();
			String contact = star.nextToken().trim();
			int tableId = Integer.parseInt(star.nextToken());
			int pax = Integer.parseInt(star.nextToken());
			String date = star.nextToken().trim();
			String atime = star.nextToken().trim();
			Customer customer = new Customer(name, contact, tableId ,pax, date, atime);
			bookingList.add(customer);
		}
	}

	/**
	 * Writes the data from system into the database Booking.txt file in resource folder.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void writeBooking() throws IOException {
		List alw = new ArrayList();

		for (int i = 0; i < bookingList.size(); i++) {
			Customer customer = (Customer) bookingList.get(i);
			StringBuilder st = new StringBuilder();
			st.append(customer.getName().trim());
			st.append(Database.getSeparator());
			st.append(customer.getContact().trim());
			st.append(Database.getSeparator());
			st.append(customer.getTableid());
			st.append(Database.getSeparator());
			st.append(customer.getPax());
			st.append(Database.getSeparator());
			st.append(customer.getDate().trim());
			st.append(Database.getSeparator());
			st.append(customer.getArrivalTime().trim());
			alw.add(st.toString());
		}
		Database.getRwFile().write(BOOKFILENAME, alw);
	}
	
	/**
	 * Reads the database in resource.
	 *
	 * @return true, if read successful
	 */
	private boolean callRead() {
		try {
			readBooking();
			return true;
		} catch (IOException e) {
			Database.getGui().displayStringsB("IOException > " + e.getMessage());
		}
		return false;

	}

	/**
	 * Writes the database with new data.
	 *
	 * @return true, if write successful
	 */
	private boolean callWrite() {
		try {
			writeBooking();
			return true;
		} catch (IOException e) {
			Database.getGui().displayStringsB("IOException > " + e.getMessage());
		}
		return false;
	}
	
}
