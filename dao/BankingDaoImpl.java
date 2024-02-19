package in.ineuron.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import in.ineuron.service.Account;
import in.ineuron.service.Transaction;
import in.ineuron.service.User;
import in.ineuron.util.JdbcUtil;

public class BankingDaoImpl implements IBankingDao {

	Connection connection = null;
	PreparedStatement pstmt = null;

	@Override
	public int registerUser(User user) {
		System.out.println("BankingDaoImpl.registerUser()");
		int count = 0;
		String sqlinsertquery = "insert into userinfo(`username`,`userpassword`,`email`,`dob`,`address`)values(?,?,?,?,?)";
		try {
			connection = JdbcUtil.getConnection();
			if (connection != null) {
				if (pstmt != null) {
					pstmt=connection.prepareStatement(sqlinsertquery);
					pstmt.setString(1, user.getUsername());
					pstmt.setString(2, user.getUserpassword());
					pstmt.setString(3, user.getEmail());
					pstmt.setDate(4, java.sql.Date.valueOf(user.getDob()));
					pstmt.setString(5, user.getAddress());
					count = pstmt.executeUpdate();
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}

		return count;
	}

	public ResultSet getUser() throws SQLException, IOException {
		System.out.println("BankingDaoImpl.getUser()");
		connection = JdbcUtil.getConnection();
		ResultSet resultSet = null;
		String sqlSelectQuery = "select username,userpassword from userinfo";
		if (connection != null) {
			pstmt = connection.prepareStatement(sqlSelectQuery);
			if (pstmt != null) {
				resultSet = pstmt.executeQuery();
			}
		}

		return resultSet;

	}

	public synchronized int updateBalance(int accountNumber,int amount,long balance,String transactionType) {
		System.out.println("BankingDaoImpl.updateBalance()");
		int count = 0;
		try {
			connection = JdbcUtil.getConnection();
			String update_query = "update account set balance=? where accountNumber=?";
			if (connection != null) {
				pstmt = connection.prepareStatement(update_query);
				if (pstmt != null) {
					if(transactionType.equalsIgnoreCase("credit")) {
						pstmt.setLong(1, balance+amount);
						pstmt.setInt(2, accountNumber);
					}
					else if(transactionType.equalsIgnoreCase("debit")) {
						pstmt.setLong(1, balance-amount);
						pstmt.setInt(2, accountNumber);
					}
					
					count = pstmt.executeUpdate();
				}
			}

		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return count;
	}

	@Override
	public int addTransaction(Transaction transaction) {
		System.out.println("BankingDaoImpl.addTransaction()");
		String insert_Query = "insert into transactionDetails(`SenderAccountNumber`,`ReceiverAccountNUmber`,`amount`,`dateOfTransaction`)values(?,?,?,?)";
		int count = 0;
		try {
			connection = JdbcUtil.getConnection();
			if (connection != null) {
				pstmt = connection.prepareStatement(insert_Query);
				if (pstmt != null) {
					pstmt.setInt(1, transaction.getSenderAccountNumber());
					pstmt.setInt(2, transaction.getReceiverAccountNumber());
					pstmt.setInt(3, transaction.getAmount());
					pstmt.setTimestamp(4,java.sql.Timestamp.valueOf(transaction.getDate()));
					count = pstmt.executeUpdate();
				}
			}

		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	public ResultSet getTheTransaction(int accountNumber) {
		System.out.println("BankingDaoImpl.getTheTransaction()");
		String select_query = "select transaction_id,SenderAccountNumber,ReceiverAccountNumber,amount,dateOfTransaction from transactionDetails where SenderAccountNumber=? or ReceiverAccountNumber=?";
		ResultSet resultSet = null;
		try {
			connection = JdbcUtil.getConnection();
			if (connection != null) {
				pstmt = connection.prepareStatement(select_query);
				if (pstmt != null) {
					pstmt.setInt(1, accountNumber);
					pstmt.setInt(2, accountNumber);
					resultSet = pstmt.executeQuery();
				}
			}
		} catch (IOException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultSet;
	}

	public ResultSet getAccountForPin(String username) {
		System.out.println("BankingDaoImpl.getAccountForPin()");
		ResultSet resultSet = null;
		String select_query = "select accountNumber,pinnumber from account where userid=(select userid from userinfo where username=?)";
		try {
			connection = JdbcUtil.getConnection();
			if (connection != null) {
				pstmt = connection.prepareStatement(select_query);
				if (pstmt != null) {
					pstmt.setString(1, username);
					resultSet = pstmt.executeQuery();
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return resultSet;
	}

	@Override
	public int updatePin(int pin, String username) {
		System.out.println("BankingDaoImpl.updatePin()");
		int count = 0;
		String update_query = "update account set pinnumber=? where userid=(select userid from userinfo where username=?)";
		try {
			connection = JdbcUtil.getConnection();
			if (connection != null) {
				pstmt = connection.prepareStatement(update_query);
				if (pstmt != null) {
					pstmt.setInt(1, pin);
					pstmt.setString(2, username);
					count = pstmt.executeUpdate();
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return count;
	}

	public long getBalance(int accountNumber) {
		System.out.println("BankingDaoImpl.getAccountForPin()");
		ResultSet resultSet = null;
		String select_query = "select balance from account where accountnumber=?";
		Long balance=-1l;
		try {
			connection = JdbcUtil.getConnection();
			if (connection != null) {
				pstmt = connection.prepareStatement(select_query);
				if (pstmt != null) {
					pstmt.setInt(1, accountNumber);
					resultSet = pstmt.executeQuery();
					if(resultSet!=null && resultSet.next())
						balance =resultSet.getLong(1);
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return balance;
	}

	@Override
	public int getUserId(String username) {
		ResultSet resultSet = null;
		String select_query = "select userid from userinfo where username=?";
		int user_id = 0;
		try {
			connection = JdbcUtil.getConnection();
			if (connection != null) {
				pstmt = connection.prepareStatement(select_query);
				if (pstmt != null) {
					pstmt.setString(1, username);
					resultSet = pstmt.executeQuery();
					if(resultSet!=null && resultSet.next())
						user_id = resultSet.getInt("userid");
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return user_id;
	}

	@Override
	public int createAccount(Account account) {
		int count = 0;
		String insert_query = "insert into account(`accountNumber`,`userid`,`pinnumber`,`mobilenumber`,`balance`,`accountHoldername`,`accountType`,`accountcreated`,`cardnumber`)values(?,?,?,?,?,?,?,?,?)";
		try {
			connection = JdbcUtil.getConnection();
			if (connection != null) {
				pstmt = connection.prepareStatement(insert_query);
				if (pstmt != null) {
					pstmt.setInt(1, account.getAccountNumber());
					pstmt.setInt(2, account.getUserid());
					pstmt.setInt(3, account.getPinNumber());
					pstmt.setString(4, account.getMobileNumber());
					pstmt.setLong(5, account.getBalance());
					pstmt.setString(6, account.getAccountHolderName());
					pstmt.setString(7, account.getAccountType());
					pstmt.setTimestamp(8, java.sql.Timestamp.valueOf(account.getAccountcreated()));
					pstmt.setString(9, account.getCardNumber());
					count = pstmt.executeUpdate();
				}
			}
		} catch (SQLException | IOException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public ResultSet getTheAccountNumber(String username) {
		String select_query="select accountNumber,balance from account where userid=(select userid from userinfo where username=?)";
		ResultSet resultSet=null;
		try {
			connection= JdbcUtil.getConnection();
			if(connection!=null) {
				pstmt= connection.prepareStatement(select_query);
				if(pstmt!=null) {
					pstmt.setString(1, username);
					resultSet=pstmt.executeQuery();
				}
				
			}
			
		}
		catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		
		return resultSet;
	}

	@Override
	public int getPinNumber(int accountNumber) {
		System.out.println("BankingDaoImpl.getAccountPin()");
		String select_query = "select pinnumber from account where accountnumber=?";
		int pin_number=-1;
		ResultSet resultSet=null;
		try {
			connection= JdbcUtil.getConnection();
			if(connection!=null) {
				pstmt= connection.prepareStatement(select_query);
				if(pstmt!=null) {
					pstmt.setInt(1, accountNumber);
					resultSet=pstmt.executeQuery();
					if(resultSet!=null) {
						pin_number=resultSet.getInt(1);
					}
				 }
			}
		}
		catch(SQLException | IOException e) {
			e.printStackTrace();
		}
		return pin_number;
	}

}
