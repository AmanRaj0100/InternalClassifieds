package com.amazon.internalclassifieds;

import java.util.Scanner;

import com.amazon.internalclassifieds.controller.UserManagement;
import com.amazon.internalclassifieds.db.DB;
import com.amazon.internalclassifieds.model.Users;

public class Menu {

	Scanner scanner = new Scanner(System.in);
	UserManagement userService = UserManagement.getInstance();
	
	void showMainMenu() {
		// Initial Menu for the Application
        while(true) {
        	try {
            	System.out.println("1: Register");
            	System.out.println("2: Login");
            	System.out.println("3: Quit");
            	System.out.println("Select an Option");
            	
            	int choice = Integer.parseInt(scanner.nextLine());//scanner.nextInt();
        		boolean result = false, quit = false;
        		Users user = new Users();
            	
            	switch(choice) {
            	
            	case 1:
        			user.userType = 2;
        			result = userService.register(user);
        			
        			System.out.println ("Successfully Registered!!!");
        			System.out.println("Kindly Login to Continue: ");
        			
            	case 2:
            		System.out.println("Enter Your Email: ");
                    user.email = scanner.nextLine();

                    System.out.println("Enter Your Password: ");
                    user.password = scanner.nextLine();
                    
        			result = userService.login(user);
        			
        			if(!result) {
        				System.err.println("Invalid Credentials. Please Try Again !!");
        			}
        			else {
        				System.out.println("Login Successful");
        				
        				userSession.user = user; 
        				
                		try {
            				MenuFactory.getMenu(user.userType).showMenu();
            			}
            			catch (Exception e) {
            				System.out.println("[Main] Error while showing the menu"+e);
            			}
        			}
        				
            		break;
            	
            	case 3:
            		scanner.close();
            		DB db = DB.getInstance();
            		db.closeConnection();
            		System.out.println("Thank You For using Amazon Internal Classifieds App");
            		quit = true;
            		break;
            		
            	default:
            		System.err.println("Invalid Input");
            	}
            	
	        	if(quit) {
	        		break;
	        	}
            	
			} catch (Exception e) {
				System.err.println("Invalid Input: "+e);
			}
        }
	}

	public void showMenu() {
		System.out.println("Showing Menu...");
	}
}
