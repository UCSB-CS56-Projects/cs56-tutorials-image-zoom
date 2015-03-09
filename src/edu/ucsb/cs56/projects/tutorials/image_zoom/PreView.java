package edu.ucsb.cs56.projects.tutorials.image_zoom;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JTextArea;

public class PreView {

	JPanel directionsPanel = new JPanel();

	/**
	 *
	 */
	public void setDirectionsPanel() {
		directionsPanel.setLayout(new BoxLayout(directionsPanel, BoxLayout.Y_AXIS));
		directionsPanel.setPreferredSize(new Dimension(250, 600));
		JTextArea directionsTextArea = new JTextArea(Constants.defaultDirections);
		directionsTextArea.setEditable(false);
		directionsTextArea.setLineWrap(true);
		directionsTextArea.setWrapStyleWord(true);
		directionsTextArea.setPreferredSize(new Dimension(200, 200));
		//TODO handle resetpreviewsectionbullshit
	}



}
