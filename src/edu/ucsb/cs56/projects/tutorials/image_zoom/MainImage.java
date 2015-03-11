package edu.ucsb.cs56.projects.tutorials.image_zoom;

import javax.swing.*;
import java.util.logging.Logger;

public class MainImage extends ImageLoader {
	Logger logger = Logger.getLogger("MainImage");

    /**
     * The current image
     */
	public ImageIcon currentImage;

    /**
     * Zoom values
     */
    int zoomMagnitude = 0;
    int zoomWidth = 0;
    int zoomHeight = 0;

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
