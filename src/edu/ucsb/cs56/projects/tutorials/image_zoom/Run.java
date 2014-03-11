package edu.ucsb.cs56.projects.tutorials.image_zoom;

import java.io.IOException;


/**
 * Runs contains the main function the create a new instance of the GUI class 
 * @author Aki Stankoski and Dennis Huynh
 * @author Spencer Pao and Bohan Lin
 */
public class Run{
    /**
     * Main function that creates an instance of the GUI class.
     * It displays a single image (the HFH image as a sample image) in a simple window
     * @exception IOException is thrown
     */
    public static void main(String[] args) throws IOException {

        if(args.length==1){
            TheGUI a = new TheGUI(args[0]);
            a.setUpDisplay();
        }else if(args.length==2){
            TheGUI a = new TheGUI(args[0],args[1]);
            a.setUpDisplay();
        }else{
            TheGUI a = new TheGUI();
            a.setUpDisplay();
        }

    }

}
