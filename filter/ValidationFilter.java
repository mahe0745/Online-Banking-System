package in.ineuron.filter;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.http.HttpRequest;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class ValidationFilter implements Filter {

	public ValidationFilter() {
		super();
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest=(HttpServletRequest)request;
	   
		if(httpRequest.getRequestURI().endsWith("newuser")) {
		boolean upper=false;
		boolean lower=false;
		boolean digit=false;
		boolean mob = false;
		boolean password_length=false;
		boolean email_flag=false;
		
		String password_msg="password length be greater than or Equal to 8";
		String upper_msg="upperCharacterRequired";
		String lower_msg="lowerCharacterRequired";
		String digit_msg="DigitRequired";
		String mob_msg="mobile number should be 10 digit and starts with 6-9";
		String email_msg="Invalid Email";
		
		PrintWriter out = response.getWriter();
		String mobileNumber = request.getParameter("mobileNumber");
		String password = request.getParameter("password");
		
		Pattern mobile = Pattern.compile("(0|91)?[6-9]{1}[0-9]{9}");
		if(password.length()>=8) {
			password_length=true;
			password_msg="";
		}

		Matcher m = mobile.matcher(mobileNumber);
		if (m.matches()) {
			mob = true;
			mob_msg="";
		}
		System.out.println("mobile message is "+mob_msg);
		
		Pattern email_validation = Pattern.compile("^[A-Za-z0-9_]+@[A-Za-z]+\\.[a-zA-z]{2,}$");
		String email = request.getParameter("email");
		Matcher matcher=email_validation.matcher(email);
		if(matcher.matches()) {
			email_flag=true;
			email_msg="";
		}
		
        for(int i=0;i<password.length();i++) {
        	char current=password.charAt(i);
        	if(current>=65 && current <=90) {
        		upper=true;
        		upper_msg="";
        	}
        	else if(current>=90 && current<=122) {
        		lower=true;
        		lower_msg="";
        	}
        	else if(Character.isDigit(current)) {
        		digit=true;
        		digit_msg="";
        	}
        }
        
        if(upper==false || lower==false || digit==false || mob==false || password_length==false || email_flag==false) {
        	//System.out.println(request.getParameter("accountHolderName"));
        	//System.out.println(request.getParameter("mobileNumber"));
        	request.setAttribute("password_msg", password_msg);
        	request.setAttribute("upper_msg", upper_msg);
        	request.setAttribute("lower_msg", lower_msg);
        	request.setAttribute("digit_msg", digit_msg);
        	request.setAttribute("mob_msg", mob_msg);
        	request.setAttribute("email_msg", email_msg);
        	RequestDispatcher rd= request.getRequestDispatcher("../register_error.jsp");
        	rd.forward(request, response);
        	


         }else {
        	 chain.doFilter(request, response);
        	 
         }
		
		//out.println("<h1>response after request processing from filter</h1>");
	   }
	   
	   if(httpRequest.getRequestURI().endsWith("transfer")) {
		   PrintWriter out= response.getWriter();
		   boolean accountNumber=false;
		   boolean pinNumber=false;
		   
		   String accountNumber_msg="Account Number Should be Six Digit";
		   String pin_msg="Pin Number should be Four Digit";
		   Pattern p = Pattern.compile("[0-9]{6}");
		   Matcher m = p.matcher(request.getParameter("beneficiaryAccountNumber"));
		   System.out.println("for accountNumber "+m.matches());
		   
		   if( m.matches()) {
			   accountNumber=true;
			   accountNumber_msg="";
		   }
		   p = Pattern.compile("[0-9]{4}");
		   m=p.matcher(request.getParameter("pinNumber"));
		   System.out.println("for pinnumber "+m.matches());
		   if(m.matches()) {
			   pinNumber=true;
			   pin_msg="";
		   }
		   if(accountNumber==false || pinNumber==false) {
			   httpRequest.setAttribute("accountNumber", accountNumber_msg);
			   httpRequest.setAttribute("pinNumber", pin_msg);
			   RequestDispatcher rd = httpRequest.getRequestDispatcher("../transaction_error.jsp");
			   rd.forward(httpRequest, response);
		   }
		   else {
			   chain.doFilter(request, response);
		   }
	   }
	   
	   
	   if(httpRequest.getRequestURI().endsWith("olduser")){
		   
		   chain.doFilter(request, response);
	   }
	   if(httpRequest.getRequestURI().endsWith("transaction")){
		   
		   chain.doFilter(request, response);
	   }
	   	
	   if(httpRequest.getRequestURI().endsWith("pin")){
		   PrintWriter out = response.getWriter();
		   boolean pin=false;
		   boolean newPin=false;
		   boolean confirmpin=false;
		   boolean pinandConfirmPin=false;
		   
		   String msg="new pin and Confirm Pin should be same";
		   String pin_msg="Pin should be 4 Digits";
		   String current_msg="Pin should be 4 Digits";
		   String confirm_msg="Pin should be 4 Digits";
		   String Current_pin  = request.getParameter("currentPin");
		   String new_pin  = request.getParameter("newPin");
		   String Confirm_pin  = request.getParameter("confirmPin");
		   
		   Pattern p = Pattern.compile("[0-9]{4}");
		   Matcher m=p.matcher(Current_pin);
		   
		   if(m.matches()) {
			   System.out.println(" true for pin ");
			   pin=true;
			   pin_msg="";
		   }
		   m=p.matcher(new_pin);
		   if(m.matches()) {
			   System.out.println(" true for newpin ");
			   newPin=true;
			   current_msg="";
		   }
		   m=p.matcher(Confirm_pin);
		   if(m.matches()) {
			   System.out.println(" true for confirm pin ");
			   confirmpin=true;
			   confirm_msg="";
		   }
		   
		   if(new_pin.equals(Confirm_pin)) {
			   System.out.println(" true for new and confirm pin ");
			   pinandConfirmPin=true;
			   msg="";
		   }
		   
		   if(pin && newPin && confirmpin &&pinandConfirmPin)chain.doFilter(request, response);
		   else {
			   httpRequest.setAttribute("pin", pin_msg);
			   httpRequest.setAttribute("newpin", current_msg);
			   httpRequest.setAttribute("confirmpin", confirmpin);
			   httpRequest.setAttribute("pinandConfirmPin", msg);
			   RequestDispatcher rd= httpRequest.getRequestDispatcher("../pin_error.jsp");
			   rd.forward(httpRequest, response);
		   }
	   }
	   
	   if(httpRequest.getRequestURI().endsWith("balance")){
		  chain.doFilter(httpRequest, response);
	   }
		
	}

}
