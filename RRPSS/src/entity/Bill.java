
package entity;

import java.util.ArrayList;
import java.util.List;

import service.BillingInterface;

/**
 * The Class Bill.
 */
public class Bill implements BillingInterface {

	/** The Constant GST for receipt. */
	private static final double GST = 0.17;
	
	/** The sales list. */
	private ArrayList<String> salesDatabase = new ArrayList<String>();

	/**
	 * Gets the GST.
	 *
	 * @return the GST
	 */
	public double getGst() {
		return GST;
	}

	/* (non-Javadoc)
	 * @see service.BillingInterface#getBill(int, java.util.ArrayList)
	 */
	public OrderItem getBill(int tablenum, ArrayList<OrderItem> orderList) {
		OrderItem order = null;
		for (int i = 0; i < orderList.size(); i++) {
			if (tablenum == orderList.get(i).getTableId() && !orderList.get(i).getPrintedInvoice()) {
				order = orderList.get(i);
				return order;
			}
		}
		return order;
	}

	/* (non-Javadoc)
	 * @see service.BillingInterface#getSalesReport(int, java.lang.String, java.util.ArrayList)
	 */
	public List<SalesItem> getSalesReport(int choice, String userdate, ArrayList<OrderItem> orderList) {

		ArrayList<SalesItem> saleslist = new ArrayList<SalesItem>();
		Boolean dataAvailable = false;
		for (int i = 0; i < salesDatabase.size(); i++) {
			String menuitem = salesDatabase.get(i);
			SalesItem tempsalesitem = new SalesItem();
			tempsalesitem.setItemname(menuitem);
			int qtyOrdered = 0;
			double price = 0;
			boolean ispromo = false;
			OrderItem temporder;
			for (int j = 0; j < orderList.size(); j++) {
				temporder = orderList.get(j);
				char[] getperiod = new char[20];
				if (choice == 1) {
					temporder.getDate().getChars(0, 10, getperiod, 0);
				} else if (choice == 2) {
					temporder.getDate().getChars(3, 10, getperiod, 0);
				} else if (choice == 3) {
					temporder.getDate().getChars(6, 10, getperiod, 0);
				}
				String period = new String(getperiod).trim();

				if (period.equals(userdate)) {
					dataAvailable = true;
					ArrayList<MenuItem> menulist = temporder.getAlaCarte();
					ArrayList<PromoSet> setlist = temporder.getPromoSet();

					for (int k = 0; k < menulist.size(); k++) {
						if (menuitem.equals(menulist.get(k).getName())) {
							qtyOrdered += menulist.get(k).getOrderedQuantity();
							price = menulist.get(k).getPrice();
						}
					}
					for (int p = 0; p < setlist.size(); p++) {
						if (menuitem.equals(Integer.toString(setlist.get(p).getSetID()))) {
							qtyOrdered += setlist.get(p).getOrderedQuantity();
							price = setlist.get(p).getSetPrice();
							ispromo = true;
						}
					}
				}
			}
			tempsalesitem.setPromo(ispromo);
			tempsalesitem.setPrice(price);
			tempsalesitem.setQuantitySold(qtyOrdered);
			saleslist.add(tempsalesitem);
		}
		if (dataAvailable) {
			return saleslist;
		} else {
			return null;
		}

	}

	/* (non-Javadoc)
	 * @see service.BillingInterface#getSalesDatabase(entity.Menu)
	 */
	public ArrayList<String> getSalesDatabase(Menu menu) {

		ArrayList<MainCourse> mcList = (ArrayList<MainCourse>) menu.getMenuList().get(0);
		ArrayList<Dessert> dsList = (ArrayList<Dessert>) menu.getMenuList().get(1);
		ArrayList<Drinks> drList = (ArrayList<Drinks>) menu.getMenuList().get(2);

		ArrayList<PromoSet> promoList = (ArrayList<PromoSet>) menu.getPromoSetList();

		int i = 0;
		for (; i < mcList.size(); i++) {
			salesDatabase.add(mcList.get(i).getName().trim());
		}
		for (i = 0; i < drList.size(); i++) {
			salesDatabase.add(drList.get(i).getName().trim());
		}
		for (i = 0; i < dsList.size(); i++) {
			salesDatabase.add(dsList.get(i).getName().trim());
		}
		for (i = 0; i < promoList.size(); i++) {
			salesDatabase.add(Integer.toString(promoList.get(i).getSetID()).trim());
		}

		return salesDatabase;

	}

}
