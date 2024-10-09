/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

import javax.swing.JOptionPane;

/**
 *
 * @author jmone
 */
public class MainMenuController {

    private MainMenuView mainMenuView;
    private MyDetailsView myDetailsView;
    private LoginRegisterView loginView;
    private UserManager users;

    public MainMenuController(MainMenuView mainMenuView, MyDetailsView myDetailsView, LoginRegisterView loginView ) {
        this.mainMenuView = mainMenuView;
        this.myDetailsView = myDetailsView;
        this.loginView = loginView;
        this.mainMenuView.addDetailsListener(e -> showDetails());
        this.myDetailsView.addBackListener(e -> myDetailsView.dispose());
        this.mainMenuView.addLogoutListener(e -> logout());
    }

    private void showDetails() {
        /*if (users.currentUser != null) {
            myDetailsView.setDetails(users.currentUser.getName(),
                    users.currentUser.getStudentID(),
                    users.currentUser.getEmail(),
                    users.currentUser.getPhone());*/
            mainMenuView.dispose();
            myDetailsView.setVisible(true);
        //} else {
            mainMenuView.displayError("ERROR");
        //}
    }

    public void display() {
        mainMenuView.setVisible(true);
    }
    
    public void logout(){
        mainMenuView.dispose();
        users.currentUser = null;
        loginView.setVisible(true);
    }

}
