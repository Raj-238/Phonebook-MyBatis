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
@WebServlet("/delete-contact")
public class DeleteContactServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String input = req.getParameter("id");	
		
		if(input==null) {
			resp.sendRedirect("./");
			return;
		}
		Integer id = new Integer(input);
		User user = (User) req.getSession().getAttribute("user");

		Contact c1 = new ContactService().getContactById(id, user.getId());
		req.setAttribute("contact", c1);
        req.getRequestDispatcher("/WEB-INF/views/confirm-delete-contact.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	     Integer id = new Integer(req.getParameter("id") );
	     String confirm = req.getParameter("confirm");
	     
			User user = (User) req.getSession().getAttribute("user");
           ContactService service = new ContactService();
		
           if(confirm.equals("Permanently delete")==false) {
        	   Contact c1 = new ContactService().getContactById(id, user.getId());
       		   req.setAttribute("contact", c1);
       		 req.setAttribute("errMessage" , "You have to type Permanently delete text t delete the contact");
       		 req.getRequestDispatcher("/WEB-INF/views/confirm-delete-contact.jsp").forward(req, resp); 
          }
           else {
        	   Map<String , String> errors = new HashMap<String , String>();
        	   service.deleteContactById(id, user.getId(), errors);
        	   if(errors.size()==0) {
        		   resp.sendRedirect("./");
        	   }
        	   else {
        		   req.setAttribute("errors", errors);
        	        req.getRequestDispatcher("/WEB-INF/views/confirm-delete-contact.jsp").forward(req, resp);

        	   }
           }
	}

}
