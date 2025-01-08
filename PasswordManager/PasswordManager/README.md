# Advanced Password Manager

This project is a Java-based GUI application that allows users to generate, save, and retrieve passwords securely. It includes a user-friendly interface and supports customizable password generation options.

---

## Features
- **Password Generation**: Create strong passwords with options to include:
  - Uppercase letters
  - Numbers
  - Special characters
  - Customizable password length (8–32 characters)
- **Secure Password Storage**: Save passwords locally using an SQLite database.
- **Retrieve Saved Passwords**: Retrieve stored passwords for specific accounts using the account name.
- **Dark-Themed GUI**: An intuitive, clean, and modern interface for an enhanced user experience.

---

## Setup Instructions
Follow these steps to set up and run the Password Manager application:

### Step 1: Prerequisites
Before running the application, ensure the following are installed on your system:
1. **Java Development Kit (JDK)**:
   - Download and install the latest JDK from [Oracle](https://www.oracle.com/java/technologies/javase-downloads.html) or [OpenJDK](https://openjdk.org/).
   - Verify installation by running:
     ```bash
     java -version
     ```
   - The output should show the installed version.
2. **SQLite JDBC Library**:
   - Included in the project folder as `sqlite-jdbc-3.47.2.0.jar`.

### Step 2: Clone the Repository
1. Open a terminal or Git client.
2. Clone the repository from GitHub:
   ```bash
   git clone https://github.com/schoudhary90210/portfolio.git

	3.	Navigate to the PasswordManager folder:

cd portfolio/PasswordManager



Step 3: Compile the Code
	1.	Open a terminal and navigate to the PasswordManager directory.
	2.	Compile the Java program with the SQLite JDBC library:

javac -cp ".:sqlite-jdbc-3.47.2.0.jar" PasswordManager.java



Step 4: Run the Application
	1.	Run the compiled program:

java -cp ".:sqlite-jdbc-3.47.2.0.jar" PasswordManager



Step 5: Using the Application
	1.	Generate a Password:
	•	Enter the desired password length and select options (e.g., include uppercase letters, numbers, or special characters).
	•	Click Generate Password to create a secure password.
	2.	Save a Password:
	•	Enter the account name and the generated password.
	•	Click Save to store it securely in the database.
	3.	Retrieve a Password:
	•	Enter the account name for which you want to retrieve the password.
	•	Click Retrieve to view the saved password.

Project Files
	•	PasswordManager.java: The main Java program.
	•	sqlite-jdbc-3.47.2.0.jar: SQLite JDBC library for local database integration.
	•	README.md: Instructions and project overview.

Technologies Used
	•	Java: For the main application logic and GUI (Swing framework).
	•	SQLite: For secure local password storage.
	•	Swing: For creating a user-friendly graphical user interface.

Screenshots
<img width="1114" alt="Screenshot 2025-01-08 at 6 10 54 PM" src="https://github.com/user-attachments/assets/3209334c-e078-4a3c-b130-ffe7fe972191" />


Main Application Interface:

Troubleshooting
	1.	Java Not Found:
	•	If you encounter a “Java not found” error, ensure JDK is installed and added to your system’s PATH.
	2.	SQLite JDBC Missing:
	•	If you get errors related to SQLite, ensure the sqlite-jdbc-3.47.2.0.jar file is in the project folder.
	3.	GUI Not Displaying Properly:
	•	Ensure your system supports Java Swing applications.

FAQ
	1.	Where are my passwords stored?
	•	Passwords are stored locally in an SQLite database (passwords.db) located in the project directory.
	2.	Is my data secure?
	•	Passwords are saved locally. Ensure you don’t share the database file (passwords.db) with others to maintain privacy.
	3.	Can I customize the password length?
	•	Yes, the password length is adjustable between 8–32 characters.
	4.	Can I use this on macOS, Windows, or Linux?
	•	Yes, the application is platform-independent, as long as Java is installed.

Contributions

Contributions are welcome! If you have suggestions or want to add features, feel free to fork the repository and submit a pull request.

---
