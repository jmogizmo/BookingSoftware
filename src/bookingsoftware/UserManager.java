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
    private Map<String, userInfo> users = new HashMap<>();
    
    public boolean addUser(String name, int id){
        if(!users.containsKey(name)){
            users.put(name, new userInfo(name, id));
            return true;
        }
        return false;
    }
    
    public boolean authenticateUser(String name, int id){
        if(users.containsKey(name)){
            return users.get(name).getStudentID()==id;
        }
        return false;
    }
    
}
