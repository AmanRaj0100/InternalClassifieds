package com.amazon.internalclassifieds;

import java.util.Scanner;

import com.amazon.internalclassifieds.db.DB;

public class Menu {

	Scanner scanner = new Scanner(System.in);
	
	void showMainMenu() {
		// Initial Menu for the Application
        while(true) {
        	try {
            	System.out.println("1: Admin");
            	System.out.println("2: User");
            	System.out.println("3: Quit");
            	System.out.println("Select an Option");
            	
            	int choice = Integer.parseInt(scanner.nextLine());//scanner.nextInt();
            	
            	if (choice == 3) {
            		scanner.close();
            		DB db = DB.getInstance();
            		db.closeConnection();
            		System.out.println("Thank You For using Amazon Internal Classifieds App");
            		break;
            	}
            	
    			try {
    				MenuFactory.getMenu(choice).showMenu();
    			}
    			catch (Exception e) {
    				System.out.println("[main] Error While showing the menu"+e);
    			}
			} catch (Exception e) {
				System.err.println("Invalid Input:" +e);
			}
        }
	}

	public void showMenu() {
		System.out.println("Showing Menu...");
	}
}
