package edu.ucsb.cs56.projects.tutorials.image_zoom;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author elswenson, andrewtran1995
 *
 */
public class MainView {
	// main frame and panels managed in MainView.java
	JFrame frame = new JFrame(Constants.frameTitle);
	JPanel zoomControlPanel = new JPanel();

	// button objects managed by zoomControlPanel
	JButton quitButton = new JButton("Quit");
	JButton zoomInButton = new JButton("Zoom +");
	JButton zoomOutButton = new JButton("Zoom -");

	/**
	 *
	 */
	public MainView(ActionListener quitListener, ActionListener zoomInListener, ActionListener zoomOutListener) {
		frame.setPreferredSize(new Dimension(1000,625));
		frame.setResizable(false);
		setZoomControlPanel(quitListener, zoomInListener, zoomOutListener);

	}


	/**
	 *
	 * @param quitListener
	 * @param zoomInListener
	 * @param zoomOutListener
	 */
	private void setZoomControlPanel(ActionListener quitListener, ActionListener zoomInListener, ActionListener zoomOutListener) {
		// add buttons to zoomControlPanel
		zoomControlPanel.add(Box.createRigidArea(new Dimension(200,50)));
		zoomControlPanel.add(quitButton);
		zoomControlPanel.add(Box.createRigidArea(new Dimension(200,50)));
		zoomControlPanel.add(zoomInButton);
		zoomControlPanel.add(Box.createRigidArea(new Dimension(200,50)));
		zoomControlPanel.add(zoomOutButton);
		//set listeners
		setZoomListeners(quitListener, zoomInListener, zoomOutListener);
	}

	/**
	 *
	 * @param quitListener
	 * @param zoomInListener
	 * @param zoomOutListener
	 */
	private void setZoomListeners(ActionListener quitListener, ActionListener zoomInListener, ActionListener zoomOutListener) {
		quitButton.addActionListener(quitListener);
		zoomInButton.addActionListener(zoomInListener);
		zoomOutButton.addActionListener(zoomOutListener);
	}



}
