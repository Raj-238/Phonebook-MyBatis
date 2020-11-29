package com.raj.phonebook.web;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.raj.phonebook.entity.Contact;
import com.raj.phonebook.entity.User;
import com.raj.phonebook.service.ContactService;

@WebServlet("/add-contact")
public class AddContactServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
   
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getRequestDispatcher("/WEB-INF/views/Contactform.jsp").forward(req, resp);
		
		}

@Override
protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
  String firstname = req.getParameter("firstname");
  
  String lastname = req.getParameter("lastname");

  String email = req.getParameter("email");

  String phone = req.getParameter("phone");

  String address = req.getParameter("address");

  String state = req.getParameter("state");

  String pincode = req.getParameter("pincode");

  String country = req.getParameter("country");

  String avatar = req.getParameter("avatar");
  
  Contact c = new Contact();
  c.setAddress(address);
  c.setAvatar(avatar);
  c.setCountry(country);
  c.setPhone(phone);
  c.setEmail(email);
  c.setFirstname(firstname);
  c.setLastname(lastname);
  c.setState(state);
  c.setPincode(pincode);
  
  User user = (User) req.getSession().getAttribute("user");
  c.setUserId(user.getId());
  ContactService service = new ContactService();
  Map<String, String> errors = new HashMap<String , String>();
  service.addNewContact(c , errors);
  
  if(errors.size()>0) {
	  req.setAttribute("errors", errors);
	  req.setAttribute("contact", c);
		req.getRequestDispatcher("/WEB-INF/views/Contactform.jsp").forward(req, resp);

	  }else {
	  resp.sendRedirect("./view-contact-details?id = " + c.getId() );
	  }
	  }
  
}

