package entity;

public class Staff {
	private String name;
	private char gender;
	private int employeeID;
	private String jobTitle;
	
	public Staff(String name, char gender, int employeeID, String jobTitle){
		this.name = name;
		this.gender = gender;
		this.employeeID = employeeID;
		this.jobTitle = jobTitle;
	}
	
	public Staff(){}
	
	public void setName(String name){
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setGender(char gender) {
		this.gender = gender;
	}
	
	public char getGender() {
		return gender;
	}
	
	public void setEmployeeID(int employeeID) {
		this.employeeID = employeeID;
	}
	
	public int getEmployeeID(){
		return employeeID;
	}
	
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}
	
	public String getJobTitle() {
		return jobTitle;
	}
}
