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
    private MyDetailsView myDetailsView;
    //private LoginRegisterView loginView;
    private LoginRegisterView2 loginView;
    private UserManager users;

    public MainMenuController(MainMenuView2 mainMenuView, MyDetailsView myDetailsView, LoginRegisterView2 loginView, UserManager users) {
        this.mainMenuView = mainMenuView;
        this.myDetailsView = myDetailsView;
        this.loginView = loginView;
        this.users = users;
        this.mainMenuView.addDetailsListener(e -> showDetails());
        this.myDetailsView.addBackListener(e -> detailsToMenu());
        this.mainMenuView.addLogoutListener(e -> logout());
        
    }

    private void showDetails() {
        //check if currentUser has been loaded
        if (users.currentUser != null) {
            myDetailsView.setDetails(users.currentUser.getName(),
                    users.currentUser.getStudentID(),
                    users.currentUser.getEmail(),
                    users.currentUser.getPhone());
            mainMenuView.jTabbedPane1.setSelectedIndex(5);
            myDetailsView.setVisible(true);
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
        users.currentUser = null;
        loginView.IDField.setText("");
        loginView.passwordField.setText("");

        loginView.setVisible(true);

    }

    public void detailsToMenu() {

        myDetailsView.dispose();
        mainMenuView.setVisible(true);
        //mainMenuView.toFront();
        //mainMenuView.setEnabled(true);
        //mainMenuView.toFront();

    }
    


}
