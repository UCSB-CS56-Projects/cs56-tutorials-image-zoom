package edu.ucsb.cs56.projects.tutorials.image_zoom;

import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PreviewImage extends ImageLoader {

	Logger logger = Logger.getLogger("previewImageModel");
	public ArrayList <ImageIcon> loadedImages;
	public int currentImageIndex;

	/**
	 * Default Constructor PreviewImage() loads the default image files
	 */
	public PreviewImage() {
		loadedImages = new ArrayList<ImageIcon>();
		loadFiles(ImageLoader.DEFAULT_DIR);
		currentImageIndex = 0;
	}

	/**
	 * loadFiles loads all files in a directory into the system
	 * @param dirName the relative path to the directory
	 * @return true if it is a directory and the files are added, false if it is not a directory
	 */
	private boolean loadFiles(String dirName) {
		File dir = new File(dirName);
		if(dir.isDirectory()) {
			for(File f : dir.listFiles()) {
				try {
					loadedImages.add(loadImage(f));
				}
				catch (Exception ex) {
					logger.log(Level.INFO, ex.getMessage());
				}
			}
			return true; //returns true if it is a directory
		}
		return false; //returns false if not a directory
	}


	/**
	 * addUserImage adds a custom user image by absolute filepath
	 * @param filePath the absolute filepath to the image
	 * @return true if the image is added, false if the file is not an image
	 */
	public boolean addUserImage(String filePath) {
		File image = new File(filePath);
		if(image.isFile()) {
			try {
				loadedImages.add(loadImage(image));
			}
			catch (IOException ex) {
				logger.log(Level.FINE, filePath + " is not an image", ex);
				return false; //returns false if not an image
			}
		}
		return true; //returns true once image added
	}

	/**
	 * getCurrentImage() returns the imageIcon for the currently selected image
	 * @return the imageIcon object that is the currently selected image
	 */
	public ImageIcon getCurrentImage() {
		return loadedImages.get(currentImageIndex);
	}

	/**
	 * setCurrentImageIndex() sets the current image index, uses two assertions to require proper values.
	 * @param i is the new image index
	 */
	public void setCurrentImageIndex(int i) {
		assert (i < loadedImages.size());
		assert (i >= 0);
		currentImageIndex = i;
	}
	
    /**
     */
    public void getPreviousPreviewImage()
    {
        if (currentImageIndex == 0)
            setCurrentImageIndex(loadedImages.size() - 1);
        else
            setCurrentImageIndex(--(currentImageIndex));
    }

	/**
	 * getNextPreviewImage() sets the previewImage model to the next of its list of images.
	 */
	public void getNextPreviewImage()
	{
		if (currentImageIndex == loadedImages.size() - 1)
			setCurrentImageIndex(0);
		else
			setCurrentImageIndex(++(currentImageIndex));
	}

}
