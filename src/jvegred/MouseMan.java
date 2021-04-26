package jvegred;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.*;
import javax.swing.event.MouseInputListener;

import jvegred.figures.FigureHandler;

public class MouseMan implements MouseInputListener, MouseWheelListener {

    private Color[] colors = {Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY,
        Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
        Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};

    private int wheelCount = 0, wheelCountColors = 0;

    @Override
    public void mouseEntered(MouseEvent e) {}

    @Override
    public void mouseExited(MouseEvent e) {}

    @Override
    public void mouseMoved(MouseEvent e) {}


    @Override
    public void mouseClicked(MouseEvent e) {
        Component c = e.getComponent();

        FigureHandler.x = c.getMousePosition().x;
        FigureHandler.y = c.getMousePosition().y;

       if (FigureHandler.onMultipleSelectMode) {
            FigureHandler.multipleSwapFocusAtXY();
        } else {
            FigureHandler.swapFocusAtXY();
        }

        c.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Component c = e.getComponent();
        FigureHandler.x = c.getMousePosition().x;
        FigureHandler.y = c.getMousePosition().y;
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Component c = e.getComponent();
        FigureHandler.x = c.getMousePosition().x;
        FigureHandler.y = c.getMousePosition().y;
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        Component c = e.getComponent();
        int dx = c.getMousePosition().x - FigureHandler.x;
        int dy = c.getMousePosition().y - FigureHandler.y;

        if (FigureHandler.onResizeMode) {
            FigureHandler.resizeFigure(dx, dy);
        } else {
            FigureHandler.dragFiguresOnFocus(dx, dy);
        }

        FigureHandler.x = c.getMousePosition().x;
        FigureHandler.y = c.getMousePosition().y;

        c.repaint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        wheelCountColors = Math.abs((wheelCountColors + e.getWheelRotation()) % 13);
        wheelCount = Math.abs(wheelCount + e.getWheelRotation());
        if (FigureHandler.changingPropertyType == 0) {
            FigureHandler.fill = colors[wheelCountColors];
        } else if (FigureHandler.changingPropertyType == 1) {
            FigureHandler.outline = colors[wheelCountColors];
        } else {
            FigureHandler.outlineThickness = wheelCount;
        }

        FigureHandler.refreshFigures();
        e.getComponent().repaint();
    }    
}
