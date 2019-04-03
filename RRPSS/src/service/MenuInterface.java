package service;

import entity.MenuItem;
import entity.PromoSet;

public interface MenuInterface {
	
	public boolean createMenuItem(MenuItem item);
	public boolean updateMenuItem(int[] index, MenuItem item);
	public boolean deleteMenuItem(int[] index);
	public boolean createPromoItem(PromoSet set);
	public boolean updatePromoItem(int index, PromoSet set);
	public boolean deletePromoItem(int index);

}
