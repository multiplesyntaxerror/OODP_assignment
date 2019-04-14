package entity;

/**
 * The Class Staff.
 */
public class Staff extends Person {
	
	/** The employee ID. */
	private int employeeId;
	
	/** The staff job title. */
	private String jobTitle;
	
	/** The staff salary. */
	private double salary;
	
	/**
	 * Instantiates a new staff.
	 *
	 * @param name the name
	 * @param contact the contact
	 * @param employeeId the employee ID
	 * @param jobTitle the employee job title
	 * @param salary the employee salary
	 */
	public Staff(String name, String contact, int employeeId, String jobTitle, double salary){
		super.name = name;
		super.contact = contact;
		this.employeeId = employeeId;
		this.jobTitle = jobTitle;
		this.salary = salary;
	}

	/**
	 * Gets the employee ID.
	 *
	 * @return the employee ID
	 */
	public int getEmployeeId() {
		return employeeId;
	}

	/**
	 * Sets the employee ID.
	 *
	 * @param employeeId the new employee ID
	 */
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	
	/**
	 * Gets the employee job title.
	 *
	 * @return the employee job title
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * Sets the employee job title.
	 *
	 * @param jobTitle the new employee job title
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	/**
	 * Gets the employee salary.
	 *
	 * @return the employee salary
	 */
	public double getSalary() {
		return salary;
	}

	/**
	 * Sets the employee salary.
	 *
	 * @param salary the new employee salary
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}

	
	
}
