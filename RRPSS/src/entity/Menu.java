package entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import service.MenuInterface;
import utils.Database;
import utils.ReadWriteFile;

public class Menu implements MenuInterface{
	
	private static final String ITEMSFILENAME = "res/MenuItems.txt";
	private static final String SETSFILENAME = "res/PromoSets.txt";
		
	private ArrayList<MainCourse> mcList = new ArrayList<MainCourse>();
	private ArrayList<Dessert> deList = new ArrayList<Dessert>();
	private ArrayList<Drinks> drList = new ArrayList<Drinks>();

	private ArrayList menuItemsList = new ArrayList();
	
	private ArrayList<PromoSet> promoSetList = new ArrayList<PromoSet>();
	
	
	public Menu() {
		callRead();
	}

	public ArrayList getMenuList() {
		return menuItemsList;
	}

	public MenuItem getMenuItem(String name) {
		MenuItem item = null;
		int index[] = itemExistReturnIndex(name);
		switch(index[0]) {
			case 0:
				item = mcList.get(index[1]);
				break;
			case 1:
				item = deList.get(index[1]);
				break;
			case 2:
				item = drList.get(index[1]);
				break;
		}
		return item;
	}
	
	@Override
	public boolean createMenuItem(MenuItem item) {
		int[] index = itemExistReturnIndex(item.getName());
		String type = item.getType();
		if(index[1] == -1) {
			if (type == "Main Course") {
				mcList.add((MainCourse)item);
			}
			else if(type == "Dessert") {
				deList.add((Dessert)item);
			}
			else if (type == "Drinks") {
				drList.add((Drinks)item);
			}
			callWrite();
			callRead();
			return true;
		}
		return false;
	}

	@Override
	public boolean updateMenuItem(int[] index, MenuItem item) {
		deleteMenuItem(index);
		createMenuItem(item);
		return true;
	}

	@Override
	public boolean deleteMenuItem(int[] index) {
		switch(index[0]) {
			case 0:
				mcList.remove(index[1]);
				break;
			case 1:
				deList.remove(index[1]);
				break;
			case 2:
				drList.remove(index[1]);
				break;
		}
		callWrite();
		callRead();
		return true;
	}
	
	public int[] itemExistReturnIndex(String name) {
		int[] index = {-1, -1};
		for (int i = 0; i < menuItemsList.size(); i++) {
			ArrayList list = (ArrayList) menuItemsList.get(i);
			for (int j = 0; j < list.size(); j++) {
				MenuItem item = (MenuItem) list.get(j);
				if (item.getName().equalsIgnoreCase(name)) {
					index[0] = i;
					index[1] = j;
					return index;
				}
			}
		}
		return index;
	}
	
	public void printMenuItems() {
		for (int i = 0; i < menuItemsList.size(); i++) {
			ArrayList list = (ArrayList) menuItemsList.get(i);
			for (int j = 0; j < list.size(); j++) {
				MenuItem item = (MenuItem) list.get(j);
				Database.getGui().displayStringsB("Name: " + item.getName());
				Database.getGui().displayStringsB("Description: " + item.getDescription());
				Database.getGui().displayStringsB("Type: " + item.getType());
				Database.getGui().displayStringsB("Price: $" + item.getPrice() + "\n");
			}
		}
	}
	
	public MenuItem pickMenuItems() {
		
		MenuItem item = null;
		
		String listOfMenuItem[] = new String[countItems() + 1];
		
		int count = 0;
		
		for (int i = 0; i < menuItemsList.size(); i++) {
			ArrayList list = (ArrayList) menuItemsList.get(i);
			for (int j = 0; j < list.size(); j++) {
				MenuItem it = (MenuItem) list.get(j);
				listOfMenuItem[count] = "\t" + it.getType() + ": " + it.getName() + " - $" + it.getPrice() + "\n\tDescription: " + it.getDescription() + "\n";
				count++;
			}
		}
		
		listOfMenuItem[count] = "\tDone";

		int choice = Database.getGui().detectChoice(listOfMenuItem);
		
		count = 1;
		for (int i = 0; i < menuItemsList.size(); i++) {
			ArrayList list = (ArrayList) menuItemsList.get(i);
			for (int j = 0; j < list.size(); j++) {
				if (choice == count) {
					item = (MenuItem) list.get(j);
					return item;
				}
				count++;
			}
		}
		return item;
	}
	
	public int countItems() {
		int count = 0;
		for (int i = 0; i < menuItemsList.size(); i++) {
			ArrayList list = (ArrayList) menuItemsList.get(i);
			for (int j = 0; j < list.size(); j++) {
				count++;
			}
		}
		return count;
	}
	
	
	private void readMenuItem() throws IOException {

		ArrayList<String> stringArray = (ArrayList<String>) Database.getRwFile().read(ITEMSFILENAME);
		
		menuItemsList.clear();
		mcList.clear();
		deList.clear();
		drList.clear();
		
        for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st, Database.getSeparator());	
			String name = star.nextToken().trim();
			String description = star.nextToken().trim();
			int type = Integer.parseInt(star.nextToken());
			double price = Double.parseDouble(star.nextToken().trim()); 
			int orderQuantity = Integer.parseInt(star.nextToken());
							
			switch (type) {
				case 1:
					MainCourse mc = new MainCourse(name, description, price, orderQuantity);
					mcList.add(mc);
					break;
				case 2: 
					Dessert de = new Dessert(name, description, price, orderQuantity);
					deList.add(de);
					break;
				case 3:
					Drinks dr = new Drinks(name, description, price, orderQuantity);
					drList.add(dr);
					break;
				default:
					Database.getGui().displayStringsB("Error");
			}
		}
        menuItemsList.add(mcList);
        menuItemsList.add(deList);
        menuItemsList.add(drList);
	}
	
	private void writeMenuItem() throws IOException {

		List alw = new ArrayList() ;
		
		menuItemsList.set(0, mcList);
		menuItemsList.set(1, deList);
		menuItemsList.set(2, drList);

        for (int i = 0 ; i < menuItemsList.size() ; i++) {
        	ArrayList array = (ArrayList)menuItemsList.get(i);
	        for (int j = 0 ; j < array.size() ; j++) {
	        	MenuItem item = (MenuItem)array.get(j);
				StringBuilder st = new StringBuilder() ;
				st.append(item.getName().trim());
				st.append(Database.getSeparator());
				st.append(item.getDescription().trim());
				st.append(Database.getSeparator());
				String type = item.getType();
				if (type == "Main Course") {
					st.append(1);
				}
				else if(type == "Dessert") {
					st.append(2);
				}
				else if (type == "Drinks") {
					st.append(3);
				}
				st.append(Database.getSeparator());
				st.append(item.getPrice());
				st.append(Database.getSeparator());
				st.append(item.getOrderedQuantity());
				alw.add(st.toString()) ;
			}
        }
        Database.getRwFile().write(ITEMSFILENAME, alw);
	}
	
	public ArrayList<PromoSet> getPromoSetList() {
		return promoSetList;
	}
	
	public PromoSet getPromoSet(int index) {
		return promoSetList.get(index);
	}
	
	@Override
	public boolean createPromoItem(PromoSet set) {
		if (!promoExist(set)) {
			promoSetList.add(set);
			return true;
		}
		return false;
	}

	@Override
	public boolean updatePromoItem(int index, PromoSet set) {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deletePromoItem(int index) {
		return false;
		// TODO Auto-generated method stub
		
	}
	
	public boolean promoExist(PromoSet set) {
		for (int i = 0; i < promoSetList.size(); i++) {
			PromoSet setIn = (PromoSet) promoSetList.get(i);
			if (setIn.equals(set)) {
				return true;
			}
		}
		return false;
	}
	
	public void printPromoSets() {
		for (int i = 0; i < promoSetList.size(); i++) {
			PromoSet set = (PromoSet) promoSetList.get(i);

			Database.getGui().displayStringsB("Set " + set.getSetID() + " - $" + set.getSetPrice());
			Database.getGui().displayStringsB("Description: " + set.getSetDescription());
			
			ArrayList<MenuItem> itemList = (ArrayList<MenuItem>) set.getSetItems();
			for (int j = 0; j < itemList.size(); j++) {
				Database.getGui().displayStringsB(itemList.get(j).getType() + ": " + itemList.get(j).getName());
			}
			Database.getGui().displayStringsB("");
		}
	}
	
	private void readPromoSets() throws IOException {

		ArrayList<String> stringArray = (ArrayList<String>) Database.getRwFile().read(SETSFILENAME);
		
		promoSetList.clear();
		
        for (int i = 0 ; i < stringArray.size() ; i++) {
			String st = (String)stringArray.get(i);
			StringTokenizer star = new StringTokenizer(st, Database.getSeparator());	
			int setID = Integer.parseInt(star.nextToken().trim());
			String setDescription = star.nextToken().trim();
			String setItems = star.nextToken().trim();
			double setPrice = Double.parseDouble(star.nextToken().trim()); 
					
			List itemsStrArray = new ArrayList();
			itemsStrArray.add(setItems);
			
			ArrayList<MenuItem> itemsArray = new ArrayList<MenuItem>();
	        for (int j = 0 ; j < itemsStrArray.size() ; j++) {
	        	
	        	String str = (String)itemsStrArray.get(j);
				StringTokenizer starr = new StringTokenizer(str, Database.getSetSeparator());
				
				int count = starr.countTokens();
				
		        for (int k = 0 ; k < count ; k++) {
					String item = starr.nextToken().trim();				
					itemsArray.add(getMenuItem(item));
		        }
	        }
	        PromoSet ps = new PromoSet(setID, setDescription, itemsArray, setPrice);
	        promoSetList.add(ps);
		}
	}
	
	private void writePromoSets() throws IOException {
		//TODO
//		List alw = new ArrayList();
//
//        for (int i = 0 ; i < promoSetList.size() ; i++) {
//        	PromoSet set = (PromoSet)menuItemsList.get(i);
//			StringBuilder st = new StringBuilder() ;
//			st.append(i + 1);
//			st.append(Database.getSeparator());
//			st.append(set.getSetDescription().trim());
//			st.append(Database.getSeparator());
//			
//			ArrayList<MenuItem> items = set.getSetItems();
//			
//			for (int j = 0 ; j < items.size() ; j++) {
//				st.append(items.get(j).getName());
//				if (j != items.size() - 1) {
//					st.append(Database.getSetSeparator());
//				}
//			}
//			
//			st.append(Database.getSeparator());
//			st.append(set.getSetPrice());
//			
//			alw.add(st.toString()) ;
//        }
//        Database.getRwFile().write(ITEMSFILENAME, alw);
	}
	

	private boolean callRead() {
		try {
			readMenuItem();
			readPromoSets();
			return true;
		} catch (IOException e) {
			Database.getGui().displayStringsB("IOException > " + e.getMessage());
		}
		return false;
		
	}
	
	private boolean callWrite() {
		try {
			//writePromoSets();
			writeMenuItem();
			return true;
		} catch (IOException e) {
			Database.getGui().displayStringsB("IOException > " + e.getMessage());
		}
		return false;
	}
	
}
