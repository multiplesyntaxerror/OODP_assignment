package service;

import entity.MenuItem;
import entity.PromoSet;

public interface MenuInterface {
	
	public boolean createMenuItem(MenuItem item);
	public boolean updateMenuItem(MenuItem item, String newDescription, double newPrice);
	public boolean deleteMenuItem(MenuItem item);
	public boolean createPromoItem(PromoSet set);
	public boolean updatePromoItem(PromoSet set, String newDescription, double newPrice);
	public boolean deletePromoItem(PromoSet set);

}
