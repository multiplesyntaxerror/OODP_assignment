package entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import service.OrderInterface;
import utils.Database;

public class Order implements OrderInterface{
	
	private static final String ITEMSFILENAME = "res/Order.txt";
	
	private ArrayList<OrderItem> allOrders = new ArrayList<OrderItem>();
	
	public ArrayList<OrderItem> getAllOrders(){
		callRead();
		return this.allOrders;
	}

	public int countOrders() {
		callRead();
		return allOrders.size();
	}
	
	public boolean createOrder(OrderItem order) {
		callRead();
		allOrders.add(order);
		callWrite();
		return true;
	}
	
	public boolean updateMenuItemOrder(MenuItem item, int orderId) {
		callRead();
		boolean dupe = false;
		boolean updated = false;
		ArrayList<MenuItem> currentOrder = allOrders.get(orderId-1).getAlaCarte();
		for(int i = 0 ; i < currentOrder.size(); i++) {
			if(currentOrder.get(i).getName().equals(item.getName())){
				allOrders.get(orderId-1).getAlaCarte().get(i).addOrderedQuantity(item.getOrderedQuantity());
				dupe = true; 
				updated = true;
				break;
			} 
		}
		if(dupe == false) {
			allOrders.get(orderId-1).getAlaCarte().add(item);
			updated = true;
		}
		callWrite();
		return updated;
	}

	public boolean removeMenuItemOrder(int orderId, int choice, int qty) {
		callRead();	
		boolean updated = false;
		int index = orderId - 1;
		int itemIndex = choice - 1;
		if(qty <= allOrders.get(index).getAlaCarte().get(itemIndex).getOrderedQuantity()) {
			allOrders.get(index).getAlaCarte().get(itemIndex).addOrderedQuantity(-qty);	
			updated = true;
		}
		else {
			Database.getGui().displayStringsB("The Item Has Not Been Ordered That Many Times!");
		}
		callWrite();
		return updated;
	}
	
	public boolean updatePromoSetOrder(PromoSet set, int orderId) {
		callRead();
		boolean dupe = false;
		boolean updated = false;
		int index = orderId - 1;
		ArrayList<PromoSet> promoSet = allOrders.get(index).getPromoSet();
		for(int i = 0 ; i < promoSet.size(); i++) {
			if(promoSet.get(i).getSetID() == set.getSetID()){
				allOrders.get(index).getPromoSet().get(i).addOrderedQuantity(set.getOrderedQuantity());
				dupe = true;
				updated = true;
				break;
			} 
		} 
		if(dupe == false) {
			allOrders.get(index).getPromoSet().add(set);
			updated = true;
		}
		callWrite();
		return updated;
	}
	
	public boolean removePromoSetOrder(int orderId, int choice, int qty) {
		callRead();	
		boolean updated = false;
		int index = orderId - 1;
		int itemIndex = choice - 1;
		if(qty <= allOrders.get(index).getPromoSet().get(itemIndex).getOrderedQuantity()) {
			allOrders.get(index).getPromoSet().get(itemIndex).addOrderedQuantity(-qty);	
			updated = true;
		}
		else {
			Database.getGui().displayStringsB("The Item Has Not Been Ordered That Many Times");
		}
		callWrite();
		return updated;
	}
	 
	public void printOrder(){
		callRead();
		Database.getGui().displayStringsB("");
		if (allOrders.size() == 0) {
			Database.getGui().displayStringsB("There Are No Oders Yet");
		}
		else {
			for(int i = 0 ; i < allOrders.size();i++) {
				OrderItem order = allOrders.get(i);
				Database.getGui().displayStringsB("Order No. " + order.getOrderId() + " / Table No. " + order.getTableId());
				String bill = "";
				if (order.getPrintedInvoice()) {
					bill = "Billed";
				}
				else {
					bill = "Not Billed";
				}
				Database.getGui().displayStringsB("Served By: " + order.getStaff().getName() + " / " + bill);
				Database.getGui().displayStringsB("Date: \t" + order.getDate());
				Database.getGui().displayRow("QTY\tITEM");
				for(int j = 0; j<allOrders.get(i).getAlaCarte().size();j++) {
					Database.getGui().displayStringsB(order.getAlaCarte().get(j).getOrderedQuantity() + "\t" + order.getAlaCarte().get(j).getName());
				}
				for(int k = 0; k<allOrders.get(i).getPromoSet().size();k++) {
					Database.getGui().displayStringsB(order.getPromoSet().get(k).getOrderedQuantity() + "\tPromoSet " + order.getPromoSet().get(k).getSetID());
				}
				Database.getGui().displayStringsB("");
			}
		}
	}
	
	public OrderItem pickOrderItems(String text) { 
		callRead();
		Database.getGui().displayStringsB("");
		OrderItem order = null;
		
		String listOfOrderItem[] = new String[countOrders() + 1];
		
		int count = 0;
		
		for (int i = 0; i < allOrders.size(); i++) {
			OrderItem oi = allOrders.get(i);
			listOfOrderItem[count] = "\tOrder " + oi.getOrderId() + " " + oi.getDate() + "\n";
			for(int j = 0; j < allOrders.get(i).getAlaCarte().size();j++) {
				listOfOrderItem[count] += "\t" + allOrders.get(i).getAlaCarte().get(j).getOrderedQuantity() + "\t" + allOrders.get(i).getAlaCarte().get(j).getName() + "\n";
			}
			for(int k = 0; k<allOrders.get(i).getPromoSet().size();k++) {
				listOfOrderItem[count] += "\t" + allOrders.get(i).getPromoSet().get(k).getOrderedQuantity() + "\tPromoSet " + allOrders.get(i).getPromoSet().get(k).getSetID() + "\n";
			}
			count++;
		}
		
		listOfOrderItem[count] = "\t" + text;
		
		int choice = Database.getGui().detectChoice(listOfOrderItem);
		
		count = 1;

		for (int i = 0; i < allOrders.size(); i++) {
			if (choice == count) {
				return allOrders.get(i);
			}
			count++;
		}
		
		return order;
	}
	
	public String[] getSpecificOrder(int orderID) {
		callRead();
		List<String> ordereditems = new ArrayList<>();
		Database.getGui().displayStringsB("Order ID: " + allOrders.get(orderID-1).getOrderId());
		for(int j = 0; j<allOrders.get(orderID-1).getAlaCarte().size();j++) {
			ordereditems.add(allOrders.get(orderID-1).getAlaCarte().get(j).getName());
		}
		for(int k = 0; k<allOrders.get(orderID-1).getPromoSet().size();k++) {
			ordereditems.add("Set: " + ((Integer)allOrders.get(orderID-1).getPromoSet().get(k).getSetID()).toString());
		}
		String[] newlist = listToStringArray(ordereditems);
		return  newlist;
	}

	public String[] listToStringArray(List<String> orderlist) {
		
		String[] newlist = new String[orderlist.size()];
		for(int i=0; i<orderlist.size();i++)
		{
			newlist[i] = orderlist.get(i);
		}
		
		return newlist;
	}
	
	private void readOrderItem() throws IOException{

		ArrayList<String> stringArray = (ArrayList<String>) Database.getRwFile().read(ITEMSFILENAME);
		
		allOrders.clear(); 

        for (int i = 0 ; i < stringArray.size() ; i++) {
        	ArrayList<PromoSet> promoSet = new ArrayList<PromoSet>();
        	ArrayList<MenuItem> alaCarte = new ArrayList<MenuItem>();
//        	Staff staff = new Staff(name, contact, employeeId, jobTitle, salary);
        	Staff staff = new Staff();
			String st = (String)stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st, Database.getSeparator());	
			
			if(star.hasMoreElements()) {
				int orderID = Integer.parseInt(star.nextToken());
				int tableID = Integer.parseInt(star.nextToken());
				Boolean printed = Boolean.parseBoolean(star.nextToken());
				
				String nameAndDate = star.nextToken().trim();
				StringTokenizer star2 = new StringTokenizer(nameAndDate, Database.getTXTSeparator());	
				String name = star2.nextToken().trim();
				String date = star2.nextToken().trim();

				
				boolean hasMore = true;			
				while (hasMore) {
					if(star.hasMoreElements()) {
						String items = star.nextToken().trim();
						StringTokenizer star3 = new StringTokenizer(items, Database.getTXTSeparator());
		
						String firstValue = star3.nextToken().trim();
						
						try {
							int value = Integer.parseInt(firstValue);
							
							for(int k = 0; k <star3.countTokens();k++) {
								int setID;
								if (k == 0) {
									setID = value;
								}
								else {
									setID  = Integer.parseInt(star3.nextToken().trim());
								}
								int qtyOrdered_p = Integer.parseInt(star3.nextToken().trim());
								double price_p = Double.parseDouble(star3.nextToken().trim()); 
								double iPrice_p = price_p/qtyOrdered_p;
								PromoSet temp2  = new PromoSet("",null,iPrice_p); 
								temp2.setSetID(setID);
								temp2.setOrderedQuantity(qtyOrdered_p); 
								promoSet.add(temp2); 
							}
						} catch (NumberFormatException e) {
							for(int j = 0; j<star3.countTokens(); j++) {
								String menuItemName;
								if (j == 0) {
									menuItemName = firstValue;
								}
								else {
									menuItemName = star3.nextToken().trim();
								}
								int qtyOrdered = Integer.parseInt(star3.nextToken().trim());
								double price = Double.parseDouble(star3.nextToken().trim());
								double iPrice = price/qtyOrdered;
								MainCourse temp = new MainCourse(menuItemName,"",iPrice,qtyOrdered);
								temp.setType("alaCarte");
								alaCarte.add(temp);
							}
						}				
					}
					else {
						hasMore = false;
					}
				}
				staff.setName(name);
				OrderItem oi = new OrderItem(orderID, tableID, staff, date, 0, alaCarte, promoSet, printed);
				allOrders.add(oi);
			}
		}
	}
	
	private void writeOrderItem() throws IOException {	
		List alw = new ArrayList();
		boolean hasMenuItem;
		boolean hasPromoSet;
		int id = 0;
		for(int k = 0; k<allOrders.size();k++) {
			hasMenuItem = false;
			hasPromoSet = false;
			OrderItem orderItem = (OrderItem)allOrders.get(k);
			for(int n = 0; n<orderItem.getAlaCarte().size();n++) {
				if(orderItem.getAlaCarte().get(n).getOrderedQuantity() > 0) {
					hasMenuItem = true;
					break;
				}
			}
			for(int n = 0; n<orderItem.getPromoSet().size();n++) {
				if(orderItem.getPromoSet().get(n).getOrderedQuantity() > 0) {
					hasPromoSet = true;
					break;
				}
			}
			
			StringBuilder st = new StringBuilder() ;
			if(hasMenuItem || hasPromoSet) {
				st.append(id + 1); 
				st.append(Database.getSeparator());
				st.append(orderItem.getTableId());
				st.append(Database.getSeparator());
				st.append(orderItem.getPrintedInvoice());
				st.append(Database.getSeparator());
				st.append(orderItem.getStaff().getName());
				st.append(Database.getTXTSeparator());
				st.append(orderItem.getDate());
				
				if(hasMenuItem==true) {
					st.append(Database.getSeparator());
					ArrayList<MenuItem> menuItem = orderItem.getAlaCarte();
					for (int j = 0; j < menuItem.size(); j++) {
						if(menuItem.get(j).getOrderedQuantity()>0) {
							st.append(menuItem.get(j).getName());
							st.append(Database.getTXTSeparator());
							st.append(menuItem.get(j).getOrderedQuantity());
							st.append(Database.getTXTSeparator());
							double totalPrice = (double)menuItem.get(j).getOrderedQuantity() * (double)menuItem.get(j).getPrice();
							st.append(String.format("%.2f", totalPrice));
							st.append(Database.getTXTSeparator());
						}
					}
					st.delete(st.length() - 2, st.length());
				}
				
				if(hasPromoSet == true) {
					st.append(Database.getSeparator());
					ArrayList<PromoSet> promoSet = orderItem.getPromoSet();
					for (int i = 0; i < promoSet.size(); i++) {
						if(promoSet.get(i).getOrderedQuantity()>0) {
							st.append(promoSet.get(i).getSetID());
							st.append(Database.getTXTSeparator());
							st.append(promoSet.get(i).getOrderedQuantity());
							st.append(Database.getTXTSeparator());
							double totalPrice = (double)promoSet.get(i).getOrderedQuantity() * (double)promoSet.get(i).getSetPrice();					
							st.append(String.format("%.2f", totalPrice));
							st.append(Database.getTXTSeparator());
						}
					}
					st.delete(st.length() - 2, st.length());
				}
				alw.add(st.toString());
				id++;
			}
		}
        Database.getRwFile().write(ITEMSFILENAME, alw);
	}
	
	private boolean callRead(){
		try {
			readOrderItem();
			return true;
		} catch (IOException e) {
			Database.getGui().displayStringsB("IOException > " + e.getMessage());
		}
		return false;
		
	}
	
	public boolean callWrite() {
		try {
			writeOrderItem();
			return true;
		} catch (IOException e) {
			Database.getGui().displayStringsB("IOException > " + e.getMessage());
		}
		return false;
	}
	
}
 