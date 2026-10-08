package dao;

import java.sql.Connection;
import java.util.List;

import entity.Employee;

public interface EmployeeDAO {

	    int saveEmployee(Employee employee);

	    int updateEmployee(int id,long phoneNumber,String department);

	    int deleteEmployee(int employeeId);

	    Employee findEmployeeById(int employeeId);

	    List<Employee> findAllEmployees();

	    List<Employee> findEmployeeByDepartment(String department);

	    Employee findEmployeeByEmail(String email);

	    List<Employee> findEmployeesBySalaryRange(
	            double minSalary,
	            double maxSalary);

	    int countEmployees();

	    void exportEmployeesToFile(int employeeId);
	    
	    void exportEmployeesToFileByDepartment(String department);
	    
	    void employeeStatics();
	    
	    int getEmployeeCountByDepartment(Connection connection);
}
