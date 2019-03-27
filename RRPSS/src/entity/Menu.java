package entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import service.MenuInterface;
import utils.Database;
import utils.ReadWriteFile;

public class Menu implements MenuInterface{
	
	private static final String FILENAME = "res/MenuItems.txt";
	
	private ArrayList<MainCourse> mcList = new ArrayList<MainCourse>();
	private ArrayList<Dessert> deList = new ArrayList<Dessert>();
	private ArrayList<Drinks> drList = new ArrayList<Drinks>();

	private ArrayList menuList = new ArrayList();
	
	public Menu() {
		callRead();
		//printMenuItems();
	}

	public ArrayList getMenuList() {
		return menuList;
	}
	
	@Override
	public boolean createMenuItem(MenuItem item) {
		int[] index = itemExistReturnIndex(item.getName());
		String type = item.getType();
//		System.out.println(index);
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
		switch(index[0]) {
			case 1:
				mcList.remove(index[1]);
				break;
			case 2:
				deList.remove(index[1]);
				break;
			case 3:
				drList.remove(index[1]);
				break;
		}
		createMenuItem(item);
		return true;
	}

	@Override
	public boolean deleteMenuItem(int[] index) {
		switch(index[0]) {
			case 1:
				mcList.remove(index[1]);
				break;
			case 2:
				deList.remove(index[1]);
				break;
			case 3:
				drList.remove(index[1]);
				break;
		}
		callWrite();
		callRead();
		return true;
	}
	
	public int[] itemExistReturnIndex(String name) {
		int[] index = {-1, -1};
		for (int i = 0; i < menuList.size(); i++) {
			ArrayList list = (ArrayList) menuList.get(i);
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
		for (int i = 0; i < menuList.size(); i++) {
			ArrayList list = (ArrayList) menuList.get(i);
			for (int j = 0; j < list.size(); j++) {
				MenuItem item = (MenuItem) list.get(j);
				Database.getGui().displayStringsB("Name: " + item.getName());
				Database.getGui().displayStringsB("Description: " + item.getDescription());
				Database.getGui().displayStringsB("Type: " + item.getType());
				Database.getGui().displayStringsB("Price: $" + item.getPrice() + "\n");
			}
		}
	}
	
	@Override
	public boolean createPromoItem() {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean updatePromoItem() {
		return false;
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean deletePromoItem() {
		return false;
		// TODO Auto-generated method stub
		
	}
	
	private boolean callRead() {
		try {
			menuList = readMenuItem(FILENAME);
			return true;
		} catch (IOException e) {
			Database.getGui().displayStringsB("IOException > " + e.getMessage());
		}
		return false;
		
	}
	
	private boolean callWrite() {
		menuList.add(mcList);
		menuList.add(deList);
		menuList.add(drList);
		try {
			writeMenuItem(FILENAME, menuList);
			return true;
		} catch (IOException e) {
			Database.getGui().displayStringsB("IOException > " + e.getMessage());
		}
		return false;
	}
	
	public ArrayList<MenuItem> readMenuItem(String filename) throws IOException {

		ArrayList<String> stringArray = (ArrayList<String>) Database.getRwFile().read(filename);
		ArrayList<MainCourse> courseList = new ArrayList<MainCourse>();
		ArrayList<Dessert> desertList = new ArrayList<Dessert>();
		ArrayList<Drinks> drinksList = new ArrayList<Drinks>();

		ArrayList itemList = new ArrayList();
		
        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				StringTokenizer star = new StringTokenizer(st , Database.getSeparator());	
				String name = star.nextToken().trim();
				String description = star.nextToken().trim();
				int type = Integer.parseInt(star.nextToken());
				double price = Double.parseDouble(star.nextToken().trim()); 
								
				switch (type) {
					case 1:
						MainCourse mc = new MainCourse(name, description, price);
						courseList.add(mc);
						break;
					case 2: 
						Dessert de = new Dessert(name, description, price);
						desertList.add(de);
						break;
					case 3:
						Drinks dr = new Drinks(name, description, price);
						drinksList.add(dr);
						break;
					default:
						Database.getGui().displayStringsB("Error");
				}
			}

		itemList.add(courseList);
		itemList.add(desertList);
		itemList.add(drinksList);
        return itemList ;
	}
	
	public void writeMenuItem(String filename, ArrayList menuList) throws IOException {
		List alw = new ArrayList() ;

        for (int i = 0 ; i < menuList.size() ; i++) {
        	ArrayList array = (ArrayList)menuList.get(i);
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
				alw.add(st.toString()) ;
			}
        }
        Database.getRwFile().write(filename,alw);
	}
}