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
        //start login
        if (DBManager.establishConnection() == 0) {
            SwingUtilities.invokeLater(() -> {
                LoginRegisterView2 loginView = new LoginRegisterView2();
                UserManager model = new UserManager();
                try { model.loadUsers();
                } catch (SQLException ex) { Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);}
                MainMenuView2 menuView = new MainMenuView2();
                UserDetailsView2 userDetailsView = new UserDetailsView2();
                BookingInfo bookingInfo = new BookingInfo();
                new LoginRegisterController(loginView, model, menuView, userDetailsView);
                new MainMenuController(menuView, loginView, model);
                new BookingController(menuView, bookingInfo);
                loginView.setVisible(true);
            });
        } else {
            System.err.println("Error Connecting to database. \nPlease try again.");
        }

    }
}
