package edu.ucsb.cs56.projects.tutorials.image_zoom;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Logger;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

/**
 * @author Aki Stankoski and Dennis Huynh
 * @author Spencer Pao and Bohan Lin
 * @author Xinzhe Wang and Shuai Lang
 * @author Andrew Tran and Eric Swenson
 * ImageController is a controller that handles all the interactions between the
 * models and views. In particular, ImageController contains specialized subclasses of
 * listener classes as inner classes that are then added to the views, and allow
 * modification of items in the models
 */
public class ImageController {
    Logger logger = Logger.getLogger("ImageController");

    //frame
    JFrame frame;

    //models
    MainImage mainImage;
    PreviewImage previewImage;

    //views
    MainView mainView;
    PreView preView;
    
    /**
     *  ImageController() creates a new image controller object and sets up the parts of the system
     *  following MVC architecture. 
     */
    public ImageController() {
    	this.previewImage = new PreviewImage();
    	this.mainImage = new MainImage(previewImage.getCurrentImage());
    	this.preView = new PreView(this.previewImage);
    	this.mainView = new MainView(this.mainImage);
    	this.frame = mainView.frame;
        // set listeners
        this.mainView.setZoomListeners(new quitActionListener(),
                                       new zoomInActionListener(),
                                       new zoomOutActionListener());
        this.preView.setPreviewListeners(new previousActionListener(),
                                         new nextActionListener(),
                                         new loadActionListener());
        setUpDisplay();
    }
    
    /**
     * setUpDisplay() adds panes to the display, sets the size, background, and sets visibility
     */
    public void setUpDisplay() 
    {
        frame.getContentPane().add(BorderLayout.CENTER, mainView.zoomPanel);
        frame.getContentPane().add(BorderLayout.EAST, preView.previewSection);
        frame.getContentPane().add(BorderLayout.SOUTH, mainView.zoomControlPanel);
        frame.setSize(1250,625);
        frame.setBackground(Color.WHITE);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    ///////////////// Listeners /////////////////

    /**
	 * @author Aki Stankoski and Dennis Huynh
	 * @author Spencer Pao and Bohan Lin
	 * @author Xinzhe Wang and Shuai Lang
	 * @author Andrew Tran and Eric Swenson
     * Specialized ActionListener to handle updates to the previewImage
     */
    class previousActionListener implements ActionListener 
    {
		public void actionPerformed(ActionEvent event) 
		{
	        //update previewImage model
		    previewImage.getPreviousPreviewImage();
		    //update preView view
	        preView.setPreviewSection();
	        SwingUtilities.updateComponentTreeUI(frame);
	        System.out.println(previewImage.currentImageIndex);
		}
    }

    /**
	 * @author Aki Stankoski and Dennis Huynh
	 * @author Spencer Pao and Bohan Lin
	 * @author Xinzhe Wang and Shuai Lang
	 * @author Andrew Tran and Eric Swenson
     * Specialized ActionListener to handle updates to the previewImage
     */
    class nextActionListener implements ActionListener 
    {
    	public void actionPerformed(ActionEvent event) 
		{
		    //update previewImage model
		    previewImage.getNextPreviewImage();
		    // update preView view
	        preView.setPreviewSection();
	        SwingUtilities.updateComponentTreeUI(frame);
	        System.out.println(previewImage.currentImageIndex);
		}
    }

    /**
	 * @author Aki Stankoski and Dennis Huynh
	 * @author Spencer Pao and Bohan Lin
	 * @author Xinzhe Wang and Shuai Lang
	 * @author Andrew Tran and Eric Swenson
     * Specialized ActionListener to handle updates to the mainImage
     */
    class loadActionListener implements ActionListener 
    {
    	public void actionPerformed(ActionEvent event) 
    	{
		    //reset zoom and location variables
	        mainImage.zoomMagnitude = 1;
		    mainView.zoomPanel.setX(0);
		    mainView.zoomPanel.setY(0);
		    mainView.zoomOutButton.setEnabled(false);
	        mainView.setZoomPanel();
		    mainImage.setCurrentImage(previewImage.getCurrentImage());
	        mainView.setMainImage();
	        SwingUtilities.updateComponentTreeUI(frame);
		}	
    }
    
    /**
	 * @author Aki Stankoski and Dennis Huynh
	 * @author Spencer Pao and Bohan Lin
	 * @author Xinzhe Wang and Shuai Lang
	 * @author Andrew Tran and Eric Swenson
     * Specialized ActionListener to handle exiting the system.
     */
    class quitActionListener implements ActionListener 
    {
        public void actionPerformed(ActionEvent event) 
        {
            System.exit(0);
        }
    }

    /**
	 * @author Aki Stankoski and Dennis Huynh
	 * @author Spencer Pao and Bohan Lin
	 * @author Xinzhe Wang and Shuai Lang
	 * @author Andrew Tran and Eric Swenson
     * Specialized ActionListener to handle updates the zooming properties
     * of the main Image and View
     */
    class zoomInActionListener implements ActionListener 
    {
    	public void actionPerformed(ActionEvent event) 
    	{
            mainImage.zoomMagnitude++;
            mainView.zoomOutButton.setEnabled(true);
            if(mainImage.zoomMagnitude >= 5)
                mainView.zoomInButton.setEnabled(false);
            System.out.println("actionListener: zoom=" + mainImage.zoomMagnitude);
            mainView.setZoomValues();
            mainView.setZoomPanel();
            mainView.setMainImage();
            SwingUtilities.updateComponentTreeUI(frame);
            frame.setVisible(true);
    	}
    }

    /**
	 * @author Aki Stankoski and Dennis Huynh
	 * @author Spencer Pao and Bohan Lin
	 * @author Xinzhe Wang and Shuai Lang
	 * @author Andrew Tran and Eric Swenson
     * Specialized ActionListener to handle updates the zooming properties
     * of the main Image and View
     */
    class zoomOutActionListener implements ActionListener 
    {
    	public void actionPerformed(ActionEvent event) 
    	{
    		mainImage.zoomMagnitude--;
            mainView.zoomInButton.setEnabled(true);
            if(mainImage.zoomMagnitude == 1) 
            {
			    mainView.zoomOutButton.setEnabled(false);
			    mainView.zoomPanel.setX(0);
			    mainView.zoomPanel.setY(0);
			}
            else if(mainImage.zoomMagnitude > 1)
                mainView.zoomOutButton.setEnabled(true);
		    System.out.println("actionListener: zoom=" + mainImage.zoomMagnitude);
            mainView.setZoomValues();
            mainView.setZoomPanel();
		    mainView.setMainImage();
            SwingUtilities.updateComponentTreeUI(frame);
		    frame.setVisible(true);
    	}
    }




}
