package com.dao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.model.Employee;
public class EmployeeDAO {
	Connection connection = null;

	public boolean saveEmployee(Employee obj) throws ClassNotFoundException, SQLException {
		connection = ConnectionManager.getConnection(); 
		String query ="insert into employee (name, email, password, age, gender, mobile, department, address) values (?,?, ?, ?, ?, ?, ?,?)";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, obj.getName());

		statement.setString(2, obj.getEmail());

		statement.setString(3, obj.getPassword());

		statement.setInt(4, obj.getAge());

		statement.setString(5, obj.getGender());

		statement.setString(6, obj.getMobile());

		statement.setString(7, obj.getDepartment());

		statement.setString(8, obj.getAddress());
		int count = statement.executeUpdate();
		if(count == 1) {
			return true;
		}
		else {
			return false;
		}
				
	}

	public void commit() throws SQLException {
		connection.commit();
		connection.close();
		
	}

	public void rollback() throws SQLException {
		connection.rollback();
		connection.close();
		
	}

	public boolean checkLogin(String email, String password) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionManager.getConnection();
		connection.setAutoCommit(true);
		String query = "select count(*) from employee where email = ? and password =?";
		PreparedStatement statement  = connection.prepareStatement(query);
		statement.setString(1, email);
		statement.setString(2, password);
		ResultSet set =statement.executeQuery();
		int  count = 0;
		if(set.next()) {
			count =  set.getInt(1);	
		}
		connection.close();
		if(count == 1) {
			return true;
		}
		else {
			return false;
		}	
		
	}

	public List<Employee> getAllEmployees() throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionManager.getConnection();
		String query = "select * from employee";
		PreparedStatement statement = connection.prepareStatement(query);
		ResultSet set = statement.executeQuery();
		List<Employee> emplist = new ArrayList<Employee>();
		while(set.next()) {
			Employee obj = new Employee();
			obj.setId(set.getInt(1));
			obj.setName(set.getString(2));
			obj.setEmail(set.getString(3));
			obj.setAge(set.getInt(5));
			obj.setGender(set.getString(6));
			obj.setMobile(set.getString(7));
			obj.setDepartment(set.getString(8));
			obj.setAddress(set.getString(9));
			emplist.add(obj);
			
			
		}
		
		return emplist;
	}

	public Employee searchEmployee(String email) throws ClassNotFoundException, SQLException {
		Connection connection = ConnectionManager.getConnection();
		String query = "select * from employee where email = ?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, email);
		ResultSet set = statement.executeQuery();
		Employee obj = new Employee();
		while(set.next()) {
			
			obj.setId(set.getInt(1));
			obj.setName(set.getString(2));
			obj.setEmail(set.getString(3));
			obj.setAge(set.getInt(5));
			obj.setGender(set.getString(6));
			obj.setMobile(set.getString(7));
			obj.setDepartment(set.getString(8));
			obj.setAddress(set.getString(9));
				
		}
		
		return obj;
			
	}

	public boolean updateEmployee(Employee obj) throws ClassNotFoundException, SQLException {
		connection = ConnectionManager.getConnection(); 
		String query ="update  employee set name=?, email=?, age=?, gender=?, mobile=?, department=?, address=? where id =?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setString(1, obj.getName());
		statement.setString(2, obj.getEmail());
		statement.setInt(3, obj.getAge());
		statement.setString(4, obj.getGender());
		statement.setString(5, obj.getMobile());
		statement.setString(6, obj.getDepartment());
		statement.setString(7, obj.getAddress());
		statement.setInt(8, obj.getId());
		
		int count = statement.executeUpdate();
		if(count == 1) {
			return true;
		}
		else {
			return false;
		}
		
	}

	public void deleteEmployee(int id) throws ClassNotFoundException, SQLException {
		connection = ConnectionManager.getConnection(); 
		String query ="delete  from employee  where id =?";
		PreparedStatement statement = connection.prepareStatement(query);
		statement.setInt(1,id);
		int count = statement.executeUpdate();
		if(count == 1) {
			commit();
		}
		else {
			rollback();;
		}	
	}

}
