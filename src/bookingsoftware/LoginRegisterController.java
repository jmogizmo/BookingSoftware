/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

import Interface.*;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jmone
 */
public class LoginRegisterController {

    //private LoginRegisterView loginView;
    private LoginRegisterView2 loginView;
    private UserManager model;
    private MainMenuView2 menuView;
    private UserDetailsView2 userDetailsView;
    public static userInfo currentUser;

    public LoginRegisterController(LoginRegisterView2 view, UserManager model, MainMenuView2 menuView, UserDetailsView2 userDetailsView) {
        this.loginView = view;
        this.model = model;
        this.menuView = menuView;
        this.userDetailsView = userDetailsView;
        this.loginView.addLoginListener(e -> {
            try {
                login();
            } catch (SQLException ex) {
                Logger.getLogger(LoginRegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.loginView.addRegisterListener(e -> register());
        this.userDetailsView.addSaveDetailsListener(e -> {
            try {
                saveUserDetails();
            } catch (SQLException ex) {
                Logger.getLogger(LoginRegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.loginView.addPasswordFieldListener(e -> {
            try {
                login();
            } catch (SQLException ex) {
                Logger.getLogger(LoginRegisterController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.userDetailsView.addBackListener(e -> backToLogin());
    }

    private void login() throws SQLException {
        //extract ID input
        int id = loginView.getID();
        //extract PW input
        String password = loginView.getPassword();
        //id non-numeric
        if (id == -1) {
            loginView.displayError("Student ID must be numeric.");
        }
        //successful login
        if (model.authenticateUser(id, password)) {
            //proceed to main program
            loginView.dispose();
            menuView.setVisible(true);
        //unsuccessful login
        } else {
            loginView.displayError("Invalid login details.");
        }
    }

    private void register() {
        loginView.dispose();
        userDetailsView.setVisible(true);
    }

    private void saveUserDetails() throws SQLException {
        //extract user entered details
        int id = userDetailsView.getID();
        String name = userDetailsView.getName();
        String password = userDetailsView.getPassword();
        String email = userDetailsView.getEmail();
        long phone = userDetailsView.getPhoneNumber();

        //register the user
        int check = model.addUser(id, name, password, email, phone);
        switch (check) {
            case 1:
                //user added successfully
                userDetailsView.dispose();
                loginView.setVisible(true);
                break;

            case -1:
                //error user already exists.
                loginView.displayError("User already exists.");
                break;
            case -2:
                //ID non-numeric
                loginView.displayError("Student ID must be numeric");
                break;
            case -3:
                //phone non-numeric
                loginView.displayError("Phone number must be numeric");
                break;
            default:
                //addUser returned 0
                //error not all fields completed
                loginView.displayError("Please complete all details");
                break;
        }
    }

    private void backToLogin() {
        //back button
        userDetailsView.dispose();
        loginView.setVisible(true);
    }

}
