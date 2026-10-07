package dao;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import entity.Employee;
import util.DatabaseConnection;

public class EmployeeDAOImpl implements EmployeeDAO{

	@Override
	public int saveEmployee(Employee employee) {
		int result=0;
		String insertQuery="INSERT INTO EMPLOYEE_DETAILS VALUES(?,?,?,?,?,?,?,?)";
		
		try(Connection connection=DatabaseConnection.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(insertQuery))
		{
			preparedStatement.setInt(1, employee.getEmployeeId());
			preparedStatement.setString(2, employee.getEmployeeName());
			preparedStatement.setString(3, employee.getEmail());
			preparedStatement.setLong(4, employee.getPhoneNumber());
			preparedStatement.setString(5,employee.getDepartment());
			preparedStatement.setDouble(6, employee.getSalary());
			preparedStatement.setDate(7, Date.valueOf(employee.getJoiningDate()));
			preparedStatement.setTimestamp(8, Timestamp.valueOf(LocalDateTime.now()));
			result=preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int updateEmployee(int id,long phoneNumber,String department) {

		int result=0;
		String updateQuery="UPDATE EMPLOYEE_DETAILS SET PHONE_NUMBER=?, DEPARTMENT=? WHERE EMPLOYEE_ID=?";
		
		try(Connection connection=DatabaseConnection.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(updateQuery))
		{
			preparedStatement.setLong(1, phoneNumber);
			preparedStatement.setString(2,department);
			preparedStatement.setInt(3, id);

			result=preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	
	@Override
	public int deleteEmployee(int employeeId)
	{
		int result=0;
		String updateQuery="DELETE FROM EMPLOYEE_DETAILS WHERE EMPLOYEE_ID=?";
		try(Connection connection = DatabaseConnection.getConnection();
			PreparedStatement preparedStatment=connection.prepareStatement(updateQuery))
		{
			preparedStatment.setInt(1, employeeId);
			
			result=preparedStatment.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public Employee findEmployeeById(int employeeId)
	{
		Employee employee=null;
		String selectQuery="SELECT * FROM EMPLOYEE_DETAILS WHERE EMPLOYEE_ID=?";
		try(Connection connection = DatabaseConnection.getConnection();
				PreparedStatement preparedStatment=connection.prepareStatement(selectQuery))
			{
				preparedStatment.setInt(1, employeeId);
				
				ResultSet resultSet=preparedStatment.executeQuery();
				
				while(resultSet.next())
				{
					int id=resultSet.getInt(1);
					String name=resultSet.getString(2);
					String email=resultSet.getString(3);
					long phoneNumber=resultSet.getLong(4);
					String department=resultSet.getString(5);
					double salary=resultSet.getLong(6);
					LocalDate date=resultSet.getDate(7).toLocalDate();
					LocalDateTime createdDate=resultSet.getTimestamp(8).toLocalDateTime();
					
					employee =new Employee(id,name,email,phoneNumber,department,salary,date,createdDate);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		return employee;
	}


	@Override
	public List<Employee> findAllEmployees() {
		Employee employee=null;
		List<Employee> employees=new ArrayList<>();
		String selectQuery="SELECT * FROM EMPLOYEE_DETAILS";
		try(Connection connection=DatabaseConnection.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(selectQuery))
		{
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				int id=resultSet.getInt(1);
				String name=resultSet.getString(2);
				String email=resultSet.getString(3);
				long phoneNumber=resultSet.getLong(4);
				String department=resultSet.getString(5);
				double salary=resultSet.getLong(6);
				LocalDate date=resultSet.getDate(7).toLocalDate();
				LocalDateTime createdDate=resultSet.getTimestamp(8).toLocalDateTime();
				
				employee =new Employee(id,name,email,phoneNumber,department,salary,date,createdDate);
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	@Override
	public Employee findEmployeeByEmail(String employee_Email) {
		Employee employee=null;
		String selectQuery="SELECT * FROM EMPLOYEE_DETAILS WHERE EMAIL=?";
		try(Connection connection=DatabaseConnection.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(selectQuery))
		{
			preparedStatement.setString(1, employee_Email);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				int id=resultSet.getInt(1);
				String name=resultSet.getString(2);
				String email=resultSet.getString(3);
				long phoneNumber=resultSet.getLong(4);
				String department=resultSet.getString(5);
				double salary=resultSet.getLong(6);
				LocalDate date=resultSet.getDate(7).toLocalDate();
				LocalDateTime createdDate=resultSet.getTimestamp(8).toLocalDateTime();
				
				employee =new Employee(id,name,email,phoneNumber,department,salary,date,createdDate);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employee;
	}

	
	@Override
	public List<Employee> findEmployeeByDepartment(String employeeDepartment) {
		Employee employee=null;
		List<Employee> employees=new ArrayList<>();
		String selectQuery="SELECT * FROM EMPLOYEE_DETAILS WHERE TRIM(LOWER(DEPARTMENT)) LIKE ?";
		try(Connection connection=DatabaseConnection.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(selectQuery);
				)
		{
			preparedStatement.setString(1, "%"+employeeDepartment.toLowerCase().trim()+"%");
			ResultSet resultSet=preparedStatement.executeQuery();
			
			while(resultSet.next())
			{
				int id=resultSet.getInt(1);
				String name=resultSet.getString(2);
				String email=resultSet.getString(3);
				long phoneNumber=resultSet.getLong(4);
				String department=resultSet.getString(5);
				double salary=resultSet.getLong(6);
				LocalDate date=resultSet.getDate(7).toLocalDate();
				LocalDateTime createdDate=resultSet.getTimestamp(8).toLocalDateTime();
				
				employee =new Employee(id,name,email,phoneNumber,department,salary,date,createdDate);
				employees.add(employee);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		return employees;
	}

	
	@Override
	public List<Employee> findEmployeesBySalaryRange(double minSalary, double maxSalary)
	{
		Employee employee=null;
		List<Employee> employees=new ArrayList<>();
 		String selectQuery="SELECT * FROM EMPLOYEE_DETAILS WHERE SALARY BETWEEN ? AND ?";
		try(Connection connection=DatabaseConnection.getConnection();
			PreparedStatement preparedStatement=connection.prepareCall(selectQuery))
		{
			preparedStatement.setDouble(1, minSalary);
			preparedStatement.setDouble(2, maxSalary);
			
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				int id=resultSet.getInt(1);
				String name=resultSet.getString(2);
				String email=resultSet.getString(3);
				long phoneNumber=resultSet.getLong(4);
				String department=resultSet.getString(5);
				double salary=resultSet.getLong(6);
				LocalDate date=resultSet.getDate(7).toLocalDate();
				LocalDateTime createdDate=resultSet.getTimestamp(8).toLocalDateTime();
				
				employee =new Employee(id,name,email,phoneNumber,department,salary,date,createdDate);
				employees.add(employee);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return employees;
	}

	
	@Override
	public int countEmployees() {
		String selectQuery="SELECT COUNT(*) FROM EMPLOYEE_DETAILS";
		int count=0;
		try(Connection connection=DatabaseConnection.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(selectQuery))
		{
			ResultSet resultSet=preparedStatement.executeQuery();
			while(resultSet.next())
			{
				count=resultSet.getInt(1);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public void exportEmployeesToFile(int employeeId) {
		String selectQuery="SELECT * FROM EMPLOYEE_DETAILS WHERE EMPLOYEE_ID=?";
		try(Connection connection=DatabaseConnection.getConnection();
			PreparedStatement preparedStatement=connection.prepareStatement(selectQuery))
		{
			preparedStatement.setInt(1, employeeId);
			ResultSet resultSet=preparedStatement.executeQuery();
			File file=new File("C:\\Users\\vinay\\JDBC\\EmployeeManagementSystem\\EmployeeDetails.txt");
			FileWriter fileWriter=new FileWriter(file);
			
			while(resultSet.next())
			{
				fileWriter.write(" Employee id is\t\t\t: "+resultSet.getInt(1));
				fileWriter.write("\n Employee name is\t\t\t:"+resultSet.getString(2));
				fileWriter.write("\n Employee email is\t\t\t: "+resultSet.getString(3));
				fileWriter.write("\n Employee phone is\t\t\t: "+resultSet.getString(4));
				fileWriter.write("\n Employee department is\t: "+resultSet.getString(5));
				fileWriter.write("\n Employee salary is\t\t\t: "+resultSet.getDouble(6));
				fileWriter.write("\n Employee joined date is\t\t: "+resultSet.getDate(7).toLocalDate());
				fileWriter.write("\n Employee created date is\t:"+resultSet.getTimestamp(8).toLocalDateTime());
			}
			fileWriter.flush();
			System.out.println("Data stored in file successfully");
			fileWriter.close();
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
	}
}
