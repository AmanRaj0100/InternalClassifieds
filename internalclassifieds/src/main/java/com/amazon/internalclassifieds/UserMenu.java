package com.amazon.internalclassifieds;

import java.util.Date;

import com.amazon.internalclassifieds.controller.ClassifiedManagement;
import com.amazon.internalclassifieds.controller.OrderManagement;
import com.amazon.internalclassifieds.controller.UserManagement;


public class UserMenu extends Menu{

	UserManagement userService = UserManagement.getInstance();
	ClassifiedManagement classifiedService = ClassifiedManagement.getInstance();
	OrderManagement orderService = OrderManagement.getInstance();
	
	private static UserMenu userMenu = new UserMenu();

	public static Menu getInstance() {
		return userMenu;
	}
	
	public void showMenu() {
		
		System.out.println("^^^^^^^^^^^^^^^^^^^");
		System.out.println("Hello, "+userSession.user.name);
		System.out.println("Its: "+new Date());
		System.out.println("^^^^^^^^^^^^^^^^^^^");
		
		boolean quit = false;
		
		while(true) {
        	try {
	        	System.out.println("1: My Profile");
	        	System.out.println("2: Manage Your Classifieds");
	        	System.out.println("3: List all Classifieds Up for Sale");
	        	System.out.println("4: Buy/Sell Classifieds");
	        	System.out.println("5: LogOut");
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
						classifiedService.manageClassifiedForUser();
						break;
	
					case 3:
						classifiedService.displayClassified();
						break;

					case 4:
						if(orderService.buyClassified())
							System.out.println("Classified Bought");
						break;
						
					case 5:
						System.out.println("Thank You for using the App !!");
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
