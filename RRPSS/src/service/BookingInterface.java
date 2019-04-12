package service;

import entity.Customer;

public interface BookingInterface {
	public boolean createBooking(Customer customer);
	public boolean deleteBooking(Customer customer);
}