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
 
    JLabel HFHLabel         =      new JLabel("Image zoom demonstration - HFH");
    int zoomKeeper = 0; //keeps track of the level of zoom. Zoom in and out then either zoom farther in by incrementing 1 or decrementing 1

    

    //building information
    String HFHInfo =//directions to get to Harold Frank Hall from storke tower
	"Generic instructions\n";


    //function to set up the basic display
    /**
     * Sets up the basic display which includes the image, zoom, and quit buttons
     * @exception IOException is thrown
     */

    public void setUpDisplay() throws IOException{
	newPanel.setBackground(Color.WHITE);//creates new panel for the sample image
	newPanel.setLayout(new BorderLayout());//sets the new panel to a BorderLayout
	newPanel.setSize(1000,600);//sets the size of new panel
        bottomPanel.add(Box.createRigidArea(new Dimension(200,50)));
	bottomPanel.add(quit);//adds a quit button on the panel located at the bottom of the frame
	topPanel.add(HFHLabel);//adds the label to the top panel
	quit.addActionListener(new QuitActionListener());//adds a new ActionListener to the quit button

	java.net.URL HFH_URL = getClass().getResource("/HFH.jpg");//getClass().getResource loads the example image: HFH.jpg
	ImageIcon icon = new ImageIcon(HFH_URL);
	JLabel HFHlabel = new JLabel(icon);//Creates a new label for the loaded image

	//NewPanel newPanel = new NewPanel(HFHlabel);
	newPanel.add(HFHlabel);

	ZoomIn.setPreferredSize(new Dimension(100,50));
	bottomPanel.add(Box.createRigidArea(new Dimension(200,50)));
	bottomPanel.add(ZoomIn);
	ZoomIn.addActionListener(new ZoomInActionListener());
	ZoomOut.setPreferredSize(new Dimension(100,50));
	bottomPanel.add(Box.createRigidArea(new Dimension(200,50)));
	bottomPanel.add(ZoomOut);
	ZoomOut.addActionListener(new ZoomOutActionListener());
       	ZoomOut.setEnabled(false);

	frame.getContentPane().add(BorderLayout.NORTH, topPanel);//adds the top panel including the label to the top of the frame
	frame.getContentPane().add(BorderLayout.CENTER,newPanel);//adds the new panel on the center of the frame
	frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);//adds the bottom panel, or the pannel with the cancel button, to the bottom of the frame
	frame.setSize(1000,625);//sets the size of the frame 
	frame.setBackground(Color.WHITE);//sets the background color of the frame to white
	frame.setVisible(true);//enables us to see the frame
    }//end setUpDisplay
    
    /**
     * Zooms by redrawing the image in newPanel to a different scale.
     */
    
    
    class ZoomInActionListener implements ActionListener{
	public void actionPerformed(ActionEvent event){
	    zoomKeeper++;//increments zoomKeeper which controls the level of zoom
       	    if(zoomKeeper == 5)
		ZoomIn.setEnabled(false);
	    ZoomOut.setEnabled(true);//enables the zoomout key after the initial zoom. Zooming out in a picture that hasn't been zoomed in on would be pointless.
	    int zoomWidth=zoomKeeper*1500; //scales the image width based on zoomKeeper
	    int zoomHeight=zoomKeeper*900;//scales the image height based on zoomKeeper
	    newPanel.removeAll();
	    newPanel.setLayout(new BorderLayout());//BorderLayout manager automatically centers our image
	    newPanel.setBackground(Color.WHITE);
	    newPanel.setSize(1000,600);

	    java.net.URL HFH_URL = getClass().getResource("/HFH.jpg");
	    ImageIcon icon = new ImageIcon(HFH_URL);
	    Image image = icon.getImage();
	    Image ZoomedIn = image.getScaledInstance(zoomWidth, zoomHeight, Image.SCALE_SMOOTH);//zooms in the image
	    ImageIcon finalIcon = new ImageIcon(ZoomedIn);
	    JLabel HFHlabel = new JLabel(finalIcon);
	    HFHlabel.setSize(new Dimension(1500,900));//sets size of resized label

	    //KeyPanel zoomInKeyPanel = new KeyPanel(HFHlabel);
	    newPanel.add(HFHlabel);
	    //NewPanel newPanel = new NewPanel(HFHlabel);
	    frame.getContentPane().add(BorderLayout.CENTER,newPanel);
	    frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);
	    frame.setSize(1000,625);
	    frame.setBackground(Color.WHITE);
	    frame.setVisible(true);
	}
    }

    class ZoomOutActionListener implements ActionListener{
	public void actionPerformed(ActionEvent event){
	    //NewPanel newPanel;
	    zoomKeeper--;
      	    ZoomIn.setEnabled(true);
	    if(zoomKeeper == 0)
		ZoomOut.setEnabled(false);
	    else if(zoomKeeper > 1)
		ZoomOut.setEnabled(true);
	    int zoomWidth=zoomKeeper*1500; //scales the image based on zoomKeeper
	    int zoomHeight=zoomKeeper*900;
	    newPanel.removeAll();
	    newPanel.setLayout(new BorderLayout());//BorderLayout manager automatically centers our image
	    newPanel.setBackground(Color.WHITE);
	    newPanel.setSize(100,600);

	    java.net.URL HFH_URL = getClass().getResource("/HFH.jpg");
	    ImageIcon icon = new ImageIcon(HFH_URL);
	    if(zoomKeeper == 0){
		JLabel HFHlabel2 = new JLabel(icon);
		HFHlabel2.setSize(new Dimension(1500,900));
		//KeyPanel zoomOutKeyPanel = new KeyPanel(HFHlabel2);
		newPanel.add(HFHlabel2);
		//newPanel = new NewPanel(HFHlabel2);
	    }
	    else{
		Image image = icon.getImage();
		Image ZoomedOut = image.getScaledInstance(zoomWidth, zoomHeight, Image.SCALE_SMOOTH);//zooms in the image
		ImageIcon finalIcon = new ImageIcon(ZoomedOut);
		JLabel HFHlabel = new JLabel(finalIcon);
		HFHlabel.setSize(new Dimension(1500,900));//sets size of resized label
		//KeyPanel zoomOutKeyPanel = new KeyPanel(HFHlabel);
		newPanel.add(HFHlabel);
		//newPanel = new NewPanel(HFHlabel);
	    }
	    frame.getContentPane().add(BorderLayout.CENTER,newPanel);
	    frame.getContentPane().add(BorderLayout.SOUTH,bottomPanel);
	    frame.setSize(1000,625);
	    frame.setBackground(Color.WHITE);
	    frame.setVisible(true);
	}
    }

    class NewPanel extends JPanel implements KeyListener{
	BufferedImage image;
	private int x = 0;
	private int y = 0;

	public NewPanel(JLabel label){
	    this.removeAll();
	    this.setLayout(new BorderLayout());//BorderLayout manager automatically centers our image
	    this.setBackground(Color.WHITE);
	    this.setSize(1000,600);
	    Icon icon = label.getIcon();
       	    image = new BufferedImage(icon.getIconWidth(),icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
	    this.addKeyListener(this);
	    setFocusable(true);
	    setFocusTraversalKeysEnabled(false);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(image,x,y,this);
	}
 
	public void keyPressed(KeyEvent key)
	{
	    switch (key.getKeyCode())
		{
		case KeyEvent.VK_RIGHT:
		    x++;System.out.println("RIGHT");  //debugging output statement
		    break;
		case KeyEvent.VK_LEFT:
		    x--;System.out.println("LEFT");
		    break;
		case KeyEvent.VK_DOWN:
		    y++;System.out.println("DOWN");
		    break;
		case KeyEvent.VK_UP:
		    y--;System.out.println("UP");
		    break;
		}
	    this.repaint();
	}
	public void keyReleased(KeyEvent key){} // keylistener
	public void keyTyped(KeyEvent key){}    // is abstract
    }

    /*
    class KeyPanel extends JPanel implements KeyListener{
	BufferedImage image;
	private int x = 0;
	private int y = 0;
	public KeyPanel(JLabel label){
	    Icon icon = label.getIcon();
       	    image = new BufferedImage(icon.getIconWidth(),icon.getIconHeight(), BufferedImage.TYPE_INT_RGB);
	    this.addKeyListener(this);
	    setFocusable(true);
	    setFocusTraversalKeysEnabled(false);
	}
	
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.drawImage(image,null,x,y);
	}
 
	public void keyPressed(KeyEvent key)
	{
	    switch (key.getKeyCode())
		{
		case KeyEvent.VK_RIGHT:
		    x++;System.out.println("RIGHT");  //debugging output statement
		    break;
		case KeyEvent.VK_LEFT:
		    x--;System.out.println("LEFT");
		    break;
		case KeyEvent.VK_DOWN:
		    y++;System.out.println("DOWN");
		    break;
		case KeyEvent.VK_UP:
		    y--;System.out.println("UP");
		    break;
		}
	    this.repaint();
	}
	public void keyReleased(KeyEvent key){} // keylistener
	public void keyTyped(KeyEvent key){}    // is abstract
    }
*/
    /**
     * Quit button action listener. Exits on-click.
     */
    class QuitActionListener implements ActionListener{//the action listener when the quit button is pressed
	public void actionPerformed(ActionEvent event){//the action that is performed after pressing quit
	    System.exit(0);
	    
	}
    }//end CancelActionListener


    
    
} //end class

