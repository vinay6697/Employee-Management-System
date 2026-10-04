package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

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
}
