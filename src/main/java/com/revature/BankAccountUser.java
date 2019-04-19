package com.revature;

import java.util.Scanner;

public class BankAccountUser {

	public String userString;
	protected String passwordString;
	public String input;
	public Scanner scan = new Scanner(System.in);
	
	//User not found
	public BankAccountUser() {
		this.userString = "";
		this.passwordString = "";		
	}
	
	//Already a user
	public BankAccountUser(String user, String pass) {
		this.userString = user;
		this.passwordString = pass;
	}
	
	public Boolean changePassword(String newPass) {
		this.passwordString = newPass;
		return true;
	}

}
