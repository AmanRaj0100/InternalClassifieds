package com.amazon.internalclassifieds;

import java.util.Date;

import com.amazon.internalclassifieds.controller.UserManagement;
import com.amazon.internalclassifieds.model.Users;

public class AdminMenu extends Menu{

	UserManagement userService = UserManagement.getInstnace();
	
	
	private static AdminMenu adminMenu =new AdminMenu();
	
	public static Menu getInstance() {
		return adminMenu;
	}
	
public void showMenu() {
		
		Users adminUser = new Users();
		
		// Login Code should come before the Menu becomes Visible to the Admin
	
		System.out.println("Enter the Email ID: ");
		adminUser.email=scanner.nextLine();
		
		System.out.println("Enter the Password: ");
		adminUser.password=scanner.nextLine();
		

		boolean result = userService.login(adminUser);
		
		
		if(result && adminUser.userType == 1) {
			
			userSession.user = adminUser;
		
			System.out.println("*********************");
			System.out.println("Welcome to Admin App");
			System.out.println("Hello, "+adminUser.name);
			System.out.println("Its: "+new Date());
			System.out.println("*********************");
			
			boolean quit = false;
			
			while(true) {
				try {
		        	System.out.println("1: Manage Classifieds");
		        	System.out.println("2: Manage Users");
		        	System.out.println("3: Connect/Chat with other User (Sell/Buy and Pay)");
		        	System.out.println("4: Quit Admin App");
		        	System.out.println("Select an Option");
		        	
		        	int choice = Integer.parseInt(scanner.nextLine());//scanner.nextInt();
		        	
		        	switch (choice) {
						case 1:
							
							break;
							
						case 2:
							
							break;
		
						case 3:
							
							break;
							
						case 4:
							System.out.println("Thank You for Using Admin App !!");
							quit = true;
							break;
			
						default:
							System.err.println("Invalid Choice...");
							break;
					}
		        	
		        	if(quit) {
		        		break;
		        	}
				} catch (Exception e) {
					System.err.println("Invalid Input:" +e);
				}	
	        }
		}
		else {
			System.err.println("Invalid Credentials. Please Try Again !!");
		}
	}
}
