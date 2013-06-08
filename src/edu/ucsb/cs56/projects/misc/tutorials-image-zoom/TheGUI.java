package edu.ucsb.cs56.projects.misc.map_gui;
import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
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

/**
 * TheGUI class creates the interface using swing for the image GUI
 * @author Aki Stankoski and Dennis Huynh
 * @author Spencer Pao and Bohan Lin
 *
 */


public class TheGUI{
    //declare the panels and buttons to be accessed from multiple methods  

    JFrame frame            =      new JFrame("Image Zoom Demonstration");//main frame
    JPanel bottomPanel      =      new JPanel();//Bottom subpanels
    JPanel topPanel         =      new JPanel();//Top subpanels
    JButton quit            =      new JButton("Quit");//cancel button for subscreens
    JButton ZoomIn          =      new JButton("Zoom +");
    JButton ZoomOut         =      new JButton("Zoom -");
    JLabel HFHDefaultLabel         =      new JLabel("Image zoom demonstration - HFH");
    int zoomKeeper = 0; //keeps track of the level of zoom. Zoom in and out then either zoom farther in by incrementing 1 or decrementing 1
    int zoomWidth;
    int zoomHeight;
    java.net.URL HFH_URL = getClass().getResource("/HFH.jpg");
    ImageIcon icon = new ImageIcon(HFH_URL);
    ImageIcon defaultIcon = new ImageIcon(HFH_URL);
    NewPanel newPanel = null;
    JLabel HFHlabel = new JLabel(icon);

    /**
     * Sets up the basic display which includes the image, zoom, and quit buttons
     * @exception IOException is thrown
     */

    public void setUpDisplay() throws IOException{   
	
	HFHlabel.setSize(new Dimension(1500,900));
	newPanel = new NewPanel();
	newPanel.addKeyListener(newPanel);
	addTopPanel();
	setButtons();
	addBottomPanel();
	setNewPanel();
	setHFHlabel();
	addToFrame();
    }//end setUpDisplay
    
  
     //Zooms by redrawing the image in newPanel to a different scale.
    class ZoomInActionListener implements ActionListener{
	public void actionPerformed(ActionEvent event){
	    zoomKeeper++;//increments zoomKeeper which controls the level of zoom
	    ZoomOut.setEnabled(true);//enables the zoomout key after the initial zoom. Zooming out in a picture that hasn't been zoomed in on would be pointless.
       	    if(zoomKeeper == 5)
		ZoomIn.setEnabled(false);
	    setZoomValues();
	    setNewPanel();
	    setHFHlabel();
	    addToFrame();
	}
    }

    class ZoomOutActionListener implements ActionListener{
	public void actionPerformed(ActionEvent event){
	    zoomKeeper--;
      	    ZoomIn.setEnabled(true);
	    if(zoomKeeper == 0){
		ZoomOut.setEnabled(false);
		newPanel.setX(0);
		newPanel.setY(0);
		System.out.println("actionlistener" + zoomKeeper);
	    }
	    else if(zoomKeeper > 1)
		ZoomOut.setEnabled(true);
	    setZoomValues();
	    setNewPanel();
	    setHFHlabel();
	    addToFrame();
	}
    }
    
    /**
     *Adds introductory header to top panel
     */
    public void addTopPanel(){
	topPanel.add(HFHDefaultLabel);//adds the label to the top panel
    }

    /**
     *Adds the buttons and their action listeners
     */
    public void setButtons(){
	quit.addActionListener(new QuitActionListener());//adds a new ActionListener to the quit button
	ZoomIn.setPreferredSize(new Dimension(100,50));
	ZoomIn.addActionListener(new ZoomInActionListener());
	ZoomOut.setPreferredSize(new Dimension(100,50));
	ZoomOut.addActionListener(new ZoomOutActionListener());
       	ZoomOut.setEnabled(false);
    }


    /**
     *Produces modified width and length for enlarged image for zoom functionality
     */
    public void setZoomValues(){
	zoomWidth=zoomKeeper*1500; //scales the image width based on zoomKeeper
	zoomHeight=zoomKeeper*900;//scales the image height based on zoomKeeper
    }

    /**
     *Adds elements of bottom panel: quit, zoom-, zoom+ buttons
     */
    public void addBottomPanel(){
        bottomPanel.add(Box.createRigidArea(new Dimension(200,50)));
	bottomPanel.add(quit);//adds a quit button on the panel located at the bottom of the frame
	bottomPanel.add(Box.createRigidArea(new Dimension(200,50)));
	bottomPanel.add(ZoomIn);
	bottomPanel.add(Box.createRigidArea(new Dimension(200,50)));
	bottomPanel.add(ZoomOut);
    }

    /**
     *Resets newPanel for a repaint
     */
    public void setNewPanel(){
	if(newPanel != null) {
	    newPanel.removeAll();
	    newPanel.setBackground(Color.WHITE);//creates new panel for the sample image
	    newPanel.setLayout(new BorderLayout());//sets the new panel to a BorderLayout
	    newPanel.setSize(1000,600);//sets the size of new panel
	}
    }

    /**
     *loads the demo-image (HFH.jpeg) into HFHlabel
     */
    public void setHFHlabel(){
	newPanel.remove(HFHlabel);
	if(zoomKeeper == 0){
	    Image image = defaultIcon.getImage();
	    icon = new ImageIcon(image);
	    HFHlabel = new JLabel(icon);
	    HFHlabel.setSize(new Dimension(1500,900));
	}
	else{
	    Image image = icon.getImage();
	    Image Zoomed = image.getScaledInstance(zoomWidth, zoomHeight, Image.SCALE_SMOOTH);//zooms in the image
	    icon = new ImageIcon(Zoomed);
	    HFHlabel = new JLabel(icon);
	    HFHlabel.setSize(new Dimension(1500,900));//sets size of resized label
	}
    }

    /**
     *Adds contents to frame
     */
    public void addToFrame(){
	frame.getContentPane().add(BorderLayout.NORTH, topPanel);//adds the top panel including the label to the top of the frame
	frame.getContentPane().add(BorderLayout.CENTER,newPanel);//adds the new panel on the center of the frame
	frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);//adds the bottom panel, or the pannel with the cancel button, to the bottom of the frame
	frame.setSize(1000,625);//sets the size of the frame 
	frame.setBackground(Color.WHITE);//sets the background color of the frame to white
	frame.setVisible(true);//enables us to see the frame
    }
	
    //Code to implement panning that doesn't work. Left here for future progress.
    class NewPanel extends JPanel implements KeyListener{
	private int x;
	private int y;
	
	public NewPanel(){
	    this.removeAll();
	    this.setLayout(new BorderLayout());//BorderLayout manager automatically centers our image
	    this.setBackground(Color.WHITE);
	    this.setSize(1000,600);
	    setFocusable(true);
	    setFocusTraversalKeysEnabled(false);
	}
	
	public void setX(int x){
	    this.x = x;
	}
	public void setY(int y){
	    this.y = y;
	}

	public void paintComponent(Graphics g)
	{
	    super.paintComponent(g);
	    Graphics2D g2d = (Graphics2D) g;
	    g2d.drawImage(icon.getImage(),x,y,this);
	}
	
	public void keyPressed(KeyEvent key)
	{
	    switch (key.getKeyCode())
		{
		case KeyEvent.VK_RIGHT:
		    x = x - 10;
		    break;
		case KeyEvent.VK_LEFT:
		    x = x + 10;
		    break;
		case KeyEvent.VK_DOWN:
		    y = y - 10;
		    break;
		case KeyEvent.VK_UP:
		    y = y + 10;
		    break;
		}
	    this.repaint();
	}
	public void keyReleased(KeyEvent key){} // keylistener
	public void keyTyped(KeyEvent key){}    // is abstract
    }
    
    // Quit button action listener. Exits on-click.
    class QuitActionListener implements ActionListener{//the action listener when the quit button is pressed
	public void actionPerformed(ActionEvent event){//the action that is performed after pressing quit
	    System.exit(0);
	    
	}
    }//end CancelActionListener
    
} //end class

