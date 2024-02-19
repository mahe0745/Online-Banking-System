package in.ineuron.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ineuron.service.Account;
import in.ineuron.service.IBankingService;
import in.ineuron.service.Transaction;
import in.ineuron.service.User;
import in.ineuron.servicefactory.BankingServiceFactory;

public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FrontController() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	public void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		IBankingService bankingService = BankingServiceFactory.getBankingService();
		System.out.println(request.getServletPath());
		if (request.getRequestURI().endsWith("olduser")) {
			PrintWriter out = response.getWriter();
			String userName = request.getParameter("username");
			String password = request.getParameter("password");
			System.out.println("user " + userName + "logged in");
			if (bankingService.checkUser(userName, password)) {
				HttpSession session = request.getSession();
				session.setAttribute("username", userName);
				RequestDispatcher rd = request.getRequestDispatcher("../mainpage.html");
				rd.forward(request, response);
			} else {
				out.println("<h1>Invalid Credentials</h1>");
				out.println("<a href='../index.html'>Login</a>");
			}

		}

		if (request.getRequestURI().endsWith("newuser")) {
			System.out.println("new user here");
			PrintWriter out = response.getWriter();
			String username = request.getParameter("username");
			String mobileNumber = request.getParameter("mobileNumber");
			String msg = bankingService.userExists(username, mobileNumber);
			System.out.println("Message is " + msg);
			if (msg != null) {
				RequestDispatcher rd = request.getRequestDispatcher("../register_error.jsp");
				request.setAttribute("msg", msg);
				rd.forward(request, response);
			} else {
				System.out.println("in else block");
				int accountNumber = bankingService.createAccountNumber();
				int pinNumber = bankingService.createPinNumber();
				int balance = 500;
				String dob = request.getParameter("dateofbirth");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
				LocalDate localDate = LocalDate.parse(dob, formatter);


				User user = new User(username, request.getParameter("password"), request.getParameter("email"),
						localDate, request.getParameter("address"));
				int user_registered = 0;
				int account_created = 0;
				try {
					user_registered = bankingService.registerUser(user);

					int user_id = bankingService.getUserId(username);
					Account account = new Account(accountNumber, user_id, pinNumber, mobileNumber, balance,
							request.getParameter("accountHolderName"), request.getParameter("accountType"),
							LocalDateTime.now(), null);
					account_created = bankingService.createAccount(account);
				} catch (IOException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				if (user_registered > 0 && account_created > 0) {
					System.out.println("success");
					out.println("<html>");
					out.println("<body>");
					out.println("<h2>Account Created</h2>");
					out.println("<a href='../index.html'>Login</a>");
					out.println("</body>");
					out.println("</html>");
				} else {
					out.println("<h1>OOPS Something wrong</h1>");
				}
			}
		}

		if (request.getRequestURI().endsWith("transfer")) {
			System.out.println(request.getRequestURI());
			PrintWriter out = response.getWriter();
			String account_number = request.getParameter("beneficiaryAccountNumber");
			HttpSession session = request.getSession(false);
			ResultSet senderAccount = bankingService.getTheAccountNumber((String) session.getAttribute("username"));
			int senderAccountNumber = 0;
			long senderBalance = 0;
			int amount = Integer.valueOf(request.getParameter("amount"));
			try {
				if (senderAccount != null) {
					if(senderAccount!=null && senderAccount.next()) {
					senderAccountNumber = senderAccount.getInt(1);
					senderBalance = senderAccount.getLong(2);
					}
				}
				int receiverAccountNumber = Integer.valueOf(account_number);
				long receiverBalance = bankingService.getBalance(receiverAccountNumber);
				ResultSet resultforpin = bankingService.getAccountForPin((String) session.getAttribute("username"));
				int pin=-1;
				if(resultforpin!=null && resultforpin.next())
					  pin = resultforpin.getInt("pinnumber");
				if (receiverBalance == -1) {
					RequestDispatcher rd = request.getRequestDispatcher("../accountNumber_error.html");
					rd.forward(request, response);
				}

				String pin_number = request.getParameter("pinNumber");
				int pinNumber = Integer.valueOf(pin_number);
				System.out.println("Pinnumber entered is " + pinNumber);
				System.out.println("sender pinnumber is " + pin);
				if (pin != pinNumber) {
					RequestDispatcher rd = request.getRequestDispatcher("../incorrectpin.html");
					rd.forward(request, response);
				} else if (senderBalance < Integer.valueOf(request.getParameter("amount"))) {
					System.out.println(senderBalance);
					RequestDispatcher rd = request.getRequestDispatcher("../insufficientBalance.html");
					rd.forward(request, response);

				} else {
					bankingService.updateBalance(senderAccountNumber, amount, senderBalance, "debit");
					bankingService.updateBalance(receiverAccountNumber, amount, receiverBalance, "credit");
					Transaction transaction = new Transaction(senderAccountNumber, receiverAccountNumber, amount,
							LocalDateTime.now());
					int result = bankingService.addTransaction(transaction);
					if (result == 0) {
						out.println("<h1>OOPS Something Went Wrong</h1>");
					} else {
						out.println("<h1>Amount transfered Successfully</h1>");
						out.println("<a href='../DummyMain.html'>Main Page</a>");
					}
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}

		if (request.getRequestURI().endsWith("transaction"))

		{
			System.out.println("in transactions");
			RequestDispatcher rd = request.getRequestDispatcher("/servlet/transaction");
			rd.forward(request, response);
		}

		if (request.getRequestURI().endsWith("pin")) {
			RequestDispatcher rd = request.getRequestDispatcher("/servlet/pin");
			rd.forward(request, response);
		}

		if (request.getRequestURI().endsWith("balance")) {
			RequestDispatcher rd = request.getRequestDispatcher("/servlet/balance");
			rd.forward(request, response);
		}
		
		if(request.getRequestURI().endsWith("logout")) {
			System.out.println("in logout");
			RequestDispatcher rd = request.getRequestDispatcher("../index.html");
			rd.forward(request, response);
		}

	}
}
