/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author jmone
 */
public class UserManager {

    private Map<Integer, userInfo> users = new HashMap<>();
    public userInfo currentUser = null;
    //DBManager db = new DBManager();

    public int addUser(int id, String name, String password, String email, long phone) throws SQLException {
        //some fields are empty
        if (id == 0 || name.equals("") || password.equals("") || email.equals("") || phone == 0) {
            System.out.println("addUser returned false. empty fields");
            return 0;
        }
        //check if id and phone are valid
        if (id == -1) {
            System.out.println("id error");
            return -2;
        }
        if (phone == -1) {
            System.out.println("Phone number error");
            return -3;
        }
        //check if user is in the database
        if (!users.containsKey(id)) {
            currentUser = new userInfo(id, name, password, email, phone);
            users.put(id, currentUser);
                // (student_id, first_name, email, phone, password, event)
            String appendUser = id + ", '"+name+ "', '"+email+ "', "+phone+ ", '"+password+ "', false";
            
            DBManager.appendToField("USERINFO", appendUser);
            System.out.println("user added. returned true");
            return 1;
        }
        //User already exists
        System.out.println("User already exists");
        return -1;
    }

    public boolean authenticateUser(int id, String password) throws SQLException {
        loadUsers();
        if (users.containsKey(id)) {
            this.currentUser = users.get(id);
            return users.get(id).getPassword().equals(password);
        }
        return false;
    }
    public void loadUsers() throws SQLException {

        for (Map.Entry<Integer, userInfo> entry : DBManager.returnAllUsers().entrySet()) {
            Integer studentId = entry.getKey();
            userInfo u = entry.getValue();

            // Put the key-value pair into userMap
            users.put(studentId, u);
        }
    }
}
