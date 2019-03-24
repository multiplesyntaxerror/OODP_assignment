package service;

import entity.MenuItem;

public interface MenuInterface {
	
	public boolean createMenuItem(MenuItem item);
	public boolean updateMenuItem(int index, MenuItem item);
	public boolean deleteMenuItem(int index);
	public boolean createPromoItem();
	public boolean updatePromoItem();
	public boolean deletePromoItem();

}
