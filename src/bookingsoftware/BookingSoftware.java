
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package bookingsoftware;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 *
 * @author dante
 */
public class BookingSoftware {

    static userInfo user = new userInfo();

    static boolean control = false;
    static boolean loggedOn = true;
    static Scanner scan = new Scanner(System.in);

    public void clrConsole() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }

    //login
    public void login() throws IOException {

        System.out.println("Please enter your name & student ID as your "
                + "login details:");
        try {
            // GATHER LOGIN DETAILS
            System.out.println("Name: ");
            String nameLogin = scan.nextLine();
            System.out.println("Student ID: ");
            String IDLogin = scan.nextLine();

            // CHECKS IF USER EXISTS
            if (!(userInfo.checkExistingUser(nameLogin, IDLogin))) {
                // REDIRECTS TO MAKE USER INFORMATION
                clrConsole();
                System.out.println("User does not exist...\nRedirecting to registration");
                user.getUserInfo();
                clrConsole();
            } else {

                clrConsole();
                user.retrieveExisting(nameLogin, IDLogin);
                System.out.println("Successfully found user.\n"
                        + "Welcome back " + user.getName() + ".\n");

            }
        } catch (InputMismatchException E) {
            System.out.println("Error Occurred: " + E.getMessage());
        }
    }

    public int chooseBuilding(String building) {
        int code = 0;
        while (true) {
            try {
                switch (building) {
                    case "WG":
                        System.out.println("Select a room: [402] [403] [405]");
                        code = Integer.parseInt(scan.nextLine());
                        if (code == 402 || code == 403 || code == 405) {
                            return code;
                        }
                        break;
                    case "WZ":
                        System.out.println("Select a room: [301] [306] [307]");
                        code = Integer.parseInt(scan.nextLine());
                        if (code == 301 || code == 306 || code == 307) {
                            return code;
                        }
                        break;
                    case "WA":
                        System.out.println("Select a room: [203] [204] [209]");
                        code = Integer.parseInt(scan.nextLine());
                        if (code == 203 || code == 204 || code == 209) {
                            return code;
                        }
                        break;
                    default:
                        return 0;
                }
                System.out.println("Invalid room number. Please try again.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }
    }

    public String chooseTime() {
        String[] times = {"12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00", "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00"};

        System.out.println("Select a time slot: ");
        System.out.println(String.join(", ", times));
        String targetTime;
        targetTime = scan.nextLine();

        //find a match from array
        for (String t : times) {
            if (t.equals(targetTime)) {
                return targetTime;
            }
        }
        return "0";
    }

    public String chooseDate() {
        scan.nextLine();

        String[] dates = {"19-08-2024", "20-08-2024", "21-08-2024", "22-08-2024", "23-08-2024", "24-08-2024"};
        System.out.println("Select a date: ");
        System.out.println(String.join(", ", dates));

        String targetDate;
        targetDate = scan.nextLine();

        //find a match from array
        for (String d : dates) {
            if (d.equals(targetDate)) {
                return targetDate;
            }
        }
        return "0";
    }

    public void bookingMenu() throws IOException {
        boolean bookingDone = false;
        while (!bookingDone) {
            BookingInfo b = new BookingInfo();

            System.out.println("(1) CREATE BOOKING");
            System.out.println("(2) CANCEL BOOKING");
            System.out.println("(3) BACK");

            char input;
            input = scan.nextLine().charAt(0);
            switch (input) {
                case '1':
                    //choose building
                    System.out.println("Select a building: [WG] [WZ] [WA]");
                    String building = scan.nextLine();
                    //choose room
                    int contr = chooseBuilding(building);
                    if (contr == 0) {
                        System.out.println("Wrong Input. Going back to Booking Menu.");
                        bookingDone = false;
                        break;
                    }

                    roomInfo room1 = new roomInfo(building, contr);

                    //choose date
                    String date = chooseDate();
                    if (date.equals("0")) {
                        System.out.println("Wrong Input. Going back to Booking Menu.");
                        break;
                    }
                    //choose time
                    String time = chooseTime();
                    if (time.equals("0")) {
                        System.out.println("Wrong Input. Going back to Booking Menu.");
                        break;
                    }

                    timeSlot timeslot1 = new timeSlot(time);
                    timeslot1.setDate(date);

                    System.out.println(timeslot1.getTime() + " " + timeslot1.getDate());

                    int bookingID = b.createBooking(user, room1, timeslot1);
                    if (bookingID == 0) {
                        System.out.println("Booking not successful. Please enter your booking details again.");
                        break;
                    }
                    System.out.println("Booking Successful. Your bookingID is: " + bookingID + ". Returning to main menu...");
                    bookingDone = true;
                    break;
                case '2':
                    //CANCEL BOOKING
                    System.out.println("Please enter booking ID:");
                    int BookingID = scan.nextInt();
                    b.cancelBooking(BookingID, user);
                    scan.nextLine();
                    break;
                case '3':
                    //back to main menu
                    return;
                default:
                    clrConsole();
                    System.out.println("Wrong input, try again...");
                    break;
            }
        }

    }

    public void searchMenu() {
        while (true) {
            System.out.println("(1) BY DATE"
                    + "\n(2) BY ROOM"
                    + "\n(3) BACK");

            char input;

            input = scan.nextLine().charAt(0);
            switch (input) {
                case '1':
                    System.out.println("Enter a date: 19-08-2024 to 24-08-2024");
                    String targetDate = scan.nextLine();
                    clrConsole();
                    roomInfo.searchByDate(targetDate);
                    System.out.println("\nPress Enter to continue.");
                    scan.nextLine();
                    clrConsole();
                    break;
                case '2':
                    //search by room
                    System.out.println("Enter a room: e.g. \'WZ301\'");
                    System.out.println("WG: 402,403,405"
                            + "\nWZ: 301,306,307"
                            + "\nWA: 203,204,209");
                    String targetRoom = scan.nextLine();
                    clrConsole();
                    roomInfo.searchByRoom(targetRoom);
                    System.out.println("\nPress Enter to continue.");
                    scan.nextLine();
                    clrConsole();
                    break;
                case '3':
                    return;
                default:
                    clrConsole();
                    System.out.println("Wrong Input. try again...");
                    searchMenu();
                    break;
            }
        }
    }

    public void displayMenu() {
        System.out.println("---MAIN MENU---"
                + "\n(1) CREATE/CANCEL BOOKING"
                + "\n(2) SEARCH"
                + "\n(3) MY DETAILS"
                + "\n(x) QUIT PROGRAM");
    }

    public void start() {
        boolean done = false;
        char input;
        while (!done) {
            displayMenu();
            try {
                input = scan.nextLine().charAt(0);
                switch (input) {
                    //BOOKINGS
                    case '1':
                        clrConsole();
                        bookingMenu();
                        break;
                    //SEARCH
                    case '2':
                        clrConsole();
                        searchMenu();
                        break;
                    //MY DETAILS
                    case '3':
                        clrConsole();
                        System.out.println(String.format("%-10s %-10s %-20s %-15s", "[NAME]", "[ID]", "[EMAIL]", "[PHONE]"));
                        System.out.println(user.userDetails());
                        System.out.println("\nPress Enter to continue.");
                        scan.nextLine();
                        clrConsole();
                        break;
                    //QUIT
                    case 'x':
                        done = true;
                        return;
                    //WRONG INPUT
                    default:
                        clrConsole();
                        System.out.println("Wrong input, try again...");
                        break;
                }
            } catch (Exception E) {
                System.out.println(E.getMessage());
            }
        }
    }

    public static void main(String[] args) throws IOException {

        //initialise booking software   
        //creating special event room in wg
        //EventRoom eventroom1 = new EventRoom("WG", 101, 50);

        //creating computer room in wz
        //ComputerRoom comproom1 = new ComputerRoom("WZ", 102, 21);

        BookingSoftware x = new BookingSoftware();
        x.login();
        x.start();
        scan.close();
        System.out.println("Program stopped.");
    }

}
