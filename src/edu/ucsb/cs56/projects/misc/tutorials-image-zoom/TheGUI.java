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
import java.awt.Point;  
import java.awt.Rectangle; 
import java.awt.event.MouseAdapter;  
import java.awt.event.MouseEvent;
/**
 * TheGUI class creates the interface using swing for the image GUI
 * @author Aki Stankoski and Dennis Huynh
 * @author Spencer Pao and Bohan Lin
 *
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
    JButton quit            =      new JButton("Quit");//cancel button for subscreens
    JButton ZoomIn          =      new JButton("Zoom +");
    JButton ZoomOut         =      new JButton("Zoom -");
 
    JLabel HFHLabel         =      new JLabel("HFH - Harold Frank Hall");
    int zoomKeeper = 0;
   
    

    //building information
    String HFHInfo =//directions to get to Harold Frank Hall from storke tower
	"Generic instructions\n";


    //function to set up the basic display
    /**
     * Sets up the basic display which includes the image, zoom, and quit buttons
     * @exception IOException is thrown
     */
    public void setUpDisplay() throws IOException{

	newPanel.setBackground(Color.WHITE);//creates new panel for the directions to Harold Frank Hall
	newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));//sets the new panel to a BoxLayout
	newPanel.setSize(800,625);//sets the size of new panel

	JTextArea HFHTA = new JTextArea(HFHInfo);//creates a new space for text for directions
	HFHTA.setEditable(false);//makes the new text area NOT editable
	HFHTA.setLineWrap(true);//allows the lines to go to the next line if the current on is full
	HFHTA.setWrapStyleWord(true);//allows long words to break off and continue in the proceeding line
	JScrollPane HFHScroll = new JScrollPane(HFHTA);//creates a new scrollable widget
	HFHScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//allows for scrolling on that widget
	HFHTA.setPreferredSize(new Dimension(200,600));//sets the size of the text area
	infoPanel.add(HFHScroll);//adds the scrolling
        bottomPanel.add(Box.createRigidArea(new Dimension(200,50)));
	bottomPanel.add(quit);//adds a cancel button on the panel located at the bottom of the frame

	newPanel.add(bottomPanel);//adds the bottom panel onto the new panel that has the directions to Harold Frank Hall
	topPanel.add(HFHLabel);//adds the label to the top panel
	quit.addActionListener(new QuitActionListener());//adds a new ActionListener to the Cancel button
	java.net.URL HFH_URL = getClass().getResource("/HFH.jpg");//getClass().getResource loads the HFH.jpg image, which has a line that directs from storke to the location
	ImageIcon icon = new ImageIcon(HFH_URL);
	JLabel HFHlabel = new JLabel(icon);//Creates a new label for the loaded image
	
	ZoomIn.setPreferredSize(new Dimension(100,50));
	bottomPanel.add(Box.createRigidArea(new Dimension(200,50)));
	bottomPanel.add(ZoomIn);
	ZoomIn.addActionListener(new ZoomInActionListener());

	ZoomOut.setPreferredSize(new Dimension(100,50));
	bottomPanel.add(Box.createRigidArea(new Dimension(200,50)));
	bottomPanel.add(ZoomOut);
	ZoomOut.addActionListener(new ZoomOutActionListener());

	newPanel.setMaximumSize(new Dimension(200,200));
	
	newPanel.add(HFHlabel);//adds the image label onto the new panel
	frame.getContentPane().add(BorderLayout.EAST, infoPanel);//puts the panel with the direction text to the right side of the frame
	frame.getContentPane().add(BorderLayout.NORTH, topPanel);//adds the top panel including the label to the top of the frame
	frame.getContentPane().add(BorderLayout.CENTER,newPanel);//adds the new panel on the center of the frame
	frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);//adds the bottom panel, or the pannel with the cancel button, to the bottom of the frame
	frame.setSize(1000,625);//sets the size of the frame 
	


	frame.setBackground(Color.WHITE);//sets the background color of the frame to white
	frame.setVisible(true);//enables us to see the frame
    }//end setUpHomeScreen
    
    /**
     * Zooms by redrawing the image in newPanel to a different scale.
     */
    
    
    class ZoomInActionListener implements ActionListener{
	public void actionPerformed(ActionEvent event){
	    zoomKeeper++;
	    int zoomWidth=zoomKeeper*1500; //scales the image based on zoomKeeper
	    int zoomHeight=zoomKeeper*900;
	    newPanel.removeAll();
	    
	    newPanel.setLayout(new BorderLayout());//BorderLayout manager automatically centers our image
	    newPanel.setBackground(Color.WHITE);
	    newPanel.setSize(1500,900);
	    newPanel.add(bottomPanel);
	   
	    java.net.URL HFH_URL = getClass().getResource("/HFH.jpg");
	    ImageIcon icon = new ImageIcon(HFH_URL);
	    Image image = icon.getImage();
	   
	    Image ZoomedIn = image.getScaledInstance(zoomWidth, zoomHeight, Image.SCALE_SMOOTH);//zooms in the image
		    
	    ImageIcon finalIcon = new ImageIcon(ZoomedIn);
	    JLabel HFHlabel = new JLabel(finalIcon);
	    HFHlabel.setLocation(-750,-450);
	
	    HFHlabel.setSize(new Dimension(1500,900));//sets size of resized label

	    newPanel.add(HFHlabel);//adds the image label onto the new panel	    	   
	    frame.getContentPane().add(BorderLayout.CENTER,newPanel);
	    frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
	    frame.setSize(1000,625);
	    frame.setBackground(Color.WHITE);
	    frame.setVisible(true);
	}
    }

    class ZoomOutActionListener implements ActionListener{
	public void actionPerformed(ActionEvent event){
	    zoomKeeper--;
	    int zoomWidth=1500/zoomKeeper; //scales the image based on zoomKeeper
	    int zoomHeight=900/zoomKeeper;
	    newPanel.removeAll();
	    
	    newPanel.setLayout(new BorderLayout());//BorderLayout manager automatically centers our image
	    newPanel.setBackground(Color.WHITE);
	    newPanel.setSize(1500,900);
	    newPanel.add(bottomPanel);
	   
	    java.net.URL HFH_URL = getClass().getResource("/HFH.jpg");
	    ImageIcon icon = new ImageIcon(HFH_URL);
	    Image image = icon.getImage();
	   
	    Image ZoomedOut = image.getScaledInstance(zoomWidth, zoomHeight, Image.SCALE_SMOOTH);//zooms in the image
		    
	    ImageIcon finalIcon = new ImageIcon(ZoomedOut);
	    JLabel HFHlabel = new JLabel(finalIcon);
	    HFHlabel.setLocation(-750,-450);
	
	    HFHlabel.setSize(new Dimension(1500,900));//sets size of resized label

	    newPanel.add(HFHlabel);//adds the image label onto the new panel	    	   
	    frame.getContentPane().add(BorderLayout.CENTER,newPanel);
	    frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
	    frame.setSize(1000,625);
	    frame.setBackground(Color.WHITE);
	    frame.setVisible(true);
	}
    }



    /*
    class ZoomOutActionListener implements ActionListener{

	public void actionPerformed(ActionEvent event){
	    bottomPanel.removeAll();
	    newPanel.removeAll();

	    newPanel.setBackground(Color.WHITE);
	    newPanel.setLayout(new BoxLayout(newPanel, BoxLayout.Y_AXIS));
	    newPanel.setSize(800,625);

	    bottomPanel.add(Box.createRigidArea(new Dimension(200,50)));
	    bottomPanel.add(quit);
	    newPanel.add(bottomPanel);

	    quit.addActionListener(new QuitActionListener());

	    java.net.URL HFH_URL = getClass().getResource("/HFH.jpg");
	    ImageIcon icon = new ImageIcon(HFH_URL);
	    JLabel HFHlabel = new JLabel(icon);
	     
	    ZoomIn.setPreferredSize(new Dimension(100,50));
	    bottomPanel.add(Box.createRigidArea(new Dimension(200,50)));
	    bottomPanel.add(ZoomIn);
	    ZoomIn.addActionListener(new ZoomInActionListener());
	    newPanel.add(HFHlabel);//adds the image onto newPanel
	    frame.getContentPane().add(BorderLayout.CENTER,newPanel);
	    frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
	    frame.setSize(1000,625);
	    frame.setBackground(Color.WHITE);
	    frame.setVisible(true);
	}
    }*/
	  
    //action listener class for the cancel button
    
    /**
     * Quit button action listener. Exits on-click.
     */
    class QuitActionListener implements ActionListener{//the action listener when the quit button is pressed
	public void actionPerformed(ActionEvent event){//the action that is performed after pressing quit
	    System.exit(0);
	    
	}
    }//end CancelActionListener


    
    
} //end class

