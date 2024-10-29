/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

import Interface.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author jmone
 */
public class MainMenuController<E> {

    private MainMenuView2 mainMenuView;
    private LoginRegisterView2 loginView;
    private UserManager users;

    public MainMenuController(MainMenuView2 mainMenuView, LoginRegisterView2 loginView, UserManager users) {
        this.mainMenuView = mainMenuView;
        this.loginView = loginView;
        this.users = users;
        this.mainMenuView.addDetailsListener(e -> {
            try {
                showDetails();
            } catch (SQLException ex) {
                Logger.getLogger(MainMenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.mainMenuView.addLogoutListener(e -> logout());
        

    }

    private void showDetails() throws SQLException{ // VIEW PROFILE
        //check if currentUser has been loaded
        if (users.currentUser != null) {
            mainMenuView.setDetails(users.currentUser.getName(),
                    users.currentUser.getStudentID(),
                    users.currentUser.getEmail(),
                    users.currentUser.getPhone());
                    showMyBookings();
            //go to view profile tab
            mainMenuView.jTabbedPane1.setSelectedIndex(5);
        } else {
            mainMenuView.displayError("ERROR");
        }
    }
    
    public void showMyBookings() throws SQLException{
        //refresh user bookings
        users.refreshUserBookings();
        //remove all elements from gui list
        mainMenuView.listModel1.removeAllElements();
        //re-add all elements from gui list
        for(String booking : users.getBookingList()){
            mainMenuView.listModel1.addElement(booking);
        }
    
    }

    public void display() {
        mainMenuView.setVisible(true);
    }

    public void logout() {
        mainMenuView.dispose();
        //clear user details
        users.currentUser = new userInfo();
        //reset ID and PW fields
        loginView.IDField.setText("");
        loginView.passwordField.setText("");

        loginView.setVisible(true);

    }

}
