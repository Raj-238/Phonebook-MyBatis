package com.raj.phonebook.service;

import java.io.IOException;
import java.util.Map;

import org.mindrot.jbcrypt.BCrypt;

import com.raj.phonebook.dao.DaoFactory;
import com.raj.phonebook.dao.UserDao;
import com.raj.phonebook.entity.User;

public class UserService {

	public User registerUser(String name, String email, String password, String cPassword, Map<String, String> errors)
			throws IOException {

		if (name.trim().length() == 0) {

			errors.put("name", "name is mandatory");
		} else if (name.trim().length() <= 3) {

			errors.put("name", "insufficient length");
		}
		if (email.trim().length() == 0) {

			errors.put("email", "email is mandatory");
		}
		if (password.trim().length() == 0) {

			errors.put("password", "password is mandatory");
		}
		if (password.equals(cPassword) == false) {

			errors.put("cPassword", "password must match");
		}

		UserDao dao = DaoFactory.getUserDao();
		User user = dao.getUserByEmail(email);
		if (user != null) {
			errors.put("email", "email already exists");
		}
		if (errors.size() > 0) {

			return null;
		}
		password = BCrypt.hashpw(password, BCrypt.gensalt(12));
		user = new User(name, email, password);
		dao.createUser(user);

		return user;
	}

	public User login(String email, String password, Map<String, String> errors) throws IOException {

		if (email.trim().length() == 0) {

			errors.put("email", "email is mandatory");
		}
		if (password.trim().length() == 0) {

			errors.put("password", "password is mandatory");
		}
		if (errors.size() > 0) {

			return null;
		}
		UserDao dao = DaoFactory.getUserDao();
		User user = dao.getUserByEmail(email);

		if (user == null) {
			errors.put("email", "email/password is invalid");
			
		} 
		else {
			boolean result = BCrypt.checkpw(password, user.getPassword());

			if (result == false) {
				errors.put(password, "email/password is invalid");
			}
		}
		if (errors.size() > 0) {

			return null;
		}
		return user;
	}
}
