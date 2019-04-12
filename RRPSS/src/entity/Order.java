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
	
	public boolean createOrder(OrderItem order) {
		callRead();
		allOrders.add(order);
		callWrite();
		return true;
	}
	
	public int countOrders() {
		callRead();
		return allOrders.size();
	}
	
	public boolean updateMenuItemOrder(MenuItem item, int orderID) {
		callRead();
		boolean dupe = false;
		boolean updated = false;
		ArrayList<MenuItem> currentOrder = allOrders.get(orderID-1).getOrder();
		for(int i = 0 ; i < currentOrder.size(); i++) {
			if(currentOrder.get(i).getName().equals(item.getName())){
				allOrders.get(orderID-1).getOrder().get(i).addOrderedQuantity(item.getOrderedQuantity());
				dupe = true; 
				updated = true;
				break;
			} 
		}
		if(dupe == false) {
			allOrders.get(orderID-1).getOrder().add(item);
			updated = true;
		}
		callWrite();
		return updated;
	}

	public boolean removeMenuItemOrder(int orderID, int choice, int qty) {
		callRead();	
		boolean updated = false;
		if(qty <= allOrders.get(orderID-1).getOrder().get(choice-1).getOrderedQuantity()) {
			allOrders.get(orderID-1).getOrder().get(choice-1).addOrderedQuantity(-qty);	
			updated = true;
		}
		else {
			Database.getGui().displayStringsB("The item has not been ordered that many times!");
		}
		callWrite();
		return updated;
	}
	
	public boolean updatePromoSetOrder(PromoSet set, int orderID) {
		callRead();
		boolean dupe = false;
		boolean updated = false;
		ArrayList<PromoSet> promoSet = allOrders.get(orderID-1).getPromoSet();
		for(int i = 0 ; i < promoSet.size(); i++) {
			if(promoSet.get(i).getSetID() == set.getSetID()){
				allOrders.get(orderID-1).getPromoSet().get(i).addOrderedQuantity(set.getOrderedQuantity());
				dupe = true;
				updated = true;
				break;
			} 
		} 
		if(dupe == false) {
			allOrders.get(orderID-1).getPromoSet().add(set);
			updated = true;
		}
		callWrite();
		return updated;
	}
	
	public boolean removePromoSetOrder(int orderID, int choice, int qty) {
		callRead();	
		boolean updated = false;
		if(qty <= allOrders.get(orderID-1).getPromoSet().get(choice-1).getOrderedQuantity()) {
			allOrders.get(orderID-1).getPromoSet().get(choice-1).addOrderedQuantity(-qty);	
			updated = true;
		}
		else {
			Database.getGui().displayStringsB("The item has not been ordered that many times!");
		}
		callWrite();
		return updated;
	}
	 
	public boolean printOrder(){
		callRead();
		System.out.println("");
		for(int i = 0 ; i < allOrders.size();i++) {
			System.out.println("Order ID: " + allOrders.get(i).getOrderId());
			System.out.println("Table ID: " + allOrders.get(i).getTable());
			System.out.println("Staff: " + allOrders.get(i).getStaff().getName());
			System.out.println("Date: \t" + allOrders.get(i).getDate());
			
			for(int j = 0; j<allOrders.get(i).getOrder().size();j++) {
				System.out.println(allOrders.get(i).getOrder().get(j).getOrderedQuantity() + "\t" + allOrders.get(i).getOrder().get(j).getName());
			}
			for(int k = 0; k<allOrders.get(i).getPromoSet().size();k++) {
				System.out.println(allOrders.get(i).getPromoSet().get(k).getOrderedQuantity() + "\tPromoSet " + allOrders.get(i).getPromoSet().get(k).getSetID());
			}
			System.out.println();
		}
		return true;
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
			for(int j = 0; j < allOrders.get(i).getOrder().size();j++) {
				listOfOrderItem[count] += "\t" + allOrders.get(i).getOrder().get(j).getOrderedQuantity() + "\t" + allOrders.get(i).getOrder().get(j).getName() + "\n";
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
		System.out.println("Order ID: " + allOrders.get(orderID-1).getOrderId());
		for(int j = 0; j<allOrders.get(orderID-1).getOrder().size();j++) {
			ordereditems.add(allOrders.get(orderID-1).getOrder().get(j).getName());
		}
		for(int k = 0; k<allOrders.get(orderID-1).getPromoSet().size();k++) {
			ordereditems.add("Set: " + ((Integer)allOrders.get(orderID-1).getPromoSet().get(k).getSetID()).toString());
		}
		String[] newlist = listToStringArray(ordereditems);
		return  newlist;
	}
	
	
	public void printSpecificOrder(int orderID) {
		callRead();
		System.out.println("Order ID: " + allOrders.get(orderID-1).getOrderId());
		System.out.println("Table ID: " + allOrders.get(orderID-1).getTable());
		System.out.println("Staff: " + allOrders.get(orderID-1).getStaff().getName());
		System.out.println("Date:" + allOrders.get(orderID-1).getDate());
		for(int j = 0; j<allOrders.get(orderID-1).getOrder().size();j++) {
			System.out.println(allOrders.get(orderID-1).getOrder().get(j).getName() + "   " + allOrders.get(orderID-1).getOrder().get(j).getOrderedQuantity());
		}
		for(int k = 0; k<allOrders.get(orderID-1).getPromoSet().size();k++) {
			System.out.println("PromoSet: " + allOrders.get(orderID-1).getPromoSet().get(k).getSetID() + "   " + allOrders.get(orderID-1).getPromoSet().get(k).getOrderedQuantity());
		}
	}
	
	
	private void readOrderItem() throws IOException{

		ArrayList<String> stringArray = (ArrayList<String>) Database.getRwFile().read(ITEMSFILENAME);
		
		allOrders.clear(); 

        for (int i = 0 ; i < stringArray.size() ; i++) {
        	ArrayList<PromoSet> promoSet = new ArrayList<PromoSet>();
        	ArrayList<MenuItem> alaCarte = new ArrayList<MenuItem>();
        	Staff staff = new Staff();
			String st = (String)stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st, Database.getSeparator());	
			
			if(star.hasMoreElements()) {
				int orderID = Integer.parseInt(star.nextToken());
				int tableID = Integer.parseInt(star.nextToken());
				String printed = star.nextToken().trim();
				
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
				OrderItem od = new OrderItem(orderID,date,alaCarte,promoSet, staff);
				od.setPrintedInvoice(printed);
				od.setTable(tableID);
				allOrders.add(od);
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
			for(int n = 0; n<orderItem.getOrder().size();n++) {
				if(orderItem.getOrder().get(n).getOrderedQuantity() > 0) {
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
				st.append(orderItem.getTable());
				st.append(Database.getSeparator());
				st.append(orderItem.getPrintedInvoice());
				st.append(Database.getSeparator());
				st.append(orderItem.getStaff().getName());
				st.append(Database.getTXTSeparator());
				st.append(orderItem.getDate());
				
				if(hasMenuItem==true) {
					st.append(Database.getSeparator());
					ArrayList<MenuItem> menuItem = orderItem.getOrder();
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
	public String[] listToStringArray(List<String> orderlist) {
		
		String[] newlist = new String[orderlist.size()];
		for(int i=0; i<orderlist.size();i++)
		{
			newlist[i] = orderlist.get(i);
		}
		
		return newlist;
	}
}
 