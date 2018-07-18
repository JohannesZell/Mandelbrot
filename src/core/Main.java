package core;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import gui.*;


import javax.swing.*;


public class Main {

    private static double xValueMin = -2;

    private static double xValueMax = 1.5;

    private static double yValueMin = -2;

    private static double yValueMax = 2;

//    private static double total_x = xValueMax - xValueMin;

//    private static double total_y = yValueMax - yValueMin;

    private static Mandelbrot man = new Mandelbrot();

    private static double offset = 2;

    private static ComplexNum center = new ComplexNum(0, 0);

    private static BufferedImage img = new BufferedImage(800, 800, BufferedImage.TYPE_INT_RGB);

    //private static JFrame frame = new JFrame();


    private static MandelbrotFrame frame = new MandelbrotFrame();
    private static BufferedImage image;

    public static void main(String[] args) {

        System.out.println("Starting...");

        ComplexNum c = new ComplexNum(-0.7269, 0.01889);

            //man.generateMandelbrotImage(img, xValueMin,xValueMax,yValueMin,yValueMax, new SingleColorGenerator(2/8f));
            // man.generateJuliaImage(img, -2, 1.5, -2, 2, c );
            man.generateMandelbrotImage(img, center.re + offset, center.re - offset, center.im + offset, center.im - offset);
            ImageIcon icon = new ImageIcon(img);
                //frame.setLayout(new FlowLayout());
                //frame.setSize((int)(img.getWidth() * 1.1), (int)(img.getHeight() * 1.1));
            JLabel lbl = new JLabel();
            lbl.setIcon(icon);

            frame.add(lbl);
            //frame.addMouseListener(listener);
                //frame.setVisible(true);
                //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        System.out.println("sdgdfsgdfg");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.setContentPane(new hashTable());
        frame.pack();
        frame.setVisible(true);
    }

    public static void drawMandelbrot( MandelbrotPanel MandelbrotViewer ) {
        //MandelbrotViewer.paintComponent( img );
        MandelbrotViewer.loadImage( img );

        MandelbrotViewer.repaint();
    }

   private static MouseListener listener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println(e.getX());
//            xValueMin += total_x / 30;
//            yValueMin += total_y / 30;
//            xValueMax -= total_x / 30;
//            yValueMax -= total_y / 30;
            offset -= 0.1;
            //offset = offset/1.1;
            man.generateMandelbrotImage(img, center.re + offset, center.re - offset, center.im + offset, center.im - offset);
            frame.repaint();

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    };

}