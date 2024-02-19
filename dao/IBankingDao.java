package in.ineuron.dao;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.ineuron.service.Account;
import in.ineuron.service.Transaction;
import in.ineuron.service.User;

public interface IBankingDao {
	public int registerUser(User user) throws IOException, SQLException;

	public ResultSet getUser() throws SQLException, IOException;

	public int updateBalance(int accountNumber,int amount,long balance,String transactionType);

	public int addTransaction(Transaction transaction);

	public ResultSet getTheTransaction(int accountNumber);

	public ResultSet getAccountForPin(String username);

	public int updatePin(int pin, String username);

	public long getBalance(int accountNumber);
	
	public int getUserId(String username) ;

	public int createAccount(Account account);
	
	public ResultSet getTheAccountNumber(String username);
	
	public int getPinNumber(int accountNumber) ;
}
