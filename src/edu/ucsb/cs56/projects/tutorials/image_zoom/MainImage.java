package edu.ucsb.cs56.projects.tutorials.image_zoom;

import java.util.logging.Logger;

import javax.swing.ImageIcon;

public class MainImage extends ImageLoader {
	Logger logger = Logger.getLogger("MainImage");

	public ImageIcon currentImage;

	/**
	 *
	 */
	public MainImage() {

	}

	/**
	 *
	 * @param image
	 */
	public void setCurrentImage(ImageIcon image) {
		currentImage = image;
	}



}
