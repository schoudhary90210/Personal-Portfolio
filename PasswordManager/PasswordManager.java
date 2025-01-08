import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.Random;

public class PasswordManager {
    private static final String DB_URL = "jdbc:sqlite:passwords.db";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(PasswordManager::createAndShowGUI);

        // Initialize the database
        initializeDatabase();
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Advanced Password Manager");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);

        // Set the dark theme (black background and white text)
        UIManager.put("Panel.background", Color.BLACK);
        UIManager.put("Label.foreground", Color.WHITE);
        UIManager.put("TextField.background", Color.DARK_GRAY);
        UIManager.put("TextField.foreground", Color.WHITE);
        UIManager.put("TextArea.background", Color.DARK_GRAY);
        UIManager.put("TextArea.foreground", Color.WHITE);
        UIManager.put("CheckBox.background", Color.BLACK);
        UIManager.put("CheckBox.foreground", Color.WHITE);

        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 15));

        JLabel accountLabel = new JLabel("Account:");
        JTextField accountField = new JTextField();

        JLabel passwordLabel = new JLabel("Generated Password:");
        JTextField passwordField = new JTextField();
        passwordField.setEditable(false);

        JLabel lengthLabel = new JLabel("Password Length:");
        JSpinner lengthSpinner = new JSpinner(new SpinnerNumberModel(12, 8, 32, 1));

        JCheckBox includeUppercase = new JCheckBox("Include Uppercase Letters", true);
        JCheckBox includeNumbers = new JCheckBox("Include Numbers", true);
        JCheckBox includeSpecialChars = new JCheckBox("Include Special Characters", true);

        JButton generateButton = new JButton("Generate Password");
        styleButton(generateButton);

        JButton saveButton = new JButton("Save");
        styleButton(saveButton);

        JButton retrieveButton = new JButton("Retrieve");
        styleButton(retrieveButton);

        JTextArea outputArea = new JTextArea(10, 40);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Add components to input panel
        inputPanel.add(accountLabel);
        inputPanel.add(accountField);
        inputPanel.add(passwordLabel);
        inputPanel.add(passwordField);
        inputPanel.add(lengthLabel);
        inputPanel.add(lengthSpinner);
        inputPanel.add(includeUppercase);
        inputPanel.add(includeNumbers);
        inputPanel.add(includeSpecialChars);

        // Add buttons to button panel
        buttonPanel.add(generateButton);
        buttonPanel.add(saveButton);
        buttonPanel.add(retrieveButton);

        // Add everything to main panel
        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        mainPanel.add(scrollPane, BorderLayout.SOUTH);

        frame.add(mainPanel);
        frame.setVisible(true);

        // Button Actions
        generateButton.addActionListener(e -> {
            int length = (int) lengthSpinner.getValue();
            boolean useUppercase = includeUppercase.isSelected();
            boolean useNumbers = includeNumbers.isSelected();
            boolean useSpecialChars = includeSpecialChars.isSelected();

            String generatedPassword = generatePassword(length, useUppercase, useNumbers, useSpecialChars);
            passwordField.setText(generatedPassword);
        });

        saveButton.addActionListener(e -> {
            String account = accountField.getText();
            String password = passwordField.getText();
            if (!account.isEmpty() && !password.isEmpty()) {
                savePassword(account, password);
                outputArea.setText("Password saved for account: " + account);
            } else {
                outputArea.setText("Please fill in both account and password fields.");
            }
        });

        retrieveButton.addActionListener(e -> {
            String account = accountField.getText();
            if (!account.isEmpty()) {
                String password = retrievePassword(account);
                if (password != null) {
                    outputArea.setText("Password for account " + account + ": " + password);
                } else {
                    outputArea.setText("No password found for account: " + account);
                }
            } else {
                outputArea.setText("Please enter an account to retrieve the password.");
            }
        });
    }

    private static void initializeDatabase() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            String createTableSQL = "CREATE TABLE IF NOT EXISTS passwords (" +
                                    "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                    "account TEXT UNIQUE NOT NULL, " +
                                    "password TEXT NOT NULL)";
            stmt.execute(createTableSQL);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void savePassword(String account, String password) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String insertSQL = "INSERT OR REPLACE INTO passwords (account, password) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insertSQL);
            pstmt.setString(1, account);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static String retrievePassword(String account) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String selectSQL = "SELECT password FROM passwords WHERE account = ?";
            PreparedStatement pstmt = conn.prepareStatement(selectSQL);
            pstmt.setString(1, account);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("password");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String generatePassword(int length, boolean useUppercase, boolean useNumbers, boolean useSpecialChars) {
        String lowerCase = "abcdefghijklmnopqrstuvwxyz";
        String upperCase = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String specialChars = "!@#$%^&*()-_=+[]{}|;:',.<>?/";

        StringBuilder characterPool = new StringBuilder(lowerCase);
        if (useUppercase) characterPool.append(upperCase);
        if (useNumbers) characterPool.append(numbers);
        if (useSpecialChars) characterPool.append(specialChars);

        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            password.append(characterPool.charAt(random.nextInt(characterPool.length())));
        }

        return password.toString();
    }

    private static void styleButton(JButton button) {
        button.setBackground(Color.DARK_GRAY);
        button.setForeground(Color.WHITE);
        button.setOpaque(true);
        button.setBorderPainted(false);
        button.setPreferredSize(new Dimension(150, 40));  // Ensure all buttons are the same size
    }
}