package gui;
import java.awt.*;
import java.awt.event.MouseEvent;


public class MouseListener implements java.awt.event.MouseListener {

    private final int LEFT_MOUSE_BUTTON = 1;

    @Override
    public void mouseClicked( MouseEvent e ) {
        int xPosition = e.getX();
        int yPosition = e.getY();

        if( e.getButton() == LEFT_MOUSE_BUTTON ) ;
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
