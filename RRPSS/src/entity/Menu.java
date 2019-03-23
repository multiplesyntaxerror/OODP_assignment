package entity;

import java.io.IOException;
import java.util.ArrayList;

import service.MenuInterface;
import utils.InitData;

public class Menu implements MenuInterface{
	
	private static final String FILENAME = "res/MenuItems.txt";
	
	private ArrayList<MenuItem> menuList = new ArrayList<MenuItem>();
	
	public Menu() {
		callRead();
		printMenuItem();
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
			printMenuItem();
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
	
	public void printMenuItem() {
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
		InitData txtDB = new InitData();
		
		try {
			menuList = InitData.readMenuItem(FILENAME);
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
		return false;
		
	}
	
	private boolean callWrite() {
		try {
			InitData.writeMenuItem(FILENAME, menuList);
			return true;
		} catch (IOException e) {
			System.out.println("IOException > " + e.getMessage());
		}
		return false;
	}
}
