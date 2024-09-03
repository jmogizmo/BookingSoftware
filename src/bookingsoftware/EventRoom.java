/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

/**
 *
 * @author jmone
 */
public class EventRoom extends roomInfo {

    //subclass of roomInfo but has extra variable: capacity (# of ppl)
    public String building;
    public int roomCode;
    public boolean isAvailable;
    private int capacity;
    
    public EventRoom(String building, int roomCode, int capacity){
        this.building = building;
        this.roomCode = roomCode;
        this.capacity = capacity;
    }

    public int getCapacity(){
        return this.capacity;
    }
    
    public void setCapacity(int num){
        this.capacity = num;
    }
    
}
