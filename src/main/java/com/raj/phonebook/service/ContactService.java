package com.raj.phonebook.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import com.raj.phonebook.dao.ContactDao;
import com.raj.phonebook.dao.DaoFactory;
import com.raj.phonebook.entity.Contact;

public class ContactService {

	public Contact addNewContact(Contact c, Map<String, String> errors) throws IOException {

		String fname = c.getFirstname().trim();
		if (fname.length() == 0) {

			errors.put("firstname", "FirstName is Mandatory");
		} else if (fname.length() <= 3) {
			errors.put("firstname", "FirstName cant be less than 3 letters");
		}
		if (c.getEmail().trim().length() == 0) {

			errors.put("email", "Email is Mandatory");
		}

		if (c.getPhone().trim().length() == 0) {

			errors.put("phone", "Phone number is Mandatory");
		}
		if (errors.size() > 0) {
			return null;
		}

		ContactDao dao = DaoFactory.getContactDao();
		Contact c1 = dao.getContactByEmail(c.getEmail());
		if (c1 != null) {
			errors.put("email", "email already exists");
		}

		c1 = dao.getContactByPhone(c.getPhone());

		if (c1 != null) {
			errors.put("phone", "Phone number already exists");
		}
		if (errors.size() > 0) {
			return null;
		}
		dao.createContact(c);
		return c;
	}

	public void updateContact(Integer id , Contact c , Map<String ,String> errors ) throws IOException {
		c.setId(id);
		
		String fname = c.getFirstname().trim();
		if (fname.length() == 0) {

			errors.put("firstname", "FirstName is Mandatory");
		} else if (fname.length() <= 3) {
			errors.put("firstname", "FirstName cant be less than 3 letters");
		}
		if (c.getEmail().trim().length() == 0) {

			errors.put("email", "Email is Mandatory");
		}

		if (c.getPhone().trim().length() == 0) {

			errors.put("phone", "Phone number is Mandatory");
		}
		if (errors.size() > 0) {
			return ;
		}

		ContactDao dao = DaoFactory.getContactDao();
		dao.updateContact(c);
	}
	
	public Contact getContactById(Integer id, Integer user) throws IOException {
		return DaoFactory.getContactDao().getContact(id , user);
				}

	public List<Contact> getAllContacts(Integer user) throws IOException {
		return DaoFactory.getContactDao().getAllContacts(user);
	}
	
	public void deleteContactById(Integer id , Integer userId, Map<String ,String>errors) throws IOException {
		
		ContactDao dao = DaoFactory.getContactDao();
		Contact c1 = dao.getContact(id, userId);
		
		if(c1 == null) {
			errors.put("userId", "permission denied to delete the contact");
			return;
			
		}
		dao.deleteContact(id);
		}
		
	}
	



