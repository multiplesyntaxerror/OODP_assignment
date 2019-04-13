package controller;

import java.util.Scanner;

import utils.Database;
import entity.Bill;
import entity.OrderItem;

/**
 * The Class BillController.
 */
public class BillController extends Controller {
	public void run(Database db) {

		setDb(db);
		setGui(db.getGui());

		int choice;

		String[] billOptions = { "Print Bill Invoice", "Print Sales Revenue Report", "Back" };

		getGui().displayTitle("Billing Option");
		choice = getGui().detectChoice(billOptions);

		switch (choice) {
		case 1:
			printBill();
			break;
		case 2:
			printSalesRevenueReport();
			break;
		case 3:
			getGui().displayStringsB("Returning ...");
			return;
		}

	}

	public void printBill() {

		Scanner sc = new Scanner(System.in);
		getGui().displayTitle("Billing Interface");

		getGui().displayStringsB("Enter table number: ");
		int tablenum = sc.nextInt();
		Bill bill = new Bill();

		int printedId = bill.printBillInvoice(tablenum, getDb().getOrder().getAllOrders());
		if (printedId == 0) {
			getGui().displayStringsB("The table has no order in place");
		} else {
			getDb().getOrder().getAllOrders().get(printedId - 1).setPrintedInvoice();
			getDb().getRestaurant().getTableList().get(tablenum).setOccupied(false);
			getDb().getOrder().callWrite();
		}

	};

	public void printSalesRevenueReport() {
		Scanner sc = new Scanner(System.in);

		getGui().displayTitle("Sales Report Interface");
		String[] salesOptions = { "Print by Day", "Print by Month" };
		int choice = getGui().detectChoice(salesOptions);

		Bill bill = new Bill();
		if (choice == 1) {
			getGui().displayStringsB("Enter date in format DD eg. 04 for 4th day of the month");
		} else if (choice == 2) {
			getGui().displayStringsB("Enter date in format MM eg. 04 for April");
		}
		String userinputdate = sc.nextLine().trim();
		Boolean report = bill.printSalesReport(choice, userinputdate, getDb().getOrder().getAllOrders());
		if (!report) {
			getGui().displayStringsB("Did not print successfully");
		} else {
			getGui().displayStringsB("Printed successfully");
		}

	};
}
