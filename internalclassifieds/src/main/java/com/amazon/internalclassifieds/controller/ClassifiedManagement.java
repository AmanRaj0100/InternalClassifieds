package com.amazon.internalclassifieds.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.amazon.internalclassifieds.userSession;
import com.amazon.internalclassifieds.db.CategoryDAO;
import com.amazon.internalclassifieds.db.ClassifiedDAO;
import com.amazon.internalclassifieds.model.Categories;
import com.amazon.internalclassifieds.model.Classifieds;

public class ClassifiedManagement {
	
	Scanner scanner = new Scanner(System.in);
	ClassifiedDAO classifieddao = new ClassifiedDAO();
	Classifieds classified = new Classifieds();
	
	private static ClassifiedManagement manageClassifieds = new ClassifiedManagement();
	
	public static ClassifiedManagement getInstance() {
		return manageClassifieds;
	}
	
	//For Admin
	public void manageClassified() {
		
		while(true) {
			try {
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				System.out.println("1: Classifieds Approval/Rejection");
				System.out.println("2: Post a new Classified");
				System.out.println("3: Update an existing Classified");
				System.out.println("4: Remove Classifieds(One/Many)");
				System.out.println("5: Quit Managing Classified");
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				System.out.println("Enter Your Choice: ");
				int choice = Integer.parseInt(scanner.nextLine());//scanner.nextInt();
				boolean quit = false;
				switch(choice) {
				case 1:
					if(approvalOfClassified())
						System.out.println("Classified Approved");
					else
						System.out.println("Classified Rejected");
					break;
					
				case 2:
					if(postClassified())
						System.out.println("Classified Added");
					else
						System.out.println("Classified Upload Failed");
					break;
					
				case 3:
					if(updateClassified())
						System.out.println("Classified Updated");
					else
						System.out.println("Classified Updation Failed");
					break;
					
				case 4:
					if(removeClassified())
						System.out.println("Classified Removed");
					else
						System.out.println("Classified Removal Failed");
					break;
					
				case 5:
					quit = true;
					break;
					
				default:
					
				}
				
				if (quit)
					break;
			} catch (Exception e) {
				System.err.println("Invalid Input"+e);
			}
		}
	}
	
	//For User
	public void manageClassifiedForUser() {
		while(true) {
			try {
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				System.out.println("1: View your Classifieds");
				System.out.println("2: Post a new Classified");
				System.out.println("3: Update your Existing Classified");
				System.out.println("4: Quit Managing Classified");
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				System.out.println("Enter Your Choice: ");
				int choice = Integer.parseInt(scanner.nextLine());
				boolean quit = false;
				switch(choice) {
				case 1:
					displayUserClassified();
					break;
					
				case 2:
					if(postClassified())
						System.out.println("Classified Added");
					else
						System.out.println("Classified Upload Failed");
					break;
					
				case 3:
					if(updateClassified())
						System.out.println("Classified Updated");
					else
						System.out.println("Classified Updation Failed");
					break;

				case 4:
					quit = true;
					break;
					
				default:
					
				}
				
				if (quit)
					break;
			} catch (Exception e) {
				System.err.println("Invalid Input"+e);
			}
		}
	}
	
	
	// For Admin
	public boolean approvalOfClassified() {

		// Retrieve all classifieds which are in pending state
        List <Classifieds> classifieds = new ArrayList<Classifieds>();
        String sql = "SELECT * FROM Classifieds WHERE status = " +2;
        classifieds = classifieddao.retrieve(sql);
        
        // Display all the Classifieds that are in pending state
        for (Classifieds classifiedDetails : classifieds) {
            classified.prettyPrintForAdmin(classifiedDetails);
        }
        
        // Asking for the Classified ID to be Approve or Rejected
        System.out.println("Enter the Classified ID to Approve/Reject: ");
        int classifiedID = Integer.parseInt(scanner.nextLine());

        // Retrieving the particular Classified
        sql = "SELECT * FROM Classifieds WHERE classifiedID = "+classifiedID;
        List <Classifieds> classifiedtoActivate = new ArrayList<Classifieds>();
        classifiedtoActivate = classifieddao.retrieve(sql);
        
        classified = classifiedtoActivate.get(0);

        // Ask the Admin for Approval or Rejection
        System.out.println("\n 1-Approve \n 0-Reject");
        int status = Integer.parseInt(scanner.nextLine());
        
        // Change the status of the Classified
        classified.status=(status==1) ? 1 : 0;

        // Update the Classified
        if(classifieddao.update(classified)>0)
            return true;
        else
            return false;
    }
	
	// For Admin
	public boolean removeClassified() {
		
		System.out.println("Do you want to remove all rejected classified or a particular classified");
		System.out.println("1. for All Rejected Classifieds");
		System.out.println("2. for A Particular Classified");
		int choice = Integer.parseInt(scanner.nextLine());
		
		boolean deletion = true;
		
		// Delete all Rejected Classifieds
		if (choice == 1) {
			
			// Retrieve the classified which has been rejected.
			List<Classifieds> classified = new ArrayList<Classifieds>();
			String sql = "SELECT * FROM Classifieds where status = " +0;
			classified = classifieddao.retrieve(sql);
			
			// Remove all rejected Classifieds one by one
			for (Classifieds classifiedToBeDeleted : classified)
				if (classifieddao.delete(classifiedToBeDeleted)<=0) 
					deletion = false;
		}
		
		// Delete a Particular Classified
		else  if (choice == 2) {
			
			List<Classifieds> classified = new ArrayList<Classifieds>();
			classified = classifieddao.retrieve();
			// Display each classified
			for (Classifieds classifiedInfo : classified)
				classifiedInfo.prettyPrintForAdmin(classifiedInfo);
			
			// Input the Classified ID to be deleted
			System.out.println("Enter the Classified ID you want to delete");
			int classifiedID = Integer.parseInt(scanner.nextLine());
			
			// Retrieve the Classified to be deleted
			String sql = "SELECT * FROM Classifieds where classifiedID = " +classifiedID;
			List<Classifieds> classifieds = new ArrayList<Classifieds>();
			classifieds = classifieddao.retrieve(sql);
			
			// Remove the Classifieds
			if (classifieddao.delete(classifieds.get(0))<=0)
				deletion = false;
		}
		
		return deletion;
		
	}
	
	// For Admin and User
	public boolean updateClassified() {
		
		// Can only update those classifieds which was posted by themselves
		// Retrieving all the classified from a certain user
		String sql = "SELECT * from Classifieds WHERE userID = "+userSession.user.userID;
		List<Classifieds> classifiedList = classifieddao.retrieve(sql);
		// Display the classifieds using pretty print
		for (Classifieds classified : classifiedList)
			classified.prettyPrintForUser(classified);
		
		// Ask the user for the classified he wants to update
		System.out.println("Enter the Classified ID");
		int classifiedID = Integer.parseInt(scanner.nextLine());
		
		// Fetch the classified based on the classified ID
		sql = "SELECT * FROM Classifieds WHERE classifiedID = " +classifiedID;
		List <Classifieds> classifiedDetail = classifieddao.retrieve(sql);
		
		if (classifiedDetail.get(0).status == 3) {
			System.err.println("You can't modify a Classified which is already Sold");
			return false;
		}
		
		//Ask the user to update the details
		classified.getDetails(classifiedDetail.get(0));
		
		//Update the details in SQL.
		if (classifieddao.update(classifiedDetail.get(0))>0)
			return true;
		
		return false;
	}
	
	// For User.
	// User can see all the classified which have been approved and is available for transaction
	public void displayClassified() {
		
		//Fetch Classified Detail
		String sql = "SELECT * FROM Classifieds WHERE status= "+1;
		List <Classifieds> classifiedList = classifieddao.retrieve(sql);
		
		//Display the Details
		for (Classifieds classified : classifiedList) {
			classified.prettyPrintForUser(classified);
		}
	}
	
	// Display User's Classified
	public void displayUserClassified() {
		String sql = "Select * from Classifieds where userID= "+userSession.user.userID;
		List <Classifieds> classifiedList = classifieddao.retrieve(sql);
		
		//Display the Details
		for (Classifieds classified : classifiedList) {
			classified.prettyPrintForUser(classified);
		}
	}
	
	// For both Admin and User
	public boolean postClassified() {
		
		// Getting the user ID of current user
		classified.userID = userSession.user.userID;
		
		UserManagement userService = UserManagement.getInstnace();
		
		// Check if the user is active or not.
		// Only Active Users can post a classified. 
		if (userService.checkUserStatus()) {
		
		// Asking the user to add the classified details
			classified.getDetails(classified);			
			
			// Adding the classified to table
			if (classifieddao.insert(classified)>0)
				return true;
			else 
				return false;	
		}
		else {
			System.out.println("You can't post a classified as you're not Active");
			return false;
		}			
	}

	// To set the price of apartment to 20000
	public void setPrice() {
		// Setting up price for houses for rent
		// Retrieving classifieds which are houses
		String sql = "SELECT * from Classifieds WHERE productName LIKE '%House%' OR productName LIKE '%Apartment%' OR headline LIKE '%House%' OR headline LIKE '%Apartment%' OR description LIKE '%House for rent%' OR description LIKE '%Apartment for rent%'";
		List <Classifieds> classifiedList = new ArrayList<Classifieds>();
		classifiedList = classifieddao.retrieve(sql);
		
		// Change the price of each house to 20000
		for (Classifieds classified : classifiedList) {
			classified.price = 20000;
			classifieddao.update(classified);
		}
	}
}