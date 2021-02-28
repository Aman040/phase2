package com.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.entity.Employee;
import com.entity.Project;
import com.utiliity.HiberanteUtlity;


@WebServlet("/add-employee-with-project")
public class AddEmployeeWithProject extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
    public AddEmployeeWithProject() {
        super();
      
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.sendRedirect("add-employee-with-project.html");
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String firstName=request.getParameter("firstName");
		String lastName=request.getParameter("lastName");
		String salary=request.getParameter("salary");
		String department=request.getParameter("dept");
		
		String pTitle1=request.getParameter("ptitle1");
		String pNo1=request.getParameter("pno1");
		String pTitle2=request.getParameter("ptitle2");
		String pNo2=request.getParameter("pno2");
		
		Session session=HiberanteUtlity.getSession();
		Transaction tx=session.beginTransaction();

		Employee e=new Employee(firstName,lastName,Double.parseDouble(salary),department);
		Set<Project> projects=new HashSet<>();
		Project p1=new Project(pNo1,pTitle1		);
		Project p2=new Project(pNo2,pTitle2);
		projects.add(p1);
		projects.add(p2);
		e.setProjects(projects);
		session.save(e);
		tx.commit();
		out.println("<h2>Employee  info added successfully!</h2>");
	}

}
