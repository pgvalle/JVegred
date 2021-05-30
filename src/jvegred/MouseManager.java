package jvegred;

import java.awt.*;
import java.awt.event.*;

public class MouseManager extends MouseAdapter implements MouseWheelListener {

    private Color[] colors = {Color.BLACK, Color.BLUE, Color.CYAN, Color.DARK_GRAY,
        Color.GRAY, Color.GREEN, Color.LIGHT_GRAY, Color.MAGENTA, Color.ORANGE,
        Color.PINK, Color.RED, Color.WHITE, Color.YELLOW};

    private int wheelCount = 0, wheelCountColors = 0;

    private int mx, my;

    public void mousePressed(MouseEvent e) {
        this.mx = e.getX();
        this.my = e.getY();
    }

    public void mouseReleased(MouseEvent e) {
        CanvasManager.drag_resize_call = 1;
    }

    public void mouseDragged(MouseEvent e) {
        if (CanvasManager.onResize) {
            CanvasManager.resizeFigure(this.mx, this.my, e.getX(), e.getY());
        } else {
            CanvasManager.dragFigures(this.mx, this.my, e.getX(), e.getY());
        }

        this.mx = e.getX();
        this.my = e.getY();

        e.getComponent().repaint();
    }

    public void mouseClicked(MouseEvent e) {
        char c = ToolBox.getFigureType();
        int x = e.getX();
        int y = e.getY();

        if (!ToolBox.clickFigureButton(x, y)) {
            if (CanvasManager.createFigure(x, y, c));
            else if (CanvasManager.onMultiselection) {
                CanvasManager.clickFigures(x, y);
            } else {
                CanvasManager.clickFigure(x, y);
            }
        }

        e.getComponent().repaint();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        wheelCountColors = Math.abs((wheelCountColors + e.getWheelRotation()) % 13);
        wheelCount = Math.abs(wheelCount + e.getWheelRotation());
        if (CanvasManager.propertyChangeType == 0) {
            CanvasManager.canvas.contextFill = colors[wheelCountColors];
        } else if (CanvasManager.propertyChangeType == 1) {
            CanvasManager.canvas.contextOutline = colors[wheelCountColors];
        } else if (CanvasManager.propertyChangeType == 2) {
            CanvasManager.canvas.contextOutlineThinkness = wheelCount;
        }

        CanvasManager.updateSelectedFigures();
        e.getComponent().repaint();
    }
}