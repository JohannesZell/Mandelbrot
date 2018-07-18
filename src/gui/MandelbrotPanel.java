package gui;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;

/**
 * @author Ronny Kohlhaus
 */
public class MandelbrotPanel extends JPanel {

    private static final long serialVersionUID = 42L;
    private BufferedImage image;

    public MandelbrotPanel( int sideLength ) {
        this.setPreferredSize( new Dimension( sideLength, sideLength ));
        this.addMouseListener( new MouseListener() );

        /*BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("/Users/ronny/IdeaProjects/mandelbrotRonny/src/javaGUI/image.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //Main.drawMandelbrot( this );
        //JLabel picLabel = new JLabel(new ImageIcon(myPicture.getScaledInstance(1000,800,0)));
        //picLabel.setBounds(0,0,800,800);
        //add(picLabel);
            try {
                image = ImageIO.read(new File("/Users/ronny/IdeaProjects/mandelbrot/src/javaGUI/image.png"));
            } catch (IOException ex) {
                ex.printStackTrace();
            }  */
    }

    public void clearAndRegenerate() {
        super.paint( getGraphics() );
    }

    /**
     * Sets the local image
     * @param img Actual BufferedImage
     */
    public void setImage( BufferedImage img) {
        this.image = img;
    }

    public void loadImage( BufferedImage img ) {
        clearAndRegenerate();
        //JLabel picLabel = new JLabel(new ImageIcon(img.getScaledInstance(800,800,0)));
        //picLabel.setBounds(0,0,800,800);
        //add(picLabel);
    }

    /**
     * Paints the local image on the panel if it's changed
     */
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(this.image, 0, 0, this);
    }
}
