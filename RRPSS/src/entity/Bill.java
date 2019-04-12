package entity;

import java.util.ArrayList;
import java.util.List;

import service.BillingInterface;
import view.GUI;

public class Bill implements BillingInterface{
	
	private ArrayList<MenuItem> menuitemlist = new ArrayList<MenuItem>();
	private ArrayList<PromoSet> promoitemlist = new ArrayList<PromoSet>();
	
	GUI billgui = new GUI();
	
	String[] salesDatabase = {
			"Jack's Special Steak",
			"Bearnaise Steak",
			"Mini Tiramisu in a Cup",
			"Banana Fritters A La Mode",
			"Root Beer Float",
			"Tiger Beer",
			"1",
			"2"
	}; 
	
	public int printBillInvoice(int tablenum, ArrayList<OrderItem> orderList)
	{
	
		OrderItem order = getBill(tablenum, orderList);
		if(order==null) {
			return 0;
		}
		String num = genReceiptNo(order.getOrderId());
		billgui.displayStringsB("Receipt No: "+ num);
		billgui.displayStringsB("NOM-NOM Restaurant");
		billgui.displayStringsB("------------------");
		billgui.displayStringsB("6 Eu Tong Sen St, 02-82/83");
		billgui.displayStringsB("Singapore 059817");
		billgui.displayStringsB(order.getStaff().getName());
		billgui.displayStringsB(order.getDate()); 
		billgui.displayStringsB("Table: "+tablenum);
		// print number of people 
		billgui.displayStringsB("------------------");
		menuitemlist = order.getAlaCarte();
		promoitemlist = order.getPromoSet();
		MenuItem item;
		PromoSet set;
		for(int i =0; i<menuitemlist.size();i++)
		{
			item = menuitemlist.get(i);
			billgui.displayStringsB(item.getOrderedQuantity()+ "    "+item.getName() + "     "+ item.getPrice());
		}
		for(int j =0; j<promoitemlist.size();j++)
		{
			set = promoitemlist.get(j);
			billgui.displayStringsB(set.getOrderedQuantity()+ "    "+"Set "+set.getSetID() + "     "+ set.getSetPrice());
		}
		order.setTotalPrice();
		double orderTotalPrice = order.getTotalPrice();
		billgui.displayStringsB("------------------");
		billgui.displayStringsB("SubTotal: "+String.format("%.2f",orderTotalPrice));
		billgui.displayStringsB("GST & Service Charge "+(String.format("%.2f", orderTotalPrice*0.17)));
		billgui.displayStringsB("------------------");
		billgui.displayStringsB("TOTAL: "+(String.format("%.2f",orderTotalPrice*1.17)));
		billgui.displayStringsB("------------------");
		
		
		return order.getOrderId();
	}
	
	public boolean printSalesReport(int choice,String userdate,ArrayList<OrderItem> orderList)
	{
		ArrayList<SalesItem> salesreport = (ArrayList<SalesItem>) getSalesReport(choice,userdate, orderList);
		double totalRevenue = 0;
		if(salesreport==null)
		{
			return false;
		}
		System.out.println("-----Ala Carte-----");
		System.out.println("-------------------");
		int i =0;
		for(;i<salesreport.size();i++)
		{
			SalesItem salesitem = salesreport.get(i);
			if(salesreport.get(i).isPromo())
			{
				break;
			}
			
			System.out.println("Item name: "+ salesitem.getItemname()+"Qty: "+ salesitem.getQuantitySold()+ "Revenue from this item: "+ (salesitem.getQuantitySold()*salesitem.getPrice()));
			totalRevenue+= salesitem.getQuantitySold()*salesitem.getPrice();
		}
		System.out.println("-----Promo Item-----");
		System.out.println("-------------------");
		for(;i<salesreport.size();i++)
		{
			SalesItem promoitem = salesreport.get(i);
			System.out.println("Set: "+ promoitem.getItemname()+"Qty: "+ promoitem.getQuantitySold()+ "Revenue from this item: "+ (promoitem.getQuantitySold()*promoitem.getPrice()));
			totalRevenue+= promoitem.getQuantitySold()*promoitem.getPrice();
		}
		
		System.out.println("Total Revenue: "+ totalRevenue);
		return true;
	}
	
	public OrderItem getBill(int tablenum, ArrayList<OrderItem> orderList)
	{
	
		OrderItem order = null;
		for(int i =0;i<orderList.size();i++)
		{
			if(tablenum==orderList.get(i).getTableId() && !orderList.get(i).getPrintedInvoice())
			{
				order = orderList.get(i); 
				return order;
			}
		}
		
		return order;
		
	}
	
	public List<SalesItem> getSalesReport(int choice,String userdate,ArrayList<OrderItem> orderList)
	{

		ArrayList<SalesItem> saleslist = new ArrayList<SalesItem>();

		for(int i=0;i<salesDatabase.length;i++) {
			String menuitem = salesDatabase[i];
			SalesItem tempsalesitem = new SalesItem(); //not sure if okay
			tempsalesitem.setItemname(menuitem);
			int qtyOrdered = 0;
			double price = 0;
			boolean ispromo = false;
			OrderItem temporder;
			for (int j =0; j<orderList.size();j++)
			{
				temporder = orderList.get(j);
				char[] getperiod = new char[20];
				if(choice==1)
				{
					temporder.getDate().getChars(0,2,getperiod,0);
				}
				else if(choice==2)
				{
					temporder.getDate().getChars(3,5,getperiod,0);
				}
				String period = new String(getperiod).trim();
				
				if(period.equals(userdate)) 
				{
					ArrayList<MenuItem> menulist = temporder.getAlaCarte();
					ArrayList<PromoSet> setlist= temporder.getPromoSet();
					
					for(int k=0;k<menulist.size();k++)
					{
						if(menuitem.equals(menulist.get(k).getName()))
						{
							qtyOrdered+= menulist.get(k).getOrderedQuantity();
							price = menulist.get(k).getPrice();
						}
					} 
					for(int p=0; p<setlist.size();p++)
					{
						if(menuitem.equals(Integer.toString(setlist.get(p).getSetID()))) 
						{
							qtyOrdered+=setlist.get(p).getOrderedQuantity();
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
		return saleslist;
	}
	
	public String genReceiptNo(int orderid){
		
		String receiptnum = String.format("%05d", orderid);

		return receiptnum;
	}
	


	
}
