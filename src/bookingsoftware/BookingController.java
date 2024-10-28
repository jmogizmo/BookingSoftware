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
    private BookingInfo bookingInfo;
    private DBManager db;

    public BookingController(MainMenuView2 menuView, BookingInfo bookingInfo) {
        this.menuView = menuView;
        this.bookingInfo = bookingInfo;
        this.menuView.addConfirmBookingListener(e -> {
            try {
                createBooking();
            } catch (SQLException ex) {
                Logger.getLogger(BookingController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        this.menuView.addCancelBookingListener(e -> cancelBooking());
    }

    public void showMyBookings(int ID) {
        //read all bookings belonging to a specific studentID
    }

    public void showTimes() {
        //read times from bookings database
        //if booked, make the time red
        //if available, make the time green
    }

    public void cancelBooking() {

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
        String room = getSelectedButton(x, menuView.wgButtonGroup, menuView.wzButtonGroup, menuView.waButtonGroup);
        String time = menuView.timeList.getSelectedValue();
        String date = menuView.dateList.getSelectedValue();

        DBManager.createBooking(UserManager.currentUser, building, room, time, date);
    }

}
