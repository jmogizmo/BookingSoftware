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
    
    public MainMenuController(MainMenuView mainMenuView, MyDetailsView myDetailsView, userInfo userInfo){
        this.mainMenuView = mainMenuView;
        this.myDetailsView = myDetailsView;
        this.userInfo = userInfo;
        
        this.mainMenuView.addDetailsListener(e -> showDetails());
        this.myDetailsView.addBackListener(e -> myDetailsView.dispose());
        
    }
    
    private void showDetails(){
        userInfo currentUser = userInfo.getCurrentUser();
        if(currentUser != null){
            myDetailsView.setDetails(currentUser.getName(),
                            currentUser.getStudentID(), 
                            currentUser.getEmail(),
                            currentUser.getPhone());
        } else{
            mainMenuView.displayError("ERROR");
        }
    }
    
    public void display(){
        mainMenuView.setVisible(true);
    }
    
}
