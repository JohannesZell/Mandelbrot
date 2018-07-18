package gui;
import core.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.GroupLayout;

/**
 * @author Ronny Kohlhaus
 */
public class MandelbrotFrame extends JFrame {

    private class thehandler implements ActionListener {

        public void actionPerformed( ActionEvent event ) {
            //String
        }

    }

    private final int SIDE_LENGTH = 800;

    public MandelbrotFrame() {
        initComponents();
        //daf<s
    }

    private void bGenerateActionPerformed(ActionEvent e) {
        System.out.println("Generator");
        Main.drawMandelbrot( pMandelbrotViewer );
    }

    private void bSaveActionPerformed(ActionEvent e) {
        /* Hier die Methode zum Speichern */
        System.out.println("Speichern abgeschlossen");
    }

    private void initComponents() {
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
        eZoom.setText("4,0");
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
                                .addComponent(lReelMin)))
                        //.addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                           // .addContainerGap()
                            .addComponent(lImagMax))
                           // .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
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


    private void initComponents() {
        // JFormDesigner - Component initialization - DO NOT MODIFY  //GEN-BEGIN:initComponents
        // Generated using JFormDesigner Evaluation license - Ronny Kohlhau
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
        pMandelbrotViewer = new MandelbrotPanel();

        //======== this ========
        setResizable(false);
        setTitle("Mandelbrot-Menge");
        setName("this");
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
        eZoom.setText("4,0");
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
                        .addGroup(GroupLayout.Alignment.TRAILING, contentPaneLayout.createSequentialGroup()
                            .addComponent(lImagMax)
                            .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)))
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
                            .addGap(0, 0, 0)
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
        // JFormDesigner - End of component initialization  //GEN-END:initComponents
    }

    // JFormDesigner - Variables declaration - DO NOT MODIFY  //GEN-BEGIN:variables
    // Generated using JFormDesigner Evaluation license - Ronny Kohlhau
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
    // JFormDesigner - End of variables declaration  //GEN-END:variables
}
