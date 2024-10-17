/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

import Interface.*;
import javax.swing.SwingUtilities;
import javax.swing.JFrame;


/**
 *
 * @author jmone
 */
public class Main extends JFrame {

    public static void main(String[] args) {
        //start login

        SwingUtilities.invokeLater(() -> {
            LoginRegisterView2 loginView = new LoginRegisterView2();
            UserManager model = new UserManager();
            model.loadUsers();
            MainMenuView2 menuView = new MainMenuView2();
            UserDetailsView2 userDetailsView = new UserDetailsView2();
            MyDetailsView myDetailsView = new MyDetailsView();
            BookingView bookingView = new BookingView();
            CancelBookingView cancelBookingView = new CancelBookingView();
            BookingInfo bookingInfo = new BookingInfo();
            new LoginRegisterController(loginView, model, menuView, userDetailsView, myDetailsView);
            new MainMenuController(menuView, myDetailsView, loginView, model);
            new BookingController(menuView, bookingView, cancelBookingView, bookingInfo);
            loginView.setVisible(true);
        });
    }
}
