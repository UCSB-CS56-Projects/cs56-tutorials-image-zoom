package edu.ucsb.cs56.projects.tutorials.image_zoom;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.logging.Logger;

import javax.swing.JFrame;

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
	 *
	 */
	public ImageController(MainImage mainImage, PreviewImage previewImage,
                           MainView mainView, PreView preView){
        this.frame = mainView.frame;
        this.mainImage = mainImage;
        this.previewImage = previewImage;
        this.mainView = mainView;
        this.preView = preView;
		mainImage.setCurrentImage(previewImage.getCurrentImage());
        // set listeners
        this.mainView.setZoomListeners(new quitActionListener(),
                                       new zoomInActionListener(),
                                       new zoomOutActionListener());
        this.preView.setPreviewListeners(new previousActionListener(),
                                         new nextActionListener(),
                                         new loadActionListener());

	}

    public void setUpDisplay() throws IOException {
        addToFrame();
    }

    public void addToFrame() {
        frame.getContentPane().add(BorderLayout.CENTER, mainView.zoomPanel);
        frame.getContentPane().add(BorderLayout.EAST, preView.previewSection);
        frame.getContentPane().add(BorderLayout.SOUTH, mainView.zoomControlPanel);
    }

    /**
     */
    public void getPreviousPreviewImage(){
        if (previewImage.currentImageIndex == 0)
            previewImage.setCurrentImageIndex(previewImage.loadedImages.size() - 1);
        else
            previewImage.setCurrentImageIndex(--(previewImage.currentImageIndex));
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

	///////////////// Listeners /////////////////

	/**
	 *
	 * @author elswenson, andrewtran1995
	 *
	 */
	class previousActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
            //update previewImage model
			getPreviousPreviewImage();
			//update preView view
            preView.previewName = previewImage.getCurrentImage().getDescription();
            preView.setPreviewSection();
            addToFrame();
            System.out.println(previewImage.currentImageIndex);
		}
	}

	/**
	 *
	 * @author elswenson, andrewtran1995
	 *
	 */
	class nextActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			//update previewImage model
			getNextPreviewImage();
			// update preView view
            preView.previewName = previewImage.getCurrentImage().getDescription();
            preView.setPreviewSection();
            addToFrame();
            System.out.println(previewImage.currentImageIndex);
		}
	}

	/**
	 *
	 * @author elswenson, andrewtran1995
	 *
	 */
	class loadActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
            mainImage.zoomMagnitude = 0;
            mainView.setZoomPanel();
			mainImage.setCurrentImage(previewImage.getCurrentImage());
            //additional items (setting map) that can be handled in mainView
            mainView.setImageLabel();
            addToFrame();
		}
	}

    class quitActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            System.exit(0);
        }
    }

	/**
	 *
	 * @author elswenson, andrewtran1995
	 */
	class zoomInActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
            mainImage.zoomMagnitude++;
            mainView.zoomOutButton.setEnabled(true);
            if(mainImage.zoomMagnitude == 5)
                mainView.zoomInButton.setEnabled(false);
            mainView.setZoomValues();
            mainView.setZoomPanel();
            mainView.setImageLabel();
            addToFrame();
		}
	}

	class zoomOutActionListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			mainImage.zoomMagnitude--;
            mainView.zoomInButton.setEnabled(true);
            if(mainImage.zoomMagnitude == 0) {
                mainView.zoomOutButton.setEnabled(false);
                mainView.zoomPanel.setX(0);
                mainView.zoomPanel.setY(0);
                System.out.println("actionListener" + mainImage.zoomMagnitude);
            }
            else if(mainImage.zoomMagnitude > 1)
                mainView.zoomOutButton.setEnabled(true);
            mainView.setZoomValues();
            mainView.setZoomPanel();
			mainView.setImageLabel();
            addToFrame();
		}
	}




}
