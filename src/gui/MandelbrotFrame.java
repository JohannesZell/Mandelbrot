package gui;

import core.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import javax.swing.JOptionPane;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 * This class creates a MandelbrotFrame instance with all included components.
 * There can only be one instance at a time, since it usually gets used in the Singleton-Pattern.
 * @author Ronny Kohlhaus
 */
public class MandelbrotFrame extends JFrame {

    private static final long serialVersionUID = 42L;
    private static MandelbrotFrame frame = new MandelbrotFrame();
    private final int SIDE_LENGTH = 800;
    private double xMin, xMax, yMin, yMax, zoomFactor;
    private Viewport viewport;

    //---Declaration of the components---
    private JButton bGenerate;
    private JButton bSave;
    private JTextField eReelMin;
    private JTextField eReelMax;
    private JLabel lReelMin;
    private JLabel lReelMax;
    private JLabel lImagMin;
    private JLabel lImagMax;
    private JTextField eImagMin;
    private JTextField eImagMax;
    private JLabel lZoom;
    private JTextField eZoom;
    private MandelbrotPanel pMandelbrotViewer;

    /**
     * Constructor of the MandelbrotFrame, which initialises all variables and instantiates all objects
     */
    public MandelbrotFrame() {
        initComponents();
        this.xMin = Double.parseDouble( eReelMin.getText() );
        this.xMax = Double.parseDouble( eReelMax.getText() );
        this.yMin = Double.parseDouble( eImagMin.getText() );
        this.yMax = Double.parseDouble( eImagMax.getText() );
        this.zoomFactor = Double.parseDouble( eZoom.getText() );
        viewport = new Viewport( SIDE_LENGTH, xMin, xMax, yMin, yMax, zoomFactor );
        Main.drawMandelbrot( pMandelbrotViewer, xMin, xMax, yMin, yMax );
    }

    /**
     * Creates a new MandelbrotFrame if not already one is existing.
     * @return returns the MandelbrotFrame
     */
    public static MandelbrotFrame getFrame() {
        if ( MandelbrotFrame.frame == null ) {
            MandelbrotFrame.frame = new MandelbrotFrame();
        }
        return MandelbrotFrame.frame;
    }

    /**
     * ActionListener for the bGenerate button object which checks, if the value is type double.
     * Shows information message in case user has put a false value in it.
     * @param e
     */
    private void bGenerateActionPerformed(ActionEvent e) {
        try {
            this.xMin = Double.parseDouble( eReelMin.getText() );
            this.xMax = Double.parseDouble( eReelMax.getText() );
            this.yMin = Double.parseDouble( eImagMin.getText() );
            this.yMax = Double.parseDouble( eImagMax.getText() );
            this.zoomFactor = Double.parseDouble( eZoom.getText() );
        } catch ( NumberFormatException e1 ) {
            JOptionPane.showMessageDialog( null,"Bitte geben Sie nur ganze Zahlen oder " +
                            "Dezimalwerte (getrennt mit Punkt) ein!", "Fehlerhafter Wert",JOptionPane.INFORMATION_MESSAGE );
        }
        viewport.reset( xMin, xMax, yMin, yMax, zoomFactor );
        Main.drawMandelbrot( pMandelbrotViewer, xMin, xMax, yMin, yMax );
    }

    /**
     * ActionListener for the bSave button object
     * @param e
     */
    private void bSaveActionPerformed( ActionEvent e ) {
        FileDialog fd = new FileDialog( this, "Bitte Speicherort auswählen",FileDialog.SAVE );
        fd.setVisible( true );
        String chosenDir = fd.getDirectory();
        String chosenFile = fd.getFile();
        File file = new File( chosenDir + chosenFile );
        if ( chosenDir == null || chosenFile == null )
            System.out.println("Du hast den Dialog abgebrochen!");
        else {
            try {
                ImageIO.write( pMandelbrotViewer.getImage(), "jpg", file );
            } catch ( IOException e1 ) {
                e1.printStackTrace();
            }
        }
        fd.dispose();
        }

    /**
     * Calls the zoom method of the viewport and draws the new image.
     * Following the JTextField are getting refreshed with the actual values.
     * @param x x-Value of the mouse cursor
     * @param y y-Value of the mouse cursor
     */
    public void zoom( int x, int y ) {
        this.viewport.zoom( x, y );
        Main.drawMandelbrot( pMandelbrotViewer, viewport.getxMin(), viewport.getxMax(),
                viewport.getyMin(), viewport.getyMax() );
        eReelMin.setText( String.valueOf(viewport.getxMin() ) );
        eReelMax.setText( String.valueOf(viewport.getxMax() ) );
        eImagMin.setText( String.valueOf(viewport.getyMin() ) );
        eImagMax.setText( String.valueOf(viewport.getyMax() ) );
    }

    /**
     * Initialises the components of the MandelbrotFrame
     */
    private void initComponents() {
        this.viewport = new Viewport( this.SIDE_LENGTH, xMin, xMax, yMin, yMax, zoomFactor );
        bGenerate = new JButton();
        bSave = new JButton();
        eReelMin = new JTextField();
        eReelMax = new JTextField();
        lReelMin = new JLabel();
        lReelMax = new JLabel();
        lImagMin = new JLabel();
        lImagMax = new JLabel();
        eImagMin = new JTextField();
        eImagMax = new JTextField();
        lZoom = new JLabel();
        eZoom = new JTextField();
        pMandelbrotViewer = new MandelbrotPanel( this.SIDE_LENGTH );

        //======== this ========
        this.setResizable(false);
        this.setTitle("Mandelbrot-Menge");
        this.setName("this");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.pack();
        this.setVisible(true);
        Container contentPane = getContentPane();

        //---- bGenerate ----
        bGenerate.setText("Generieren");
        bGenerate.setName("bGenerate");
        bGenerate.addActionListener(e -> bGenerateActionPerformed(e));

        //---- bSave ----
        bSave.setText("Speichern");
        bSave.setName("bSave");
        bSave.addActionListener(e -> bSaveActionPerformed(e));

        //---- eReelMin ----
        eReelMin.setText("-2");
        eReelMin.setHorizontalAlignment(SwingConstants.CENTER);
        eReelMin.setName("eReelMin");

        //---- eReelMax ----
        eReelMax.setText("2");
        eReelMax.setHorizontalAlignment(SwingConstants.CENTER);
        eReelMax.setName("eReelMax");

        //---- lReelMin ----
        lReelMin.setText("Minimum der reellen Achse");
        lReelMin.setName("lReelMin");

        //---- lReelMax ----
        lReelMax.setText("Maximum der reellen Achse");
        lReelMax.setName("lReelMax");

        //---- lImagMin ----
        lImagMin.setText("Minimum der imagin\u00e4ren Achse");
        lImagMin.setName("lImagMin");

        //---- lImagMax ----
        lImagMax.setText("Maximum der imagin\u00e4ren Achse");
        lImagMax.setName("lImagMax");

        //---- eImagMin ----
        eImagMin.setText("-2");
        eImagMin.setHorizontalAlignment(SwingConstants.CENTER);
        eImagMin.setName("eImagMin");

        //---- eImagMax ----
        eImagMax.setText("2");
        eImagMax.setHorizontalAlignment(SwingConstants.CENTER);
        eImagMax.setName("eImagMax");

        //---- lZoom ----
        lZoom.setText("Zoomfaktor");
        lZoom.setName("lZoom");

        //---- eZoom ----
        eZoom.setText("4.0");
        eZoom.setHorizontalAlignment(SwingConstants.CENTER);
        eZoom.setToolTipText("Zoomfaktor beim Klick in die Anzeigefl\u00e4che");
        eZoom.setName("eZoom");

        //---- pMandelbrotViewer ----
        pMandelbrotViewer.setName("pMandelbrotViewer");

        GroupLayout contentPaneLayout = new GroupLayout(contentPane);
        contentPane.setLayout(contentPaneLayout);
        contentPaneLayout.setHorizontalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                            .addGap(28, 28, 28)
                            .addGroup(contentPaneLayout.createParallelGroup()
                                .addComponent(lImagMin)
                                .addComponent(lZoom, GroupLayout.PREFERRED_SIZE, 126, GroupLayout.PREFERRED_SIZE)
                                .addGroup(contentPaneLayout.createSequentialGroup()
                                    .addComponent(bGenerate)
                                    .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(bSave))
                                .addComponent(lReelMax, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lReelMin)
                                .addComponent(lImagMax))))
                    .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                        .addComponent(eReelMin, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        .addComponent(eImagMin, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        .addComponent(eReelMax, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        .addComponent(eImagMax, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE)
                        .addComponent(eZoom, GroupLayout.PREFERRED_SIZE, 180, GroupLayout.PREFERRED_SIZE))
                    .addComponent(pMandelbrotViewer, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
                                .addGap(22, 22, 22)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(eReelMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lReelMin))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(eReelMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lReelMax))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(eImagMin, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lImagMin))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(eImagMax, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lImagMax))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(eZoom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addComponent(lZoom))
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                            .addGroup(contentPaneLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(bGenerate)
                                .addComponent(bSave)))
                        .addComponent(pMandelbrotViewer, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE)))
        );
        pack();
        setLocationRelativeTo( getOwner() );
    }
}
