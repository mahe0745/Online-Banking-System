package in.ineuron.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import in.ineuron.service.IBankingService;
import in.ineuron.servicefactory.BankingServiceFactory;

public class SecondServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doProcess(request, response);
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		IBankingService bankingService = BankingServiceFactory.getBankingService();

		if (request.getRequestURI().endsWith("/transaction")) {
			System.out.println(request.getRequestURI());
			System.out.println("in second servlet transaction");
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			ResultSet rs = bankingService.getTheAccountNumber((String) session.getAttribute("username"));

			int accountNumber = -1;
			try {
				if (rs != null && rs.next())
					accountNumber = rs.getInt("accountNumber");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ResultSet resultSet = bankingService.getTheTransaction(accountNumber);
			request.setAttribute("accountNumber", accountNumber);
			request.setAttribute("resultSet", resultSet);
			RequestDispatcher rd = request.getRequestDispatcher("../transfer.jsp");
			rd.forward(request, response);
		}

		if (request.getRequestURI().endsWith("pin")) {
			System.out.println("in pin controller");
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			ResultSet resultSet = bankingService.getAccountForPin(username);
			boolean val = bankingService.changePin(resultSet, request.getParameter("currentPin"));

			if (val) {
				int count = bankingService.updatePin(Integer.valueOf(request.getParameter("newPin")), username);
				if (count == 1) {
					RequestDispatcher rd = request.getRequestDispatcher("../pinchange.html");
					rd.forward(request, response);

				} else {
					out.println("<h1>OOPS Something wrong</h1>");
				}
			}
		}

		if (request.getRequestURI().endsWith("balance")) {
			PrintWriter out = response.getWriter();
			HttpSession session = request.getSession();
			String username = (String) session.getAttribute("username");
			ResultSet rs = bankingService.getTheAccountNumber(username);
			long balance = -1l;

			try {
				if (rs != null && rs.next()) {
					int accountNumber = rs.getInt("accountNumber");
					balance = bankingService.getBalance(accountNumber);
					request.setAttribute("balance", balance);
					RequestDispatcher rd = request.getRequestDispatcher("../balance.jsp");
					rd.forward(request, response);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
