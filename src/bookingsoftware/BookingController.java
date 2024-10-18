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
    
    public void cancelBooking(){
//        menuView.dispose();
//        cancelBookingView.setVisible(true);
        
        
    }
    
    private void createBooking(){
        //bookingView.setVisible(true);
        
//        menuView.remove(menuView.menuPanel);
//        menuView.menuPanel.setVisible(false);
//        menuView.menuText.setVisible(false);
//        menuView.add(menuView.createBookingPanel);
//        menuView.createBookingPanel.setVisible(true);
//        menuView.validate();
//        System.out.println("create booking pressed");
        
    }
    
}
