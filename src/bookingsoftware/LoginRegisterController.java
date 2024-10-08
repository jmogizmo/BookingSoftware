/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

/**
 *
 * @author jmone
 */
public class LoginRegisterController {
    private LoginRegisterView loginView;
    private UserManager model;
    private MainMenuView menuView;
    public static userInfo currentUser;
    
    public LoginRegisterController(LoginRegisterView view, UserManager model, MainMenuView menuView){
        this.loginView = view;
        this.model = model;
        this.menuView = menuView;
        
        this.loginView.addLoginListener(e -> login());
        this.loginView.addRegisterListener(e -> register());
    }
    
    private void login(){
        int id = loginView.getID();
        String password = loginView.getPassword();
        if(model.authenticateUser(id, password)){
            //successful login
            //proceed to main program
            loginView.dispose();
            menuView.setVisible(true);
        } else {
            //unsuccessful login
            loginView.displayError("Invalid login details.");
        }
    }
    
    private void register(){
        int id = loginView.getID();
        String password = loginView.getPassword();
        //id is not numeric
        if(id == -1){
            loginView.displayError("Student ID should be numeric.");
        }
        else if((model.addUser(id, password))&&(model.authenticateUser(id, password))){
            //successful registration
            //proceed to main program
            loginView.dispose();
            menuView.setVisible(true);
        } 
        else{
            //unsuccesful registration
            //user already exists
            loginView.displayError("User already exists.");
        }
    }
    
}
