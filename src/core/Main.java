package core;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import gui.*;


import javax.swing.*;


public class Main {

    private static Mandelbrot man = new Mandelbrot();

    private static double offset = 2;

    private static ComplexNum center = new ComplexNum(0, 0);

    private static BufferedImage img = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);

    private static MandelbrotFrame frame = new MandelbrotFrame();

    ComplexNum c = new ComplexNum(-0.8342, 0.0145);

    public static void main(String[] args) {
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void drawMandelbrot( MandelbrotPanel MandelbrotViewer ) {
        //MandelbrotViewer.paintComponent( img );
        try {
            man.generateMandelbrotImage(img, center.re + offset, center.re - offset, center.im + offset, center.im - offset);
            //man.generateJuliaImage(img, -2, 1.5, -2, 2, c );
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        MandelbrotViewer.loadImage( img );
        MandelbrotViewer.repaint();
    }
}