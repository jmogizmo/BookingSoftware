/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *
 * @author jmone
 */
public class LoginRegisterView extends JFrame {

    private JTextField IDField = new JTextField(10);
    private JTextField passwordField = new JPasswordField(10);
    private JButton loginButton = new JButton("Login");
    private JButton registerButton = new JButton("Register");

    //password = studentID
    //initialise
    public LoginRegisterView() {
        super("Login/Register");

        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        add(new JLabel("Name: "));
        add(IDField);
        add(new JLabel("Student ID: "));
        add(passwordField);
        add(loginButton);
        add(registerButton);

    }

    public int getID() {
        try {
            return Integer.parseInt(IDField.getText().trim());
        } catch (NumberFormatException e) {
            this.displayError("Student ID must be numeric.");
            return -1;
        }
    }

    public String getPassword() {
        return passwordField.getText().trim();
    }

    public void addLoginListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void addRegisterListener(ActionListener listener) {
        registerButton.addActionListener(listener);
    }

    //method for error message pop up
    public void displayError(String error) {
        JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
