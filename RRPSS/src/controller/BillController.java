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
		
		OrderItem order = bill.getBill(tablenum, getDb().getOrder().getAllOrders());
		
		if(order==null)
		{
			getGui().displayStringsB("The table has no order in place");
		}
		else
		{
			getGui().displayStringsB("Receipt No: "+ String.format("%05d", order.getOrderId()));
			getGui().displayStringsB("NOM-NOM Restaurant");
			getGui().displayStringsB(bill.getLineSeperator());
			getGui().displayStringsB("6 Eu Tong Sen St, 02-82/83");
			getGui().displayStringsB("Singapore 059817");
			getGui().displayStringsB(order.getStaff().getName());
			getGui().displayStringsB(order.getDate()); 
			getGui().displayStringsB("Table: "+tablenum);
			getGui().displayStringsB(bill.getLineSeperator());
			
			MenuItem item;
			PromoSet set;
			for(int i=0;i<order.getAlaCarte().size();i++)
			{
				item = order.getAlaCarte().get(i);
				getGui().displayStringsB(item.getOrderedQuantity()+ bill.getPrintSeperator() +item.getName() + bill.getPrintSeperator() + item.getPrice());
			}
			for(int j =0; j<order.getPromoSet().size();j++)
			{
				set = order.getPromoSet().get(j);
				getGui().displayStringsB(set.getOrderedQuantity()+ bill.getPrintSeperator()+"Set "+set.getSetID() + bill.getPrintSeperator()+ set.getSetPrice());
			}
			
			order.setTotalPrice();
			getGui().displayStringsB(bill.getLineSeperator());
			getGui().displayStringsB("SubTotal: "+String.format("%.2f",order.getTotalPrice()));
			getGui().displayStringsB("GST & Service Charge "+(String.format("%.2f", order.getTotalPrice()*bill.getGst())));
			getGui().displayStringsB(bill.getLineSeperator());
			getGui().displayStringsB("TOTAL: "+(String.format("%.2f",order.getTotalPrice()+order.getTotalPrice()*bill.getGst())));
			getGui().displayStringsB(bill.getLineSeperator());
			
			
			getDb().getOrder().getAllOrders().get(order.getOrderId() - 1).setPrintedInvoice();
			getDb().getRestaurant().getTableList().get(tablenum).setOccupied(false);
			getDb().getRestaurant().updateRestaurantTables();
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
		
		ArrayList<String> salesDatabase = bill.getSalesDatabase(getDb().getMenu());
		
		List<SalesItem> salesList = bill.getSalesReport(choice, userinputdate, getDb().getOrder().getAllOrders());
		
		if(salesList==null)
		{
			getGui().displayStringsB("No available data for the period specified");
		}
		else
		{
			double totalRevenue = 0;
			getGui().displayStringsB("-----Ala Carte-----");
			getGui().displayStringsB(bill.getLineSeperator());
			
			
			int i =0;
			for(;i<salesList.size();i++)
			{
				SalesItem salesitem = salesList.get(i);
				if(salesList.get(i).isPromo())
				{
					break;
				}
				
				getGui().displayStringsB("Item name: "+ salesitem.getItemname()+bill.getPrintSeperator()+"Qty: "+ salesitem.getQuantitySold()+bill.getPrintSeperator()+ "Revenue from this item: "+ String.format("%.2f",(salesitem.getQuantitySold()*salesitem.getPrice())));
				totalRevenue+= salesitem.getQuantitySold()*salesitem.getPrice();
			}
			getGui().displayStringsB("-----Promo Item-----");
			getGui().displayStringsB(bill.getLineSeperator());
			for(;i<salesList.size();i++)
			{
				SalesItem promoitem = salesList.get(i);
				getGui().displayStringsB("Promo Set: "+ promoitem.getItemname()+bill.getPrintSeperator()+"Qty: "+ promoitem.getQuantitySold()+ bill.getPrintSeperator()+"Revenue from this item: "+ String.format("%.2f",(promoitem.getQuantitySold()*promoitem.getPrice())));
				totalRevenue+= promoitem.getQuantitySold()*promoitem.getPrice();
			}
			
			System.out.println("Total Revenue: "+ String.format("%.2f",totalRevenue));
		}
		

	};
}
