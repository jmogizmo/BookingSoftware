/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;
import Interface.*;

/**
 *
 * @author jmone
 */
public class BookingController {
    private MainMenuView2 menuView;
    private BookingInfo bookingInfo;
    
    public BookingController(MainMenuView2 menuView, BookingInfo bookingInfo){
        this.menuView = menuView;
        this.bookingInfo = bookingInfo;
        this.menuView.addCreateBookingListener(e -> createBooking());
        this.menuView.addCancelBookingListener(e -> cancelBooking());
    }
    
    public void showMyBookings(int ID){
        //read all bookings belonging to a specific studentID
    }
    
    public void showTimes(){
        //read times from bookings database
        //if booked, make the time red
        //if available, make the time green
    }
    
    public void cancelBooking(){
        
        
        
    }
    
    public void createBooking(){
        int building = menuView.buildingTabs.getSelectedIndex();
        switch(building){
            //wg
            case 0:
                
                break;
            //wz
            case 1:
                
                break;
            //wa
            case 2:
                
                break;
        }
        
    }
    
}
