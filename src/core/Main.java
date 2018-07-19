package core;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import gui.*;


import javax.swing.*;


public class Main {

    private static Mandelbrot man = new Mandelbrot();

    //private static double offset = 2;

    //private static ComplexNum center = new ComplexNum(0, 0);



    private static BufferedImage img = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);

    //private static MandelbrotFrame frame = new MandelbrotFrame();

    private static MandelbrotFrame frame;

    public static void main(String[] args) {
        frame = new MandelbrotFrame();
    }



    public static void drawMandelbrot( MandelbrotPanel mandelbrotViewer, double xmin, double xmax, double ymin, double ymax) {

        try {
            man.generateMandelbrotImage( img, xmin, xmax, ymin, ymax );
            man.generateJuliaImage(img, -2, 1.5, -2, 2);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        mandelbrotViewer.setImage( img );
        mandelbrotViewer.loadImage( img );
        mandelbrotViewer.repaint();
        //mandelbrotViewer.paintComponent( img.getGraphics() );
    }

    public void zoom(){
        System.out.println("Main");
        frame.zoom();
    }
}