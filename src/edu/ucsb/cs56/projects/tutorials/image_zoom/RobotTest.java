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
                Robot r = new Robot();
                //move mouse to quit button
                r.mouseMove(300,585);
                r.delay(1000);

                //move mouse to zoom + button
                r.mouseMove(600, 600);
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


            r.delay(1500);
            r.mouseMove(900,600);
            r.delay(400);
            r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            r.delay(400);
            r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            r.delay(1500);
            r.mouseMove(300, 585);
            r.delay(400);
            r.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            r.delay(400);
            r.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);

        }

    }


