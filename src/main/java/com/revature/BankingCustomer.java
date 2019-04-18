package com.revature;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.*;
import java.io.IOException;
import java.util.Scanner;

public class BankingCustomer extends BankAccountUser{

	String input ="";
	BankingAccount bA;//Customer has a banking account
	Scanner scan = new Scanner(System.in);
	Boolean loggedIn = false;//account is logged in, shouldn't need to create a new account
	int result;//int casting variable
	int accountIDCounter;//need to read the file to determine the value, then append the account line to it
	
	public BankingCustomer(String customername, String customerpass) {
		super(customername, customerpass);
	}
	
	public Integer tryInteger(String str) {
		try {
			Integer newAmount = Integer.parseInt(str);
			return newAmount;
		}catch (Exception e) {
			System.out.println("wasn't an integer");	
		}
		return null;
	}
	
	public Double tryDouble(String str) {
		try {
			Double newAmount = Double.parseDouble(str);
			return newAmount;
		}catch (Exception e) {
			System.out.println("wasn't a double");	
		}
		return null;
	}
	
	public Boolean readCustomerFile(String userName, String password) {
		String accountsFile = "Accounts.txt";
		String line = null;
		
		try {
			FileReader fileReader = new FileReader(accountsFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) !=null) {
				String [] words = line.split(" ");				
				if(words[1].equals(userName)&&words[2].equals(password)) {
					//Create a BankingAccount Object
					Integer accountInt = tryInteger(words[0]);
					Double balanceDouble = tryDouble(words[5]);
					if(balanceDouble==null)
						balanceDouble=0.0;
					bA = new BankingAccount(accountInt, words[1], words[2], words[3], words[4], balanceDouble);
					return true;					
				}
			}
			bufferedReader.close();
		}
        catch(FileNotFoundException ex) {
            System.out.println(
                "Unable to open file '" + 
                		accountsFile + "'");                
        }
        catch(IOException ex) {
            System.out.println(
                "Error reading file '" 
                + accountsFile + "'");  
        }
		return false;//cannot log in as employee	      
	}
	
	public Boolean writeCustomerFile() {
		return false;
	}
	
	public void menu() {		
//		if(userName.equals("") && password.equals("")) {
//			loggedIn = true;//If account cannot be found on database, then create one
//		}
//		System.out.println("Sorry, could not locate an exisisting account, create an account:");
		
		
		//Open account, check balance, deposit, withdrawal, close account
		if(!loggedIn) {
			System.out.println("To open an account enter 1");
		}
		
		if(loggedIn) {
			System.out.println("To check balance enter 2");				
			System.out.println("To make a deposit enter 3");
			System.out.println("To make a withdrawal enter 4");
			System.out.println("To close your account enter 5");
		}
		input = scan.nextLine();
		try {
			result = Integer.parseInt(input);
		
			switch (result) { 
	        case 1: 
	        	createAccountUser();
				System.out.println("Creates account");
				System.out.println("So you  have chosen a user name and password which are: "+super.userString+" "+super.passwordString);
				System.out.println("Your account number will be: ");//grab from database query
				System.out.println("Your account balance is currently 0 would you like to make a deposit? ");
				input = scan.nextLine();
				if(input.toLowerCase().equals("yes")) {
					if(tryDouble(input) != null) {
		        		//change balance
		        	}
					else {
						System.out.println("Didn't enter a Double");
						break;
					}
					System.out.println("You made a deposit ");
				}
				System.out.println("Would you like to make this a joint bank account now? yes/no");
				input = scan.nextLine();
				if(input.toLowerCase().equals("yes")) {
					System.out.println("Please enter your joint username:");
					input = scan.nextLine();
					String jointUserName = input;
					System.out.println("Please enter your joint password:");
					input = scan.nextLine();
					String jointPass = input;
				}else {
					System.out.println("Thank you for using the Banking App");
				}
	            break; 
	        case 2:  
				System.out.println("Your balance is blah");
	            break; 
	        case 3: 
				System.out.println("Enter ammount to deposit: enter a double");
				if(tryDouble(input) != null) {
	        		//change balance
	        	}
				else {
					System.out.println("Didn't enter a Double");
					break;
				}
	            break; 
	        case 4:  
				System.out.println("Enter ammount to withdraw: enter a double");
				if(tryDouble(input) != null) {
	        		//change balance
	        	}
				else {
					System.out.println("Didn't enter a Double");
					break;
				}
	            break; 
	        case 5:
				System.out.println("Closed account, you are now broke");  
	            break; 
	        default: 
	        	System.out.println("You didn't make a correct selection."); 
	            break; 
	        }
		}catch (Exception e) {
			System.out.println("Did not input an integer?");
		}
	
	}

}
