package entity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import service.MenuInterface;
import utils.ReadWriteFile;

public class Menu implements MenuInterface{
	
	private static final String FILENAME = "res/MenuItems.txt";
	
	private ArrayList<MenuItem> menuList = new ArrayList<MenuItem>();
	
	private static ReadWriteFile fileToDB;
	private static String seperator;
	
	public Menu(ReadWriteFile rwFile, String sep) {
		fileToDB = rwFile;
		seperator = sep;
		callRead();
		//printMenuItems();
	}

	public ArrayList<MenuItem> getMenuList() {
		return menuList;
	}

	public void setMenuList(ArrayList<MenuItem> menuList) {
		this.menuList = menuList;
	}

	@Override
	public boolean createMenuItem(MenuItem item) {
		int index = itemExistReturnIndex(item);
		System.out.println(index);
		if(index == -1) {
			menuList.add(item);
			callWrite();
			printMenuItems();
			return true;
		}
		return false;
	}

	@Override
	public boolean updateMenuItem(int index, MenuItem item) {
		//outseide check for exist
		menuList.set(index, item);
		callWrite();
		return true;
	}

	@Override
	public boolean deleteMenuItem(MenuItem item) {
		int index = itemExistReturnIndex(item);
		if (index != -1) {
			menuList.remove(index);
			callWrite();
			return true;
		}
		return false;
	}
	
	public int itemExistReturnIndex(MenuItem item) {
		for (MenuItem i : menuList) {
			if (i.getName().equalsIgnoreCase(item.getName())) {
				return menuList.indexOf(i);
			}
		}
		return -1;
	}
	
	public void printMenuItems() {
		for (int i = 0; i < menuList.size(); i++) {
			MenuItem item = (MenuItem) menuList.get(i);
			System.out.println("Name: " + item.getName());
			System.out.println("Description: " + item.getDescription());
			System.out.println("Type: " + item.getType());
			System.out.println("Price: $" + item.getPrice());
			System.out.println();
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
			System.out.println("IOException > " + e.getMessage());
		}
		return false;
		
	}
	
	private boolean callWrite() {
		try {
			writeMenuItem(FILENAME, menuList);
			return true;
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
		return false;
	}
	
	public static ArrayList<MenuItem> readMenuItem(String filename) throws IOException {

		ArrayList<String> stringArray = (ArrayList<String>) fileToDB.read(filename);
		ArrayList<MenuItem> itemList = new ArrayList<MenuItem>() ;

        for (int i = 0 ; i < stringArray.size() ; i++) {
				String st = (String)stringArray.get(i);
				StringTokenizer star = new StringTokenizer(st , seperator);	
				String name = star.nextToken().trim();
				String description = star.nextToken().trim();
				int type = Integer.parseInt(star.nextToken());
				double price = Double.parseDouble(star.nextToken().trim()); 
				
				MenuItem item = null;
				
				switch (type) {
					case 1:
						item = new MainCourse(name, description, price);
						break;
					case 2: 
						item = new Dessert(name, description, price);
						break;
					case 3:
						item = new Drinks(name, description, price);
						break;
					default:
						System.out.println("Error");
						item = new MainCourse(name, description, price);
				}

				itemList.add(item);
				
			}
        return itemList ;
	}
	
	public static void writeMenuItem(String filename, List al) throws IOException {
		List alw = new ArrayList() ;

        for (int i = 0 ; i < al.size() ; i++) {
        		MenuItem item = (MenuItem)al.get(i);
				StringBuilder st = new StringBuilder() ;
				st.append(item.getName().trim());
				st.append(seperator);
				st.append(item.getDescription().trim());
				st.append(seperator);
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
				st.append(seperator);
				st.append(item.getPrice());
				alw.add(st.toString()) ;
			}
        fileToDB.write(filename,alw);
	}
}
