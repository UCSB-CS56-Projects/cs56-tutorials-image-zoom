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
	r.delay(1000);
	
	//move mouse to zoom + button
	r.mouseMove((int) zi.getX()+75, (int) zi.getY()+45);
	r.delay(400);
	r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(500);
	r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	
	//press arrow keys to pan the image
	
	for(int i = 0; i<20; i++)
            {
                r.keyPress(KeyEvent.VK_RIGHT);
                r.delay(100);
                r.keyRelease(KeyEvent.VK_RIGHT);
		
            }
	r.delay(100);
	for(int i = 0; i<20; i++)
            {
                r.keyPress(KeyEvent.VK_DOWN);
                r.delay(100);
                r.keyRelease(KeyEvent.VK_DOWN);
		
            }
	r.delay(100);
	for(int i = 0; i<20; i++)
            {
                r.keyPress(KeyEvent.VK_LEFT);
                r.delay(100);
                r.keyRelease(KeyEvent.VK_LEFT);
		
            }
	r.delay(100);
	for(int i = 0; i<20; i++)
            {
                r.keyPress(KeyEvent.VK_UP);
                r.delay(100);
                r.keyRelease(KeyEvent.VK_UP);
		
            }
	
	//move mouse to zoom out
	r.delay(1500);
	r.mouseMove((int) zo.getX()+75, (int) zo.getY()+45);
	r.delay(400);
	r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(400);
	r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(1500);
	

	//move mouse to next
	r.mouseMove((int) next.getX()+35, (int) next.getY()+15);
	r.delay(1000);
	r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(400);
	r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(600);
	//click next again
	r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(400);
	r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(600);
	//click next again
	r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(400);
	r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	//move mouse to previous
	r.mouseMove((int) previous.getX()+35, (int) previous.getY()+15);
	r.delay(1000);
	r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(400);
	r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(600);
	//load the current picture
	r.mouseMove((int) load.getX()+35, (int) load.getY()+15);
	r.delay(1000);
	r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(400);
	r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(600);

	//move mouse to zoom + button and click
	r.mouseMove((int) zi.getX()+75, (int) zi.getY()+45);
	r.delay(400);
	r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(500);
	r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(500);
	
	//press arrow keys to pan the image
	
	for(int i = 0; i<20; i++)
            {
                r.keyPress(KeyEvent.VK_DOWN);
                r.delay(100);
                r.keyRelease(KeyEvent.VK_DOWN);
		
            }
	
	for(int i = 0; i<20; i++)
            {
                r.keyPress(KeyEvent.VK_UP);
                r.delay(100);
                r.keyRelease(KeyEvent.VK_UP);
		
            }
	r.delay(1000);
	
	//move mouse to zoom - button and click
	r.mouseMove((int) zo.getX()+75, (int) zo.getY()+45);
	r.delay(400);
	r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(500);
	r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(500);


	//quit
	
	r.mouseMove((int) q.getX()+35, (int) q.getY()+15);
	r.delay(400);
	r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
	r.delay(400);
	r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
	
    }
    
}


