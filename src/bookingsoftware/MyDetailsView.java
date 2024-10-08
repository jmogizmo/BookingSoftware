/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

import java.awt.GridLayout;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 *
 * @author jmone
 */
public class MyDetailsView extends JFrame{
    private JLabel nameLabel = new JLabel("Name: ");
    private JLabel idLabel = new JLabel("ID: ");
    private JLabel emailLabel = new JLabel("Email: ");
    private JLabel phoneLabel = new JLabel("Phone #: ");
    private JLabel bookingsLabel = new JLabel("My Bookings: ");
    private JButton backButton = new JButton("Back");
    
    public MyDetailsView(){
        super("My Details");
        setLayout(new GridLayout(6,1));
        setSize(300,200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        
        add(nameLabel);
        add(idLabel);
        add(emailLabel);
        add(phoneLabel);
        add(bookingsLabel);
        add(backButton);
        
        
    }
    
    public void setDetails(String name, int id, String email, long phone){
        nameLabel.setText("Name: "+name);
        idLabel.setText("ID: "+id);
        emailLabel.setText("Email: "+email);
        phoneLabel.setText("Phone #: "+phone);
    }
    
    public void addBackListener(ActionListener l){
        backButton.addActionListener(l);
    }
    
    public void displayError(String error) {
        JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
