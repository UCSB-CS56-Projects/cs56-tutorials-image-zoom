package edu.ucsb.cs56.projects.tutorials.image_zoom;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;

public class ImageController {


	Logger logger = Logger.getLogger("ImageController");

	//image models
	MainImage mainImage;
	PreviewImage previewImage;

	//view


	//zoom values
	float zoomWidth, zoomHeight;

	/**
	 *
	 */
	public ImageController(){
		mainImage = new MainImage();
		previewImage = new PreviewImage();
		mainImage.setCurrentImage(previewImage.getCurrentImage());
		zoomWidth = 1; zoomHeight = 1;
	}


	/**
	 *
	 */
	public void getNextPreviewImage(){
		if (previewImage.currentImageIndex == previewImage.loadedImages.size() - 1)
			previewImage.setCurrentImageIndex(0);
		else
			previewImage.setCurrentImageIndex(++(previewImage.currentImageIndex));
	}

	/**
	 */
	public void getPreviousPreviewImage(){
		if (previewImage.currentImageIndex == 0)
			previewImage.setCurrentImageIndex(previewImage.loadedImages.size());
		else
			previewImage.setCurrentImageIndex(--(previewImage.currentImageIndex));
	}


	///////////////// Listeners /////////////////

	/**
	 *
	 * @author elswenson
	 *
	 */
	class previousActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// call controller to modify preview image
			getPreviousPreviewImage();
			// update preview image
		}
	}

	/**
	 *
	 * @author elswenson
	 *
	 */
	class nextActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// call controller to modify preview image
			getNextPreviewImage();
			// update preview image
		}
	}

	/**
	 *
	 * @author elswenson
	 *
	 */
	class loadActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			mainImage.setCurrentImage(previewImage.getCurrentImage());
			// call controller to load preview image onto main panel
		}
	}

	/**
	 *
	 * @author elswenson
	 *
	 */
	class zoomInActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// call controller to zoom in
			// update image
		}
	}

	class zoomOutActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			// call controller to zoom out
			// update image
		}
	}




}
