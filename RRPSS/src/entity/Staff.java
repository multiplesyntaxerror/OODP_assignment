package entity;

public class Staff extends Person {
	
	private int employeeId;
	private String jobTitle;
	private double salary;
	
	public Staff(String name, String contact, int employeeId, String jobTitle, double salary){
		super.name = name;
		super.contact = contact;
		this.employeeId = employeeId;
		this.jobTitle = jobTitle;
		this.salary = salary;
	}
	
	//TODO remove
	public Staff() {
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public int getEmployeeId() {
		return employeeId;
	}
	
	public int getEmployeeID(){
		return employeeId;
	}
	
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	public String getJobTitle() {
		return jobTitle;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	
	
}
