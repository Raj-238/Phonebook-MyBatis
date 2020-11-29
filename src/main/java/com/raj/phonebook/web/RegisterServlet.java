package com.raj.phonebook.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.raj.phonebook.entity.User;
import com.raj.phonebook.service.UserService;

@WebServlet("/Register")
public class RegisterServlet extends HttpServlet {

	
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	 //todo check for login
		//if user had already logged in redirect to dashboard
		User user = (User) req.getSession().getAttribute("user");
		
		if(user!=null) {
			resp.sendRedirect("./dashboard");
			return;
		}
		req.getRequestDispatcher("/WEB-INF/views/Register.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//1. read input from req
		String name = req.getParameter("name");
		String email = req.getParameter("email");
		String password = req.getParameter("password");
		String cPassword = req.getParameter("c_password");
//2.Make use of a model function
		
		 UserService service = new UserService();
		 Map<String, String> errors = new HashMap<String , String>();
		User user =  service.registerUser(name, email, password, cPassword, errors);
		
		if(errors.size()>0) {
			req.setAttribute("errors", errors);
			req.getRequestDispatcher("/WEB-INF/views/Register.jsp").forward(req, resp);
		}
		else {
			req.getSession().setAttribute("user", user);
			resp.sendRedirect("./dashboard");
		}
		}
	 
}

