package com.revature;

public class BankingAccount {
	private int AccountID;
	public String PrimaryUserName;
	public String JointUsername;
	private String PrimaryPass;
	private String JointPass;	
	private double Balance;	
	public Boolean JointAccount;//check to see if there is already a joint account	
	public Boolean AccountStatus;//account approved by employee
	public Boolean Deposit;
	public Boolean Withdrawl;
	
	public BankingAccount(int accountID, String primaryUserName, String primaryPass, 
			String jointUsername, String jointPass, double balance) {
		this.AccountID			=accountID;
		this.PrimaryUserName	=primaryUserName;
		this.JointUsername		=jointUsername;
		this.PrimaryPass		=primaryPass;
		this.JointPass			=jointPass;	
		this.Balance			=balance;	
		if(jointUsername == null)
			this.JointAccount 	= false;
		this.AccountStatus 		= false;
		this.Deposit 			= false;
		this.Withdrawl = false;
	}
	
	public int getAccountID() {
		return AccountID;
	}

	public String getPrimaryUserName() {
		return PrimaryUserName;
	}

	public String getJointUsername() {
		return JointUsername;
	}

	public void setJointUsername(String jointUsername) {
		JointUsername = jointUsername;
	}
	
	// assumes double, checks if enough money is in balance, then returns if completed
	public boolean withdrawl(double tender) {
		Boolean workBoolean = false;
		if(tender <= Balance) {// && AccountStatus) {
			this.Withdrawl=true;
			Balance -= tender;
			workBoolean = true;
		}			
		return workBoolean;
	}
	
	//assumes double, adds money to balance, checks if money added greater than 0
	public boolean deposit(double tender) {
		if(tender > 0 ) {
			Balance += tender;//and update database
			this.Deposit = true;
		}
		return Deposit;
	}
	
	//Show balance
	public double getBalance() {
		return this.Balance;
	}
	
	//ToggleAccountStatus
	public Boolean approveAccount() {
		this.AccountStatus = true;// can use !AccountStatus but just in case.
		return AccountStatus;
	}
	
	//Joint account
	public Boolean jointAccount(String jointUsername, String jointPass) {
		this.JointUsername = jointUsername;
		this.JointPass = jointPass;
		this.JointAccount = true;
		return JointAccount;
	}
	
	//Change primary password
	public Boolean changePrimaryPassword(String UserPass) {
		this.PrimaryPass = UserPass;
		return true;
	}
	
	//Change joint password
	public Boolean changeJointPassword(String UserPass) {
		this.JointPass=UserPass;
		return true;
	}	
	
	//Remove Joint account user
	public Boolean removeJointAccount() {
		this.JointUsername = "";
		this.JointPass = "";
		this.JointAccount = false;
		return JointAccount;
	}
	
	//Enter new balance
	public boolean adjustBalance(double change) {
		this.Balance=change;			
		return true;
	}

	//creating account set name
	public void setUserName(String string) {
		this.PrimaryUserName=string;
	}

	//creating account set name
	public void setUserPass(String string) {
		this.PrimaryPass=string;		
	}
	//Enter new balance
	public boolean closeAccount() {
		PrimaryUserName = null;
		JointUsername = null;
		PrimaryPass = null;
		JointPass = null;	
		Balance = 0.0;
		JointAccount = false;
		AccountStatus= false;	
		return true;
	}

	public String getPrimaryPass() {
		return this.PrimaryPass;
	}

	public String getJointPass() {
		return this.JointPass;
	}

	public Boolean getApproveStatus() {
		return this.AccountStatus;
	}

}
