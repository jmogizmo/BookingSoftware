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
    private userInfo userInfo;
    private UserManager users;
    
    public MainMenuController(MainMenuView mainMenuView, MyDetailsView myDetailsView, userInfo userInfo){
        this.mainMenuView = mainMenuView;
        this.myDetailsView = myDetailsView;
        this.userInfo = userInfo;
        
        this.mainMenuView.addDetailsListener(e -> showDetails());
<<<<<<< HEAD
        this.myDetailsView.addBackListener(e -> myDetailsView.dispose());
        
    }
    
    private void showDetails(){
        if(currentUser != null){
            myDetailsView.setDetails(currentUser.getName(),
                            currentUser.getStudentID(), 
                            currentUser.getEmail(),
                            currentUser.getPhone());
        } else{
=======
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
>>>>>>> parent of 6e0195a (Merge pull request #7 from jmogizmo/test)
            mainMenuView.displayError("ERROR");
        }
    }
    
    public void display(){
        mainMenuView.setVisible(true);
    }
<<<<<<< HEAD
    
=======

    public void logout() {
        mainMenuView.dispose();
        //clear user details
        users.currentUser = null;
        loginView.IDField.setText("");
        loginView.passwordField.setText("");

        loginView.setVisible(true);

    }
    

>>>>>>> parent of 6e0195a (Merge pull request #7 from jmogizmo/test)
}
