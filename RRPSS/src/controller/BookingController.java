package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import entity.Customer;
import utils.Database;

public class BookingController extends Controller {

	private static ArrayList<Customer> bookingList;

	public void run(Database db) {

		setDb(db);
		setGui(db.getGui());

		int choice;

		String[] menuOptions = { "Display All Bookings", "Add Booking", "Delete Booking",
				"Display All Available Tables", "Back" };

		getGui().displayTitle("Menu Option");
		choice = getGui().detectChoice(menuOptions);

		switch (choice) {
		case 1:
			getDb().getBooking().printBooking();
			break;
		case 2:
			createBooking();
			break;
		case 3:
			deleteBooking();
			break;
		case 4:
			getDb().getBooking().displayTableAvailable(db);
			break;
		case 5:
			getGui().displayStringsB("Returning ...");
			return;
		}

	}

	/*
	 * more methods
	 * 
	 * disallow reservations for more than a month later (edit createBooking)
	 * 
	 * if time over, table is expired. (edit deleteBooking)
	 * 
	 */

	public void createBooking() {

		/**for opening closing time. commented for testing**/
//		Date curTime = new Date();
//		SimpleDateFormat parser = new SimpleDateFormat("HH");
//		boolean nonBooking=true;
//		try {
//			Date eleven = parser.parse("11");
//			Date fifteen = parser.parse("15");
//			Date eighteen = parser.parse("18");
//			Date twenty2 = parser.parse("22");
//			if ((curTime.after(eleven) && curTime.before(fifteen)) || (curTime.before(twenty2) && curTime.after(eighteen))) {
//				nonBooking = false;
//			}
//			}catch (ParseException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		}

//		if (!nonBooking) {
		if(true) {
			Scanner sc = new Scanner(System.in);
			getGui().displayTitle("Adding New Customer To BookingList");
			Customer customer = null;

			while (true) {
				String name;
				do {
					getGui().displayStrings("Enter Customer Name: ");
					name = sc.nextLine();
					if (name.isEmpty())
						getGui().displayStringsB("Please Enter Something.\n");
				} while (name.isEmpty() || !name.matches("[a-zA-Z]+"));

				customer = getDb().getBooking().getCustomer(name);

				if (customer == null) {

					String contact;
					do {
						getGui().displayStrings("Enter Customer Contact: ");
						contact = sc.nextLine();
						if (contact.isEmpty())
							getGui().displayStringsB("Please Enter Something.\n");
					} while (contact.isEmpty() || !contact.matches("[0-9]+"));

					int pax = 0;
					do {
						try {
							getGui().displayStrings("Enter Number Of Customers:");
							pax = sc.nextInt();
							if (pax <= 0)
								getGui().displayStringsB("Pax Cannot Be Negative Or Zero\n");
							if (pax>10)
								getGui().displayStringsB("Pax Cannot Be More Than 10\n");
						} catch (InputMismatchException e) {
							getGui().displayStringsB("ERROR: Your Input Is Invalid.\n");
							sc.nextLine();
						}
					} while (pax <= 0||pax>10);

					sc.nextLine();

					// clean inputs here
					String pattern = "dd-MM-yyyy";
					SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
					String adate = null;
					boolean format = false;
					Date date = new Date();
					long diff;
					long tmp1;
					do {
						try {
							getGui().displayStrings("Enter Arrival Date(dd-MM-yyyy): ");
							adate = sc.nextLine();
							Date dadate = simpleDateFormat.parse(adate);
							diff = dadate.getTime() - date.getTime();
							tmp1 = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
							if (tmp1 < 30 && tmp1 >= 0) {
								break;
							}
						} catch (ParseException e) {
							System.out.println("Please Enter In Specified Format(dd-mm-yyyy)");
							format = false;
						}
						if (adate.isEmpty())
							getGui().displayStringsB("Please Enter Something.\n");

					} while (adate.isEmpty() || format == false);

					String pattern2 = "hh:mm a";
					SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat(pattern2);
					String atime = null;
					boolean format2 = false;
					do {
						try {
							getGui().displayStrings("Enter Arrival Time(hh:mm (AM/PM)): ");
							atime = sc.nextLine();
							Date datime = simpleDateFormat2.parse(atime);
							format=true;
						} catch (ParseException e) {
							System.out.println("Please Enter In Specified Format(hh:mm (AM/PM))");
							format = false;
						}

						if (atime.isEmpty())
							getGui().displayStringsB("Please Enter Something.\n");
					} while (atime.isEmpty() || format == false);

					
					
					//need to check duration so that when staff tries to make booking 
					//when near closing time, it is rejected.
					//also need duration to determine when table should be removed.
					String pattern3 = "HHmm";
					SimpleDateFormat simpleDateFormat3 = new SimpleDateFormat(pattern3);
					boolean format3 = false;
					String btime=null;
					long diff1,diff2;
					long tmpb1,tmpb2;
					do {
						try {
							getGui().displayStrings("Enter Duration of your booking(HHmm): ");
							btime = sc.nextLine();							
							Date dbtime = simpleDateFormat3.parse(btime);
							
							Date openingtimeam =  simpleDateFormat3.parse("1100");
							Date closingtimeam =  simpleDateFormat3.parse("1500");
							Date openingtimepm =  simpleDateFormat3.parse("1800");
							Date closingtimepm =  simpleDateFormat3.parse("2200");
							
							diff1 = dbtime.getTime() - closingtimeam.getTime();
							diff2 = dbtime.getTime() - closingtimepm.getTime();
							tmpb1 = TimeUnit.DAYS.convert(diff1, TimeUnit.MILLISECONDS);
							tmpb2= TimeUnit.DAYS.convert(diff2, TimeUnit.MILLISECONDS);
							if (tmpb1<=0||tmpb2<=0) {
								break;
							}
							
						} catch (ParseException e) {
							System.out.println("Please Enter In Specified Format(hh:mm)");
							format = false;
						}
						if (btime.isEmpty())
							getGui().displayStringsB("Please Enter Something.\n");
					} while (btime.isEmpty()||format==false);

					customer = new Customer(name, contact, pax, adate, atime, btime);
					boolean success = getDb().getBooking().createBooking(customer);
					if (success)
						getGui().displayStringsB("SYSTEM NOTICE: Booking Successfully Added");
					else if (!success)
						getGui().displayStringsB("SYSTEM ERROR: Booking Not Added");
					return;

				} else
					getGui().displayStringsB("Booking Already Exist.\n");
				sc.nextLine();
			}
		}
	}

	public void deleteBooking() {

		getGui().displayTitle("Deleting Menu Item");
		getGui().displayStringsB("Choose Item To Delete: ");
		Customer customer = getDb().getBooking().getCustomer("Exit");

		if (customer != null) {
			boolean success = getDb().getBooking().deleteBooking(customer);
			if (success)
				getGui().displayStringsB("SYSTEM NOTICE: Booking Deleted Successfully");
			else if (!success)
				getGui().displayStringsB("SYSTEM ERROR: Booking Not Deleted");
		}

	}
}