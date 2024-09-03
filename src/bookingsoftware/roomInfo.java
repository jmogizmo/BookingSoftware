/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jmone
 */
public class roomInfo extends timeSlot {

    private String building;
    private int roomCode;
    private boolean isAvailable;

    public static String roomsTXTpath = "./resources/rooms.txt";

    //blank constructor
    public roomInfo() {
        this.building = "";
        this.roomCode = 0;
        this.isAvailable = true;
    }

    public roomInfo(String building, int code) {
        this.building = building;
        this.roomCode = code;
        this.isAvailable = true;
    }
    
    //override toString to return proper format
    @Override
    public String toString(){
        return "Building: " + this.building + "\n" +
                "Room: " + this.roomCode;
    }

    public void setBuilding(String b) {
        this.building = b;
    }

    public void setRoomCode(int c) {
        this.roomCode = c;
    }

    /**
     * @return the building
     */
    public String getBuilding() {
        return building;
    }

    /**
     * @return the roomCode
     */
    public int getRoomCode() {
        return roomCode;
    }

    //print room details
    public String printRoom() {
        return (this.building + "-" + this.roomCode);
    }

    public static void readRoom() {
        try {
            // Read and print all rooms in file
            FileReader fr = new FileReader(roomsTXTpath);
            BufferedReader br = new BufferedReader(fr);

            int line;
            // READS UNTIL IT REACHES END OF FILE
            while ((line = br.read()) != -1) {
                // PRINTS EACH LINE
                System.out.print((char) line);
            }
            // CLOSES THE BUFFER
            br.close();
            fr.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        } catch (IOException I) {
            System.out.println("Error reading file: " + I);
        }
    }

    public static void displayRoomByDate(String room, String date) {
        // DISPLAYS A FILTERED OUTPUT OF EACH BOOKING
        try {
            FileReader fr = new FileReader(roomsTXTpath);
            BufferedReader br = new BufferedReader(fr);

            String line = br.readLine();
            // READS FILE UNTIL IT REACHES THE END OR SPECIFIFED ROOM
            while (line != null && !(line.equals(room))) {
                line = br.readLine();
            }
            // READS FILE UNTIL IT REACHES END OR SPECIFIED DATE
            while (line != null && !(line.equals(date))) {
                line = br.readLine();
            }
            // SENDS THE TIMESLOTS OF ROOM TO BE DISPLAYED, IF FOUND
            if (line != null) {
                System.out.println("Displaying time-slots for room " + room + " on " + date);
                displayVacancies(br.readLine(), false);
            } else {
                System.out.println("Error occured when finding specified date.");
            }
            // CLOSES BUFFER
            br.close();
            fr.close();
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        } catch (IOException I) {
            System.out.println("Error reading file: " + I);
        }
    }

    public static void displayVacancies(String slots, boolean vacancy) {
        // FORMATS AND PRINTS ALL TIMESLOTS

        double x = 12;
        System.out.print("[VACANT]: ");
        for (int i = 0; i < slots.length(); i++) {
            // READS EACH SYMBOL IN LINE IF VACANT OR BOOKED
            if (slots.charAt(i) == 'V') {
                if ((x % 1) == 0) {
                    System.out.print(" " + (int) (x) + ":00" + ",");
                } else {
                    System.out.print(" " + (int) (x) + ":30" + ",");
                }
            }
            // INCREASE TIMESLOT TIME
            x = x + 0.5;
            // INCREASE INDEX TO SKIP EMPTY SPACES
            i++;
        }
        System.out.println();
        if (!vacancy) {
            x = 12;
            System.out.print("[BOOKED]: ");
            for (int i = 0; i < slots.length(); i++) {
                if (slots.charAt(i) == 'B') {
                    if ((x % 1) == 0) {
                        System.out.print(" " + (int) (x) + ":00" + ",");
                    } else {
                        System.out.print(" " + (int) (x) + ":30" + ",");
                    }
                }
                // INCREASE TIMESLOT TIME
                x = x + 0.5;
                // INCREASE INDEX TO SKIP EMPTY SPACES
                i++;
            }
        }
    }

    public static void searchByDate(String target) {

        FileIO file = new FileIO(roomsTXTpath);
        ArrayList<String> line = file.readFileToList();

        System.out.println("Displaying rooms for: " + target);

        for (int i = 0; i < line.size(); i++) {

            String s = (Character.toString(line.get(i).charAt(0)) + Character.toString(line.get(i).charAt(1)));
            switch (s) {
                case "WG":
                    System.out.println();
                    System.out.println(line.get(i));
                    break;
                case "WA":
                    System.out.println();
                    System.out.println(line.get(i));
                    break;
                case "WZ":
                    System.out.println();
                    System.out.println(line.get(i));
                    break;
            }

            if (line.get(i).equals(target)) {
                displayVacancies(line.get(i + 1), false);
            }

        }
    }

    public String showMyBookings() {
        // read user bookings for specified booking
        return null;
    }

    public static int isBooked(String slot) {

        //WG-402 20-08-2024 15:30
        FileReader fr = null;
        try {
            fr = new FileReader(roomsTXTpath);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(roomInfo.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

        BufferedReader br = new BufferedReader(fr);
        String[] sp;
        String[] buildRoom;
        String[] bookingSlot;
        //String BKtime;
        try {
            sp = slot.split(" ");
            // {WG-402} {20-08-2024} {15:30}
            buildRoom = sp[0].split("-");
            // buildRoom = {WG} {402}
            //BKtime = sp[3];
            // Bktime = 15:30
            if (sp.length == 3 && buildRoom.length == 2) {
                bookingSlot = new String[]{(buildRoom[0] + buildRoom[1]), sp[1], sp[2]};
            } else {
                System.out.println("Error Occurred: Invalid amount of inputs.");
                return 2;
            }

            String line = br.readLine();
            // READS FILE UNTIL IT REACHES THE END OR SPECIFIFED ROOM
            while (line != null && !(line.equals(bookingSlot[0]))) {
                line = br.readLine();
            }
            // READS FILE UNTIL IT REACHES END OR SPECIFIED DATE
            while (line != null && !(line.equals(bookingSlot[1]))) {
                line = br.readLine();
            }

            if (line == null) {
                System.out.println("pass");
                return 2;
            } else {
                if ((formatTimeSlots(br.readLine(), sp[2]) == true)) {
                    return 1;
                } else {
                    return 0;
                }
            }

        } catch (IOException e) {
            System.out.println("Error Occurred: Specified booking not found.");
            return 2;
        }
        //return 1;
    }

    private static boolean formatTimeSlots(String slots, String target) {
        String currSlot;

        double x = 12;
        for (int i = 0; i < slots.length(); i++) {
            if ((x % 1) == 0) {
                currSlot = ((int) (x) + ":00");
            } else {
                currSlot = ((int) (x) + ":30");
            }

            if (currSlot.equals(target)) {
                switch (slots.charAt(i)) {
                    case 'V':
                        return false;
                    case 'B':
                        return true;
                }
            }
            // INCREASE TIMESLOT TIME
            x = x + 0.5;
            // INCREASE INDEX TO SKIP EMPTY SPACES
            i++;
        }
        return false;
    }

    public static void searchByRoom(String room) {
        FileIO file = new FileIO(roomsTXTpath);
        ArrayList<String> line = file.readFileToList();

        System.out.println("Displaying room: " + room);
        int x = 1;
        for (int i = 0; i < line.size(); i++) {
            //System.out.println(line.get(i));
            if (line.get(i).equals(room)) {
                while (x < 12) {
                    System.out.println(line.get(i + x));

                    displayVacancies(line.get(i + x + 1), false);
                    System.out.println();
                    x += 2;
                }
                return;
            }
        }

    }
}
