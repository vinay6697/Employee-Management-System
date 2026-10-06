package entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Employee {
	
	private int employeeId;
    private String employeeName;
    private String email;
    private long phoneNumber;
    private String department;
    private double salary;
    private LocalDate joiningDate;
    private LocalDateTime createdDate;
    
    //constructor with no arguments
	public Employee() {
	}

	//parameterized constructors 
	public Employee(int employeeId, String employeeName, String email, long phoneNumber, String department,
			double salary, LocalDate joiningDate, LocalDateTime createdDate) {
		this.employeeId = employeeId;
		this.employeeName = employeeName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.department = department;
		this.salary = salary;
		this.joiningDate = joiningDate;
		this.createdDate = createdDate;
	}

	//Getters and Setters
	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public LocalDate getJoiningDate() {
		return joiningDate;
	}

	public void setJoiningDate(LocalDate joiningDate) {
		this.joiningDate = joiningDate;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	@Override
	public String toString() {
		return 
			"Employee Id is \t\t\t:"+employeeId+
			"\nEmployee Name is \t\t:"+employeeName+
			"\nEmployee Email is \t\t:"+email+
			"\nEmployee PhoneNumber is \t:"+phoneNumber+
			"\nEmployee Department is \t\t:"+department+
			"\nEmployee Salary is \t\t:"+salary+
			"\nEmployee joining date is \t:"+joiningDate+
			"\nEmployee created Date is \t:"+createdDate+
			"\n------------------------------------------------------------------";
	}
	
	

}
