# Restaurant Reservation and Point of Sale System (RRPSS)

RRPSS is an application to computerize the processes of making reservation, recording of
orders and displaying of sale records. It will be solely used by the restaurant staff.

## Getting Started

- This program does not require installation
- Import into eclipse workspace and run RRPSS.java in app folder.
- Program does not include GUI.
- Program is a console program.

## Running the tests

1. Menu Options - able to display the lists of menu items and promotional sets in the system.
				- allows staff to add/update/remove a menu item from restaurant inventory.
				- allows staff to add/update/remove a promotional set from restaurant inventory.
				- txt database for menu items and promotional set are MenuItems.txt and PromoSets.txt files respectively.

2. Order Options - able to display the list of orders made by the staff.
				 - allow staff to create order for a table.
				 - allow staff to add/remove an item from an order.
				 - order will be removed when all items are being removed.
				 - txt database for order is in Order.txt file

3. Booking Options - able to display the list of reservations made by the staff.
				   - able to display all the available tables that are open for creating order and reserving.
				   - allows staff to create/remove a reservation.
				   - txt database for booking is in Booking.txt file.

4. Billing Options - able to print bills created when ordering and prints by table number.
				   - able to print out restaurant revenue report by day/month/year.

5. Additional Notes - Task timer is in RRPSS.java app foler to remove expired bookings.
					- Creating Booking is only available at 11am-3pm & 6pm-10pm, if testing outside of the time you may remove comment at line 75 of BookingController.java and force nonBooking to be false.
					- GST % is controlled in Bill.java.
					- There are no options to add/remove staff, however the database Staff.txt can be edited.
					- date/time for booking and billing is to be entered in specific format.

### Coding style

- Java is the language used to code this program.
- OO programming style is used to design this program.

## Contributors

Qin Kai
Shi Zheng
Bryan
Xieng Yiong

## Versioning

For the versions available, visit https://github.com/multiplesyntaxerror/OODP_assignment. 
