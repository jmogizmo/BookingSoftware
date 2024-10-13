/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

/**
 *
 * @author jmone
 */
public class MainMenuView extends JFrame {

    private JButton createBookingButton = new JButton("Create Booking");
    private JButton cancelBookingButton = new JButton("Cancel Booking");
    private JButton searchButton = new JButton("Search");
    private JButton detailsButton = new JButton("My Details");
    private JButton logoutButton = new JButton("Logout");
    private static final int HEIGHT = 400;
    private static final int WIDTH = 500;

    public MainMenuView() {
        super("Main Menu");
        
        //set window position to middle of screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - WIDTH / 2, dim.height / 2 - HEIGHT / 2);
        
        setLayout(new GridLayout(3, 1));
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(createBookingButton);
        add(searchButton);
        add(detailsButton);
        add(cancelBookingButton);
        add(logoutButton);
        
    }

    public void addCreateBookingListener(ActionListener listener) {
        createBookingButton.addActionListener(listener);
    }

    public void addCancelBookingListener(ActionListener listener) {
        cancelBookingButton.addActionListener(listener);
    }

    public void addSearchListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }
    public void addDetailsListener(ActionListener listener){
        detailsButton.addActionListener(listener);
    }
    public void addLogoutListener(ActionListener listener){
        logoutButton.addActionListener(listener);
    }
    
    public void displayError(String error) {
        JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
