/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

import Interface.*;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jmone
 */
public class Main extends JFrame {

    public static void main(String[] args) {
        //start
        if (DBManager.establishConnection() == 0) {
            SwingUtilities.invokeLater(() -> {

                //load model
                UserManager userManager = new UserManager();

                //load all users from db on startup
                try {
                    userManager.loadUsers();
                    userManager.refreshUserBookings();
                } catch (SQLException ex) {
                    Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                //load views
                LoginRegisterView2 loginView = new LoginRegisterView2();
                MainMenuView2 menuView = new MainMenuView2();
                UserDetailsView2 userDetailsView = new UserDetailsView2();

                //load controllers
                new LoginRegisterController(loginView, userManager, menuView, userDetailsView);
                new MainMenuController(menuView, loginView, userManager);
                new BookingController(menuView, userManager);
                loginView.setVisible(true);
            });
        } else {
            System.err.println("Error Connecting to database. \nPlease try again.");
        }

    }
}
