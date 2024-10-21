/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jmone
 */
public class UserManager {

    private Map<Integer, userInfo> users = new HashMap<>();
    public userInfo currentUser;

    public int addUser(int id, String name, String password, String email, long phone) {
        if (id == 0 || name.equals("") || password.equals("") || email.equals("") || phone == 0) {
            System.out.println("addUser returned false. empty fields");
           return 0;
        }
        //check if id and phone are valid
        if(id == -1){
            System.out.println("id error");
            return -2;
        }
        if (phone == -1){
            System.out.println("Phone number error");
            return -3;
        }
        if (!users.containsKey(id)) {
            currentUser = new userInfo(id, name, password, email, phone);
            users.put(id, currentUser);
            System.out.println("user added. returned true");
            return 1;
        }
        System.out.println("other error");
        return -1;
    }

    public boolean authenticateUser(int id, String password) {
        loadUsers();
        if (users.containsKey(id)) {
            this.currentUser = users.get(id);
            return users.get(id).getPassword().equals(password);
        }
        return false;
    }

    //placeholder method - REPLACE FOR FINAL PROGRAM
    public void loadUsers(){
        currentUser = new userInfo(99, "admin", "admin123", "adminEmail", 123456789);
        users.put(99, currentUser);
    }
    
    //TODO: method to load users from database into the hashmap
    
}
