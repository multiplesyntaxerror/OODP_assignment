package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import utils.Database;
import entity.Bill;
import entity.MenuItem;
import entity.OrderItem;
import entity.PromoSet;
import entity.SalesItem;

/**
 * The Class BillController.
 */
public class BillController extends Controller {
	
	/**
	 * Runs the billing options.
	 *
	 * @param db the database
	 */
	public void run(Database db) {

		setDb(db);
		setGui(db.getGui());

		int choice;

		String[] billOptions = { 
				"Print Bill", 
				"Print Sales Revenue Report", 
				"Back" };

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

	/**
	 * Prints a bill.
	 * Sets table to vacated
	 */
	public void printBill() {

		Scanner sc = new Scanner(System.in);
		getGui().displayTitle("Printing Bill");

		getGui().displayStrings("Enter Table Number: ");
		while(!sc.hasNextInt())
		{
			getGui().displayStringsB("That Is An Invalid Table Number.");
			sc.next();
		}
		
		int tablenum = sc.nextInt();
		Bill bill = new Bill();

		OrderItem order = bill.getBill(tablenum, getDb().getOrder().getAllOrders());

		if (order == null) {
			getGui().displayStringsB("The Table Has No Order");
		} else {
			
			getGui().displayStringsB("");
			getGui().displayReceipt();
			getGui().displayStringsB("NOM-NOM Restaurant\t\tReceipt #" + String.format("%05d", order.getOrderId()));
			getGui().displayStringsB("50 Nanyang Avenue, Block N4\t S'pore 639798");
			getGui().displayStringsB("Date/Time: " + order.getDate());
			getGui().displayRow("Table No. " + tablenum + "\t\t\tServer: " + order.getStaffName());

			MenuItem item;
			PromoSet set;
			getGui().displayRow("QTY\tITEM\t\t\t\tPRICE");
			for (int i = 0; i < order.getAlaCarte().size(); i++) {
				item = order.getAlaCarte().get(i);
				getGui().displayStringsB(item.getOrderedQuantity() + "\t" + item.getName());
				getGui().displayEnd(item.getPrice() + "");
			}
			for (int j = 0; j < order.getPromoSet().size(); j++) {
				set = order.getPromoSet().get(j);
				getGui().displayStringsB(set.getOrderedQuantity() + "\tSet " + set.getSetID());
				getGui().displayEnd("" + set.getSetPrice());
			}

			order.setTotalPrice();
			getGui().displayRow("");
			getGui().displayStringsB("\t\tSUB-TOTAL:\t\t" + String.format("%.2f", order.getTotalPrice()));
			getGui().displayRow("\t\tGST & Service Charge:\t" + (String.format("%.2f", order.getTotalPrice() * bill.getGst())));
			getGui().displayRow("\tTOTAL:\t\t\t\t$" + (String.format("%.2f", order.getTotalPrice() + order.getTotalPrice() * bill.getGst())));
			getGui().displayStringsB("\tThank You For Dining With Us!");
			getGui().displayReceipt();
			
			getDb().getOrder().updateOrderPrintStatus(order.getOrderId());
			getDb().getRestaurant().getTableList().get(tablenum - 1).setOccupied(false);
			getDb().getRestaurant().updateRestaurantTables();

		}

	};

	/**
	 * Prints the sales revenue report according to day, month or year.
	 */
	public void printSalesRevenueReport() {
		Scanner sc = new Scanner(System.in);

		getGui().displayTitle("Printing Sales Report");
		String[] salesOptions = { "Print By Day", "Print By Month", "Print By Year" };
		int choice = getGui().detectChoice(salesOptions);

		Bill bill = new Bill();
		String tempinputdate = null;
		if (choice == 1) {
			getGui().displayStringsB("Enter Date In Format DD-MM-YYYY eg. 04-04-2019 For 4th Of April 2019");
			tempinputdate = sc.nextLine().trim();
			while (!tempinputdate.matches("([1-31])-([1-12])-([1000-2020])")) {
				getGui().displayStringsB("Invalid Input.\n");
				getGui().displayStringsB("Enter Date In Format DD-MM-YYYY eg. 04-04-2019 For 4th Of April 2019");
				tempinputdate = sc.nextLine().trim();
			}
		} else if (choice == 2) {
			getGui().displayStringsB("Enter Date In Format MM-YYYY eg. 04-2019 The Month Of April 2019");
			tempinputdate = sc.nextLine().trim();
			while (!tempinputdate.matches("([1-12])-([1000-2020])")) {
				getGui().displayStringsB("Invalid Input.\n");
				getGui().displayStringsB("Enter Date In Format MM-YYYY eg. 04-2019 The Month Of April 2019");
				tempinputdate = sc.nextLine().trim();
			}
		} else if (choice == 3) {
			getGui().displayStringsB("Enter Date In Format YYYY eg. 2019 For Year 2019");
			tempinputdate = sc.nextLine().trim();
			while (!tempinputdate.matches("[1000-2020]")) {
				getGui().displayStringsB("Invalid Input.\n");
				getGui().displayStringsB("Enter Date In Format YYYY eg. 2019 For Year 2019");
				tempinputdate = sc.nextLine().trim();
			}
		}
		
		String userinputdate = tempinputdate;
		ArrayList<String> salesDatabase = bill.getSalesDatabase(getDb().getMenu());

		List<SalesItem> salesList = bill.getSalesReport(choice, userinputdate, getDb().getOrder().getAllOrders());

		if (salesList == null) {
			getGui().displayStringsB("No Available Data For The Period Specified.");
		} else {

			getGui().displayRow("\nSales Report For The Period: " + userinputdate);
			double totalRevenue = 0;
			getGui().displayRow("ALA CARTE");
			getGui().displayRow("QTY\tITEM\t\t\t\tREVENUE");

			int i = 0;
			for (; i < salesList.size(); i++) {
				SalesItem salesitem = salesList.get(i);
				if (salesList.get(i).isPromo()) {
					break;
				}
				getGui().displayStringsB(salesitem.getQuantitySold() + "\t" + salesitem.getItemname());
				getGui().displayEnd(String.format("%.2f", (salesitem.getQuantitySold() * salesitem.getPrice())));
				totalRevenue += salesitem.getQuantitySold() * salesitem.getPrice();
			}

			getGui().displayRow("");
			getGui().displayRow("PROMO SET");
			getGui().displayRow("QTY\tITEM\t\t\t\tREVENUE");
			for (; i < salesList.size(); i++) {
				SalesItem promoitem = salesList.get(i);
				getGui().displayStringsB(promoitem.getQuantitySold() + "\t" + promoitem.getItemname());
				getGui().displayEnd(String.format("%.2f", (promoitem.getQuantitySold() * promoitem.getPrice())));
				totalRevenue += promoitem.getQuantitySold() * promoitem.getPrice();
			}

			getGui().displayRow("");
			getGui().displayStringsB("TOTAL REVENUE");
			getGui().displayEnd(String.format("%.2f", totalRevenue));
		}

	};
}
