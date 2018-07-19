package gui;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import core.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.tools.JavaFileManager;

/**
 * @author Ronny Kohlhaus
 */
public class MandelbrotFrame extends JFrame {

    //private static MandelbrotFrame frame = new MandelbrotFrame();

    private final int SIDE_LENGTH = 800;
    private  Viewport viewport;
    private int sideLength;
    private double xMin, xMax, yMin, yMax, zoomFactor;
    private double total_x;
    private double total_y;


    public MandelbrotFrame() {
        initComponents();
        this.xMin = Double.parseDouble(eReelMin.getText());
        this.xMax = Double.parseDouble(eReelMax.getText());
        this.yMin = Double.parseDouble(eImagMin.getText());
        this.yMax = Double.parseDouble(eImagMax.getText());
        this.zoomFactor = Double.parseDouble(eZoom.getText());
        total_x = xMax - xMin;
        total_y = yMax - yMin;
        //Main.drawMandelbrot( pMandelbrotViewer, xMin, xMax, yMin, yMax );
        Main.drawMandelbrot( pMandelbrotViewer, xMin, xMax, yMin, yMax );
    }
/*
    public static MandelbrotFrame getFrame() {
        if (MandelbrotFrame.frame == null) {
            MandelbrotFrame.frame = new MandelbrotFrame();
        }
        return MandelbrotFrame.frame;
    }*/

    public void generateImage() {
        Main.drawMandelbrot( pMandelbrotViewer, xMin, xMax, yMin, yMax );
    }

    public Viewport getViewport() {
        return this.viewport;
    }

    private void bGenerateActionPerformed(ActionEvent e) {
        System.out.println("Generator");
        this.xMin = Double.parseDouble(eReelMin.getText());
        this.xMax = Double.parseDouble(eReelMax.getText());
        this.yMin = Double.parseDouble(eImagMin.getText());
        this.yMax = Double.parseDouble(eImagMax.getText());
        this.zoomFactor = Double.parseDouble(eZoom.getText());
        Main.drawMandelbrot( pMandelbrotViewer, xMin, xMax, yMin, yMax );
        //generateImage();
    }

    private void bSaveActionPerformed(ActionEvent e) {
        FileDialog fd = new FileDialog(this, "Bitte Speicherort auswählen",FileDialog.SAVE);
        fd.setVisible(true);
        String chosenDir = fd.getDirectory();
        String chosenFile = fd.getFile();
        File file = new File(chosenDir + chosenFile);
        if (chosenDir == null || chosenFile == null)
            System.out.println("Du hast den Dialog abgebrochen!");
        else {
            try {
                ImageIO.write(pMandelbrotViewer.getImage(), "jpg", file);
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
        fd.dispose();
        }

    public void zoom(int x, int y) {
        this.zoomFactor = Double.parseDouble(eZoom.getText());
        System.out.println(xMin);

        xMin = (xMin / zoomFactor) + (((total_x / getWidth()) * x) / 2);
        yMin = (yMin / zoomFactor) + (((total_y / getHeight()) * y) / 2);
        xMax = (xMax / zoomFactor) + (((total_x / getWidth()) * x) / 2);
        yMax = (yMax / zoomFactor) + (((total_y / getHeight()) * y) / 2);
//        xMin = (((total_x / getWidth()) * x) * zoomFactor);
//        yMin = (((total_y / getHeight()) * y) * zoomFactor);
//        xMax = (((total_x / getWidth()) * x) / zoomFactor);
//        yMax = (((total_y / getHeight()) * y) / zoomFactor);

        System.out.println(total_x);
        System.out.println((total_x / getWidth()) * x);
        total_x = xMax - xMin;
        total_y = yMax - yMin;
        Main.drawMandelbrot( pMandelbrotViewer, xMin, xMax, yMin, yMax );
        pMandelbrotViewer.repaint();
    }


    private void initComponents() {
        this.viewport = new Viewport(this.SIDE_LENGTH, xMin, xMax, yMin, yMax, zoomFactor);
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
                        .addComponent(eReelMin, GroupLayout.Alignment.LEADING, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                        .addComponent(eImagMin, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                        .addComponent(eReelMax, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                        .addComponent(eImagMax, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE)
                        .addComponent(eZoom, GroupLayout.PREFERRED_SIZE, 42, GroupLayout.PREFERRED_SIZE))
                    .addGap(67, 67, 67)
                    .addComponent(pMandelbrotViewer, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, 0))
        );
        contentPaneLayout.setVerticalGroup(
            contentPaneLayout.createParallelGroup()
                .addGroup(contentPaneLayout.createSequentialGroup()
                    .addGap(0, 0, 0)
                    .addGroup(contentPaneLayout.createParallelGroup()
                        .addGroup(contentPaneLayout.createSequentialGroup()
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
                        .addComponent(pMandelbrotViewer, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE))
                    .addGap(0, 0, Short.MAX_VALUE))
        );
        pack();
        setLocationRelativeTo(getOwner());
    }

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
}
