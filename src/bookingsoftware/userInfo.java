/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author dante
 */
public class userInfo {

    private userInfo user;

    private String password;
    private String name;
    private String email;
    private long phone;
    private int studentID;

    public static String usersTXTpath = "./resources/users.txt";

    // Override toString to return properly formatted string
    @Override
    public String toString() {
        return "Name: " + this.name + "\n"
                + "Student ID: " + this.studentID + "\n"
                + "Email: " + this.email + "\n"
                + "Phone Num: " + this.phone;
    }

    public userInfo(int id, String password) {
        this.studentID = id;
        this.password = password;
    }

    public userInfo() {
        this.studentID = 0;
        this.name = null;
        this.email = null;
        this.phone = 0;
        this.password = "";
    }

    public userInfo(String name, int id, String password) {
        this.name = name;
        this.studentID = id;
        this.password = password;
        this.email = "";
        this.phone = 0;
    }

    public userInfo(int id, String name, String password, String email, long phone) {
        this.studentID = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    public userInfo(int id, String name, String email, long phone) {
        this.studentID = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return (email + "@email.com");
    }

    public long getPhone() {
        return phone;
    }

    public int getStudentID() {
        return studentID;
    }

    public String getPassword() {
        return password;
    }

    public userInfo getCurrentUser(int id) {
        return this.user;
    }

    public void writeUserInfo() throws IOException {
        // Copy this user's info to user.txt

        PrintWriter pw = null;
        try {
            FileWriter fr = new FileWriter(usersTXTpath, true);
            BufferedWriter br = new BufferedWriter(fr);
            pw = new PrintWriter(br);
            // APPENDS USER INFORMATION INTO USER.TXT
            pw.println(this.name + " "
                    + this.studentID + " "
                    + this.email + " "
                    + this.phone);

            // CLOSES BUFFERS
            pw.close();
            br.close();
            fr.close();

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void getUserInfo() throws IOException {
        boolean registered = false;
        while (!registered) {
            Scanner scan = new Scanner(System.in);

            // REGISTERS USER DETAILS
            try {
                System.out.println("Enter Name:");
                this.setName(scan.nextLine());

                System.out.println("Enter Student ID:");
                this.setStudentID(scan.nextInt());

                System.out.println("Enter Contact Email:");
                scan.nextLine();
                this.setEmail(scan.nextLine());

                System.out.println("Enter phone: (Format: \'1234567890\')");
                this.setPhone(scan.nextLong());

                this.writeUserInfo();
                scan.close();

                break;

            } catch (InputMismatchException E) {
                System.out.println("Error occured: " + E);
            }
        }

    }

    public String userDetails() {
        String output = String.format("%-10s %-10s %-20s %-15s", this.name, this.studentID, this.getEmail(),
                this.phone);
        return output;
    }

    public static boolean checkExistingUser(String name, String ID) throws FileNotFoundException, IOException {
        // CHECKS IF USER ALREADY EXISTS IN USER.TXT DATABASE
        FileReader fr = new FileReader(usersTXTpath);
        BufferedReader inputStream = new BufferedReader(fr);

        String line = inputStream.readLine();
        // READS USER.TXT UNTIL END OF FILE
        while (line != null) {
            // SPLITS EACH LINE INTO A ARRAY
            String[] array = line.split(" ");

            // CHECKS IF THE FIRST TWO USER ENTRIES (name & ID) MATCHES.
            if (name.equals(array[0]) && ID.equals(array[1])) {
                inputStream.close();
                return true;
            }
            line = inputStream.readLine();
        }
        fr.close();
        inputStream.close();
        return false;
    }

    public void retrieveExisting(String u, String id) throws FileNotFoundException, IOException {
        FileReader fr = new FileReader(usersTXTpath);
        BufferedReader inputStream = new BufferedReader(fr);

        String line = inputStream.readLine();
        // READS USER.TXT UNTIL END OF FILE

        while (line != null) {

            // SPLITS EACH LINE INTO A ARRAY
            String[] array = line.split(" ");

            // CHECKS IF THE FIRST TWO USER ENTRIES (name & ID) MATCHES.
            if (u.equals(array[0]) && id.equals(array[1])) {

                this.setName(array[0]);
                this.setStudentID(Integer.parseInt(array[1]));
                this.setEmail(array[2]);
                this.setPhone((long) Long.valueOf(array[3]));
                inputStream.close();
                return;
            }
            line = inputStream.readLine();
        }
        System.out.println("User not found");
        fr.close();
        inputStream.close();
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(Long phone) {
        this.setPhone((long) phone);
    }

    /**
     * @param studentID the studentID to set
     */
    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    /**
     * @param user the user to set
     */
    public void setUser(userInfo user) {
        this.user = user;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(long phone) {
        this.phone = phone;
    }
}
