package com.amazon.internalclassifieds.model;

import java.util.Scanner;

public class Users {

/*
MSSQL:
	create table Users(
	userID INT IDENTITY(1,1),
	name NVARCHAR(50) NOT NULL,
	phone NVARCHAR(20) NOT NULL UNIQUE,
	email NVARCHAR(30) NOT NULL UNIQUE,
	password NVARCHAR(100) NOT NULL,
	address NVARCHAR(100),
	userType INT NOT NULL, --(1-Admin, 2-User)
	userStatus BIT NOT NULL,	--(1-Active, 0 InActive)
	createdOn DATETIME DEFAULT CURRENT_TIMESTAMP,
	PRIMARY KEY(userID));
*/	
	
	public int userID;
	public String name;
	public String phone;
	public String email;
	public String password;
	public String address;
	public int userType;
	public int userStatus;
	public String createdOn;
	
	
	public Users() {
	}


	public Users(int userID, String name, String phone, String email, String password, String address, int userType,
			int userStatus, String createdOn) {
		this.userID = userID;
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.address = address;
		this.userType = userType;
		this.userStatus = userStatus;
		this.createdOn = createdOn;
	}


	public void prettyPrintForAdmin(Users user) {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("UserID:\t\t"+user.userID);
		System.out.println("Name:\t\t"+user.name);
		System.out.println("Phone:\t\t"+user.phone);
		System.out.println("Email:\t\t"+user.email);
		System.out.println("Address:\t"+user.address);
		
		String statusText = "";
		
		if(user.userStatus == 1) {
			statusText = "Active";
		}else if (user.userStatus == 0) {
			statusText = "InActive";
		}
		
		System.out.println("User Type:\t"+user.userType);
		System.out.println("Status:\t\t"+statusText);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
	}
	
	public void prettyPrintForUser(Users user) {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Name:\t\t"+user.name);
		System.out.println("Phone:\t\t"+user.phone);
		System.out.println("Email:\t\t"+user.email);
		System.out.println("Address:\t"+user.address);
		
		String statusText = "";
		
		if(user.userStatus == 1) {
			statusText = "Active";
		}else if (user.userStatus == 0) {
			statusText = "InActive";
		}
		
		System.out.println("Status:\t\t"+statusText);
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
	}
	
	public void getDetails(Users user) {
		
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter your Name: ");
		String name = scanner.nextLine();
		if (!name.isEmpty())
			user.name = name;
		
		System.out.println("Enter your Phone: ");
		String phone = scanner.nextLine();
		if (!phone.isEmpty())
			user.phone = phone;
		
		System.out.println("Enter your Address: ");
		String address = scanner.nextLine();
		if (!address.isEmpty())
			user.address = address;
		
		if (user.email == null) {
			
            String email;
            do {
                System.out.println("Enter your Email ID: ");
                email = scanner.nextLine();
            }
            while (email.isBlank() || email.isEmpty());
            user.email = email;

            String password;
            do {
            System.out.println("Enter your Password: ");
            password = scanner.nextLine();
            }
            while (password.isBlank() || password.isEmpty());
            user.password = password;

        }
	}

	@Override
	public String toString() {
		return "Users [userID=" + userID + ", name=" + name + ", phone=" + phone + ", email=" + email + ", password="
				+ password + ", address=" + address + ", userType=" + userType + ", userStatus=" + userStatus
				+ ", createdOn=" + createdOn + "]";
	}
	
}
