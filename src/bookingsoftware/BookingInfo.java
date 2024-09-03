/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author jmone
 */
public class BookingInfo {

    private final int bookingID;
    private userInfo user = new userInfo();
    private roomInfo room = new roomInfo();
    private timeSlot timeslot = new timeSlot();
    public static String bookingFile = "./resources/bookedRooms.txt";

    //empty default constructor
    public BookingInfo() {
        this.bookingID = 0;
        this.user = new userInfo();
        this.room = new roomInfo();
        this.timeslot = new timeSlot();
    }

    public BookingInfo(int bookingID, userInfo user, roomInfo room, timeSlot timeslot) {
        this.bookingID = bookingID;
        this.user = user;
        this.room = room;
        this.timeslot = timeslot;
    }

    //override toString to print in proper format
    @Override
    public String toString() {
        return "Booking ID: " + this.bookingID + "\n"
                + "Name: " + this.user.getName() + "\n"
                + "Room: " + this.room.getBuilding() + this.room.getRoomCode() + "\n"
                + "Date and Time: " + this.timeslot.getDate() + this.timeslot.getTime();
    }

    //generates a booking
    public int createBooking(userInfo user, roomInfo room, timeSlot timeslot) {

        //arraylist of bookings in file
        FileIO f1 = new FileIO(bookingFile);

        ArrayList<String> bookings = f1.readFileToList();

        //iterate through arraylist
        //room and time are taken
        String slot;
        slot = room.getBuilding() + "-" + room.getRoomCode() + " " + timeslot.getDate() + " " + timeslot.getTime();

        switch (roomInfo.isBooked(slot)) {
            case 1:
                System.out.println("Room is already booked at that time.");
                return 0;
            case 2:
                System.out.println("1Error Occurred: Specified booking not found.");
                return 0;
        }

        //has gone through the whole list and no duplicate found
        //assign a new empty ID number and make a string with all the data
        String newBooking;
        int num = bookings.size() + 1;
        newBooking = num + " " + user.getName() + " " + user.getStudentID() + " " + room.getRoomCode() + " " + timeslot.getTime();

        //write to file
        try {
            f1.writeToFile(newBooking);
        } catch (IOException e) {
            System.out.println("Error in writing file.");
        }

        return num;
    }

    //set bookingID to null, free up room and overwrite file
    public void cancelBooking(int bookingID, userInfo user) throws IOException {

        //arraylist of bookings in file
        FileIO f1 = new FileIO(bookingFile);
        ArrayList<String> bookings = f1.readFileToList();
        try {

            boolean bookingFound = false;

            // iterate through
            for (int i = 0; i < bookings.size(); i++) {
                String booking = bookings.get(i);
                String[] part = booking.split(" ");

                // bookingID matches file and user is authorized
                if (part[0].equals(Integer.toString(bookingID)) && part[2].equals(Integer.toString(user.getStudentID()))) {
                    bookings.set(i, "null"); // Mark booking as "null"
                    bookingFound = true;
                    System.out.println("Booking cancelled. Returning to Main Menu...");
                    break;
                }
            }

            if (bookingFound) {
                // write the whole arraylist to file
                f1.writeAllToFile(bookings);
            } else {
                System.out.println("Error. No Authorization to cancel this booking.");
            }

        } catch (IOException e) {
            System.out.println("ERROR: IO Exception");
        }
    }

    //displays the specified booking using a student id
    public void printBooking(int newStudentID) {

        //instantiate and load bookings to an arraylist
        FileIO f1 = new FileIO(bookingFile);
        ArrayList<String> bookings = f1.readFileToList();

        //iterate through bookings
        for (String booking : bookings) {

            String[] parts = booking.split(" ");
            String studentID = String.valueOf(newStudentID);

            //found a match, display
            if (parts[2].equals(studentID)) {
                System.out.println(booking);
                return;
            }
        }
        //no match error
        System.out.println("No bookings found.");
    }

}
