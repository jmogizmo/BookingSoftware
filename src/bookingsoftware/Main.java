/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

import javax.swing.SwingUtilities;

/**
 *
 * @author jmone
 */
public class Main {

    public static void main(String[] args) {
        //start login
        SwingUtilities.invokeLater(() -> {
            LoginRegisterView view = new LoginRegisterView();
            UserManager model = new UserManager();
            new LoginRegisterController(view, model);
            view.setVisible(true);
        });

    }
}
