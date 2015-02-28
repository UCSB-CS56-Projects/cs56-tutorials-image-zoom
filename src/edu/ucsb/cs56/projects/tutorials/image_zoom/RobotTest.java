package edu.ucsb.cs56.projects.tutorials.image_zoom;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * Created by Shuai 2/26/2014
 * To perform a demo of operating the GUI
 *
 */


public class RobotTest {

    public static void main(String args[]) throws Exception
    {
        final int GUI_DELAY = 1000;
        final int INPUT_DELAY = 400;
        final int KEYPRESS_DELAY = 100;

	TheGUI a = new TheGUI();
	a.setUpDisplay();
	Point zi = a.zoomIn.getLocationOnScreen();
	Point zo = a.zoomOut.getLocationOnScreen();
	Point previous = a.previous.getLocationOnScreen();
	Point next = a.next.getLocationOnScreen();
	Point load = a.load.getLocationOnScreen();
	Point q = a.quit.getLocationOnScreen();
	
		
	Robot r = new Robot();
	//move mouse to quit button
	r.mouseMove((int) q.getX()+35, (int) q.getY()+15);
	r.delay(GUI_DELAY);
	
	//move mouse to zoom + button
	r.mouseMove((int) zi.getX()+75, (int) zi.getY()+45);
	r.delay(INPUT_DELAY);
	r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(INPUT_DELAY);
	r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	
	//press arrow keys to pan the image
	
	for(int i = 0; i<20; i++)
            {
                r.keyPress(KeyEvent.VK_RIGHT);
                r.delay(KEYPRESS_DELAY);
                r.keyRelease(KeyEvent.VK_RIGHT);
		
            }
	r.delay(KEYPRESS_DELAY);
	for(int i = 0; i<20; i++)
            {
                r.keyPress(KeyEvent.VK_DOWN);
                r.delay(KEYPRESS_DELAY);
                r.keyRelease(KeyEvent.VK_DOWN);
		
            }
	r.delay(KEYPRESS_DELAY);
	for(int i = 0; i<20; i++)
            {
                r.keyPress(KeyEvent.VK_LEFT);
                r.delay(KEYPRESS_DELAY);
                r.keyRelease(KeyEvent.VK_LEFT);
		
            }
	r.delay(KEYPRESS_DELAY);
	for(int i = 0; i<20; i++)
            {
                r.keyPress(KeyEvent.VK_UP);
                r.delay(KEYPRESS_DELAY);
                r.keyRelease(KeyEvent.VK_UP);
		
            }
	
	//move mouse to zoom out
	r.delay(GUI_DELAY);
	r.mouseMove((int) zo.getX()+75, (int) zo.getY()+45);
	r.delay(INPUT_DELAY);
	r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(INPUT_DELAY);
	r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(GUI_DELAY);
	

	//move mouse to next
	r.mouseMove((int) next.getX()+35, (int) next.getY()+15);
	r.delay(GUI_DELAY);
	r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(INPUT_DELAY);
	r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(INPUT_DELAY);
	//click next again
	r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(INPUT_DELAY);
	r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(INPUT_DELAY);
	//click next again
	r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(INPUT_DELAY);
	r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	//move mouse to previous
	r.mouseMove((int) previous.getX()+35, (int) previous.getY()+15);
	r.delay(GUI_DELAY);
	r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(INPUT_DELAY);
	r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(INPUT_DELAY);
	//load the current picture
	r.mouseMove((int) load.getX()+35, (int) load.getY()+15);
	r.delay(GUI_DELAY);
	r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(INPUT_DELAY);
	r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(INPUT_DELAY);

	//move mouse to zoom + button and click
	r.mouseMove((int) zi.getX()+75, (int) zi.getY()+45);
	r.delay(INPUT_DELAY);
	r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(INPUT_DELAY);
	r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(INPUT_DELAY);
	
	//press arrow keys to pan the image
	
	for(int i = 0; i<20; i++)
            {
                r.keyPress(KeyEvent.VK_DOWN);
                r.delay(KEYPRESS_DELAY);
                r.keyRelease(KeyEvent.VK_DOWN);
		
            }
	
	for(int i = 0; i<20; i++)
            {
                r.keyPress(KeyEvent.VK_UP);
                r.delay(KEYPRESS_DELAY);
                r.keyRelease(KeyEvent.VK_UP);
		
            }
	r.delay(GUI_DELAY);
	
	//move mouse to zoom - button and click
	r.mouseMove((int) zo.getX()+75, (int) zo.getY()+45);
	r.delay(INPUT_DELAY);
	r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(INPUT_DELAY);
	r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(INPUT_DELAY);


	//quit
	
	r.mouseMove((int) q.getX()+35, (int) q.getY()+15);
	r.delay(INPUT_DELAY);
	r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(INPUT_DELAY);
	r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	
    }
    
}


