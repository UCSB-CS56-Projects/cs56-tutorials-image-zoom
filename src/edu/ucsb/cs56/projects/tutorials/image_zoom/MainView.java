package edu.ucsb.cs56.projects.tutorials.image_zoom;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author elswenson, andrewtran1995
 *
 */
public class MainView {
	// main frame and panels managed in MainView.java
	JFrame frame = new JFrame(Constants.FRAME_TITLE);
    ZoomPanel zoomPanel = new ZoomPanel();
	JPanel zoomControlPanel = new JPanel();

    MainImage mainImage;//model taken by mainView
    JLabel imageLabel;

	// button objects managed by zoomControlPanel
	JButton quitButton = new JButton("Quit");
	JButton zoomInButton = new JButton("Zoom +");
	JButton zoomOutButton = new JButton("Zoom -");

	/**
	 *
	 */
	public MainView(MainImage mainImage) 
	{
        this.mainImage = mainImage;
		frame.setPreferredSize(new Dimension(1000,625));
		frame.setResizable(false);
		setZoomPanel();
        setZoomControlPanel();
        setMainImage();
        zoomPanel.addKeyListener(zoomPanel);
	}
	
	/**
	 * 
	 */
    public void setZoomValues() 
    {
        mainImage.zoomWidth = mainImage.zoomMagnitude * Constants.MAIN_IMAGE_BASE_WIDTH;//scales width
        mainImage.zoomHeight = mainImage.zoomMagnitude * Constants.MAIN_IMAGE_BASE_HEIGHT;//scales height
    }
    
    /**
     * 
     */
    public void setZoomPanel() 
    {
        if(zoomPanel != null) {
            zoomPanel.removeAll();
            zoomPanel.setBackground(Color.WHITE);//creates new panel for the sample image
            zoomPanel.setLayout(new BorderLayout());//sets the new panel to a BorderLayout
            zoomPanel.setSize(1000,600);//sets the size of new panel
        }
    }

    /**
     * 
     */
    public void setMainImage() 
    {
    	if(imageLabel != null)
    		zoomPanel.remove(imageLabel);
    	ImageIcon loadedImageIcon = new ImageIcon("./images/" + mainImage.getCurrentImage().getDescription());
    	ImageIcon scaledImageIcon = new ImageIcon(loadedImageIcon.getImage().getScaledInstance(mainImage.zoomWidth, 
    																						   mainImage.zoomHeight,
    																						   Image.SCALE_SMOOTH));
        imageLabel = new JLabel(scaledImageIcon);
    	imageLabel.setSize(new Dimension(1500,900));
        zoomPanel.add(imageLabel); 
    }

	/**
	 *
	 */
	public void setZoomControlPanel() 
	{
		// add buttons to zoomControlPanel
		zoomControlPanel.add(Box.createRigidArea(new Dimension(200,50)));
		zoomControlPanel.add(quitButton);

        zoomInButton.setPreferredSize(new Dimension(100,50));
		zoomControlPanel.add(Box.createRigidArea(new Dimension(200,50)));
		zoomControlPanel.add(zoomInButton);

        zoomOutButton.setPreferredSize(new Dimension(100,50));
        zoomOutButton.setEnabled(false);
		zoomControlPanel.add(Box.createRigidArea(new Dimension(200,50)));
		zoomControlPanel.add(zoomOutButton);
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

    class ZoomPanel extends JPanel implements KeyListener 
    {
        /**
		 * 
		 */
		private static final long serialVersionUID = 4642288009275892215L;
		private int x;
        private int y;

        /**
         * 
         */
        public ZoomPanel() 
        {
            this.removeAll();
            this.setSize(1000, 625);
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
            g2d.drawImage(mainImage.getCurrentImage().getImage(),x,y,this);
        }
        	
        @Override
        public void keyPressed(KeyEvent key) 
        {
            if(mainImage.zoomMagnitude > 1)
            {
                switch(key.getKeyCode()) {
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
            }
            this.repaint();
        }
        
        @Override
        public void keyReleased(KeyEvent key) {}
        
        @Override
        public void keyTyped(KeyEvent key) {}
    }



}
