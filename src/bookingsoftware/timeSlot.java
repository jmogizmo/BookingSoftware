/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

/**
 *
 * @author jmone
 */
public class timeSlot {
    private String time;
    //date format: DD-MM-YY
    private String date;
    
    public timeSlot()
    {
        this.time = "00:00";
        this.date = "00-00-00";
    }
    
        public timeSlot(String time)
    {
        this.time = time;
        this.date = "01-01-24";
    }
         
    public timeSlot(String time, String date)
    {
        this.time = time;
        this.date = date;
    }
    
    
    
    /**
     * @return the startTime
     */
    public String getTime() {
        return time;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }
    
    //returns the duration of the timeslot in hours
    public String getDuration(){
        return "30 Minutes";
    }

    /**
     * @param time the time to set
     */
    public void setTime(String time) {
        this.time = time;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }
}