package main;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import entity.Employee;
import service.EmployeeService;

public class EmployeeManagementSystem {
	
	public static void main(String[] args) {
		EmployeeService employeeService=new EmployeeService();
		Scanner sc=new Scanner(System.in);
		String c="Y";
		String str="";
		do {
			System.out.println("Enter 1 to save the Employee");
			System.out.println("Enter 2 to update the Employee");
			System.out.println("Enter 3 to delete the Employee");
			System.out.println("Enter 4 to find employee by Id");
			System.out.println("Enter 5 to find all the employees");
			System.out.println("Enter 6 to find the employee by email");
			System.out.println("Enter 7 to find the employee by department");
			System.out.println("Enter 8 to find the employee using salary range");
			System.out.println("Enter 9 to find no of employees");
			System.out.println("Enter 10 to export employee details to file");
			System.out.println("Enter 11 to export departmentDetails to file");
			System.out.println("Enter 12 to find the employee statics");
			System.out.println("Enter 13 to find the employee statics");
			int choice=sc.nextInt();
			
			switch(choice)
			{
				case 1:
				{
					System.out.println("Enter the employe id");
					int id=sc.nextInt();
					sc.nextLine();
					
					System.out.println("Enter the employee name");
					String name=sc.nextLine();
					
					System.out.println("Enter the email id");
					String email=sc.nextLine();
					
					System.out.println("Enter the mobile number");
					long mobileNumber=sc.nextLong();
					sc.nextLine();
					
					
					System.out.println("Enter the department");
					String department=sc.nextLine();
					
					System.out.println("Enter the salary");
					double salary=sc.nextDouble();
					
					LocalDate date=LocalDate.now();
					
					LocalDateTime dateTime = LocalDateTime.now();
					
					Employee employee=
							new Employee(id,name,email,mobileNumber,department,salary,date,dateTime);
					
					int result=employeeService.addEmployee(employee);
					if(result>0)
						System.out.println("Employee details saved successfully");
					else
						System.out.println("Details not saved");
					break;
					
				}
				case 2:
				{
					System.out.println("Enter the id to update the details");
					int id=sc.nextInt();
					
					System.out.println("Enter the phoneNumber");
					long phoneNumber=sc.nextLong();
					
					System.out.println("Enter the department name");
					sc.nextLine();
					String department=sc.nextLine();
					
					int result=employeeService.updateEmployee(id,phoneNumber, department);
					if(result>0)
						System.out.println("Employee details updated");
					else
						System.out.println("Employee details not updated");
					break;
				}
				case 3:
				{
					System.out.println("Enter the employee id");
					int employeeId=sc.nextInt();
					
					int result=employeeService.deleteEmployee(employeeId);
					if(result>0)
						System.out.println("Employee deleted successfully");
					else
						System.out.println("Employee not deleted");
					
					break;
				}
				case 4:
				{
					System.out.println("Enter the employee id");
					int employeeId=sc.nextInt();
					
					Employee employee=employeeService.findEmployeeById(employeeId);
					if(employee!=null)
						System.out.println(employee);
					else
						System.out.println("Unable to fetch the employee details");
					break;
				}
				case 5:{
						List<Employee> employees=employeeService.findAllEmployees();
						if(employees!=null)
						{
							for(Employee employee:employees)
							{
								System.out.println(employee);
							}
						}
						else
							System.out.println("No employees found");
						break;
				}
				case 6:
				{
					sc.nextLine();
					System.out.println("Enter the email id");
					String email=sc.nextLine();
					Employee employee=employeeService.findByEmail(email);
					if(employee!=null)
						System.out.println(employee);
					else
						System.out.println("unable to find the employee");
					break;
				}
				case 7:
				{
					sc.nextLine();
					System.out.println("Enter the department name");
					String department=sc.nextLine();
					
					List<Employee> employees=employeeService.findEmployeeByDepartment(department);
					if(employees!=null)
					{
						for(Employee employee:employees)
						{
							System.out.println(employee);
						}
					}
					else
					{
						System.out.println("employee not found");
					}
					break;
				}
				case 8:
				{
					System.out.println("Enter the min salary");
					double minSalary=sc.nextDouble();
					System.out.println("Enter the max salary");
					double maxSalary=sc.nextDouble();
					
					List<Employee> employees=employeeService.findEmployeesBySalaryRange(minSalary, maxSalary);
					if(employees!=null)
					{
						for(Employee employee:employees)
						{
							System.out.println(employee);
						}
					}
					else
						System.out.println("No employees found");
					break;
				}
				case 9:
				{
					int count=employeeService.countOfEmployee();
					if(count>0)
						System.out.println("No of employees is:"+count);
					else
						System.out.println("no employees found");
					
					break;
				}
				case 10:
				{
					System.out.println("Enter the employee id");
					int employeeId=sc.nextInt();
					employeeService.exportEmployeesToFile(employeeId);
					break;
				}
				case 11:
				{
					System.out.println("Enter the department name");
					sc.nextLine();
					String name=sc.nextLine();
					employeeService.exportITDeparment(name);
					break;
				}
				case 12:
				{
					employeeService.employeeStatics();
					break;
				}
				case 13:
				{
					Map<String ,Integer> employees=employeeService.getEmployeeCountByDepartment();
					
					for(String department : employees.keySet())
					{
						 System.out.printf("%-25s : %d%n",department,employees.get(department));
					}
					break;
				}
				default:
				{
					System.out.println("Invalid input \nplease enter the valid input");
					break;
				}
				
			}//end of switch
			
			System.out.println("Do you want to repeat the \nEnter Y for YES  & N for NO");
			str=sc.next();
		}while(c.equalsIgnoreCase(str));
		
		sc.close();
		System.out.println("Connection closed successfully");
	}
}
