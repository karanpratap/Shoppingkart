package com.kart.service;

import com.kart.dao.UserDAO;
import com.kart.entity.User;

public class UserService {
	public static boolean create(User user) {
		UserDAO uDAO=new UserDAO();
		return uDAO.create(user);
	}
	
	public static boolean authorize(User user) {
		UserDAO uDAO=new UserDAO();
		return uDAO.authorize(user);
	}
}
