package service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import dao.EmployeeDAOImpl;
import entity.Employee;
import util.DatabaseConnection;

public class EmployeeService {
	EmployeeDAOImpl dao = new EmployeeDAOImpl();

	public int addEmployee(Employee employee) {
		if (employee.getEmployeeName().equals(null))
			return 0;
		if (employee.getSalary() < 0)
			return 0;
		if ((!(employee.getEmail().contains("@")) || employee.getEmail().length() < 10))
			return 0;
		if (employee.getEmployeeId() < 0)
			return 0;
		if (employee.getDepartment().equals(null) || employee.getDepartment().isBlank())
			return 0;

		return dao.saveEmployee(employee);

	}

	public int updateEmployee(int id, long phoneNumber, String department) {
		String phoneNo = "" + phoneNumber;
		if (phoneNo.length() < 10 || phoneNo.charAt(0) - 48 <= 5 || department.equals(null))
			return 0;
		else
			return dao.updateEmployee(id, phoneNumber, department);
	}

	public int deleteEmployee(int employee_id) {
		if (employee_id < 0)
			return 0;
		else
			return dao.deleteEmployee(employee_id);
	}

	public Employee findEmployeeById(int employee_id) {
		if (employee_id < 0)
			return null;
		else
			return dao.findEmployeeById(employee_id);
	}

	public List<Employee> findAllEmployees() {
		return !(dao.findAllEmployees().equals(null)) ? dao.findAllEmployees() : null;
	}

	public Employee findByEmail(String email) {
		if (email.isBlank() || email.equals(null) || !(email.contains("@")))
			return null;
		else
			return dao.findEmployeeByEmail(email);
	}

	public List<Employee> findEmployeeByDepartment(String department) {
		if (department.isBlank() || department.equals(null))
			return null;
		else
			return dao.findEmployeeByDepartment(department);
	}

	public List<Employee> findEmployeesBySalaryRange(double minSalary, double maxSalary) {
		if (minSalary < 0 || maxSalary > 10000000)
			return null;
		else
			return dao.findEmployeesBySalaryRange(minSalary, maxSalary);
	}

	public int countOfEmployee() {
		return dao.countEmployees() != 0 ? dao.countEmployees() : 0;
	}

	public void exportEmployeesToFile(int employeeId) {
		if (employeeId < 0)
			System.out.println("employeeId is invalid");
		else
			dao.exportEmployeesToFile(employeeId);
	}

	public void exportITDeparment(String department) {
		if (department.isBlank() || department.equals(null))
			System.out.println("Invalid department name");
		else
			dao.exportEmployeesToFileByDepartment(department);
	}

	public Map<String, Double> employeeStatics() {
		return dao.employeeStatics()!=null?dao.employeeStatics():null;
	}

	public Map<String, Integer> getEmployeeCountByDepartment() {
		if(dao.getEmployeeCountByDepartment(DatabaseConnection.getConnection())!=null)
			return dao.getEmployeeCountByDepartment(DatabaseConnection.getConnection());
		else
			return null;
				
	}

	// importing the employees from csv file
	public int importEmployeesFromCsv(String filePath) throws IOException {

		List<Employee> employees = new ArrayList<>();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

		try (BufferedReader reader = Files.newBufferedReader(Path.of(filePath))) {

			String line;
			int lineNumber = 0;

			while ((line = reader.readLine()) != null) {
				lineNumber++;

				// Skip the CSV header
				if (lineNumber == 1) {
					continue;
				}

				if (line.isBlank()) {
					continue;
				}

				String[] data = line.split(",", -1);

				if (data.length != 8) {
					throw new IllegalArgumentException("Invalid number of columns at line " + lineNumber);
				}

				try {
					Employee employee = new Employee(Integer.parseInt(data[0].trim()), data[1].trim(), data[2].trim(),
							Long.parseLong(data[3].trim()), data[4].trim(), Double.parseDouble(data[5].trim()),
							LocalDate.parse(data[6].trim()), LocalDateTime.parse(data[7].trim(), formatter));
					employees.add(employee);

				} catch (RuntimeException e) {
					throw new IllegalArgumentException("Invalid employee data at line " + lineNumber, e);
				}
			}
		}
		if (employees.isEmpty()) {
			return 0;
		}
		return dao.saveEmployeesBatch(employees);
	}
}
