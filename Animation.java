
//***********************************************************************
//
//   File: Animation.java        Assignment No.: 4
//
//   Author: CS112TA             Email: cs112ta@yale.edu
//
//   Class: Animation
// 
//   Time spent on this problem: 1 hour
//   --------------------
//      This program implements animation for PS 4
//
//***********************************************************************
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.BasicStroke;
import java.util.Scanner;

import apple.laf.JRSUIConstants.FrameOnly;

public class Animation {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 450;
    public static final int FRAME_T = 17; // in ms
    public static final double GRAVITY = 9.81; // in pixels/frame^2

    // Setup DrawingPanel
    public static DrawingPanel panel = new DrawingPanel(WIDTH, HEIGHT);
    public static Graphics2D g = panel.getGraphics();

    public static BufferedImage offscreen = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
    public static Graphics2D osg = offscreen.createGraphics();

    // main method
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        // use Scanner object to read in the user's values
        double countdown;
        double figureSize;
        double velocity;
        double angle;
        double duration;

        /*
        System.out.print("Enter the number of seconds for the countdown: ");
        countdown = in.nextDouble();
        System.out.print("Enter the size of the smiley face: ");
        figureSize = in.nextDouble();
        System.out.print("Enter the velocity: ");
        velocity = in.nextDouble();
        System.out.print("Enter the angle: ");
        angle = in.nextDouble();
        System.out.print("Enter the duration: ");
        duration = in.nextDouble();
        */

        countdown = 5;
        figureSize = 90;
        velocity = 80;
        angle = 45;
        duration = 12;

        // start animations one after the other
        animateClock(countdown);
        animateFace(figureSize, velocity, angle, duration);

        // close the Scanner object
        console.close();
    }

    // draws the clock with the hand pointing in the correct direction based on the currentTime
    public static void drawClock(double cx, double cy, double diameter, double currentTime, double totalTime) {
        // FILL ME IN
        g.setColor(Color.black);
        int handX = (int) (diameter/2*Math.cos(degreesToRadians(currentTime/totalTime*180)));
        int handY = (int) (diameter/2*Math.sin(degreesToRadians(currentTime/totalTime*180)));
        g.drawOval(round(cx-.5*diameter), round(cy-.5*diameter), round(diameter), round(diameter));
        g.drawLine(round(cx), round(cy), handX, handY);

    }

    // animation the clock with one full revolution taking countdown seconds
    public static void animateClock(double countdown) {
        // FILL ME IN
        for (double time = 0; time<countdown; time+=FRAME_T/10000){
            g.setColor(Color.white);
            g.drawRect(0, 0, WIDTH, HEIGHT);

            drawClock(WIDTH/2, HEIGHT/2, 300, time, countdown);
            panel.sleep(FRAME_T);
        }
    }

    // draws the smiley face using 3 ovals and 1 arc
    public static void drawFace(double x, double y, double size) {
        // FILL ME IN
        //making base layer of the face
        int eyeSpace = (int) (size/3);
        g.setColor(Color.yellow);
        g.fillOval(round(x), round(y), round(size), round(size));

        //making the two eyes using the eyeSpacer value to reference the ratio of the face size
        g.setColor(Color.BLACK);
        g.fillOval(round(x+.75*eyeSpace), round(y+eyeSpace), round (size/8), round (size/6));
        g.fillOval(round(x+size-.75*eyeSpace-size/8), round(y+eyeSpace), round (size/8), round (size/6));

        //making the mouth using the eyeSpacer value to reference the ratio of the face size and angles from 0 to -180 to make a concave up arc
        g.drawArc(round(x+eyeSpace), round(y+2*eyeSpace), round(eyeSpace), round(eyeSpace/2), 0, -180);


    }

    // animation the face launched across the screen for duration seconds
    public static void animateFace(double figureSize, double velocity, double angle, double duration) {
        // FILL ME IN
    }

	// converts degrees to radians
	// assume degrees only range from 0 to 360
	// and radians range from 0 to 2pi
    public static double degreesToRadians(double degrees) {
        // FILL ME IN
        // HINT: You can make use of scale as a "helper method"
        double radians = scale(degrees, 0, 360, 0, 2 * Math.PI);
        return radians;

    }

    // =====================================================================================
    // CODE BELOW THIS POINT COMES STRAIGHT FROM LECTURE EXAMPLES AND YOU MAY USE IT FREELY
    // =====================================================================================

    // calculate an object's position using physics
    public static double position(double initPos, double v, double a, double t) {
        return initPos + v * t + .5 * a * t * t;
    }

    // General scaling method. Can be used for any two ranges.
    public static double scale(double oldValue,
            double oldMin,
            double oldMax,
            double newMin,
            double newMax) {

        // this is the percent into the old range
        double percentInOldRange = (oldValue - oldMin) / (oldMax - oldMin);
        // multiply by the size of the new range
        double scaledToNewRange = percentInOldRange * (newMax - newMin);
        // shift over by the start of the new range
        double newValue = scaledToNewRange + newMin;
        return newValue;
    }

    // round a double to the nearest int
    public static int round(double d) {
        return (int) Math.round(d);
    }

}
