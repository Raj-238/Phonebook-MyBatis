package com.raj.phonebook.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.raj.phonebook.entity.User;

@WebServlet("/dashboard")
public class DashboardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	User user = (User) req.getSession().getAttribute("user");
	
  if(user==null) {
	  resp.sendRedirect("./");
	  return;
  }
  
  req.getRequestDispatcher("/WEB-INF/views/dashboard.jsp").forward(req, resp);;
  }
	
}
