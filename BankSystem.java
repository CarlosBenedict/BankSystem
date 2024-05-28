/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.banksystem;

/**
 *
 * @author Benedict
 */
import javax.swing.*; //import javax.swing.*;: This line imports the Swing library, which provides a set of GUI components for building desktop applications in Java.
import java.awt.*; //import java.awt.*;: This line imports the AWT (Abstract Window Toolkit) library, which is the foundation for the Swing library and provides a set of classes for creating user interfaces and for painting graphics and images.
import java.awt.event.ActionEvent; //import java.awt.event.ActionEvent; and import java.awt.event.ActionListener;: These lines import the ActionEvent and ActionListener classes, which are used for handling user actions like clicking buttons.
import java.awt.event.ActionListener;
public class BankSystem {
    
    private JFrame frame; //private JFrame frame;: This line declares a private instance variable frame of type JFrame, which is the top-level container for the GUI.
    private JLabel nameLabel, passwordLabel;//private JLabel nameLabel, passwordLabel;: This line declares private instance variables nameLabel and passwordLabel of type JLabel, which are used to display text labels for the login fields.
    private JTextField nameField;//private JTextField nameField;: This line declares a private instance variable nameField of type JTextField, which is a text field component for entering the user's name.
    private JPasswordField passwordField; //private JPasswordField passwordField;: This line declares a private instance variable passwordField of type JPasswordField, which is a text field component for entering the user's password.
    private JButton loginButton; //private JButton loginButton;: This line declares a private instance variable loginButton of type JButton, which is a button component for submitting the user's login credentials.
    private int balance = 2000; //private int balance = 2000;: This line declares a private instance variable balance of type int, which stores the user's account balance and is initialized to 2000.

      public BankSystem() {

        frame = new JFrame("Bank GUI"); //frame = new JFrame("Bank GUI");: This line creates a new JFrame object and assigns it to the frame instance variable, with the title "Bank GUI".
        frame.setSize(300, 200); //frame.setSize(300, 200);: This line sets the size of the frame to 300 pixels wide and 200 pixels high.
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);: This line sets the default close operation of the frame to exit the application when the user closes the window.
        frame.setLayout(new BorderLayout()); //frame.setLayout(new BorderLayout());: This line sets the layout manager of the frame to a BorderLayout, which is used to arrange components in five areas: north, south, east, west, and center.

        JPanel loginPanel = new JPanel(new GridLayout(3, 2)); //JPanel loginPanel = new JPanel(new GridLayout(3, 2));: This line creates a new JPanel object named loginPanel and assigns it a new GridLayout layout manager with 3 rows and 2 columns.

        nameLabel = new JLabel("Name:");//nameLabel = new JLabel("Name:"); and passwordLabel = new JLabel("Password:");: These lines create new JLabel objects named nameLabel and passwordLabel and set their text to "Name:" and "Password:", respectively.
        passwordLabel = new JLabel("Password:");

        nameField = new JTextField(20);//nameField = new JTextField(20); and passwordField = new JPasswordField(20);: These lines create new JTextField
        passwordField = new JPasswordField(20); // The JPasswordField class is designed to handle password input and automatically displays the characters as asterisks or other masking characters to hide the actual password value from being visible on the screen. 

        loginButton = new JButton("Login");//

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String password = new String(passwordField.getPassword());
                if (name.equals("admin") && password.equals("1234")) { //If the name is "admin" and the password is "1234", the login is successful, and the user is taken to a new window that displays various banking options.
                    frame.dispose();

                    JFrame optionsFrame = new JFrame("Options");
                    optionsFrame.setSize(300, 200);
                    optionsFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    JPanel optionsPanel = new JPanel(new GridLayout(4, 2));
                    JLabel balanceLabel = new JLabel("Your Balance is: " + balance);

                    JButton cbalanceButton = new JButton("Check Balance");
                    JButton depositButton = new JButton("Deposit");
                    JButton withdrawButton = new JButton("Withdraw");

                    cbalanceButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            JOptionPane.showMessageDialog(optionsFrame, balanceLabel.getText());

                        }
                    });

                    depositButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String amountStr = JOptionPane.showInputDialog(optionsFrame, "Enter amount to deposit:");

                            if (amountStr.matches("\\d+")) { // check if input is a number
                                int deposit = Integer.parseInt(amountStr);

                                if (deposit > 0) {
                                    int newBalance = balance + deposit;
                                    if (newBalance >= 2000) {
                                        balance = newBalance;
                                        JOptionPane.showMessageDialog(optionsFrame, "Successfully deposited PHP " + deposit);
                                        balanceLabel.setText("Your balance is PHP " + balance);
                                    } else {
                                        JOptionPane.showMessageDialog(optionsFrame, "Deposit amount too low. Minimum deposit required is PHP " + (2000 - balance));
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(optionsFrame, "Invalid amount. Please enter a positive number.");
                                }
                            } else {
                                JOptionPane.showMessageDialog(optionsFrame, "Invalid input. Please enter a number.");
                            }
                        }
                    });

                    withdrawButton.addActionListener(new ActionListener() { //And the withdrawButton has an ActionListener that is triggered when the button is clicked. It shows an input dialog prompting the user to enter an amount to withdraw. The input is validated to ensure it is a positive integer. If the input is valid, the amount is subtracted from the current balance, and if the resulting balance is not less than 2000, the new balance is set and a success message is shown with the updated balance. If the resulting balance is less than 2000, an error message is displayed. If the input is not a positive integer or not an integer at all, an error message is displayed.
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            String amountStr = JOptionPane.showInputDialog(optionsFrame, "Enter amount to withdraw:");

                            if (amountStr.matches("\\d+")) { // check if input is a number
                                int withdraw = Integer.parseInt(amountStr);

                                if (withdraw > 0) {
                                    int newBalance = balance - withdraw;
                                    if (newBalance >= 2000) {
                                        balance = newBalance;
                                        JOptionPane.showMessageDialog(optionsFrame, "Successfully withdraw PHP " + withdraw);
                                        balanceLabel.setText("Your balance is PHP " + balance);
                                    } else {
                                        JOptionPane.showMessageDialog(optionsFrame, "Withdrawal amount too high. Maximum withdrawal allowed is PHP " + (balance - 2000));
                                    }
                                } else {
                                    JOptionPane.showMessageDialog(optionsFrame, "Invalid amount. Please enter a positive number.");
                                }
                            } else {
                                JOptionPane.showMessageDialog(optionsFrame, "Invalid input. Please enter a number.");
                            }
                        }
                    });

                    optionsPanel.add(balanceLabel);
                    optionsPanel.add(cbalanceButton);
                    optionsPanel.add(depositButton);
                    optionsPanel.add(withdrawButton);

                    optionsFrame.add(optionsPanel);
                    optionsFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(frame, "Invalid login information!");
                }
            }
        });

        loginPanel.add(nameLabel);
        loginPanel.add(nameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.add(loginButton);

        frame.add(loginPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new BankSystem();
    }
}