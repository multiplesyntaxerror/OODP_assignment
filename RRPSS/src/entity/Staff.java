package entity;

// TODO: Auto-generated Javadoc
/**
 * The Class Staff.
 */
public class Staff extends Person {
	
	/** The employee id. */
	private int employeeId;
	
	/** The job title. */
	private String jobTitle;
	
	/** The salary. */
	private double salary;
	
	/**
	 * Instantiates a new staff.
	 *
	 * @param name the name
	 * @param contact the contact
	 * @param employeeId the employee id
	 * @param jobTitle the job title
	 * @param salary the salary
	 */
	public Staff(String name, String contact, int employeeId, String jobTitle, double salary){
		super.name = name;
		super.contact = contact;
		this.employeeId = employeeId;
		this.jobTitle = jobTitle;
		this.salary = salary;
	}
	
	/**
	 * Instantiates a new staff.
	 */
	//TODO remove
	public Staff() {
	}

	/**
	 * Sets the employee id.
	 *
	 * @param employeeId the new employee id
	 */
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	/**
	 * Gets the employee id.
	 *
	 * @return the employee id
	 */
	public int getEmployeeId() {
		return employeeId;
	}
	
	/**
	 * Sets the job title.
	 *
	 * @param jobTitle the new job title
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	/**
	 * Gets the job title.
	 *
	 * @return the job title
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * Gets the salary.
	 *
	 * @return the salary
	 */
	public double getSalary() {
		return salary;
	}

	/**
	 * Sets the salary.
	 *
	 * @param salary the new salary
	 */
	public void setSalary(double salary) {
		this.salary = salary;
	}

	
	
}
