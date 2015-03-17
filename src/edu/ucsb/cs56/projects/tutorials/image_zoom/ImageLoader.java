package edu.ucsb.cs56.projects.tutorials.image_zoom;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author Aki Stankoski and Dennis Huynh
 * @author Spencer Pao and Bohan Lin
 * @author Xinzhe Wang and Shuai Lang
 * @author Andrew Tran and Eric Swenson
 * ImageLoader is an abstract class that defines a method to load an image as an imageIcon and
 * contains a static variable related to the location of the default image directory in the filesystem
 */
public abstract class ImageLoader {

	public static final String DEFAULT_DIR = "/build/images";


	/**
	 * loadImage() loads a File that should be an image and returns an ImageIcon
	 * @param image is a File that is assumed to be an image
	 * @return the created imageIcon
	 * @throws IOException if the File is not an image
	 */
	public ImageIcon loadImage(File image) throws IOException {
		BufferedImage img = null;
		float width, height, scale;
		img = ImageIO.read(image);
		width = img.getWidth();
		height = img.getHeight();
		scale = height/width;
		width = 250f;
		height = (width * scale);
		ImageIcon newIcon = new ImageIcon(img.getScaledInstance(Math.max(1, (int) width),
				Math.max(1, (int) height), Image.SCALE_SMOOTH));
		newIcon.setDescription(image.getName());
		return newIcon;
	}

}
