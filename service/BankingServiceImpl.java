package in.ineuron.service;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import in.ineuron.dao.IBankingDao;
import in.ineuron.daofactory.BankingDaoFactory;

public class BankingServiceImpl implements IBankingService {

	IBankingDao bankingDao = BankingDaoFactory.getBankingDao();

	@Override
	public boolean checkUser(String user, String password) {
		try {
			ResultSet resultset = bankingDao.getUser();
			if (resultset != null) {
				System.out.println("Entered..");
				while (resultset.next()) {
					String userName = resultset.getString("username");
					System.out.println(userName);
					String userPassword = resultset.getString("userpassword");
					System.out.println(userPassword);
					if (userName.equals(user) && userPassword.equals(password)) {
						return true;
					}
				}
			}
			return false;
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public String userExists(String user, String mobileNumber) {
		System.out.println("BankingServiceImpl.userExists()");
		boolean mobile_exists = false;
		boolean user_exists = false;
		try {
			ResultSet resultset = bankingDao.getUser();

			while (resultset.next()) {
				String username = resultset.getString("userName");
				String mobile = resultset.getString("mobilenumber");
				if (user.equals(username)) {
					user_exists = true;

				}
				if (mobile.equals(mobileNumber)) {
					mobile_exists = true;
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		if (mobile_exists && user_exists) {
			return "username and mobilenumber already exists";
		} else if (mobile_exists) {
			return "mobile exists";
		} else if (user_exists) {
			return "username exists";
		}
		return null;
	}

	@Override
	public int createAccountNumber() {
		System.out.println("BankingServiceImpl.createAccountNumber()");
		Random random = new Random();
		String number = "";
		int count = 0;
		while (count < 6) {
			int val = random.nextInt(9) + 1;
			System.out.println(val);
			number = number + val;
			count++;
		}
		System.out.println("Created Account Number is " + number);
		return Integer.valueOf(number);

	}

	public int createPinNumber() {
		System.out.println("BankingServiceImpl.createPinNumber()");
		Random random = new Random();
		String pin = "";
		int count = 0;
		while (count < 4) {
			int val = random.nextInt(9) + 1;
			System.out.println(val);
			pin = pin + val;
			count++;
		}
		System.out.println("Created PinNumber is " + pin);
		return Integer.valueOf(pin);
	}

	@Override
	public int registerUser(User user) throws IOException, SQLException {
		System.out.println("BankingServiceImpl.registerUser()");
		int count = 0;
		System.out.println("BankingServiceImpl.registerUser()");

		count = bankingDao.registerUser(user);

		// TODO Auto-generated catch block

		return count;
	}

	

	
	public synchronized int updateBalance(int accountNumber,int amount,long balance,String transactionType) {
		System.out.println("BankingServiceImpl.updateBalance()");
		return bankingDao.updateBalance(accountNumber,amount,balance,transactionType);
	}

	@Override
	public int addTransaction(Transaction transaction) {
		return bankingDao.addTransaction(transaction);
	}

	public ResultSet getTheTransaction(int accountNumber) {
		return bankingDao.getTheTransaction(accountNumber);
	}

	@Override
	public ResultSet getAccountForPin(String username) {
		System.out.println("BankingServiceImpl.getAccountForPin()");
		return bankingDao.getAccountForPin(username);
	}

	@Override
	public boolean changePin(ResultSet rs, String pin) {
		System.out.println("BankingServiceImpl.changePin()");
		try {
			if (rs.next()) {
				if (rs.getInt("pinnumber") == Integer.valueOf(pin)) {
					System.out.println("entered pin " + pin);
					System.out.println("pin from db " + rs.getInt("pinnumber"));
					return true;
				}

			}
		} catch (NumberFormatException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return false;
	}

	public int updatePin(int pin, String username) {
		System.out.println("BankingServiceImpl.updatePin()");
		return bankingDao.updatePin(pin, username);
	}

	public long getBalance(int accountNumber) {
		return bankingDao.getBalance(accountNumber);
	}

	@Override
	public int getUserId(String username) {
		return bankingDao.getUserId(username);
	}

	@Override
	public int createAccount(Account account) {
		return bankingDao.createAccount(account);
	}

	@Override
	public ResultSet getTheAccountNumber(String username) {
		return bankingDao.getTheAccountNumber(username);
	}
	
	public int getPinNumber(int accountNumber) {
		return bankingDao.getPinNumber(accountNumber);
	}
	

}
