package edu.ucsb.cs56.projects.tutorials.image_zoom;

import java.awt.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.Dimension;
import java.awt.Point;  
import java.awt.Rectangle; 

/**
 * TheGUI class creates the interface using swing for the image GUI
 * @author Aki Stankoski and Dennis Huynh
 * @author Spencer Pao and Bohan Lin
 * @author Xinzhe Wang and Shuai Lang
 *
 */


public class TheGUI{
    //declare the panels and buttons to be accessed from multiple methods  

    JFrame frame            =      new JFrame("Image Zoom Demonstration");//main frame
    //TODO: why this code doesn't work
    //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    JPanel bottomPanel      =      new JPanel();//Bottom subpanels
    JPanel topPanel         =      new JPanel();//Top subpanels
    JPanel infoPanel 		= 	   new JPanel();
    JPanel previewSection     =       new JPanel();
    JPanel previewButtonsPanel     =       new JPanel();
    JPanel previewTopPanel     =       new JPanel();
    String previewName = "HFH.jpg";
    JButton quit            =      new JButton("Quit");//cancel button for subscreens
    JButton zoomIn          =      new JButton("Zoom +");
    JButton zoomOut         =      new JButton("Zoom -");
    JButton previous            =      new JButton("Previous");
    JButton next          =      new JButton("Next");
    JButton load         =      new JButton("Load");
    JLabel defaultLabel;
    int zoomKeeper = 0; //keeps track of the level of zoom. Zoom in and out then either zoom farther in by incrementing 1 or decrementing 1
    int zoomWidth;
    int zoomHeight;
    ArrayList<ImageIcon> imageList = new ArrayList<ImageIcon>();
    int defaultPreviewIndex;
    int currentPreviewIndex;

    // File representing the folder that you select using a FileChooser
    static final File dir = new File("build/images");

    JLabel previewLabel;

    // hard-coded for Map
    // TODO: allow for choosing from multiple images
    String imgPath;
    ImageIcon icon;
    ImageIcon defaultIcon;
    JLabel mapLabel;
    NewPanel newPanel = null;
    PreviewPanel previewPanel = null;


    String generalInfo = "Directions for zoomIng:\n 1.First zoom to desired magnification\n 2. Then press arrow keys to pan image.";

    /**
     * Three constructors have been create:
     * 1.constructor with no parameter, will use "HFH" and "images/HFH.jpg" as the default name ane path
     * 2.constructor with 1 parameter, type String as imagePath, will use "Image zoom demonstration" as defaultLabel
     * 3.constructor with 2 parameters, both type String, first is name and second is path, name will be combine to defaultLabel and path will be imgPath
     * these constructors are used in case new TheGUI called
     * for changing image while running the program, see newPanel constructors
     */


    public TheGUI() {
        defaultLabel = new JLabel("Image zoom demonstration - HFH");
        imgPath = "images/HFH.jpg";
        icon = new ImageIcon(imgPath);
        defaultIcon = new ImageIcon(imgPath);
        mapLabel = new JLabel(icon);
        loadAllImages();
        defaultPreviewIndex = getDefaultPictureNumber("HFH.jpg");
        currentPreviewIndex = defaultPreviewIndex;
        System.out.println(defaultPreviewIndex);
        System.out.println(currentPreviewIndex);

    }

    public TheGUI(String name) {
        defaultLabel = new JLabel("Image zoom demonstration");
        //TODO: do we need a checking system or throw exception in case that path is not in a right format
        imgPath = "build/images/"+name;
        icon = new ImageIcon(imgPath);
        defaultIcon = new ImageIcon(imgPath);
        mapLabel = new JLabel(icon);
        //default would not be change if no explicit name given
        loadAllImages();
        defaultPreviewIndex = getDefaultPictureNumber(name);
        currentPreviewIndex = defaultPreviewIndex;
    }

    public TheGUI(String name, String path) {
        defaultLabel = new JLabel("Image zoom demonstration - " + name);
        //same problem with the second constructor
        imgPath = path;
        icon = new ImageIcon(imgPath);
        defaultIcon = new ImageIcon(imgPath);
        mapLabel = new JLabel(icon);
        loadAllImages();
        defaultPreviewIndex = getDefaultPictureNumber(name);
        currentPreviewIndex = defaultPreviewIndex;

    }

    /**
     * Sets up the basic display which includes the image, zoom, and quit buttons
     * @exception IOException is thrown
     */

    public void setUpDisplay() throws IOException{

        //mapLabel.setSize(new Dimension(1500,900));
        newPanel = new NewPanel();
        newPanel.addKeyListener(newPanel);
        addTopPanel();
        setEastPanel();
        setButtons();
        addPreviewButtons();
        addBottomPanel();
        setNewPanel();
        setmapLabel();
        addToFrame();
    }//end setUpDisplay

    /**
     *Adds introductory header to top panel
     */
    public void addTopPanel(){
        topPanel.add(defaultLabel);//adds the label to the top panel
    }

    public void setEastPanel(){
        infoPanel.setLayout(new BoxLayout(infoPanel,BoxLayout.Y_AXIS));
        infoPanel.setPreferredSize(new Dimension(250, 600));
        JTextArea directionsTextArea = new JTextArea(generalInfo);//creates a new space for text for directions
        directionsTextArea.setEditable(false);//makes the new text area NOT editable
        directionsTextArea.setLineWrap(true);//allows the lines to go to the next line if the current on is full
        directionsTextArea.setWrapStyleWord(true);//allows long words to break off and continue in the proceeding line
        JScrollPane scroll = new JScrollPane(directionsTextArea);//creates a new scrollable widget
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);//allows for scrolling on that widget
        directionsTextArea.setPreferredSize(new Dimension(200,200));//sets the size of the text area
        infoPanel.add(scroll);//adds the scrolling
        resetPreviewSection();
        setPreviewSection();
        infoPanel.add(previewSection);
    }

    /**
     *Adds the buttons and their action listeners
     */
    public void setButtons(){
        quit.addActionListener(new QuitActionListener());//adds a new ActionListener to the quit button
        zoomIn.setPreferredSize(new Dimension(100, 50));
        zoomOut.setPreferredSize(new Dimension(100,50));
        zoomIn.addActionListener(new zoomInActionListener());
        zoomOut.addActionListener(new zoomOutActionListener());
        previous.addActionListener(new previousActionListener());
        next.addActionListener(new nextActionListener());
        load.addActionListener(new loadActionListener());
        zoomOut.setEnabled(false);
    }


    /**
     *Produces modified width and length for enlarged image for zoom functionality
     */
    public void setZoomValues(){
	zoomWidth=zoomKeeper*1500; //scales the image width based on zoomKeeper
	zoomHeight=zoomKeeper*900;//scales the image height based on zoomKeeper
    }

    /**
     *Adds elements of bottom panel: quit, zoom-, zoom+ buttons
     */
    public void addBottomPanel(){
    bottomPanel.add(Box.createRigidArea(new Dimension(200,50)));
	bottomPanel.add(quit);//adds a quit button on the panel located at the bottom of the frame
	bottomPanel.add(Box.createRigidArea(new Dimension(200,50)));
	bottomPanel.add(zoomIn);
	bottomPanel.add(Box.createRigidArea(new Dimension(200,50)));
	bottomPanel.add(zoomOut);
    }

    public void addPreviewButtons(){
        previewButtonsPanel.add(previous);
        previewButtonsPanel.add(next);
        previewButtonsPanel.add(load);
    }

    /**
     *Resets newPanel for a repaint
     */
    public void setNewPanel(){
	if(newPanel != null) {
	    newPanel.removeAll();
	    newPanel.setBackground(Color.WHITE);//creates new panel for the sample image
	    newPanel.setLayout(new BorderLayout());//sets the new panel to a BorderLayout
	    newPanel.setSize(1000,600);//sets the size of new panel
	}
    }

    /**
     *loads the demo-image (.jpg) into mapLabel
     */
    public void setmapLabel(){
        newPanel.remove(mapLabel);
        if(zoomKeeper == 0){
            Image image = defaultIcon.getImage();
            icon = new ImageIcon(image);
            mapLabel = new JLabel(icon);
            mapLabel.setSize(new Dimension(1500,900));
        }
        else{
            Image image = icon.getImage();
            Image Zoomed = image.getScaledInstance(zoomWidth, zoomHeight, Image.SCALE_SMOOTH);//zooms in the image
            icon = new ImageIcon(Zoomed);
            mapLabel = new JLabel(icon);
            mapLabel.setSize(new Dimension(1500,900));//sets size of resized label
        }
        //newPanel.add(mapLabel);
    }

    /**
     *Adds contents to frame
     */
    public void addToFrame(){
	frame.getContentPane().add(BorderLayout.NORTH, topPanel);//adds the top panel including the label to the top of the frame
	frame.getContentPane().add(BorderLayout.EAST, infoPanel);
	frame.getContentPane().add(BorderLayout.CENTER,newPanel);//adds the new panel on the center of the frame
	frame.getContentPane().add(BorderLayout.SOUTH, bottomPanel);//adds the bottom panel, or the pannel with the cancel button, to the bottom of the frame
	frame.setSize(1000,625);//sets the size of the frame 
	frame.setBackground(Color.WHITE);//sets the background color of the frame to white
	frame.setVisible(true);//enables us to see the frame
    }

    public void loadAllImages(){
        if (dir.isDirectory()) { // make sure it's a directory
            for (final File f : dir.listFiles()) {
                BufferedImage img = null;
                float width;
                float height;
                float scale;
                try {
                    img = ImageIO.read(f);
                    width = img.getWidth();
                    height = img.getHeight();
                    scale = height / width;
                    width = 250f;
                    height = (width * scale); // height should be scaled from new width
                    ImageIcon loadIcons = new ImageIcon(img.getScaledInstance(Math.max(1, (int) width), Math.max(1, (int) height), Image.SCALE_SMOOTH));
                    loadIcons.setDescription(f.getName());
                    imageList.add(loadIcons);
                } catch (final IOException e) {
                    // handle errors here
                }
            }
        }
    }
      
    public int getDefaultPictureNumber(String name){
        ArrayList<String> sl = new ArrayList<String>();
        for(ImageIcon i:imageList){
            sl.add(i.getDescription());
        }
        return sl.indexOf(name);
    }

    /**
     *Resets PreviewSection for a repaint
     */
    public void resetPreviewSection(){
        if(previewSection != null) {
            previewSection.removeAll();
            previewSection.setBackground(Color.WHITE);//creates previewSection panel for the sample image
        }
    }

    public void setPreviewSection(){
        previewSection.setLayout(new BoxLayout(previewSection,BoxLayout.Y_AXIS));
        previewPanel = new PreviewPanel();
        previewPanel.setLayout(new BorderLayout());
        previewPanel.add(new JLabel(imageList.get(currentPreviewIndex)));
        previewTopPanel = new JPanel();
        previewTopPanel.add(previewLabel);
        previewSection.add(previewTopPanel);
        previewSection.add(previewPanel);
        previewSection.add(previewButtonsPanel);
    }

    //Code to implement panning that doesn't work. Left here for future progress.
    class NewPanel extends JPanel implements KeyListener{
        private int x;
        private int y;

        public NewPanel(){
            this.removeAll();
            this.setLayout(new BorderLayout());//BorderLayout manager automatically centers our image
            this.setBackground(Color.WHITE);
            this.setSize(1000,600);
            setFocusable(true);
            setFocusTraversalKeysEnabled(false);

        }

        public void setX(int x){
            this.x = x;
        }
        public void setY(int y){
            this.y = y;
        }

        public void paintComponent(Graphics g)
        {
            super.paintComponent(g);
            Graphics2D g2d = (Graphics2D) g;
            g2d.drawImage(icon.getImage(),x,y,this);
        }

        public void keyPressed(KeyEvent key)
        {
            if(zoomKeeper == 0){}
            else{
                switch (key.getKeyCode())
                {
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
        public void keyReleased(KeyEvent key){} // keylistener
        public void keyTyped(KeyEvent key){}    // is abstract
    }


    class PreviewPanel extends JPanel{
        public PreviewPanel() {
            previewLabel = new JLabel("Preview - " + previewName);
        }

    }

    //Zooms by redrawing the image in newPanel to a different scale.
    class zoomInActionListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            zoomKeeper++;//increments zoomKeeper which controls the level of zoom
            zoomOut.setEnabled(true);//enables the zoomOut key after the initial zoom. zoomIng out in a picture that hasn't been zoomed in on would be pointless.
            if(zoomKeeper == 5)
                zoomIn.setEnabled(false);
            setZoomValues();
            setNewPanel();
            setmapLabel();
            addToFrame();
        }
    }

    class zoomOutActionListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            zoomKeeper--;
            zoomIn.setEnabled(true);
            if(zoomKeeper == 0){
                zoomOut.setEnabled(false);
                newPanel.setX(0);
                newPanel.setY(0);
                System.out.println("actionlistener" + zoomKeeper);
            }
            else if(zoomKeeper > 1)
                zoomOut.setEnabled(true);
            setZoomValues();
            setNewPanel();
            setmapLabel();
            addToFrame();
        }
    }

    class previousActionListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if(currentPreviewIndex-1 < 0){
                currentPreviewIndex = imageList.size()-1;
            }
            else {
                currentPreviewIndex = currentPreviewIndex - 1;
            }
            resetPreviewSection();
            previewName = imageList.get(currentPreviewIndex).getDescription();
            setPreviewSection();
            addToFrame();
            System.out.println(currentPreviewIndex);
        }
    }

    class nextActionListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            currentPreviewIndex = (currentPreviewIndex+1) % (imageList.size());
            resetPreviewSection();
            previewName = imageList.get(currentPreviewIndex).getDescription();
            setPreviewSection();
            addToFrame();
            System.out.println(currentPreviewIndex);
        }
    }

    class loadActionListener implements ActionListener{
        public void actionPerformed(ActionEvent event){
            zoomKeeper=0;
            setNewPanel();
            //newPanel.remove(mapLabel);
            icon = new ImageIcon("build/images/"+imageList.get(currentPreviewIndex).getDescription());
            mapLabel = new JLabel(icon);
            mapLabel.setSize(new Dimension(1500,900));
            newPanel.add(mapLabel);
            addToFrame();
        }
    }

    // Quit button action listener. Exits on-click.
    class QuitActionListener implements ActionListener{//the action listener when the quit button is pressed
        public void actionPerformed(ActionEvent event){//the action that is performed after pressing quit
            System.exit(0);
        }
    }//end CancelActionListener
} //end class

