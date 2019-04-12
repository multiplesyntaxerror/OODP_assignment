package service;

import entity.Customer;
import entity.Table;

public interface BookingInterface {
	//public boolean createBooking(String date,String atime,int pax,String name,String contact,boolean reserved); 
	public boolean createBooking(Customer customer);
	public boolean deleteBooking(Customer customer);
}