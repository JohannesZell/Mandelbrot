package core;

import java.awt.image.BufferedImage;
import gui.*;
/**
 *Main class of the project
 */
public class Main {

    private static Mandelbrot man = new Mandelbrot(); // generates a new Mandelbrot object
    private static BufferedImage img = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);
    private static MandelbrotFrame frame; // generates a new frame object

    public static void main(String[] args) {
        frame = MandelbrotFrame.getFrame();
    }

    /**
     * Draws the mandelbrot
     * @param mandelbrotViewer BufferedImage
     * @param xmin min x value
     * @param xmax max x value
     * @param ymin min y value
     * @param ymax max y value
     */
    public static void drawMandelbrot( MandelbrotPanel mandelbrotViewer, double xmin, double xmax, double ymin, double ymax) {
        try {
            man.generateMandelbrotImage( img, xmin, xmax, ymin, ymax );
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        mandelbrotViewer.setImage( img );
        mandelbrotViewer.loadImage();
        mandelbrotViewer.repaint();
    }

    /**
    *Method that is called when the mouse is clicked
    *@param x x value of the click position
    *@param y value of the click position
    */
    public void zoom(int x, int y){
        frame.zoom(x,y);
    }
}