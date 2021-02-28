package com.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.Employee;
import com.entity.Payroll;
import com.utiliity.HiberanteUtlity;


@WebServlet("/add-employee-with-payroll")
public class AddEmployeeWithPayroll extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public AddEmployeeWithPayroll() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.sendRedirect("add-employee-with-payroll.html");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String salary=request.getParameter("salary");
		String department=request.getParameter("dept");
		
		String gross=request.getParameter("gross");
		String net=request.getParameter("net");
		String tax=request.getParameter("tax");

		Employee e=new Employee(firstName,lastName,Double.parseDouble(salary),department);
		Payroll payroll=new Payroll(Double.parseDouble(gross),Double.parseDouble(net),Double.parseDouble(tax));
		
		e.setPayroll(payroll);
		
		Session session=HiberanteUtlity.getSession();
		Transaction tx=session.beginTransaction();
		session.save(e);
		tx.commit();
		out.println("<h2>Employee  info added successfully!</h2>");
	}

}
