/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author jmone
 */
public class LoginUI extends JDialog{
    
    private JTextField nameField;
    private JTextField idField;
    private JButton loginButton;
    private JButton cancelButton;
    private JButton registerButton;
    private boolean isLoggedIn = false;
    
    public LoginUI(JFrame parent){
        
        //initialise frame
        super(parent, "Login", true);
        setSize(300, 200);
        setLocationRelativeTo(parent);
        
        //create components
        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10));
        JLabel nameLabel = new JLabel("Name:");
        nameField = new JTextField(20);
        
        JLabel idLabel = new JLabel("Student ID:");
        idField = new JTextField(20);
        
        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");
        registerButton = new JButton("Register");
        
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(idLabel);
        panel.add(idField);
        panel.add(loginButton);
        panel.add(cancelButton);
        panel.add(registerButton);
        
        add(panel, BorderLayout.CENTER);
        
    }
    
    
}
