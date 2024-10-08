/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

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
            LoginRegisterView loginView = new LoginRegisterView();
            UserManager model = new UserManager();
            MainMenuView menuView = new MainMenuView();
            new LoginRegisterController(loginView, model, menuView);
            loginView.setVisible(true);
        });
    }
}
