package edu.ucsb.cs56.projects.tutorials.image_zoom;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionListener;

public class PreView {

    PreviewImage previewImage;//model to be used with PreView

    JPanel previewSection = new JPanel();//includes (from top to bottom):
                                         //directionsPanel, previewLabelPanel,
                                         //previewMainPanel, previewButtonsPanel

	JPanel directionsPanel = new JPanel();//directions, placed in top-right

    JPanel previewLabelPanel = new JPanel();
    String previewName = "HFH.jpg";//initialized string, part of label
    JLabel previewLabel = new JLabel("Preview - " + previewName);//label for preview image

    JPanel previewMainPanel = new JPanel();//panel for preview image

    JPanel previewButtonsPanel = new JPanel();
    JButton previousButton = new JButton("Previous");
    JButton nextButton = new JButton("Next");
    JButton loadButton = new JButton("Load");

    /**
     * Constructor which takes in PreviewImage for PreView to interact with
     * @param previewImage
     */
    public PreView(PreviewImage previewImage) {
        this.previewImage = previewImage;//set this PreView's model
        setPreviewSection();
    }

    public void setPreviewSection() {
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
	 *
	 */
	public void setDirectionsPanel() {
		JTextArea directionsTextArea = new JTextArea(Constants.defaultDirections);
		directionsTextArea.setEditable(false);
		directionsTextArea.setLineWrap(true);
		directionsTextArea.setWrapStyleWord(true);
		directionsTextArea.setPreferredSize(new Dimension(200, 200));
        directionsPanel.add(directionsTextArea);
		//TODO handle resetpreviewsectionbullshit
        //^love that comment^
	}


    public void setPreviewLabelPanel() {
        previewLabelPanel.add(previewLabel);
    }
    //set previewMainPanel
    public void setPreviewMainPanel() {
        previewMainPanel = new JPanel();
        previewMainPanel.setLayout(new BorderLayout());
        previewMainPanel.add(new JLabel(previewImage.getCurrentImage()));
    }

    //set previewButtonsPanel
    public void setPreviewButtonsPanel() {
        previewButtonsPanel.add(previousButton);
        previewButtonsPanel.add(nextButton);
        previewButtonsPanel.add(loadButton);
    }

    public void setPreviewListeners(ActionListener previousListener,
                                    ActionListener nextListener,
                                    ActionListener loadListener) {
        previousButton.addActionListener(previousListener);
        nextButton.addActionListener(nextListener);
        loadButton.addActionListener(loadListener);
    }

    /**
     * Class that seems unecessary.
     */
    class PreviewPanel extends JPanel {
        public PreviewPanel() {
            previewLabel = new JLabel("Preview - " + previewName);
        }
    }

}
