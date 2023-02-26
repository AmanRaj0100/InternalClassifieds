package com.amazon.internalclassifieds;


import org.junit.Assert;
import org.junit.Test;

import com.amazon.internalclassifieds.controller.UserManagement;
import com.amazon.internalclassifieds.db.UserDAO;
import com.amazon.internalclassifieds.db.passEncryption;
import com.amazon.internalclassifieds.model.Users;

public class AppTest {
	
	UserManagement userService = UserManagement.getInstance();
	passEncryption encryptor = new passEncryption();
	UserDAO userdao = new UserDAO();
	
	@Test
	public void testAdminLogin() {
		
		System.out.println("Commented for Creating Maven build as\r\n"
				+ " * Those Test Cases below because they were creating duplicates of Foreign Key\r\n"
				+ " * After running it for the second time.\r\n"
				+ " * But they are still working");
		
		Users user = new Users();
		user.email = "aman@example.com";
		user.password = "admin123";
		
		boolean result = userService.login(user);
		
		//Assertion -> Either Test Cases Passes or It will Fail
		Assert.assertEquals(true, result);
		
	}
	
	@Test
	public void testUserLogin() {
		
		Users user = new Users();
		user.email = "john@example.com";
		user.password = "john123";
		
		boolean result = userService.login(user);
		
		//Assertion -> Either Test Cases Passes or It will Fail
		Assert.assertEquals(true, result);
		
	}
	
	/*
	 * Commented for Creating Maven build as
	 * Those Test Cases below because they were creating duplicates of Foreign Key
	 * After running it for the second time.
	 * But they are still working
	 * 
	 * */
	
	@Test
	public void testUserRegister() {
		
		Users user = new Users();
		user.email = "user@example.com";
		user.password = encryptor.encryptor("user123");
		user.name = "TestUser";
		user.phone = "+91 9567834521";
		user.address = "Hyderabad";
		user.userType = 2;
		user.userStatus = 0;
		
		int result = userdao.insert(user);
		
		//Assertion -> Either Test Cases Passes or It will Fail
		Assert.assertTrue(result>0);
		Assert.assertEquals(1, user.userType);
		
	}

}

