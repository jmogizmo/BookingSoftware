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

    public boolean addUser(int id, String name, String password, String email, long phone) {
        if (id == 0 || name == null || password == null || email == null || phone == 0) {
            System.out.println("addUser returned false. empty fields");
           return false;
        }
        if (!users.containsKey(id)) {
            currentUser = new userInfo(id, name, password, email, phone);
            users.put(id, currentUser);
            System.out.println("user added. returned true");
            return true;
        }
        System.out.println("other error");
        return false;
    }

    public boolean authenticateUser(int id, String password) {
        if (users.containsKey(id)) {
            return users.get(id).getPassword().equals(password);
        }
        return false;
    }

    //TODO: method to load users from database into the hashmap
}
