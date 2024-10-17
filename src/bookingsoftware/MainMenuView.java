/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package bookingsoftware;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import javax.swing.*;

/**
 *
 * @author jmone
 */
public class MainMenuView extends JFrame {

    private JButton createBookingButton = new JButton("Create Booking");
    private JButton cancelBookingButton = new JButton("Cancel Booking");
    private JButton searchDateButton = new JButton("Search by Date");
    private JButton searchRoomButton = new JButton("Search by Room");
    private JButton detailsButton = new JButton("My Details");
    private JButton logoutButton = new JButton("Logout");
    private JLabel bookingLabel = new JLabel("Booking");
    private JLabel searchLabel = new JLabel("Search");
    private JLabel detailsLabel = new JLabel("Details");
    private static final int HEIGHT = 400;
    private static final int WIDTH = 650;

    public MainMenuView() {
        super("Main Menu");

        //set window position to middle of screen
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - WIDTH / 2, dim.height / 2 - HEIGHT / 2);

        /*
        setLayout(new GridLayout(3, 1));
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        add(createBookingButton);
        add(searchButton);
        add(detailsButton);
        add(cancelBookingButton);
        add(logoutButton);
         */
        setLayout(new BorderLayout());
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        try {
            JPanel northPanel = new JPanel();

            //image
            BufferedImage myPicture = ImageIO.read(new File("resources/AUT_Logo_New.jpg"));
            Image resizedImage = myPicture.getScaledInstance(200, 100, Image.SCALE_SMOOTH);
            JLabel picLabel = new JLabel(new ImageIcon(resizedImage));

            northPanel.add(picLabel);
            northPanel.add(Box.createHorizontalGlue());
            add(northPanel, BorderLayout.NORTH);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

        //labels (north)
//        JPanel northPanel = new JPanel();
//        northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.LINE_AXIS));
//        northPanel.add(bookingLabel);
//        northPanel.add(Box.createHorizontalGlue());
//        northPanel.add(searchLabel);
//        northPanel.add(Box.createHorizontalGlue());
//        northPanel.add(detailsLabel);
//        add(northPanel, BorderLayout.NORTH);
        //west
        JPanel westPanel = new JPanel();
        //pane.setLayout(new BoxLayout(pane,BoxLayout.PAGE_AXIS));
        westPanel.setLayout(new GridLayout(2, 1));
        westPanel.add(createBookingButton);
        westPanel.add(Box.createVerticalGlue());
        westPanel.add(cancelBookingButton);
        add(westPanel, BorderLayout.WEST);
        //westPanel.setBorder(BorderFactory.createLineBorder(Color.yellow));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(2, 1));
        centerPanel.add(searchDateButton);
        centerPanel.add(Box.createVerticalGlue());
        centerPanel.add(searchRoomButton);
        add(centerPanel, BorderLayout.CENTER);
        //add(searchButton, BorderLayout.CENTER);

        JPanel eastPanel = new JPanel();
        eastPanel.setLayout(new GridLayout(2, 1));
        eastPanel.add(detailsButton);
        add(eastPanel, BorderLayout.EAST);
        //add(detailsButton, BorderLayout.EAST);

        JPanel southPanel = new JPanel();
        southPanel.add(logoutButton);
        add(southPanel, BorderLayout.SOUTH);
        //add(logoutButton, BorderLayout.SOUTH);


    }

    public void addCreateBookingListener(ActionListener listener) {
        createBookingButton.addActionListener(listener);
    }

    public void addCancelBookingListener(ActionListener listener) {
        cancelBookingButton.addActionListener(listener);
    }

    public void addSearchDateListener(ActionListener listener) {
        searchDateButton.addActionListener(listener);
    }

    public void addSearchRoomListener(ActionListener listener) {
        searchRoomButton.addActionListener(listener);
    }

    public void addDetailsListener(ActionListener listener) {
        detailsButton.addActionListener(listener);
    }

    public void addLogoutListener(ActionListener listener) {
        logoutButton.addActionListener(listener);
    }

    public void displayError(String error) {
        JOptionPane.showMessageDialog(this, error, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
