package entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import service.MenuInterface;
import utils.Database;

/**
 * The Class Menu.
 */
public class Menu implements MenuInterface{
	
	/** The Constant ITEMSFILENAME for menu item data path. */
	private static final String ITEMSFILENAME = "res/MenuItems.txt";
	
	/** The Constant SETSFILENAME for promotional set data path. */
	private static final String SETSFILENAME = "res/PromoSets.txt";
		
	/** The list of type main course. */
	private ArrayList<MainCourse> mcList = new ArrayList<MainCourse>();
	
	/** The list of type dessert. */
	private ArrayList<Dessert> deList = new ArrayList<Dessert>(); 
	
	/** The list of type drinks. */ 
	private ArrayList<Drinks> drList = new ArrayList<Drinks>();

	/** The menu items list of all 3 types. */
	private ArrayList menuItemsList = new ArrayList();
	
	/** The list promotional sets. */
	private ArrayList<PromoSet> promoSetList = new ArrayList<PromoSet>();
	
	/**
	 * Instantiates a new menu.
	 */
	public Menu() {
		callRead();
	}

	/**
	 * Gets the menu list.
	 *
	 * @return the menu list
	 */
	public ArrayList getMenuList() {
		return menuItemsList;
	}

	/**
	 * Gets the menu item.
	 *
	 * @param name item's name
	 * @return the menu item
	 */ 
	public MenuItem getMenuItem(String name) {
		MenuItem item = null;
		for (int i = 0; i < menuItemsList.size(); i++) {
			ArrayList list = (ArrayList) menuItemsList.get(i);
			for (int j = 0; j < list.size(); j++) {
				item = (MenuItem) list.get(j);
				if (item.getName().equalsIgnoreCase(name)) {
					return item;
				}
				else {
					item = null;
				}
			}
		}
		return item;
	}
	
	/* (non-Javadoc)
	 * @see service.MenuInterface#createMenuItem(entity.MenuItem)
	 */
	@Override
	public boolean createMenuItem(MenuItem item) {
		String type = item.getType();
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

	/* (non-Javadoc)
	 * @see service.MenuInterface#updateMenuItem(entity.MenuItem, java.lang.String, java.lang.String, double)
	 */
	@Override
	public boolean updateMenuItem(MenuItem item, String newName, String newDescription, double newPrice) {
		item.setName(newName);
		item.setDescription(newDescription);
		item.setPrice(newPrice);
		callWrite();
		callRead();
		return true;
	}

	/* (non-Javadoc)
	 * @see service.MenuInterface#deleteMenuItem(entity.MenuItem)
	 */
	@Override
	public boolean deleteMenuItem(MenuItem item) {
		int index[] = itemExistReturnIndex(item);
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
	
	/**
	 * Checks existence of item and return its index.
	 *
	 * @param item the menu item
	 * @return the index of the menu item int[]
	 */
	public int[] itemExistReturnIndex(MenuItem item) {
		int[] index = {-1, -1};
		for (int i = 0; i < menuItemsList.size(); i++) {
			ArrayList list = (ArrayList) menuItemsList.get(i);
			for (int j = 0; j < list.size(); j++) {
				if (list.get(j).equals(item)) {
					index[0] = i;
					index[1] = j;
					return index;
				}
			}
		}
		return index;
	}
	  
	/**
	 * Prints all the menu items.
	 */
	public void printMenuItems() {
		Database.getGui().displayStringsB("");
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
	
	/**
	 * Allows user to pick a menu item from the list.
	 *
	 * @param text string for the last option (back/exit)
	 * @return a menu item
	 */
	public MenuItem pickMenuItems(String text) {

		Database.getGui().displayStringsB("");
		
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
		
		listOfMenuItem[count] = "\t" + text;

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
	
	/**
	 * Count the number of items in the list.
	 *
	 * @return the number of items in the list
	 */
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
	
	/**
	 * Reads the menu items from the database MenuItems.txt file in resource folder and store them in system.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
	
	/**
	 * Writes the data from system into the database MenuItems.txt file in resource folder.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
	
	/**
	 * Gets the promotional set list.
	 *
	 * @return the promotional set list
	 */
	public ArrayList<PromoSet> getPromoSetList() {
		return promoSetList;
	}
	
	/**
	 * Gets the promotional set.
	 *
	 * @param index promotional set's index
	 * @return the promotional set
	 */
	public PromoSet getPromoSet(int index) {
		return promoSetList.get(index);
	}
	
	/* (non-Javadoc)
	 * @see service.MenuInterface#createPromoItem(entity.PromoSet)
	 */
	@Override
	public boolean createPromoItem(PromoSet set) {
		if (set != null) {
			set.setSetID(promoSetList.size() + 1);
			promoSetList.add(set);
			callWrite();
			callRead();
			return true;
		}
		return false;
	}

	/* (non-Javadoc)
	 * @see service.MenuInterface#updatePromoItem(entity.PromoSet, java.lang.String, double)
	 */
	@Override
	public boolean updatePromoItem(PromoSet set, String newDescription, double newPrice) {
		set.setSetDescription(newDescription);
		set.setSetPrice(newPrice);
		callWrite();
		callRead();
		return true;
	}

	/* (non-Javadoc)
	 * @see service.MenuInterface#deletePromoItem(entity.PromoSet)
	 */
	@Override
	public boolean deletePromoItem(PromoSet set) {
		promoSetList.remove(set);
		callWrite();
		callRead();
		return true;
	}
		
	/**
	 * Prints all the promotional sets.
	 */
	public void printPromoSets() {
		
		Database.getGui().displayStringsB("");
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
	
	/**
	 * Allows user to pick a promotional set from the list.
	 *
	 * @param text string for the last option (back/exit)
	 * @return a promotional set
	 */
	public PromoSet pickPromoSet(String text) {

		Database.getGui().displayStringsB("");
		PromoSet set = null;
		String listOfPromoItem[] = new String[countPromoSet() + 1];
		
		int count = 0;
		
		for (int i = 0; i < promoSetList.size(); i++) {
			PromoSet ps = (PromoSet) promoSetList.get(i);
			ArrayList<MenuItem> item = ps.getSetItems();
			String itemString = "";
			for (int j = 0; j < item.size(); j++) {
				itemString += "" + item.get(j).getName() + " (" + item.get(j).getType() + ")\n\t";
			}
			
			listOfPromoItem[count] = "\tSet " + ps.getSetID() + " - $" + ps.getSetPrice() + "\n\tDescription: " + ps.getSetDescription() + "\n\tITEMS:\n\t" + itemString;
			count++;
		}
		
		listOfPromoItem[count] = "\t" + text;

		int choice = Database.getGui().detectChoice(listOfPromoItem);
		
		count = 1;
		for (int i = 0; i < promoSetList.size(); i++) {
			if (choice == count) {
				set = (PromoSet) promoSetList.get(i);
				return set;
			}
			count++;
		}
		return set;
	}
	
	/**
	 * Count the number of promotional sets in the list.
	 *
	 * @return the number of promotional sets in the list.
	 */
	public int countPromoSet() {
		return promoSetList.size();
	}

	/**
	 * Reads the promotional sets from the database PromoSets.txt file in resource folder and store them in system.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
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
	        PromoSet ps = new PromoSet(setDescription, itemsArray, setPrice);
	        ps.setSetID(setID);
	        promoSetList.add(ps);
		}
	}
	
	/**
	 * Writes the data from system into the database PromoSets.txt file in resource folder
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	private void writePromoSets() throws IOException {
		
		List alw = new ArrayList();

        for (int i = 0 ; i < promoSetList.size() ; i++) {
        	PromoSet set = (PromoSet)promoSetList.get(i);
			StringBuilder st = new StringBuilder() ;
			st.append(set.getSetID());
			st.append(Database.getSeparator());
			st.append(set.getSetDescription().trim());
			st.append(Database.getSeparator());
			
			ArrayList<MenuItem> items = set.getSetItems();
			
			for (int j = 0 ; j < items.size() ; j++) {
				st.append(items.get(j).getName());
				if (j != items.size() - 1) {
					st.append(Database.getSetSeparator());
				}
			}
			
			st.append(Database.getSeparator());
			st.append(set.getSetPrice());
			alw.add(st.toString()) ;
        }
        Database.getRwFile().write(SETSFILENAME, alw);
	}
	
	/**
	 * Reads the database in resource.
	 *
	 * @return true, if read successful
	 */
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
	
	/**
	 * Writes the database with new data.
	 *
	 * @return true, if write successful
	 */
	private boolean callWrite() {
		try {
			writePromoSets();
			writeMenuItem();
			return true;
		} catch (IOException e) {
			Database.getGui().displayStringsB("IOException > " + e.getMessage());
		}
		return false;
	}
	
}
