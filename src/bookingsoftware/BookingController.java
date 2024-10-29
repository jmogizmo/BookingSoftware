/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

import Interface.*;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

/**
 *
 * @author jmone
 */
public class BookingController {

    private MainMenuView2 menuView;
    private UserManager userManager;

    public BookingController(MainMenuView2 menuView, UserManager userManager) {
        this.menuView = menuView;
        this.userManager = userManager;
        
        this.menuView.addConfirmBookingListener(e -> {
            try {
                createBooking();
            } catch (SQLException ex) {
                Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.menuView.addCancelBookingListener(e -> {
            try {
                cancelBooking();
            } catch (SQLException ex) {
                Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.menuView.addCancelSelectedBookingListener(e -> {
            try {
                cancelSelectedBooking();
            } catch (SQLException ex) {
                Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void cancelBooking() throws SQLException {
        //refresh user bookinglist from database
        userManager.refreshUserBookings();
        
        //clear gui list
        menuView.listModel2.removeAllElements();
        
        //re-add everything onto gui list
        for (String booking : userManager.getBookingList()) {
            menuView.listModel2.addElement(booking);
        }
    }

    public void cancelSelectedBooking() throws SQLException {
        //detect selected string from list
        String bookingToCancel = menuView.cancelBookingList.getSelectedValue();
        //extrtact bookingID
        String[] parts = bookingToCancel.split(" ");
        //parse boooking into DBmanager cancel booking method
        int value = DBManager.cancelBooking(parts[0]);
        if (value == 0) {
            menuView.displayMessage("Booking has been Cancelled!");
        } else {
            menuView.displayError("Error");
        }
        //refresh and update the list
        cancelBooking();
    }

    public String getSelectedButton(int index, ButtonGroup bg1, ButtonGroup bg2, ButtonGroup bg3) {
        ButtonGroup[] buttonGroups = {bg1, bg2, bg3};

        //search through every radiobutton in each buttongroup to find the selected one
        for (Enumeration<AbstractButton> buttons = buttonGroups[index].getElements(); buttons.hasMoreElements();) {
            AbstractButton button = buttons.nextElement();

            if (button.isSelected()) {
                //return the text if it is selected
                return button.getText();
            }

        }

        return "";
    }

    public void createBooking() throws SQLException {
        //extract selected building
        int x = menuView.buildingTabs.getSelectedIndex();
        String building;
        switch (x) {
            case 0:
                building = "WG";
                break;
            case 1:
                building = "WZ";
                break;
            case 2:
                building = "WA";
                break;
            default:
                building = "";
                break;
        }
        //extract selected room
        String room = getSelectedButton(x, menuView.wgButtonGroup, menuView.wzButtonGroup, menuView.waButtonGroup);
        //extract selected time
        String time = menuView.timeList.getSelectedValue();
        //extract selected date
        String date = menuView.dateList.getSelectedValue();

        //check if booking is available
        switch (DBManager.returnisBooked(time, building, room, date)) {
            //booking is available
            case 0:
                //make booking
                DBManager.createBooking(UserManager.currentUser, building, room, time, date);
                menuView.displayMessage("Booking Successful!");
                //update bookinglist
                userManager.refreshUserBookings();
                break;
            //error already boooked
            case 1:
                menuView.displayError("Booking not available");
                break;
            //other error
            default:
                menuView.displayError("unknown ERROR");
                break;
        }

    }

}
