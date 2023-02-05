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


	public void prettyPrint() {
		System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		System.out.println("Name:\t\t"+name);
		System.out.println("Phone:\t\t"+phone);
		System.out.println("Email:\t\t"+email);
		System.out.println("Address:\t"+address);
		
		String statusText = "";
		
		if(userStatus == 1) {
			statusText = "Active";
		}else if (userStatus == 0) {
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
		
		System.out.println("Enter your Email ID: ");
		String email = scanner.nextLine();
		if (!email.isEmpty())
			user.email = email;
		
		System.out.println("Enter your Password: ");
		String password = scanner.nextLine();
		if (!password.isEmpty())
			user.password = password;
		
		System.out.println("Enter your Address: ");
		String address = scanner.nextLine();
		if (!address.isEmpty())
			user.address = address;
	}

	@Override
	public String toString() {
		return "Users [userID=" + userID + ", name=" + name + ", phone=" + phone + ", email=" + email + ", password="
				+ password + ", address=" + address + ", userType=" + userType + ", userStatus=" + userStatus
				+ ", createdOn=" + createdOn + "]";
	}
	
}
