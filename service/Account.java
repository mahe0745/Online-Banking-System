package in.ineuron.service;

import java.time.LocalDateTime;

public class Account {
	private int accountNumber;
	private int userid;
	private int pinNumber;
	private String mobileNumber;
	private long balance;
	private String accountHolderName;
	private String accountType;
	private LocalDateTime accountcreated;
	private String cardNumber;
	
	public Account(int accountNumber, int userid, int pinNumber, String mobileNumber, long balance,
			String accountHolderName, String accountType, LocalDateTime accountcreated, String cardNumber) {
		super();
		this.accountNumber = accountNumber;
		this.userid = userid;
		this.pinNumber = pinNumber;
		this.mobileNumber = mobileNumber;
		this.balance = balance;
		this.accountHolderName = accountHolderName;
		this.accountType = accountType;
		this.accountcreated = accountcreated;
		this.cardNumber = cardNumber;
	}

	public int getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public int getPinNumber() {
		return pinNumber;
	}

	public void setPinNumber(int pinNumber) {
		this.pinNumber = pinNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	public String getAccountHolderName() {
		return accountHolderName;
	}

	public void setAccountHolderName(String accountHolderName) {
		this.accountHolderName = accountHolderName;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public LocalDateTime getAccountcreated() {
		return accountcreated;
	}

	public void setAccountcreated(LocalDateTime accountcreated) {
		this.accountcreated = accountcreated;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	
	
	
	
	
}
