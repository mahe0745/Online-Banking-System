package in.ineuron.service;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public interface IBankingService {
	public boolean checkUser(String user, String password);

	public String userExists(String user, String mobileNumber);

	public int createAccountNumber();

	public int createPinNumber();

	public int registerUser(User user) throws IOException, SQLException;

	public int getPinNumber(int accountNumber) ;
	public  int updateBalance(int accountNumber,int amount,long balance,String transactionType);

	public int addTransaction(Transaction transaction);

	public ResultSet getTheTransaction(int accountNumber);

	public ResultSet getAccountForPin(String username);

	public boolean changePin(ResultSet rs, String pin);

	public int updatePin(int pin, String username);

	public long getBalance(int  accountNumber);
	
	public int getUserId(String username);
	
	public int createAccount(Account account);
	
	public ResultSet getTheAccountNumber(String username);
}
