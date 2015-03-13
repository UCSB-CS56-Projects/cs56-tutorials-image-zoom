package edu.ucsb.cs56.projects.tutorials.image_zoom;

import javax.swing.*;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.logging.Logger;

public class MainImage extends ImageLoader {
	Logger logger = Logger.getLogger("MainImage");

    /**
     * The current image
     */
	private ImageIcon currentImage;

    /**
     * Zoom values
     */
    public int zoomMagnitude;
    public int zoomWidth;
    public int zoomHeight;
    public int x;
    public int y;
    

	/**
	 *  default Constructor;
	 */
	public MainImage(ImageIcon icon) {
		this.setCurrentImage(icon);
		this.zoomMagnitude = 1;
		this.zoomWidth = Constants.MAIN_IMAGE_BASE_WIDTH;
		this.zoomHeight = Constants.MAIN_IMAGE_BASE_HEIGHT;
		this.x = 0;
		this.y = 0;
	}

	/**
	 * setCurrentImage sets the current imageIcon from the parameter
	 * @param image the new main image to be displayed
	 */
	public void setCurrentImage(ImageIcon image) {
		currentImage = image;
	}
	
	/**
	 * getCurrentImage() returns the current imageIcon being displayed
	 * @return the imageIcon instance variable currentImage
	 */
	public ImageIcon getCurrentImage() 
	{
		return currentImage;
	}

	/**
	 * 
	 * @return
	 */
	public ImageIcon getResizedImage() 
	{
		Image resizedImage = currentImage.getImage().getScaledInstance(zoomWidth, zoomHeight, java.awt.Image.SCALE_SMOOTH); 
		return new ImageIcon(resizedImage);
	}
	
	public JLabel getResizedImage(ImageIcon icon) 
	{
		Image resizedImage = icon.getImage().getScaledInstance(zoomWidth, zoomHeight, java.awt.Image.SCALE_SMOOTH); 
		return new JLabel(new ImageIcon(resizedImage));
	}

}
