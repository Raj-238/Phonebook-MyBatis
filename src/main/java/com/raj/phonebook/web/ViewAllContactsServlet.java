package com.raj.phonebook.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.raj.phonebook.entity.User;
import com.raj.phonebook.service.ContactService;

@WebServlet("/view-all-contacts")
public class ViewAllContactsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user = (User) req.getSession().getAttribute("user");
		
		ContactService service = new ContactService();
		req.setAttribute("contacts" , service.getAllContacts(user.getId()));
		req.getRequestDispatcher("/WEB-INF/views/show-contacts.jsp").forward(req, resp);
	}

}
	