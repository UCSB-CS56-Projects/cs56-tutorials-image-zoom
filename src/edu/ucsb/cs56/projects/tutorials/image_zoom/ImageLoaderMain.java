package edu.ucsb.cs56.projects.tutorials.image_zoom;

import java.io.IOException;

public class ImageLoaderMain {

    /**
     * Main of Image Zoom implemented using an MVC model.
     * MODELS: MainImage, PreviewImage
     * VIEWS: MainView, PreView
     * CONTROLLERS: ImageController
     * @author elswenson, andrewtran1995
     * @param args
     */
    public static void main(String[] args) throws IOException {
        MainImage mainImage = new MainImage();
        PreviewImage previewImage = new PreviewImage();
        MainView mainView = new MainView(mainImage);
        PreView preView = new PreView(previewImage);
        ImageController imageController = new ImageController(mainImage, previewImage,
                                                              mainView, preView);
        imageController.setUpDisplay();

    }
}
