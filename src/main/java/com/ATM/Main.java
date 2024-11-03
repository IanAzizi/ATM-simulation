package com.ATM;

import com.ATM.Excpetion.wrongAnswerException;  // Ensure your exception is correctly named
import com.ATM.cart.User;
import com.ATM.machinService.Money_transfer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {
    private User user;
    private boolean authenticated;

    private JTextField nameField;
    private JTextField passwordField;
    private JTextArea displayArea;

    private JButton loginButton;
    private JButton transferButton;
    private JButton checkBalanceButton;
    private JButton changePasswordButton;
    private JButton exitButton;

    public Main() {
        user = new User();
        authenticated = false;

        setTitle("ATM");
        setSize(400, 400);
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Components for user login
        nameField = new JTextField(10);
        passwordField = new JTextField(10);
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);

        loginButton = new JButton("Login");
        loginButton.addActionListener(new LoginAction());

        // Components for ATM operations
        transferButton = new JButton("Money Transfer");
        transferButton.addActionListener(new TransferAction());

        checkBalanceButton = new JButton("Show Balance");
        checkBalanceButton.addActionListener(new ShowBalanceAction());

        changePasswordButton = new JButton("Change Password");
        changePasswordButton.addActionListener(new ChangePasswordAction());

        exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            authenticated = false;
            displayArea.setText("Thank you for using the ATM. Goodbye!");
        });

        // Adding components to the JFrame
        add(new JLabel("Name:"));
        add(nameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(loginButton);
        add(displayArea);
        add(transferButton);
        add(checkBalanceButton);
        add(changePasswordButton);
        add(exitButton);

        // Initially hide the transaction buttons
        setTransactionButtonsVisible(false);

        setVisible(true);
    }

    private void setTransactionButtonsVisible(boolean visible) {
        transferButton.setVisible(visible);
        checkBalanceButton.setVisible(visible);
        changePasswordButton.setVisible(visible);
    }

    private class LoginAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String password = passwordField.getText();
            user.setName(name);
            user.setPassword(Integer.parseInt(password));  // Ensure this is done safely in your production code

            if (name.equals(user.getName()) && Integer.parseInt(password) == user.getPassword()) {
                authenticated = true;
                displayArea.setText("Welcome to ATM, " + user.getName() + ". You have: " + user.getAmount() + " in your bank account.");
                setTransactionButtonsVisible(true);
            } else {
                displayArea.setText("Incorrect username or password.");
            }
        }
    }

    private class TransferAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!authenticated) {
                displayArea.setText("Please log in first.");
                return;
            }
            try {
                int src = Integer.parseInt(JOptionPane.showInputDialog("Enter source account number:"));
                int des = Integer.parseInt(JOptionPane.showInputDialog("Enter destination account number:"));
                int money = Integer.parseInt(JOptionPane.showInputDialog("Enter amount to transfer:"));

                Money_transfer mt = new Money_transfer();
                mt.setSrc_number(src);
                mt.setDes_number(des);
                mt.setMoney(money);

                displayArea.setText("Money transfer initiated from " + src + " to " + des + " of amount " + money);
            } catch (Exception ex) {
                displayArea.setText("Error during money transfer: " + ex.getMessage());
            }
        }
    }

    private class ShowBalanceAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!authenticated) {
                displayArea.setText("Please log in first.");
                return;
            }
            displayArea.setText("Your current balance is: " + user.getAmount());
        }
    }

    private class ChangePasswordAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!authenticated) {
                displayArea.setText("Please log in first.");
                return;
            }
            try {
                String newPassword = JOptionPane.showInputDialog("Enter new password:");
                user.setPassword(Integer.parseInt(newPassword));
                displayArea.setText("Password changed successfully.");
            } catch (Exception ex) {
                displayArea.setText("Error changing password: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Main::new);
    }
}