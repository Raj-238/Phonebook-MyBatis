package com.raj.phonebook.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.raj.phonebook.entity.Contact;
import com.raj.phonebook.entity.User;
import com.raj.phonebook.service.ContactService;

@WebServlet("/view-contact-details")
public class ViewContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String input = req.getParameter("id");

		if (input == null) {
            resp.sendRedirect("./");
			return;
		}
		
		Integer id = new Integer(input);
		User user = (User) req.getSession().getAttribute("user");

		ContactService service = new ContactService();
		Contact c = service.getContactById(id, user.getId());
       req.setAttribute("contact", c);
       req.getRequestDispatcher("/WEB-INF/views/show-contact.jsp").forward(req, resp);
	}

}
