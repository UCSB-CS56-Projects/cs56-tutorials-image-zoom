package edu.ucsb.cs56.projects.tutorials.image_zoom;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

/**
 * @author Aki Stankoski and Dennis Huynh
 * @author Spencer Pao and Bohan Lin
 * @author Xinzhe Wang and Shuai Lang
 * @author Andrew Tran and Eric Swenson
 * PreView is a view that handles input and display for the preview image, 
 * previous, next, and load buttons, and the instructions.
 */
public class PreView {

    PreviewImage previewImage;//model to be used with PreView

    JPanel previewSection = new JPanel();//includes (from top to bottom):
                                         //directionsPanel, previewLabelPanel,
                                         //previewMainPanel, previewButtonsPanel

	JPanel directionsPanel = new JPanel();//directions, placed in top-right

    JPanel previewLabelPanel = new JPanel();
    JLabel previewLabel; //label for preview image

    JPanel previewMainPanel = new JPanel();//panel for preview image

    JPanel previewButtonsPanel = new JPanel();
    JButton previousButton = new JButton("Previous");
    JButton nextButton = new JButton("Next");
    JButton loadButton = new JButton("Load");

    /**
     * Constructor which takes in PreviewImage for PreView to interact with
     * @param previewImage
     */
    public PreView(PreviewImage previewImage) 
    {
        this.previewImage = previewImage;//set this PreView's model
        setPreviewSection();
    }

    /**
     * setPreviewSection() sets all the portions of the view, resetting them if it's done. 
     */
    public void setPreviewSection() 
    {
        if(previewSection != null) {
            previewSection.removeAll();
            previewSection.setBackground(Color.WHITE);
        }
        previewSection.setLayout(new BoxLayout(previewSection, BoxLayout.Y_AXIS));
        previewSection.setPreferredSize(new Dimension(250, 600));
        //set individual panels
        setDirectionsPanel();
        setPreviewLabelPanel();
        setPreviewMainPanel();
        setPreviewButtonsPanel();
        //add panels to previewSection (large panel)
        previewSection.add(directionsPanel);
        previewSection.add(previewLabelPanel);
        previewSection.add(previewMainPanel);
        previewSection.add(previewButtonsPanel);
    }
    
	/**
	 * setDirectionsPanel sets the text in the DirectionsPanel 
	 */
	public void setDirectionsPanel() 
	{
		JTextArea directionsTextArea = new JTextArea(Constants.DEFAULT_DIRECTIONS);
		directionsTextArea.setEditable(false);
		directionsTextArea.setLineWrap(true);
		directionsTextArea.setWrapStyleWord(true);
		directionsTextArea.setPreferredSize(new Dimension(200, 200));
        directionsPanel.add(directionsTextArea);
	}

	/**
	 * setPreviewLabelPanel resets the preview image label
	 */
    public void setPreviewLabelPanel() 
    {
    	String previewName = previewImage.getCurrentImage().getDescription();
    	this.previewLabel = new JLabel("Preview - " + previewName);
    	previewLabelPanel.removeAll();
    	previewLabelPanel.add(this.previewLabel);
    }
    
    /**
     * setPreviewMainPanel sets the previewImage
     */
    public void setPreviewMainPanel() 
    {
        previewMainPanel = new JPanel();
        previewMainPanel.setLayout(new BorderLayout());
        previewMainPanel.add(new JLabel(previewImage.getCurrentImage()));
    }

    /**
     * setPreviewButtonsPanel() sets the buttons in the view
     */
    public void setPreviewButtonsPanel() 
    {
        previewButtonsPanel.add(previousButton);
        previewButtonsPanel.add(nextButton);
        previewButtonsPanel.add(loadButton);
    }

    /**
     * setPreviewListeners sets the listeners
     * @param previousListener listener class for the 'previous' Button
     * @param nextListener listener class for the 'next' Button
     * @param loadListener listener class for the 'loadListener' Button
     */
    public void setPreviewListeners(ActionListener previousListener,
                                    ActionListener nextListener,
                                    ActionListener loadListener) 
    {
        previousButton.addActionListener(previousListener);
        nextButton.addActionListener(nextListener);
        loadButton.addActionListener(loadListener);
    }

}
