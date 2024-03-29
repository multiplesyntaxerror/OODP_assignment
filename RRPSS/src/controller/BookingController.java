package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import entity.Booking;
import entity.Customer;
import utils.Database;

/**
 * The Class BookingController.
 */
public class BookingController extends Controller {

	/**
	 * Runs the booking options.
	 *
	 * @param db the database
	 */
	public void run(Database db) {

		setDb(db);
		setGui(db.getGui());

		int choice;

		String[] menuOptions = { 
				"Display All Bookings", 
				"Display All Available Tables", 
				"Create Reservation", 
				"Remove Reservation",
				"Back" };

		getGui().displayTitle("Booking Option");
		choice = getGui().detectChoice(menuOptions);

		switch (choice) {
		case 1:
			getDb().getBooking().printBookings();
			break;
		case 2:
			getDb().getRestaurant().showAvailableTable();
			break;
		case 3:
			createBooking();
			break;
		case 4:
			deleteBooking();
			return;
		case 5:
			getGui().displayStringsB("Returning ...");
			break;
		}

	}
	
	/**
	 * Creates a booking.
	 */
	public void createBooking() {

		int currentHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		boolean nonBooking = true;
		if ((currentHour >= 11 && currentHour < 15) || (currentHour >= 18 && currentHour < 22)) {
			nonBooking = false;
		}

		//Remove comment for testing
//		nonBooking = false;
		
		if (!nonBooking) {
			Scanner sc = new Scanner(System.in);
			getGui().displayTitle("Create Reservation");
			Customer customer = null;

			while (true) { 
				if(getDb().getRestaurant().countAvailableTable() == 0) {
					getGui().displayStringsB("SYSTEM MESSAGE: All The Tables Are Occupied At The Moment.\n");
					return;
				}
				String name;
				do {
					getGui().displayStrings("Enter Customer Name: ");
					name = sc.nextLine();
					if (name.isEmpty())
						getGui().displayStringsB("SYSTEM MESSAGE: Please Enter Something.\n");
					if (!name.matches("[a-zA-Z\\s]+"))
						getGui().displayStringsB("SYSTEM MESSAGE: Your Input Is Invalid.\n");
				} while (name.isEmpty() || !name.matches("[a-zA-Z\\s]+"));

				customer = getDb().getBooking().getCustomer(name);

				if (customer == null) {

					String contact;
					do {
						getGui().displayStrings("Enter Customer Contact: ");
						contact = sc.nextLine();
						if (contact.isEmpty())
							getGui().displayStringsB("SYSTEM MESSAGE: Please Enter Something.\n");
						if (!contact.matches("[0-9]{8}+"))
							getGui().displayStringsB("ERROR: Please Enter A Valid Contact.\n");
					} while (contact.isEmpty() || !contact.matches("[0-9]{8}+"));

					int pax = 0;
					do {
						try {
							getGui().displayStrings("Enter Number Of Customers: ");
							pax = sc.nextInt();
							if (pax <= 0)
								getGui().displayStringsB("SYSTEM MESSAGE: Pax Cannot Be Negative Or Zero\n");
							if (pax>10)
								getGui().displayStringsB("SYSTEM MESSAGE: Pax Cannot Be More Than 10\n");
						} catch (InputMismatchException e) {
							getGui().displayStringsB("SYSTEM MESSAGE: Your Input Is Invalid.\n");
							sc.nextLine();
						}
					} while (pax <= 0 || pax>10);
					
					int tableId = getDb().getRestaurant().getTableIdByPax(pax);
					if (tableId == 0) {
						getGui().displayStringsB("There Are No More Tables With " + pax +" Seats Left.\n");
					}
					else {
						sc.nextLine();
						Date curDate = new Date();					
						String dayPattern = "dd-MM-yyyy";
						SimpleDateFormat simpleDateFormat = new SimpleDateFormat(dayPattern);
						String inputDate = null;
						boolean dateFormat = false;
						long diffInDay;
						do {
							try {

								getGui().displayStrings("Enter Reservation Date(DD-MM-YYYY): ");
								inputDate = sc.nextLine();
								while (!inputDate.matches("([1-9]|0[1-9]|1[0-9]|2[0-9]|3[0-1])-([1-9]|0[1-9]|1[0-2])-(1[0-9][0-9][0-9]|2[0-9][0-9][0-9])")) {
									getGui().displayStringsB("SYSTEM MESSAGE: Your Input Is Invalid.\n");
									getGui().displayStrings("Enter Reservation Date(dd-mm-yyyy): ");
									inputDate = sc.nextLine();
								}
								
								Date date = simpleDateFormat.parse(inputDate);
								diffInDay = TimeUnit.DAYS.convert((date.getTime() - curDate.getTime()), TimeUnit.MILLISECONDS);
								if (diffInDay >= 30 || diffInDay < 0) {
									getGui().displayStringsB("SYSTEM MESSAGE: Reservation Only Has A Max Range Of 30 Days\n");
								}
								else {
									dateFormat = true;
								}
							} catch (ParseException e) {
								getGui().displayStringsB("SYSTEM MESSAGE: Please Enter In Specified Format(DD-MM-YYYY)\n");
							}
							if (inputDate.isEmpty())
								getGui().displayStringsB("SYSTEM MESSAGE: Please Enter Something.\n");
						} while (inputDate.isEmpty() || dateFormat == false);
	
						String timePattern = "hh:mm a";
						SimpleDateFormat simpleTimeFormat = new SimpleDateFormat(timePattern);
						String inputTime = null;
						boolean timeFormat = false;
						long diffInHour;
						do {
							try {
	
								getGui().displayStringsB("Opening Hours Are 11am-3pm and 6pm-10pm");
								getGui().displayStrings("Enter Reservation Time(hh:mm AM/PM): ");
								inputTime = sc.nextLine();
								while (!inputTime.matches("([1-9]|0[1-9]|1[0-2]):([0-9]|0[0-9]|1[0-9]|2[0-9]|3[0-9]|4[0-9]|5[0-9]|60) (am|AM|pm|PM)")) {
									getGui().displayStringsB("SYSTEM MESSAGE: Your Input Is Invalid.\n");
									getGui().displayStrings("Enter Reservation Time(hh:mm AM/PM): ");
									inputTime = sc.nextLine();
								}
								
								Date time = simpleTimeFormat.parse(inputTime);
								diffInHour = TimeUnit.HOURS.convert(time.getTime(), TimeUnit.MILLISECONDS);
								if ((diffInHour >= 3 && diffInHour < 7) || (diffInHour >= 10 && diffInHour < 14)) {
									timeFormat = true;
								}
								else {
									getGui().displayStringsB("SYSTEM MESSAGE: The Time Entered Is Not In Within The Opening Hours\n");
								}
							} catch (ParseException e) {
								getGui().displayStringsB("Please Enter In Specified Format(hh:mm (AM/PM))");
							}
							if (inputTime.isEmpty())
								getGui().displayStringsB("SYSTEM MESSAGE: Please Enter Something.\n");
						} while (inputTime.isEmpty() || timeFormat == false);
	
						customer = new Customer(name, contact, tableId, pax, inputDate, inputTime);
						getDb().getRestaurant().getTableList().get(tableId - 1).setReserved(true);
						getDb().getRestaurant().updateRestaurantTables();
						
						boolean success = getDb().getBooking().createBooking(customer);
						if (success)
							getGui().displayStringsB("SYSTEM MESSAGE: Reservation Successful");
						else if (!success)
							getGui().displayStringsB("SYSTEM MESSAGE: Reservation Not Successful");
						return;
					}
				} else
					getGui().displayStringsB("SYSTEM MESSAGE: Reservation Already Exist.\n");
				sc.nextLine();
			}
		}
		else {
			getGui().displayStringsB("SYSTEM MESSAGE: Reservation Is Unavaliable At This Timing.\n");
		}
		
	}

	/**
	 * Delete a booking.
	 */
	public void deleteBooking() {

		getGui().displayTitle("Removing Reservation");
		getGui().displayStringsB("Choose Reservation To Remove: ");
		Customer customer = getDb().getBooking().pickBooking("Exit");
		
		if (customer != null) {
			boolean success = getDb().getBooking().deleteBooking(customer);
			if (success)
				getGui().displayStringsB("SYSTEM MESSAGE: Reservation Successfully Deleted");
			else if (!success)
				getGui().displayStringsB("SYSTEM MESSAGE: Reservation Not Deleted");
		}
		else {
			getGui().displayStringsB("SYSTEM MESSAGE: There Are No Reservation In The System.");
		}

	}
}