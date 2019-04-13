package entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
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
			printBookings();
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
		String contact = customer.getContact();
		int index = customerExistReturnIndex(contact);
		bookingList.remove(index);
		callWrite();
		callRead();
		return true;
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
			System.out.println("Name: " + customer.getName());
			System.out.println("Contact: " + customer.getContact());
			System.out.println("Date: " + customer.getDate());
			System.out.println("Arrival Time: " + customer.getArrivalTime());
			System.out.println("Number of people: " + customer.getPax());
			System.out.println();
		}
	}

	public ArrayList<Customer> readBooking() throws IOException {

		ArrayList<String> stringArray = (ArrayList<String>) Database.getRwFile().read(BOOKFILENAME);

		for (int i = 0; i < stringArray.size(); i++) {
			String st = (String) stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st, Database.getSeparator());
			String name = star.nextToken().trim();
			String contact = star.nextToken().trim();
			int pax = Integer.parseInt(star.nextToken());
			String date = star.nextToken().trim();
			String atime = star.nextToken().trim();
			String duration = star.nextToken().trim();
			Customer customer = new Customer(name, contact, pax, date, atime, duration);
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
			st.append(customer.getPax());
			st.append(Database.getSeparator());
			st.append(customer.getDate().trim());
			st.append(Database.getSeparator());
			st.append(customer.getArrivalTime().trim());
			st.append(Database.getSeparator());
			st.append(customer.getDuration().trim());
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
