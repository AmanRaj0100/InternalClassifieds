package com.amazon.internalclassifieds.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.amazon.internalclassifieds.db.passEncryption;
import com.amazon.internalclassifieds.userSession;
import com.amazon.internalclassifieds.db.UserDAO;
import com.amazon.internalclassifieds.model.Users;


public class UserManagement {
	
	Scanner scanner = new Scanner(System.in);
	Users user = new Users();
	UserDAO userdao = new UserDAO();
	passEncryption encrypt = passEncryption.getInstance();
	
	private static UserManagement manageUsers = new UserManagement();
	
	public static UserManagement getInstance() {
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
	
	//For Admin
	public void manageUser() {
		
		while(true) {
			try {
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				System.out.println("1: Activate/Deactivate User");
				System.out.println("2: Quit Managing User");
				System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
				System.out.println("Enter Your Choice: ");
				int choice = Integer.parseInt(scanner.nextLine());//scanner.nextInt();
				boolean quit = false;
				switch(choice) {
				case 1:
					activateUser();
					break;
					
				case 2:
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
	
	//For User
	public void displayUser() {

        //Fetch User Detail
        String sql = "Select * from Users where email= '"+userSession.user.email+"'";
        List <Users> userDetail = userdao.retrieve(sql);

        //Display the Details
        user.prettyPrintForUser(userDetail.get(0));
    }
	
	//For User
	public boolean update() {

        //Fetch User Detail
        String sql = "Select * from Users where email= '"+userSession.user.email+"'";
        List <Users> userDetail = userdao.retrieve(sql);

        //Ask the user to update the details
        user.getDetails(userDetail.get(0));

        //Update the details in SQL.
        if (userdao.update(userDetail.get(0))>0) {
        	System.out.println("User Profile Updated Successfully");
        	return true;
        }
        else {
			System.err.println("User Profile Update Failed...");
			return false;
		}
    }
	
	//For Admin
	public boolean activateUser() {
				
		List <Users> users = new ArrayList<Users>();
		users = userdao.retrieve();
		for (Users userDetails : users) {
			user.prettyPrintForAdmin(userDetails);
		}
		
		System.out.println("Enter the UserID of the User to Activate/Deactivate: ");
		int userID = Integer.parseInt(scanner.nextLine());
		
		String sql = "Select * From Users Where userID = "+userID;
		List <Users> usertoActivate = new ArrayList<Users>();
		usertoActivate = userdao.retrieve(sql);
		user = usertoActivate.get(0);
		
		System.out.println("\n 1-Activate \n 0-Deactivate");
		int status = Integer.parseInt(scanner.nextLine());
		user.userStatus=(status==1) ? 1 : 0;
		
		if(userdao.update(user)>0)
			return true;
		else
			return false;
	}
	
	//For User
	public boolean checkUserStatus() {
	
		if(userSession.user.userStatus==1) 
			return true;
		else 
			return false;
	}
	
}
