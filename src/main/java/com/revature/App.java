package com.revature;

import java.util.ArrayList;
import java.util.Scanner;

public class App {	
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
		BankAccountUser bankAccountUser = new BankAccountUser();
		do {
			System.out.println("Welcome to Banking R US");
			System.out.println("Are you a Customer?: yes / no");	
			input = scan.nextLine();
			if(input.toLowerCase().equals("no")) {//Employee
				BankingEmployee BE = new BankingEmployee(bankAccountUser.userString ,bankAccountUser.passwordString);
				BE.menu();	
			}else if(input.toLowerCase().equals("yes")){//Customer	
				BankingCustomer BC = new BankingCustomer(bankAccountUser.userString,bankAccountUser.passwordString);
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
