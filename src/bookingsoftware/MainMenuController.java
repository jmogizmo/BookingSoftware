/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

import Interface.*;
import javax.swing.JOptionPane;

/**
 *
 * @author jmone
 */
public class MainMenuController<E> {

    private MainMenuView2 mainMenuView;
    //private LoginRegisterView loginView;
    private LoginRegisterView2 loginView;
    private UserManager users;

    public MainMenuController(MainMenuView2 mainMenuView, LoginRegisterView2 loginView, UserManager users) {
        this.mainMenuView = mainMenuView;
        this.loginView = loginView;
        this.users = users;
        this.mainMenuView.addDetailsListener(e -> showDetails());
        this.mainMenuView.addLogoutListener(e -> logout());

    }

    private void showDetails() {
        //check if currentUser has been loaded
        if (users.currentUser != null) {
            mainMenuView.setDetails(users.currentUser.getName(),
                    users.currentUser.getStudentID(),
                    users.currentUser.getEmail(),
                    users.currentUser.getPhone());

            mainMenuView.jTabbedPane1.setSelectedIndex(5);
        } else {
            mainMenuView.displayError("ERROR");
        }
    }

    public void display() {
        mainMenuView.setVisible(true);
    }

    public void logout() {
        mainMenuView.dispose();
        //clear user details
        users.currentUser = new userInfo();
        loginView.IDField.setText("");
        loginView.passwordField.setText("");

        loginView.setVisible(true);

    }

}
