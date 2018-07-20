
package gui;
import core.Mandelbrot;
import java.awt.*;
import java.awt.event.MouseEvent;

/**
 * @author Ronny Kohlhaus
 */
public class MouseListener implements java.awt.event.MouseListener {

private final int LEFT_MOUSE_BUTTON = 1;
private boolean clicked = false;
//private MandelbrotFrame mf = new MandelbrotFrame();

@Override
public void mouseClicked( MouseEvent e ) {
clicked = true;
System.out.println("Mouse");
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

}
