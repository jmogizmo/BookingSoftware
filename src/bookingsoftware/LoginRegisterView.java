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

    public JTextField IDField = new JTextField(10);
    public JTextField passwordField = new JPasswordField(10);
    private JButton loginButton = new JButton("Login");
    private JButton registerButton = new JButton("Register");
    private JLabel imageLabel;
    private static final int HEIGHT = 250;
    private static final int WIDTH = 200;

    //password = studentID
    //initialise
    public LoginRegisterView(/*Graphics g*/) {
        super("Login/Register");
        //setLayout(new GridLayout(3, 2));  // Simple grid layout
        setLayout(new FlowLayout());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(HEIGHT, WIDTH);

        //set window position in the middle of screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - WIDTH / 2, dim.height / 2 - HEIGHT / 2);

        //TODO: load AUT image
        //ImageIcon autlogoIcon = new ImageIcon("/resources/autlogo.png");
        //Image autLogo = autlogoIcon.getImage();
        // Load the image
        ImageIcon imageIcon = new ImageIcon("/resources/autlogo.png"); // Ensure path is correct
        Image image = imageIcon.getImage(); // Transform it 
        Image newimg = image.getScaledInstance(200, 100, java.awt.Image.SCALE_SMOOTH); // Scale it the smooth way  
        imageIcon = new ImageIcon(newimg);  // Transform it back

        imageLabel = new JLabel(imageIcon);

        //g.drawImage(image,10,10,null);
        add(imageLabel);
        add(new JLabel("Student ID:  "));
        add(IDField);
        add(new JLabel("Password: "));
        add(passwordField);
        add(registerButton);
        add(loginButton);

    }

    public int getID() {
        try {
            return Integer.parseInt(IDField.getText().trim());
        } catch (NumberFormatException e) {
            //this.displayError("Student ID must be numeric.");
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
