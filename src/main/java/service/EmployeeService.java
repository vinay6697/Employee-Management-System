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
		if(!(employee.getEmail().contains("@")))
			return 0;
		if(employee.getEmployeeId()<0)
			return 0;
		if(employee.getDepartment().equals(null))
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

}
