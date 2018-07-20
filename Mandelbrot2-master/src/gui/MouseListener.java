package gui;

import java.awt.event.MouseEvent;

/**
 * Listens for a mouse-click and gets its cursor position
 * @author Ronny Kohlhaus
 */
public class MouseListener implements java.awt.event.MouseListener {

private final int LEFT_MOUSE_BUTTON = 1;

@Override
public void mouseClicked( MouseEvent e ) {
    int xPosition = e.getX();
    int yPosition = e.getY();

    //---Zooms in---
    if( e.getButton() == LEFT_MOUSE_BUTTON ) {
        MandelbrotFrame.getFrame().zoom( xPosition, yPosition );
    }
}

    @Override
    public void mousePressed( MouseEvent e ) {

    }

    @Override
    public void mouseReleased( MouseEvent e ) {

    }

    @Override
    public void mouseEntered( MouseEvent e ) {

    }

    @Override
    public void mouseExited( MouseEvent e ) {

    }
}
