package edu.ucsb.cs56.projects.tutorials.image_zoom;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 *
 * @author elswenson, andrewtran1995
 *
 */
public class MainView {
	// main frame and panels managed in MainView.java
	JFrame frame = new JFrame(Constants.frameTitle);
    ZoomPanel zoomPanel = new ZoomPanel();
	JPanel zoomControlPanel = new JPanel();

    MainImage mainImage;//model taken by mainView
    JLabel imageLabel;

	// button objects managed by zoomControlPanel
	JButton quitButton = new JButton("Quit");
	JButton zoomInButton = new JButton("Zoom +");
	JButton zoomOutButton = new JButton("Zoom -");

	/**
	 *
	 */
	public MainView(MainImage mainImage) {
        this.mainImage = mainImage;
		frame.setPreferredSize(new Dimension(1000,625));
		frame.setResizable(false);
		setZoomPanel();
        setImageLabel();
        setZoomControlPanel();
        zoomPanel.addKeyListener(zoomPanel);
	}

    public void setZoomValues() {
        mainImage.zoomWidth = mainImage.zoomMagnitude * Constants.width;//scales width
        mainImage.zoomHeight = mainImage.zoomMagnitude * Constants.height;//scales height
    }

    public void setZoomPanel() {
        if(zoomPanel != null) {
            zoomPanel.removeAll();
            zoomPanel.setBackground(Color.WHITE);//creates new panel for the sample image
            zoomPanel.setLayout(new BorderLayout());//sets the new panel to a BorderLayout
            zoomPanel.setSize(1000,600);//sets the size of new panel
        }
    }

    public void setImageLabel() {
        zoomPanel.remove(imageLabel);
        if(mainImage.zoomMagnitude == 0) {
            imageLabel = new JLabel(mainImage.currentImage);
            imageLabel.setSize(new Dimension(1500,900));
        } else {
            Image image = mainImage.currentImage.getImage();
            Image zoomed = image.getScaledInstance(mainImage.zoomWidth,
                                            mainImage.zoomHeight,
                                            Image.SCALE_SMOOTH);
            imageLabel = new JLabel(new ImageIcon(zoomed));
            imageLabel.setSize(new Dimension(1500,900));
        }
        zoomPanel.add(imageLabel);
    }

	/**
	 *
	 */
	public void setZoomControlPanel() {
		// add buttons to zoomControlPanel
		zoomControlPanel.add(Box.createRigidArea(new Dimension(200,50)));
		zoomControlPanel.add(quitButton);

        zoomIn.setPreferredSize(new Dimension(100,50));
		zoomControlPanel.add(Box.createRigidArea(new Dimension(200,50)));
		zoomControlPanel.add(zoomInButton);

        zoomOut.setPreferredSize(new Dimension(100,50));
        zoomOut.setEnabled(false);
		zoomControlPanel.add(Box.createRigidArea(new Dimension(200,50)));
		zoomControlPanel.add(zoomOutButton);
	}

	/**
	 *
	 * @param quitListener
	 * @param zoomInListener
	 * @param zoomOutListener
	 */
	public void setZoomListeners(ActionListener quitListener, ActionListener zoomInListener, ActionListener zoomOutListener) {
		quitButton.addActionListener(quitListener);
		zoomInButton.addActionListener(zoomInListener);
		zoomOutButton.addActionListener(zoomOutListener);
	}

    class ZoomPanel extends JPanel implements KeyListener {
        private int x;
        private int y;

        public ZoomPanel() {
            this.removeAll();
            this.setLayout(new BorderLayout());
            this.setBackground(Color.WHITE);
            this.setSize(1000, 600);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);
        }

        public void setX(int x) { this.x = x; }
        public void setY(int y) { this.y = y; }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(currentImage.getImage(),x,y,this);
        }

        public void keyPressed(KeyEvent key) {
            if(mainImage.zoomMagnitude == 0);
            else {
                switch(key.getKeyCode()) {
                    case KeyEvent.VK_RIGHT:
                        x = x - 10;
                        break;
                    case KeyEvent.VK_LEFT:
                        x = x + 10;
                        break;
                    case KeyEvent.VK_DOWN:
                        y = y - 10;
                        break;
                    case KeyEvent.VK_UP:
                        y = y + 10;
                        break;
                }
            }
            this.repaint();
        }
        public void keyReleased(KeyEvent key) {} //needed for implementation
        public void keyTyped(KeyEvent key) {} //needed for implementation
    }



}
