package gui;

import core.Main;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * Panel for drawing the Mandelbrot-Image
 * @author Ronny Kohlhaus
 */
public class MandelbrotPanel extends JPanel {

    private static final long serialVersionUID = 43L;
    private Main main = new Main();
    private BufferedImage image;

    /**
     * Constructor of the MandelbrotPanel, which sets dimensions and adds a MouseListener for zooming.
     * @param sideLength Length of the drawing surface
     */
    public MandelbrotPanel( int sideLength ) {
        this.setPreferredSize( new Dimension( sideLength, sideLength ) );
        this.addMouseListener( listener );
    }

    /**
     * Image Getter-Function
     * @return returns image
     */
    public BufferedImage getImage() {
        return image;
    }

    /**
     * MouseListener which listens for mouse-click and calls the zoom() method of the main class
     * with the associated mouse-coordinates
     */
    MouseListener listener = new MouseListener() {
        @Override
        public void mouseClicked( MouseEvent e ) { main.zoom(e.getX(), e.getY()); }

        @Override
        public void mousePressed( MouseEvent e ) { }

        @Override
        public void mouseReleased( MouseEvent e ) { }

        @Override
        public void mouseEntered( MouseEvent e ) { }

        @Override
        public void mouseExited( MouseEvent e ) { }
    };

    /**
     * Refreshes the canvas
     */
    public void clearAndRegenerate() {
        super.paint(getGraphics());
    }

    /**
     * Sets the local image
     * @param img Actual BufferedImage
     */
    public void setImage(BufferedImage img) {
        this.image = img;
    }

    /**
     * Calls the method clearAndRegenerate()
     */
    public void loadImage() { clearAndRegenerate(); }

    /**
     * Paints the local image on the panel if it's changed
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, this);
    }
}
