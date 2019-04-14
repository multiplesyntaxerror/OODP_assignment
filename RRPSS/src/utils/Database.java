package utils;

import entity.Booking;
import entity.Menu;
import entity.Order;
import entity.Restaurant;
import view.GUI;

/**
 * The Class Database.
 */
public class Database {
	
	/** The ReadWriteFile. */
	private static ReadWriteFile rwFile = new ReadWriteFile();
	
	/** The Constant SEPARATOR. */
	private static final String SEPARATOR = "|";
	
	/** The Constant SETSEPARATOR for promotional set. */
	private static final String SETSEPARATOR = "+";

	/** The Constant TXTSEPARATOR for order. */
	private static final String TXTSEPARATOR = "**";

	/** The GUI. */
	private static GUI gui;

	/** The Restaurant. */
	private Restaurant restaurant;
	
	/** The menu. */
	private Menu menu;
	
	/** The order. */
	private Order order;
	
	/**  the booking. */
	private Booking booking;
	
	/** 
	 * Initialize data.
	 */
	public void initializeData() {
		gui = new GUI();
		restaurant = new Restaurant();
		menu = new Menu();
		order = new Order();
		booking = new Booking();
		
	}
	
	/**
	 * Gets the rw file.
	 *
	 * @return the rw file
	 */
	public static ReadWriteFile getRwFile() {
		return rwFile;
	}

	/**
	 * Gets the separator.
	 *
	 * @return the separator
	 */
	public static String getSeparator() {
		return SEPARATOR;
	}
	
	/**
	 * Gets the set separator.
	 *
	 * @return the set separator
	 */
	public static String getSetSeparator() {
		return SETSEPARATOR;
	}
	
	/**
	 * Gets the order separator.
	 *
	 * @return the order separator
	 */
	public static String getTXTSeparator() {
		return TXTSEPARATOR;
	}
	
	/**
	 * Gets the GUI.
	 *
	 * @return the GUI
	 */
	public static GUI getGui() {
		return gui;
	}
	
	/**
	 * Gets the restaurant.
	 *
	 * @return the restaurant
	 */
	public Restaurant getRestaurant() {
		return restaurant;
	}

	/**
	 * Sets the restaurant.
	 *
	 * @param restaurant the new restaurant
	 */
	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	/**
	 * Gets the menu.
	 *
	 * @return the menu
	 */
	public Menu getMenu() {
		return menu;
	}

	/**
	 * Sets the menu.
	 *
	 * @param menu the new menu
	 */
	public void setMenu(Menu menu) {
		this.menu = menu;
	}

	/**
	 * Gets the order.
	 *
	 * @return the order
	 */
	public Order getOrder() {
		return order;
	}
	
	/**
	 * Sets the order.
	 *
	 * @param order the new order
	 */
	public void setOrder(Order order) {
		this.order = order;
	}

	/**
	 * Gets the booking.
	 *
	 * @return the booking
	 */
	public Booking getBooking() {
		return booking;
	}
	
	/**
	 * Sets the booking.
	 *
	 * @param booking the new booking
	 */
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	
}
