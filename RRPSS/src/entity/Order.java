package entity;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import service.OrderInterface;
import utils.Database;

public class Order implements OrderInterface{
	
	private static final String ITEMSFILENAME = "res/Order.txt";
	private ArrayList<OrderItem> allOrders = new ArrayList<OrderItem>();
	private ArrayList<MenuItem> alaCarte = new ArrayList<MenuItem>();
	private ArrayList<PromoSet> promoSet = new ArrayList<PromoSet>();
	private Staff staff = new Staff();
	private OrderItem order = new OrderItem(0, null, alaCarte, promoSet, staff);
	 
	public boolean createOrder(OrderItem order) {
		allOrders.add(order);
		callWrite();
		callRead();
		return true;
	}
	
	public int createOrderID() throws IOException {
		ArrayList<String> stringArray = (ArrayList<String>) Database.getRwFile().read(ITEMSFILENAME);
		return stringArray.size()+1;
	}
	
	public boolean updateOrder(OrderItem order, int orderID) { 
		
		return false;
	}
	 
	public boolean printOrder(){
		callRead();
		for(int i = 0 ; i<allOrders.size();i++) {
			System.out.println("Order ID:" + allOrders.get(i).getOrderId());
			System.out.println("Staff: " + allOrders.get(i).getStaff().getName());
			System.out.println("Date:" + allOrders.get(i).getDate());
			for(int j = 0; j<allOrders.get(i).getOrder().size();j++) {
				System.out.println(allOrders.get(i).getOrder().get(j).getName() + "   " + allOrders.get(i).getOrder().get(j).getOrderedQuantity());
			}
			for(int k = 0; k<allOrders.get(i).getPromoSet().size();k++) {
				System.out.println(allOrders.get(i).getPromoSet().get(k).getSetID() + "   " + allOrders.get(i).getPromoSet().get(k).getOrderedQuantity());
			}
		}
		return true;
	}
	
	public String[] printSpecificOrder(int orderID) {
		callRead();	
		List<String> ordereditems = new ArrayList<>();
		System.out.println("Order ID:" + allOrders.get(orderID-1).getOrderId());
		for(int j = 0; j<allOrders.get(orderID-1).getOrder().size();j++) {
			ordereditems.add(allOrders.get(orderID-1).getOrder().get(j).getName());
			System.out.println(allOrders.get(orderID-1).getOrder().get(j).getName() + "   " + allOrders.get(orderID-1).getOrder().get(j).getOrderedQuantity());
		}
		for(int k = 0; k<allOrders.get(orderID-1).getPromoSet().size();k++) {
			ordereditems.add(allOrders.get(orderID-1).getPromoSet().get(k).getSetDescription());
			System.out.println(allOrders.get(orderID-1).getPromoSet().get(k).getSetID() + "   " + allOrders.get(orderID-1).getPromoSet().get(k).getOrderedQuantity());
		}
		String[] newlist = listToStringArray(ordereditems);
		return  newlist;
	}
	
	private boolean updateOrder(int orderID, String name, String qty) {
		callRead();
		OrderItem orderItem = (OrderItem)allOrders.get(orderID-1);
		return false;
	}
	
	private void readOrderItem() throws IOException{

		ArrayList<String> stringArray = (ArrayList<String>) Database.getRwFile().read(ITEMSFILENAME);
		
		if(order!=null) {
			order.getOrder().clear();
			order.getPromoSet().clear();
		}
		
        for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st, Database.getSeparator());	
			int orderID = Integer.parseInt(star.nextToken());
			
			if(!star.hasMoreElements())break;
			String nameAndDate = star.nextToken().trim();
			StringTokenizer star2 = new StringTokenizer(nameAndDate, Database.getTXTSeparator());	
			String name = star2.nextToken().trim();
			String date = star2.nextToken().trim();
			 
			 
			if(star.hasMoreElements()) {
				String menuItems = star.nextToken().trim();
				StringTokenizer star3 = new StringTokenizer(menuItems, Database.getTXTSeparator());
				for(int j = 0; j<star3.countTokens(); j++) {
					String menuItemName = star3.nextToken().trim();
					int qtyOrdered = Integer.parseInt(star3.nextToken().trim());
					double price = Double.parseDouble(star3.nextToken().trim());
					MainCourse temp = new MainCourse(menuItemName,"",price,qtyOrdered);
					order.getOrder().add(temp);
				}
			}
				
			if(star.hasMoreElements()) {
				String promoItems = star.nextToken().trim();		
				StringTokenizer star4 = new StringTokenizer(promoItems, Database.getTXTSeparator());
				for(int k = 0; k <star4.countTokens();k++) {
					int setID  = Integer.parseInt(star4.nextToken().trim());
					int qtyOrdered_p = Integer.parseInt(star4.nextToken().trim());
					double price_p = Double.parseDouble(star4.nextToken().trim()); 
					PromoSet temp2  = new PromoSet("",null,price_p); 
					temp2.setSetID(setID);
					temp2.setOrderedQuantity(qtyOrdered_p);
					order.getPromoSet().add(temp2);
				}
			}
			this.staff.setName(name);
			OrderItem od = new OrderItem(orderID,date,order.getOrder(),order.getPromoSet(), order.getStaff());
			allOrders.add(od);
		}
	}
	
	private void writeOrderItem() throws IOException {	
		List alw = new ArrayList() ;
		for(int k = 0; k<allOrders.size();k++) {
			OrderItem orderItem = (OrderItem)allOrders.get(k);
			StringBuilder st = new StringBuilder() ;
			st.append(k+1);
			st.append(Database.getSeparator());
			st.append(orderItem.getStaff().getName());
			st.append(Database.getTXTSeparator());
			st.append(orderItem.getDate());
			st.append(Database.getSeparator());
			ArrayList<MenuItem> menuItem = orderItem.getOrder();
			for (int j = 0; j < menuItem.size(); j++) {
				st.append(menuItem.get(j).getName());
				st.append(Database.getTXTSeparator());
				st.append(menuItem.get(j).getOrderedQuantity());
				st.append(Database.getTXTSeparator());
				st.append(menuItem.get(j).getOrderedQuantity() * menuItem.get(j).getPrice());
				;
				st.append(Database.getTXTSeparator());
			}
			st.delete(st.length() - 2, st.length());
			st.append(Database.getSeparator());
			ArrayList<PromoSet> promoSet = orderItem.getPromoSet();
			for (int i = 0; i < order.getPromoSet().size(); i++) {
				st.append(promoSet.get(i).getSetID());
				st.append(Database.getTXTSeparator());
				st.append(promoSet.get(i).getOrderedQuantity());
				st.append(Database.getTXTSeparator());
				st.append(promoSet.get(i).getOrderedQuantity() * promoSet.get(i).getSetPrice());
				;
				st.append(Database.getTXTSeparator());
			}
			st.delete(st.length() - 2, st.length());
			alw.add(st.toString());
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
 