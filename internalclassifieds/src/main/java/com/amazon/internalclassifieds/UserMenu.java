package com.amazon.internalclassifieds;

import java.util.Date;

import com.amazon.internalclassifieds.controller.UserManagement;
import com.amazon.internalclassifieds.model.Users;


public class UserMenu extends Menu{

	UserManagement userService = UserManagement.getInstnace();
	
	private static UserMenu userMenu = new UserMenu();

	public static Menu getInstance() {
		return userMenu;
	}
	
	public void showMenu() {
		
		// ToDo: User Must Login Before to Access the Menu
		// Login Code should come before the Menu becomes Visible to the Admin
		
		System.out.println("1: Register");
		System.out.println("2: Login");
		System.out.println("3: Cancel");
		
		System.out.println("Enter Your Choice: ");
		int initialChoice = Integer.parseInt(scanner.nextLine());//scanner.nextInt();
		
		boolean result = false;
		
		Users user = new Users();
		
		switch(initialChoice) {
		
		case 1:
			
			// As we know, user is registering :)
			user.userType = 2;
			
			result = userService.register(user);
			
			System.out.println ("Successfully Registered!!!");
			System.out.println("Kindly Login to Continue: ");
			
		case 2:
			System.out.println("Enter Your Email:");
			user.email = scanner.nextLine();
			
			System.out.println("Enter Your Password:");
			user.password = scanner.nextLine();
			
			result = userService.login(user);
			
			if(!result && user.userType != 2) {
				System.err.println("Invalid Credentials. Please Try Again !!");
			}
				
			break;
			
		case 3:
			System.out.println("Thank You For using Amazon Internal Classifieds App");
			break;
		
		default:
			System.err.println("Invalid Choice...");
			System.out.println("Thank You For using Amazon Internal Classifieds App");
			break;
		}
		
		if(result && user.userType == 2) {
			
			userSession.user = user;
		
			System.out.println("^^^^^^^^^^^^^^^^^^^");
			System.out.println("Welcome to User App");
			System.out.println("Hello, "+user.name);
			System.out.println("Its: "+new Date());
			System.out.println("^^^^^^^^^^^^^^^^^^^");
			
			boolean quit = false;
			
			while(true) {
	        	try {
		        	System.out.println("1: My Profile");
		        	System.out.println("2: Post a Classified");
		        	System.out.println("3: View all Classifieds");
		        	System.out.println("4: Connect/Chat with other Users");
					System.out.println("5: Testing userSession//Test other methods here");
		        	System.out.println("6: Quit User App");
		        	System.out.println("Select an Option");
		        	
		        	int choice = Integer.parseInt(scanner.nextLine());//scanner.nextInt();
		        	
		        	switch (choice) {
						case 1:
							System.out.println("My Profile");
							userService.displayUser();
							
							System.out.println("Do you wish to update Profile (1: Update 0: Cancel)");
							choice = Integer.parseInt(scanner.nextLine());//scanner.nextInt();
							
							if(choice == 1) {
								userService.update();	
							}
							break;
							
						case 2:
							
							break;
		
						case 3:
							
							break;

						case 4:
						
							break;
							
						case 5:
							//userService.checkUserStatus();
							break;
							
						case 6:
							System.out.println("Thank You for Using User App !!");
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
	}
}
