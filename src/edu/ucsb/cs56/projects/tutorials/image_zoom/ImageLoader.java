package edu.ucsb.cs56.projects.tutorials.image_zoom;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * @author elswenson, andrewtran1995
 *
 */
public abstract class ImageLoader {

	public static final String DEFAULT_DIR = "/build/images";


	/**
	 *
	 * @param image
	 * @return
	 * @throws IOException
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
