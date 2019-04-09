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
	private Date date;
	 
	public boolean createOrder(OrderItem order) {
		alaCarte = order.getOrder();
		promoSet = order.getPromoSet();
		staff = order.getStaff();
		date = order.getDate();
		callWrite();
		return true;
	}
	
	public boolean updateOrder(OrderItem order, int orderID) {
		return false;
	}
	 
	public boolean printOrder() throws Exception {
		callRead();
		for(int i = 0 ; i<allOrders.size();i++) {
			System.out.println(allOrders.get(i).getStaff().getName());
			System.out.println(allOrders.get(i).getDate());
			for(int j = 0; j<alaCarte.size();j++) {
				System.out.println(alaCarte.get(j).getName());
				System.out.println(alaCarte.get(j).getOrderedQuantity());
			}
			for(int k = 0; k<promoSet.size();k++) {
				System.out.println(promoSet.get(k).getSetID());
				System.out.println(promoSet.get(k).getOrderedQuantity());
			}
		}
		return true;
	}
	
	private void readOrderItem() throws IOException ,Exception{

		ArrayList<String> stringArray = (ArrayList<String>) Database.getRwFile().read(ITEMSFILENAME);
		
		alaCarte.clear();
		promoSet.clear();
		
        for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st, Database.getSeparator());	
			String nameAndDate = star.nextToken().trim();
			System.out.println(nameAndDate);
			if(!star.hasMoreElements()) break;
			String menuItems = star.nextToken().trim();
			System.out.println(menuItems);
			if(!star.hasMoreElements()) break;
			String promoItems = star.nextToken().trim();
			System.out.println(promoItems);
 
			 
			StringTokenizer star2 = new StringTokenizer(nameAndDate, "**");	
			String name = star2.nextToken().trim();
			String date = star2.nextToken().trim();
			Date dateConverted=new SimpleDateFormat("E MMM dd HH:mm:ss").parse(date);
			
			StringTokenizer star3 = new StringTokenizer(menuItems, "**");
			for(int j = 0; j<star3.countTokens(); j++) {
				String menuItemName = star3.nextToken().trim();
				int qtyOrdered = Integer.parseInt(star3.nextToken().trim());
				double price = Double.parseDouble(star3.nextToken().trim());
				MainCourse temp = new MainCourse(menuItemName,"",price,qtyOrdered);
				alaCarte.add(temp);
			}
			
			StringTokenizer star4 = new StringTokenizer(promoItems, "**");
			for(int k = 0; k <star4.countTokens();k++) {
				int setID  = Integer.parseInt(star4.nextToken().trim());
				int qtyOrdered_p = Integer.parseInt(star4.nextToken().trim());
				double price_p = Double.parseDouble(star4.nextToken().trim()); 
				PromoSet temp2  = new PromoSet("",null,price_p);
				temp2.setSetID(setID);
				temp2.setOrderedQuantity(qtyOrdered_p);
				promoSet.add(temp2);
			}
			this.staff.setName(name);
			OrderItem od = new OrderItem(i,dateConverted,alaCarte,promoSet, staff);
			allOrders.add(od);
		}
	}
	
	private void writeOrderItem() throws IOException {	
		List alw = new ArrayList() ;
		StringBuilder st = new StringBuilder() ;
		st.append(staff.getName());
		st.append("**");
		st.append(date);
		st.append(Database.getSeparator());
	    for (int j = 0 ; j < alaCarte.size() ; j++) {
	        MenuItem item = (MenuItem)alaCarte.get(j);
			st.append(item.getName().trim());
			st.append("**");
			st.append(item.getOrderedQuantity());
			st.append("**");
			st.append(item.getOrderedQuantity() * item.getPrice());;
			st.append("**");
			}
	    st.delete(st.length()-2, st.length());
	    st.append(Database.getSeparator());
	    for(int i = 0; i < promoSet.size(); i++) {
	    	PromoSet promoItem = (PromoSet)promoSet.get(i);
			st.append(promoItem.getSetID());
			st.append("**");
			st.append(promoItem.getOrderedQuantity());
			st.append("**");
			st.append(promoItem.getOrderedQuantity() * promoItem.getSetPrice());;
			st.append("**");
	    }
	    st.delete(st.length()-2, st.length());
	    alw.add(st.toString());
	    alw.add(Database.getOrderSeparator());
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
 