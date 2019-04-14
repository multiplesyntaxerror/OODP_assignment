package service;

import entity.Customer;

/**
 * The Interface BookingInterface.
 */
public interface BookingInterface {
	
	/**
	 * Creates a booking.
	 *
	 * @param customer the customer
	 * @return true, if successful
	 */
	public boolean createBooking(Customer customer);
	
	/**
	 * Delete a booking.
	 *
	 * @param customer the customer
	 * @return true, if successful
	 */
	public boolean deleteBooking(Customer customer);
}