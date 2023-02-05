package com.amazon.internalclassifieds.controller;

import java.util.List;
import java.util.Scanner;

import com.amazon.internalclassifieds.db.passEncryption;
import com.amazon.internalclassifieds.db.UserDAO;
import com.amazon.internalclassifieds.model.Users;


public class UserManagement {
	
	Scanner scanner = new Scanner(System.in);
	Users user = new Users();
	UserDAO userdao = new UserDAO();
	passEncryption encrypt = passEncryption.getInstance();
	
	private static UserManagement manageUsers = new UserManagement();
	
	public static UserManagement getInstnace() {
		return manageUsers;
	}
	
	private UserManagement() {
		
/*		Users user1 = new Users();
		user1.name = "Aman Srivastava";
		user1.phone = "+91 80847 31205";
		user1.email = "aman@example.com";
		user1.password = "admin123";
		user1.address = "Gachibowli, HYD";
		user1.userType = 1;		// Admin
		user1.userStatus=1;
		
		Users user2 = new Users();
		user2.name = "John Watson";
		user2.phone = "+91 99999 11111";
		user2.email = "john@example.com";
		user2.password = "john123";
		user2.address = "Redwood Shores";
		user2.userType = 2;		// User
		user2.userStatus=1;
		
		//Add 2 Users in DataBase
		userdao.insert(user1);
		userdao.insert(user2);
*/
	}
	

	public boolean login(Users user) {

		String sql = "SELECT * FROM Users WHERE email = '"+user.email+"' AND password = '"+encrypt.encryptor(user.password)+"'";
		List<Users> users = userdao.retrieve(sql);
		
		if(users.size() > 0) {
			Users u = users.get(0);
			user.userID = u.userID;
			user.name = u.name;
			user.phone = u.phone;
			user.email = u.email;
			user.address = u.address;
			user.userType = u.userType;
			user.userStatus = u.userStatus;
			user.createdOn = u.createdOn;
			return true;
		}
		
		return false;
	}
	
	public boolean register(Users user) {
		
		user.getDetails(user);
		
		if (userdao.insert(user)>0)
			return true;
		
		return false;
	}
	
	public boolean updateUser(Users user) {
		
		if (userdao.update(user)>0)
			return true;
		
		return false;
	}
}
