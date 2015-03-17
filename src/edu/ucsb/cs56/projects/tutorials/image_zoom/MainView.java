package edu.ucsb.cs56.projects.tutorials.image_zoom;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * @author Aki Stankoski and Dennis Huynh
 * @author Spencer Pao and Bohan Lin
 * @author Xinzhe Wang and Shuai Lang
 * @author Andrew Tran and Eric Swenson
 *
 */
public class MainView {
    // main frame and panels managed in MainView.java
    JFrame frame = new JFrame(Constants.FRAME_TITLE);
    ZoomPanel zoomPanel = new ZoomPanel();
    JPanel zoomControlPanel = new JPanel();

    MainImage mainImage;//model taken by mainView
    JLabel imageLabel;
    ImageIcon imageIcon;

    // button objects managed by zoomControlPanel
    JButton quitButton = new JButton("Quit");
    JButton zoomInButton = new JButton("Zoom +");
    JButton zoomOutButton = new JButton("Zoom -");

    /**
     * Constructor for the MainView class which sets up the main image display
     * @param mainImage a mainImage model reference
     */
    public MainView(MainImage mainImage) 
    {
        this.mainImage = mainImage;
        frame.setPreferredSize(new Dimension(1500,900));
        frame.setResizable(false);
        setZoomValues();
        setZoomPanel();
        setZoomControlPanel();
        zoomPanel.addKeyListener(zoomPanel);
        setMainImage();
    }
	
    /**
     * setZoomValues() sets the current zoomValues in the mainImage of this mainView
     */
    public void setZoomValues() 
    {
        mainImage.zoomWidth = mainImage.zoomMagnitude * Constants.MAIN_IMAGE_BASE_WIDTH;//scales width
        mainImage.zoomHeight = mainImage.zoomMagnitude * Constants.MAIN_IMAGE_BASE_HEIGHT;//scales height
    }
    
    /**
     * setZoomPanel() resets the zoomablePanel
     */
    public void setZoomPanel() 
    {
        if(zoomPanel != null) {
            zoomPanel.removeAll();
            zoomPanel.setBackground(Color.WHITE);//creates new panel for the sample image
            zoomPanel.setLayout(new BorderLayout());//sets the new panel to a BorderLayout
            zoomPanel.setSize(1250,750);//sets the size of new panel
        }
    }

    /**
     * 
     */
    public void setMainImage() 
    {
    	if(imageLabel != null)
	    zoomPanel.remove(imageLabel);
		imageIcon = mainImage.getResizedImage();
		imageLabel = new JLabel(imageIcon);
		imageLabel.setSize(new Dimension(1500,900));
		//zoomPanel.add(imageLabel);
		System.out.println("[SETMAINIMAGE]zoomWidth="+mainImage.zoomWidth+",zoomHeight="+mainImage.zoomHeight);
    }

    /**
     *
     */
    public void setZoomControlPanel() 
    {
		// add buttons to zoomControlPanel
		zoomControlPanel.add(Box.createRigidArea(new Dimension(200,50)));
		zoomControlPanel.add(quitButton);
		quitButton.setFocusable(false);
	
        zoomInButton.setPreferredSize(new Dimension(100,50));
		zoomControlPanel.add(Box.createRigidArea(new Dimension(200,50)));
		zoomControlPanel.add(zoomInButton);
		zoomInButton.setFocusable(false);

        zoomOutButton.setPreferredSize(new Dimension(100,50));
        zoomOutButton.setEnabled(false);
		zoomControlPanel.add(Box.createRigidArea(new Dimension(200,50)));
		zoomControlPanel.add(zoomOutButton);
		zoomOutButton.setFocusable(false);
    }

    /**
     *
     * @param quitListener
     * @param zoomInListener
     * @param zoomOutListener
     */
    public void setZoomListeners(ActionListener quitListener, ActionListener zoomInListener, ActionListener zoomOutListener) 
    {
		quitButton.addActionListener(quitListener);
		zoomInButton.addActionListener(zoomInListener);
		zoomOutButton.addActionListener(zoomOutListener);
    }

    //TODO: Key bindings would be a better way to check for
    //button presses, since Key Listeners might not work if
    //"focus" is lost from container.
    /**
	 * @author Aki Stankoski and Dennis Huynh
	 * @author Spencer Pao and Bohan Lin
	 * @author Xinzhe Wang and Shuai Lang
	 * @author Andrew Tran and Eric Swenson
     * Specialized JPanel which implements a KeyListener that enables
     * panning through the zoomed Image
     */
    class ZoomPanel extends JPanel implements KeyListener 
    {
    	private static final long serialVersionUID = 4642288009275892215L;
    	private int x;
        private int y;

        /**
         * 
         */
        public ZoomPanel() 
        {
            this.removeAll();
            this.setSize(1250, 750);
            this.setLayout(new BorderLayout());
            this.setBackground(Color.WHITE);
            setX(0);
            setY(0);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
        }
        
        /**
         * 
         * @param x
         */
        public void setX(int x) 
        { 
        	this.x = x; 
    	}
        
        /**
         * 
         * @param y
         */
        public void setY(int y) 
        { 
        	this.y = y; 
    	}
        
        @Override
        public void paintComponent(Graphics g) 
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            mainImage.getResizedImage().paintIcon(this,g2d,x,y);
            System.out.println("[REPAINT]x="+x+",y="+y);
        }
        	
        @Override
        public void keyPressed(KeyEvent key) 
        {
            if(mainImage.zoomMagnitude > 1)
		{
		    switch(key.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        x = x - 10;
			System.out.println("KEY-RIGHT");
                        break;
                    case KeyEvent.VK_LEFT:
                        x = x + 10;
			System.out.println("KEY-LEFT");
                        break;
                    case KeyEvent.VK_DOWN:
                        y = y - 10;
			System.out.println("KEY-DOWN");
                        break;
                    case KeyEvent.VK_UP:
                        y = y + 10;
			System.out.println("KEY-UP");
                        break;
		    }
		}
            this.repaint();
        }
        
        @Override
        public void keyReleased(KeyEvent key) {}
        
        @Override
        public void keyTyped(KeyEvent key) {}
    }



}
