package com.revature;

import java.util.*;//import scanner


public class App {

	
	static String userName;//not a customer answer, else employee is the customer for this transaction
	static String userPass;//employee password
	static Boolean admin = false;//employee verifies they are an administrator or not, after selection, logs actions
	static Boolean runApplication = true;
	static Boolean loggedIn = false;//account is logged in, shouldn't need to create a new account
	static String input ="";
	static int accountCounter = 0;//Becomes account ID
	static Scanner scan = new Scanner(System.in);
	static int result;//int casting variable
	static double localBalance;
	
	public static void main(String[] args) {
		//Store applications locally that would be populated for an employee from a database
		ArrayList<BankingAccount> applicationsAccounts = new ArrayList<BankingAccount>();
		do {
			System.out.println("Welcome to Banking R US");
			System.out.println("Are you a Customer?: yes / no");	
			input = scan.nextLine();
			if(input.toLowerCase().equals("no")) {//Employee
				System.out.println("Please enter employee username:");
				userName = scan.nextLine();
				System.out.println("Please enter employee password:");
				userPass = scan.nextLine();
				BankingEmployee BE = new BankingEmployee(userName ,userPass);
				BE.menu();	
			}else if(input.toLowerCase().equals("yes")){//Customer	
				System.out.println("Please enter username:");
				userName = scan.nextLine();
				System.out.println("Please enter password:");
				userPass = scan.nextLine();
				BankingCustomer BC = new BankingCustomer(userName,userPass);
				BC.menu();		
			}
			//Logs should be done in account or here?			
			System.out.println("Are you finished with the Banking App?");	
			input = scan.nextLine();
			if(input.equals("yes"))
				runApplication = false;
		}while(runApplication);
		scan.close();
	}
}
