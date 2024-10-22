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
<<<<<<< HEAD

    public boolean addUser(int id, String name, String password, String email, long phone) {
        if (id == 0 || name == null || password == null || email == null || phone == 0) {
=======
    public userInfo currentUser;

    public int addUser(int id, String name, String password, String email, long phone) {
        if (id == 0 || name.equals("") || password.equals("") || email.equals("") || phone == 0) {
>>>>>>> parent of 6e0195a (Merge pull request #7 from jmogizmo/test)
            System.out.println("addUser returned false. empty fields");
           return false;
        }
<<<<<<< HEAD
=======
        //check if id and phone are valid
        if(id == -1){
            System.out.println("id error");
            return -2;
        }
        if (phone == -1){
            System.out.println("Phone number error");
            return -3;
        }
>>>>>>> parent of 6e0195a (Merge pull request #7 from jmogizmo/test)
        if (!users.containsKey(id)) {
            users.put(id, new userInfo(id, name, password, email, phone));
            System.out.println("user added. returned true");
            return true;
        }
        System.out.println("other error");
<<<<<<< HEAD
        return false;
=======
        return -1;
>>>>>>> parent of 6e0195a (Merge pull request #7 from jmogizmo/test)
    }

    public boolean authenticateUser(int id, String password) {
        if (users.containsKey(id)) {
            return users.get(id).getPassword().equals(password);
        }
        return false;
    }

<<<<<<< HEAD
=======
    //placeholder method - REPLACE FOR FINAL PROGRAM
    public void loadUsers(){
        currentUser = new userInfo(99, "admin", "admin123", "adminEmail", 123456789);
        users.put(99, currentUser);
    }
    
>>>>>>> parent of 6e0195a (Merge pull request #7 from jmogizmo/test)
    //TODO: method to load users from database into the hashmap
}
