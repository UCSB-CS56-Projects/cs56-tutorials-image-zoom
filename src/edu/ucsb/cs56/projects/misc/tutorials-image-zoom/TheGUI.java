package edu.ucsb.cs56.projects.misc.map_gui;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Dimension;
/**
 * TheGUI class creates the interface using swing for image GUI. 
 * @author Aki Stankoski and Dennis Huynh
 */


public class TheGUI{
    //declare the panels and buttons to be accessed from multiple methods  

    JFrame frame            =      new JFrame("UCSB Campus Map");//main frame
    JPanel thePanel         =      new JPanel();//Back homescreen panel
    JPanel newPanel         =      new JPanel();// New panel when button is clicked
    JPanel leftPanel        =      new JPanel();//Left homescreen panel
    JPanel rightPanel       =      new JPanel();//Right homescreen panel
    JPanel bottomPanel      =      new JPanel();//Bottom subpanels
    JPanel topPanel         =      new JPanel();//Top subpanels
    JPanel infoPanel        =      new JPanel();//info panel on subpanels
    JButton T387            =      new JButton("387");//button for Trailer 387
    JButton quit            =      new JButton("Quit");//cancel button for subscreens
    JButton ZoomIn          =      new JButton("Zoom In");
    JButton ZoomOut         =      new JButton("Zoom Out");

    JLabel T387Label        =      new JLabel("387 - Trailer 387");
 
    
    //building information
    String T387Info =//directions to get to trailer 387 from storke tower
	"1 Start by making your way towards the University Center.\n"
	+"\n"        
	+"2 Once you get to the University Center make a left.\n"                           
	+"\n" 
	+"3 Coninue walking straight until you get to the bike path. "
	+"Cross the bike path and cross the parking lot.\n"
	+"\n" 
	+"4 Trailer 387 will be the building parallel to the parking"
	+ "lot on the right side of the parking lot.";
    
    //function to set up the homescreen
    public void setUpHomeScreen() throws IOException{

	newPanel.setBackground(Color.WHITE);//creates new panel for the directions to Trailer 387
	newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));//sets the new panel to a BoxLayout
	newPanel.setSize(800,625);//sets the size of new panel

	JTextArea T387TA = new JTextArea(T387Info);//creates a new space for text for directions
	T387TA.setEditable(false);//makes the new text area NOT editable
	T387TA.setLineWrap(true);//allows the lines to go to the next line if the current on is full
	T387TA.setWrapStyleWord(true);//allows long words to break off and continue in the proceeding line
	JScrollPane T387Scroll = new JScrollPane(T387TA);//creates a new scrollable widget
	T387Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//allows for scrolling on that widget
	T387TA.setPreferredSize(new Dimension(200,600));//sets the size of the text area
	infoPanel.add(T387Scroll);//adds the scrolling
	bottomPanel.add(Box.createRigidArea(new Dimension(200,50)));
	bottomPanel.add(quit);//adds a cancel button on the panel located at the bottom of the frame
	newPanel.add(bottomPanel);//adds the bottom panel onto the new panel that has the directions to Trailer 387
	topPanel.add(T387Label);//adds the label to the top panel
	quit.addActionListener(new QuitActionListener());//adds a new ActionListener to the Cancel button
	java.net.URL T387_URL = getClass().getResource("/387.jpg");//getClass().getResource loads the 387.jpg image, which has a line that directs from storke to the location
	ImageIcon icon = new ImageIcon(T387_URL);
	JLabel T387label = new JLabel(icon);//Creates a new label for the loaded image
	
	ZoomIn.setPreferredSize(new Dimension(100,50));
	bottomPanel.add(Box.createRigidArea(new Dimension(200,50)));
	bottomPanel.add(ZoomIn);
	ZoomIn.addActionListener(new ZoomInActionListener());

	newPanel.setMaximumSize(new Dimension(200,200));
	
	newPanel.add(T387label);//adds the image label onto the new panel
	frame.getContentPane().add(BorderLayout.EAST, infoPanel);//puts the panel with the direction text to the right side of the frame
	frame.getContentPane().add(BorderLayout.NORTH, topPanel);//adds the top panel including the label to the top of the frame
	frame.getContentPane().add(BorderLayout.CENTER,newPanel);//adds the new panel on the center of the frame
	frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);//adds the bottom panel, or the pannel with the cancel button, to the bottom of the frame
	frame.setSize(1000,625);//sets the size of the frame 
	


	frame.setBackground(Color.WHITE);//sets the background color of the frame to white
	frame.setVisible(true);//enables us to see the frame
    }//end setUpHomeScreen
    
    //function to clear the main frame
    /**
     * Removes all components of the frame, useful before switching panels
     */
    public void guiRemoveAll() {
    	////this wipes the frame clean, use before switching panels
	leftPanel.removeAll();
	rightPanel.removeAll();
	bottomPanel.removeAll();
	topPanel.removeAll();
	infoPanel.removeAll();
	thePanel.removeAll();
	newPanel.removeAll();
	frame.getContentPane().removeAll();
	frame.getContentPane().remove(thePanel);
	frame.getContentPane().remove(newPanel);
	frame.validate();
	frame.repaint();
    }//end guiRemoveAll
    

    class ZoomInActionListener implements ActionListener{
	public void actionPerformed(ActionEvent event){

	    guiRemoveAll();
	    newPanel.setLayout(null); //added 8:51am
	    newPanel.setBackground(Color.WHITE);
	    newPanel.setSize(1000,625);
	    
	    JTextArea T387TA = new JTextArea(T387Info);
	    T387TA.setEditable(false);
	    T387TA.setLineWrap(true);
	    T387TA.setWrapStyleWord(true);
	    JScrollPane T387Scroll = new JScrollPane(T387TA);
	    T387Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    T387TA.setPreferredSize(new Dimension(200,600));
	    infoPanel.add(T387Scroll);
	    bottomPanel.add(Box.createRigidArea(new Dimension(200,50)));
	    bottomPanel.add(quit);
	    newPanel.add(bottomPanel);
	    topPanel.add(T387Label);
	    quit.addActionListener(new QuitActionListener());
	    java.net.URL T387_URL = getClass().getResource("/387.jpg");
	    
	    ImageIcon icon = new ImageIcon(T387_URL);
	    Image image = icon.getImage();
	    Image ZoomedIn = image.getScaledInstance(2000, 1200, Image.SCALE_SMOOTH);
	    ImageIcon finalIcon = new ImageIcon(ZoomedIn);
	    JLabel T387label = new JLabel(finalIcon);
	  
	    ZoomOut.setPreferredSize(new Dimension(100,50));
	    bottomPanel.add(Box.createRigidArea(new Dimension(200,50)));
	    bottomPanel.add(ZoomOut);
	    ZoomOut.addActionListener(new ZoomOutActionListener());
     
	    T387label.setLocation(-550,-900);
	    T387label.setSize(new Dimension(2000,2000));

	    newPanel.add(T387label);
		    
	    frame.getContentPane().add(BorderLayout.EAST, infoPanel);
	    frame.getContentPane().add(BorderLayout.NORTH, topPanel);
	    frame.getContentPane().add(BorderLayout.CENTER,newPanel);
	    frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
	    frame.setSize(1000,625);
	    frame.setBackground(Color.WHITE);
	    frame.setVisible(true);
	}
    }

   class ZoomOutActionListener implements ActionListener{
      	 public void actionPerformed(ActionEvent event){
	    guiRemoveAll();
	    newPanel.setBackground(Color.WHITE);
	    newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
	    newPanel.setSize(800,625);
	    
	    JTextArea T387TA = new JTextArea(T387Info);
	    T387TA.setEditable(false);
	    T387TA.setLineWrap(true);
	    T387TA.setWrapStyleWord(true);
	    JScrollPane T387Scroll = new JScrollPane(T387TA);
	    T387Scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    T387TA.setPreferredSize(new Dimension(200,600));
	    infoPanel.add(T387Scroll);
	    bottomPanel.add(Box.createRigidArea(new Dimension(200,50)));
	    bottomPanel.add(quit);
	    newPanel.add(bottomPanel);
	    topPanel.add(T387Label);
	    quit.addActionListener(new QuitActionListener());
	    java.net.URL T387_URL = getClass().getResource("/387.jpg");
	    ImageIcon icon = new ImageIcon(T387_URL);
	    JLabel T387label = new JLabel(icon);
	    
	    
	    ZoomIn.setPreferredSize(new Dimension(100,50));
	    bottomPanel.add(Box.createRigidArea(new Dimension(200,50)));
	    bottomPanel.add(ZoomIn);
	    ZoomIn.addActionListener(new ZoomInActionListener());

	    newPanel.add(T387label);
	    frame.getContentPane().add(BorderLayout.EAST, infoPanel);
	    frame.getContentPane().add(BorderLayout.NORTH, topPanel);
	    frame.getContentPane().add(BorderLayout.CENTER,newPanel);
	    frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
	    frame.setSize(1000,625);
	    frame.setBackground(Color.WHITE);
	    frame.setVisible(true);
       
	    }
    }




    //action listener class for the cancel button
    class QuitActionListener implements ActionListener{//the action listener when the cancel button is pressed
	public void actionPerformed(ActionEvent event){//the action that is performed after pressing cancel on one of the direction guis
	    System.exit(0);
	    
	}
    }//end CancelActionListener


    
    
} //end class

