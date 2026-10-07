package service;

import java.util.List;

import dao.EmployeeDAOImpl;
import entity.Employee;

public class EmployeeService {
	EmployeeDAOImpl dao=new EmployeeDAOImpl();
	
	public int addEmployee(Employee employee)
	{
		if(employee.getEmployeeName().equals(null))
			return 0;
		if(employee.getSalary()<0)
			return 0;
		if((!(employee.getEmail().contains("@")) || employee.getEmail().length()<10))
			return 0;
		if(employee.getEmployeeId()<0)
			return 0;
		if(employee.getDepartment().equals(null) || employee.getDepartment().isBlank())
			return 0;
		
		return dao.saveEmployee(employee);
		
	}
	
	public int updateEmployee(int id,long phoneNumber,String department)
	{
		String phoneNo=""+phoneNumber;
		if(phoneNo.length()<10 || phoneNo.charAt(0)-48<=5 || department.equals(null))
			return 0;
		else
			return dao.updateEmployee(id,phoneNumber, department);
	}
	
	public int deleteEmployee(int employee_id)
	{
		if(employee_id<0)
			return 0;
		else
			return dao.deleteEmployee(employee_id);
	}
	
	public Employee findEmployeeById(int employee_id)
	{
		if(employee_id<0)
			return null;
		else
			return dao.findEmployeeById(employee_id);
	}
	
	public List<Employee> findAllEmployees()
	{
		return !(dao.findAllEmployees().equals(null))?dao.findAllEmployees():null;
	}
	
	public Employee findByEmail(String email)
	{
		if(email.isBlank() || email.equals(null) || !(email.contains("@")))
			return null;
		else
			return dao.findEmployeeByEmail(email);
	}
	
	public List<Employee> findEmployeeByDepartment(String department)
	{
		if(department.isBlank() || department.equals(null))
			return null;
		else
			return dao.findEmployeeByDepartment(department);
	}
	
	public List<Employee> findEmployeesBySalaryRange(double minSalary, double maxSalary)
	{
		if(minSalary<0 || maxSalary>10000000)
			return null;
		else
			return dao.findEmployeesBySalaryRange(minSalary,maxSalary);
	}
	
	public int countOfEmployee()
	{
		return dao.countEmployees();
	}
	
	public void exportEmployeesToFile(int employeeId)
	{
		if(employeeId<0)
			System.out.println("employeeId is invalid");
		else
			dao.exportEmployeesToFile(employeeId);
	}
	
	

}
