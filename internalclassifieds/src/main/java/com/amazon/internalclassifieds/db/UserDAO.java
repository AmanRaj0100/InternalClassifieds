package com.amazon.internalclassifieds.db;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.amazon.internalclassifieds.model.Users;

public class UserDAO implements DAO<Users> {
	
	DB db = DB.getInstance();
	passEncryption encrypt = passEncryption.getInstance();

	public int insert(Users object) {
		String sql = "INSERT INTO Users (name, phone, email, password, address, userType, userStatus) VALUES ('"+object.name+"', '"+object.phone+"', '"+object.email+"', '"+encrypt.encryptor(object.password)+"', '"+object.address+"', '"+object.userType+"', "+object.userStatus+")";
		return db.executeSQL(sql);
	}

	public int update(Users object) {
		String sql = "UPDATE Users set name = '"+object.name+"', phone='"+object.phone+"', password='"+encrypt.encryptor(object.password)+"', address='"+object.address+"', userType='"+object.userType+"', userStatus='"+object.userStatus+"' WHERE email = '"+object.email+"'";
		return db.executeSQL(sql);
	}

	public int delete(Users object) {
		String sql = "DELETE FROM Users WHERE email = '"+object.email+"'";
		return db.executeSQL(sql);
	}

	public List<Users> retrieve() {
		String sql = "SELECT * from Users";
		
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<Users> users = new ArrayList<Users>();
		
		try {
			while(set.next()) {
				
				Users user = new Users();
				
				// Read the row from ResultSet and put the data into User Object
				user.userID = set.getInt("userID");
				user.name = set.getString("name");
				user.phone = set.getString("phone");
				user.email = set.getString("email");
				user.password = set.getString("password");
				user.address = set.getString("address");
				user.userType = set.getInt("userType");
				user.userStatus = set.getInt("userStatus");
				user.createdOn = set.getString("createdOn");
				
				users.add(user);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}
		return users;
	}

	public List<Users> retrieve(String sql) {
		ResultSet set = db.executeQuery(sql);
		
		ArrayList<Users> users = new ArrayList<Users>();
		
		try {
			while(set.next()) {
				
				Users user = new Users();
				
				// Read the row from ResultSet and put the data into User Object
				user.userID = set.getInt("userID");
				user.name = set.getString("name");
				user.phone = set.getString("phone");
				user.email = set.getString("email");
				user.password = set.getString("password");
				user.address = set.getString("address");
				user.userType = set.getInt("userType");
				user.userStatus = set.getInt("userStatus");
				user.createdOn = set.getString("createdOn");
				
				users.add(user);
			}
		} catch (Exception e) {
			System.err.println("Something Went Wrong: "+e);
		}
		return users;
	}
}
