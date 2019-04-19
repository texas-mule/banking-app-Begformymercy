package com.revature;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BankingCustomer extends BankAccountUser{//Customer is a user

	String input ="";
	BankingAccount bA;//Customer has a banking account
	Scanner scan = new Scanner(System.in);
	Boolean loggedIn = false;//account is logged in, shouldn't need to create a new account
	int result;//integer casting variable
	int accountIDCounter;//need to read the file to determine the value, then append the account line to it
	ArrayList<BankingAccount> applicationsAccounts = new ArrayList<BankingAccount>();//update all customers on the file
	int foundTheIndex =0;
	
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
		System.out.println(userName+" "+password);
		String accountsFile = "Accounts.txt";
		String line = null;
		boolean foundYou = false;
		
		try {
			FileReader fileReader = new FileReader(accountsFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			while((line = bufferedReader.readLine()) !=null) {
				String [] words = line.split(" ");	
				Integer accountInt = tryInteger(words[0]);
				Double balanceDouble = tryDouble(words[5]);
				BankingAccount tempA = new BankingAccount(accountInt, words[1], words[2], words[3], words[4], balanceDouble);		
				applicationsAccounts.add(tempA);//Copy everything in the File to our internal database
				if(words[1].equals(userName)&&words[2].equals(password)||words[3].equals(userName)&&words[4].equals(password) ) {
					//Create a BankingAccount Object		
					foundYou = true;	
					foundTheIndex = tryInteger(words[0]);
				}
				accountIDCounter = tryInteger(words[0]);
			}
			bufferedReader.close();
			return foundYou;
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
		return false;//cannot log in as user	      
	}
		
	private void reWriteAccounts() {
		String accountsFile = "Accounts.txt";
		
		try {
            FileWriter fileWriter =
                new FileWriter(accountsFile,false);
            

            BufferedWriter bufferedWriter =
                new BufferedWriter(fileWriter);

            System.out.println("writing to file: "+applicationsAccounts.size());
            for (BankingAccount bankingAccount : applicationsAccounts) {
            	bufferedWriter.write(bankingAccount.getAccountID()+" "+bankingAccount.getPrimaryUserName()+" "+bankingAccount.getPrimaryPass()+" "+
            			bankingAccount.getJointUsername()+" "+bankingAccount.getJointPass()+" "+bankingAccount.getBalance()+" "+
            			bankingAccount.getApproveStatus());
    		            bufferedWriter.newLine();
			}

            //fileWriter.close();
            bufferedWriter.close();
            
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + accountsFile + "'");
            ex.printStackTrace();
        }
	}
	
	public void menu() {		
		//Open account, check balance, deposit, withdrawal, close account
		if(readCustomerFile(super.userString,super.passwordString)) {
			this.loggedIn=true;
		}
		if(!loggedIn) {
			System.out.println("To open an account enter 1");
		}
		
		if(loggedIn) {
			System.out.println("To check balance enter 2");				
			System.out.println("To make a deposit enter 3");
			System.out.println("To make a withdrawal enter 4");
			System.out.println("To close your account enter 5");
			System.out.println("To change your password enter 6");
		}
		input = scan.nextLine();
		try {
			result = Integer.parseInt(input);
		
			switch (result) { 
	        case 1: 
	        	String userName=super.userString;
	        	String userPass=super.passwordString;
	        	double Balance=0.0;	
	        	
				System.out.println("Creates account");
				System.out.println("So you  have chosen a user name and password which are: "+userName+" "+userPass);
				//create new BankingAccount object to file
				accountIDCounter+=1;
				bA = new BankingAccount(accountIDCounter, userName, userPass, "", "", Balance);
				System.out.println("Your account number will be: "+ accountIDCounter);//grab from database query
				System.out.println("Your account balance is currently 0 would you like to make a deposit? ");
				input = scan.nextLine();
				System.out.println(input+" "+input.toLowerCase().trim());
				if(input.toLowerCase().trim().equals("yes")) {
					System.out.println("Please enter an ammount you would like to deposit? as a double ");
					input = scan.nextLine();
					if(tryDouble(input) != null) {
						System.out.println("Your balance was: "+bA.getBalance());
						bA.deposit(tryDouble(input));
						System.out.println("Your new balance is: "+bA.getBalance());
		        	}
					else {
						System.out.println("Didn't enter a Double");
						break;
					}
				}
				System.out.println("Would you like to make this a joint bank account now? yes/no");
				input = scan.nextLine();
				if(input.toLowerCase().trim().equals("yes")) {
					System.out.println("Please enter your joint username:");
					input = scan.nextLine();
					bA.setJointUsername(input);
					System.out.println("Please enter your joint password:");
					input = scan.nextLine();
					bA.changeJointPassword(input);

					System.out.println("your account information: "+bA.getAccountID()+" "+bA.getPrimaryUserName()+" "+bA.getJointUsername()+" "+bA.getBalance());
				}else {
					System.out.println("your account information: "+bA.getAccountID()+" "+bA.getPrimaryUserName()+" "+bA.getJointUsername()+" "+bA.getBalance());
					System.out.println("Thank you for using the Banking App");
				}
				Boolean databaseCheckBoolean = false;
				for (BankingAccount bankingAccount : applicationsAccounts) {
					if(bankingAccount.getAccountID()==bA.getAccountID()) {
						databaseCheckBoolean = true;
					}
				}
				if(!databaseCheckBoolean) {
					applicationsAccounts.add(bA);
				}
				System.out.println(applicationsAccounts.size());
				reWriteAccounts();
	            break; 
	        case 2:  
	        	if(loggedIn) {
					System.out.println("Your balance is: "+applicationsAccounts.get(foundTheIndex).getBalance());
	        	}
	            break; 
	        case 3:   
	        	if(loggedIn) {
					System.out.println("Enter ammount to deposit: enter a double");
					input = scan.nextLine();
					if(tryDouble(input) != null) {
						System.out.println("Your balance was: "+applicationsAccounts.get(foundTheIndex).getBalance());
						applicationsAccounts.get(foundTheIndex).deposit(tryDouble(input));
						System.out.println("Your new balance is: "+applicationsAccounts.get(foundTheIndex).getBalance());
						reWriteAccounts();
		        	}else {
						System.out.println("Didn't enter a Double");
						break;
					}
	        	}
	            break; 
	        case 4:    
	        	if(loggedIn) {
					System.out.println("Enter ammount to withdraw: enter a double");
					input = scan.nextLine();
					if(tryDouble(input) != null) {
		        		//change balance
						if(applicationsAccounts.get(foundTheIndex).getBalance()<tryDouble(input)) {
							System.out.println("You do not have that much in your account, You have: "+bA.getBalance());
						}else {
							System.out.println("Your balance was: "+applicationsAccounts.get(foundTheIndex).getBalance());
							applicationsAccounts.get(foundTheIndex).withdrawl(tryDouble(input));
							System.out.println("Your new balance is: "+applicationsAccounts.get(foundTheIndex).getBalance());
							reWriteAccounts();
						}
		        	}
					else {
						System.out.println("Didn't enter a Double");
						break;
					}
	        	}
	            break; 
	        case 5:  
	        	if(loggedIn) {
		        	applicationsAccounts.get(foundTheIndex).closeAccount();
					reWriteAccounts();
					System.out.println("Closed account, you are now broke");  
	        	}
	            break; 
	        case 6:  
	        	if(loggedIn) {
					System.out.println("Enter your new password: "); 
					if(applicationsAccounts.get(foundTheIndex).JointUsername.equals(super.userString)) {
						input = scan.nextLine();
						applicationsAccounts.get(foundTheIndex).changeJointPassword(input.trim());
					}else if(applicationsAccounts.get(foundTheIndex).PrimaryUserName.equals(super.userString)){
						input = scan.nextLine();
						applicationsAccounts.get(foundTheIndex).changePrimaryPassword(input.trim());					
					}
					reWriteAccounts();
	        	}
	            break; 
	        default: 
	        	System.out.println("You didn't make a correct selection."); 
	            break; 
	        }
		}catch (Exception e) {
			System.out.println("Did not input an integer?");
		}
		accountIDCounter=0;
	}

}
