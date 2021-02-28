package com.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.entity.Employee;
import com.utiliity.HiberanteUtlity;

@WebServlet("/list-employee")
public class ListEmployees extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public ListEmployees() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		Session session=HiberanteUtlity.getSession();
		List<Employee> list=session.createQuery("from Employee as e").list();
		
		out.print("<h1> Employee List : </h1>");
		out.print("<style> table,td,th {"
				+ "border:2px solid red;"
				+ "padding: 10px; "
				+ "}</style>");
			out.print("<table >");
			out.print("<tr>");
			out.print("<th> Id</th>");
			out.print("<th> First Name</th>");
			out.print("<th> Last Name</th>");
			out.print("<th> Salary</th>");
			out.print("<th> Department</th>");
			out.print("</tr>");

		for(Employee emp:list) {
			
			out.print("<tr>");
			out.print("<td>"+emp.getId()+"</td>");
			out.print("<td>"+emp.getFirstName()+"</td>");
			out.print("<td>"+emp.getLastName()+"</td>");
			out.print("<td>"+emp.getSalary()+"</td>");
			out.print("<td>"+emp.getDept()+"</td>");
			out.print("</tr>");
		}
		out.print("</table>");
		session.close();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
