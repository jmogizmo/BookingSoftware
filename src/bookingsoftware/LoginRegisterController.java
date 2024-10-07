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
    private LoginRegisterView view;
    private UserManager model;
    
    public LoginRegisterController(LoginRegisterView view, UserManager model){
        this.view = view;
        this.model = model;
        this.view.addLoginListener(e -> login());
        this.view.addRegisterListener(e -> register());
    }
    
    private void login(){
        int id = view.getID();
        String password = view.getPassword();
        if(model.authenticateUser(id, password)){
            //successful login
            //proceed to main program
        } else {
            //unsuccessful login
            view.displayError("Invalid login details.");
        }
    }
    
    private void register(){
        int id = view.getID();
        String password = view.getPassword();
        if(model.addUser(id, password)){
            //successful registration
            //proceed to main program
        } else{
            //unsuccesful registration
            //user already exists
            view.displayError("User already exists.");
        }
    }
    
}
