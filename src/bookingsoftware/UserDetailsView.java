/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author jmone
 */
public class UserDetailsView extends JFrame {

    private JTextField nameField = new JTextField(20);
    private JTextField idField = new JTextField(20);
    private JTextField passwordField = new JTextField(20);
    private JTextField emailField = new JTextField(20);
    private JTextField phoneField = new JTextField(20);
    private JButton saveButton = new JButton("Save Details");
    private JButton backButton = new JButton("Back");
    private static final int WIDTH = 400;
    private static final int HEIGHT = 300;

    public UserDetailsView() {
        super("Enter Your Details");
        setLayout(new GridLayout(6, 1));  // Simple grid layout
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(WIDTH, HEIGHT);
        //set window position in the middle of screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - WIDTH / 2, dim.height / 2 - HEIGHT / 2);

        add(new JLabel("Student ID:"));
        add(idField);
        add(new JLabel("First Name:"));
        add(nameField);
        add(new JLabel("Password:"));
        add(passwordField);
        add(new JLabel("Email:"));
        add(emailField);
        add(new JLabel("Phone Number:"));
        add(phoneField);
        add(backButton);
        add(saveButton);
    }

    public String getName() {
        return nameField.getText().trim();
    }

    public String getEmail() {
        return emailField.getText().trim();
    }

    public int getID() {
        try {
            return Integer.parseInt(idField.getText().trim());
        } catch (NumberFormatException e) {
            //this.displayError("Student ID must be numeric.");
            return -1;
        }
    }

    public long getPhoneNumber() {
        try {
            return Long.parseLong(phoneField.getText().trim());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Phone Number must be numeric.", "Error", JOptionPane.ERROR_MESSAGE);
            return -1;
        }
    }

    public String getPassword() {
        return passwordField.getText().trim();
    }

    public void addSaveDetailsListener(ActionListener listener) {
        saveButton.addActionListener(listener);
    }
    
    public void addBackListener(ActionListener listener){
        backButton.addActionListener(listener);
    }

}
