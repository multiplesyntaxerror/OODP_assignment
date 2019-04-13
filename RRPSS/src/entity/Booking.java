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

public class Booking implements BookingInterface {

	private static final String BOOKFILENAME = "res/Booking.txt";

	private ArrayList<Customer> bookingList = new ArrayList<Customer>();

	public Booking() {
		callRead();
	}

	public ArrayList<Customer> getBooking() {
		return bookingList;
	}

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

	public int customerExistReturnIndex(String contact) {
		for (Customer i : bookingList) {
			if (i.getContact().equalsIgnoreCase(contact)) {
				return bookingList.indexOf(i);
			}
		}
		return -1;
	}

	@Override
	public boolean deleteBooking(Customer customer) {
		bookingList.remove(customer);
		callWrite();
		callRead();
		return true;
	}
	
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
					System.out.println("sth deleted");
				}
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

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

	public void printBookings() {
		Database.getGui().displayStringsB("");
		for (int i = 0; i < bookingList.size(); i++) {
			Customer customer = (Customer) bookingList.get(i);
			System.out.println("Name: " + customer.getName() + "\tPax: " + customer.getPax());
			System.out.println("Contact: " + customer.getContact());
			System.out.println("Date/Time: " + customer.getDate() + " " + customer.getArrivalTime());
			System.out.println();
		}
	}

	public Customer pickBooking(String text) {

		Database.getGui().displayStringsB("");
		
		Customer customer = null;
		
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
	
	public int countBooking() {
		return bookingList.size();
	}
	
	public ArrayList<Customer> readBooking() throws IOException {

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
		return bookingList;
	}

	public void writeBooking() throws IOException {
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
	
	private boolean callRead() {
		try {
			readBooking();
			return true;
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
		return false;

	}

	private boolean callWrite() {
		try {
			writeBooking();
			return true;
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
		return false;
	}
	
}
