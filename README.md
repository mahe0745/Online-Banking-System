#Online Banking System

Overview

This online banking system is a web application developed using Java Servlets and JDBC for database operations. It follows a layered architecture with a controller layer, service layer, and DAO layer, ensuring a clean separation of concerns and easy maintainability.

Features

User Account Management: Users can create an account, log in, and manage their account details.

Fund Transfer: Users can transfer money between their accounts or to other users' accounts securely.

Transaction History: Users can view their transaction history to track their financial activities.

PIN Management: Users can manage their transaction PINs for added security.

Balance Inquiry: Users can check their account balance at any time.

Implementation Details

Controller Layer: The front controller handles incoming requests and dispatches them to appropriate servlets.

Service Layer: Contains business logic for operations such as money transfer, user validation, and PIN checking.

DAO Layer: Contains JDBC code for database operations, ensuring data integrity and consistency.

Utility Class: Contains JDBC operations for connecting to the database and managing drivers.

Properties File: Manages database credentials for secure database access.

User Authentication Filter: Validates user credentials using regex for mobile and email formats.

Usage

Access the application through a web browser.

Register for an account if you are a new user, or log in with your credentials.

Use the provided functionalities to manage your account, transfer funds, and view transaction history.

Technologies Used

Java Servlets

JDBC

Apache Tomcat 

MySQL 
