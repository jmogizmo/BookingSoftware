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
    
    public boolean addUser(int id, String password){
        if(!users.containsKey(id)){
            users.put(id, new userInfo(id, password));
            return true;
        }
        return false;
    }
    
    public boolean authenticateUser(int id, String password){
        if(users.containsKey(id)){
            return users.get(id).getPassword().equals(password);
        }
        return false;
    }
    
}
