package com.controller;

import java.io.IOException;
import java.net.Authenticator.RequestorType;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.EmployeeDAO;
import com.model.Employee;


@WebServlet("/findAll")
public class GetAllEmployees extends HttpServlet {
	private static final long serialVersionUID = 1L;
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	
		EmployeeDAO dao = new EmployeeDAO();
		RequestDispatcher dispatcher = request.getRequestDispatcher("viewemps.jsp");
		
		try {
		List<Employee> empList = dao.getAllEmployees();
		request.setAttribute("empList",empList);
		dispatcher.forward(request, response);
			
		}
		catch(ClassNotFoundException e) {
			System.out.println(e);	
		}
		catch(SQLException e) {
			System.out.println(e);	
		}
		
	}

}
