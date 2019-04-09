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
		this.order = order;
		callWrite();
		return true;
	}
	
	public int createOrderID() throws IOException {
		ArrayList<String> stringArray = (ArrayList<String>) Database.getRwFile().read(ITEMSFILENAME);
		return stringArray.size()+1;
	}
	
	public boolean updateOrder(OrderItem order, int orderID) { 
		
		return false;
	}
	 
	public boolean printOrder() throws Exception {
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
	
	private void readOrderItem() throws IOException ,Exception{

		ArrayList<String> stringArray = (ArrayList<String>) Database.getRwFile().read(ITEMSFILENAME);
		
		//alaCarte.clear();
		//promoSet.clear();
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
			Date dateConverted=new SimpleDateFormat("E MMM dd HH:mm:ss").parse(date);
			 
			
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
			OrderItem od = new OrderItem(orderID,dateConverted,order.getOrder(),order.getPromoSet(), order.getStaff());
			allOrders.add(od);
		}
	}
	
	private void writeOrderItem() throws IOException {	
		List alw = new ArrayList() ;
		StringBuilder st = new StringBuilder() ;
		st.append(createOrderID());
		st.append(Database.getSeparator());
		st.append(order.getStaff().getName());
		st.append(Database.getTXTSeparator());
		st.append(order.getDate());
		st.append(Database.getSeparator());
	    for (int j = 0 ; j < order.getOrder().size() ; j++) {
	        MenuItem item = (MenuItem)order.getOrder().get(j);
			st.append(item.getName().trim());
			st.append(Database.getTXTSeparator());
			st.append(item.getOrderedQuantity());
			st.append(Database.getTXTSeparator());
			st.append(item.getOrderedQuantity() * item.getPrice());;
			st.append(Database.getTXTSeparator());
			}
	    st.delete(st.length()-2, st.length());
	    st.append(Database.getSeparator());
	    for(int i = 0; i < order.getPromoSet().size(); i++) {
	    	PromoSet promoItem = (PromoSet)order.getPromoSet().get(i);
			st.append(promoItem.getSetID());
			st.append(Database.getTXTSeparator());
			st.append(promoItem.getOrderedQuantity());
			st.append(Database.getTXTSeparator());
			st.append(promoItem.getOrderedQuantity() * promoItem.getSetPrice());;
			st.append(Database.getTXTSeparator());
	    }
	    st.delete(st.length()-2, st.length());
	    alw.add(st.toString());
        Database.getRwFile().write(ITEMSFILENAME, alw);
	}
	
	private boolean callRead() throws Exception {
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
 