/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.CheckboxGroup;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

/**
 *
 * @author jmone
 */
public class BookingView extends JFrame {

    private JButton backButton = new JButton("Back");
    private CheckboxGroup buildingCheckBox = new CheckboxGroup();
    private CheckboxGroup roomCheckBox = new CheckboxGroup();
    private List timeList = new List(10);
    private JButton reserveButton = new JButton("Reserve Room");
    private JLabel buildingLabel = new JLabel("Choose Building");
    private JLabel roomLabel = new JLabel("Choose Room");
    private JLabel timeLabel = new JLabel("Choose Time");

    private static final int HEIGHT = 300;
    private static final int WIDTH = 400;

    public BookingView() {
        super("Create Booking");

        //set window position to middle of screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - WIDTH / 2, dim.height / 2 - HEIGHT / 2);
        //setLayout(new GridLayout(3, 1));
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        //so that its not hard-coded, we
        //need method to access building list from db
        //need method to access room list from db
        //need method to access timeslots from db
        //if not available, time slot should be red
        String[] buildings = {"WG", "WZ", "WA"};
        String[] rooms = {"402", "403", "405", "301", "306", "307", "203", "204", "209"};
        String[] timeslots = {"12:00", "12:30", "13:00", "13:30", "14:00", "14:30", "15:00", "15:30", "16:00",
            "16:30", "17:00", "17:30", "18:00", "18:30", "19:00", "19:30", "20:00"};

        //labels (north)
        JPanel pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.LINE_AXIS));
        pane.add(buildingLabel);
        pane.add(Box.createHorizontalGlue());
        pane.add(roomLabel);
        pane.add(Box.createHorizontalGlue());
        pane.add(timeLabel);
        add(pane, BorderLayout.NORTH);

        //building (west) 
        //this shit still needs to be fixed
        int counter = 0;
        for (String building : buildings) {
            Checkbox buildingBox = new Checkbox(building, buildingCheckBox, false);
            buildingBox.setBounds(30, counter, 200, 133);

            add(buildingBox, BorderLayout.WEST);
            counter += 100;

        }

        //rooms (center)
        
        
        //time slots (east)
        timeList.add("10:00");
        timeList.add("10:30");
        timeList.add("11:00");
        timeList.add("11:30");
        add(timeList, BorderLayout.EAST);

        //buttons (south)
        pane = new JPanel();
        pane.setLayout(new BoxLayout(pane, BoxLayout.LINE_AXIS));
        pane.add(backButton);
        pane.add(Box.createHorizontalGlue());
        pane.add(reserveButton);
        add(pane, BorderLayout.SOUTH);

    }

    public void addBackListener(ActionListener l) {
        backButton.addActionListener(l);
    }
}
