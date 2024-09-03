/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

/**
 *
 * @author jmone
 */
public class ComputerRoom extends roomInfo{
    private int numComputers;
    public String building;
    public int roomCode;
    public boolean isAvailable;
    
    //constructor must include numcomputers
    public ComputerRoom(String building, int roomCode, int numComputers){
        this.building = building;
        this.roomCode = roomCode;
        this.numComputers = numComputers;
    }
    
    public int getNumComputers(){
        return this.numComputers;
    }
    
    public void setNumComputers(int num){
        this.numComputers = num;
    }
}
