/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

import Interface.MainMenuView2;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dante Leyting
 */
public class SearchController {

    private final MainMenuView2 menuView;
    private String[] availableTimes;

    public SearchController(MainMenuView2 menuView) {
        this.menuView = menuView;
        this.menuView.addConfirmSearchButton(e -> {
            try {
                search();
            } catch (SQLException ex) {
                Logger.getLogger(SearchController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
    }

    public void search() throws SQLException {
        //access building combo box
        String building = (String) menuView.buildingCombo.getSelectedItem();
        //update room combo box according to selected building
        
        //access room combo box
        String room = (String) menuView.roomCombo.getSelectedItem();
        //access date combo box
        String date = (String) menuView.dateCombo.getSelectedItem();

        //update list of available times
        availableTimes = DBManager.returnSearch(building, room, date);
        //display list of available times
        menuView.listModel3.removeAllElements();
        for (String time : availableTimes) {
            System.out.println(time);
            menuView.listModel3.addElement(time);
        }
    }

    

}
