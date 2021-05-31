package jvegred;

import java.awt.MouseInfo;
import java.awt.event.*;

class KeyboardManager extends KeyAdapter {

    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_CONTROL) {
            CanvasManager.onMultiselection = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        char keyChar = e.getKeyChar();
        int x = MouseInfo.getPointerInfo().getLocation().x - e.getComponent().getX();
        int y = MouseInfo.getPointerInfo().getLocation().y - e.getComponent().getY();

        if (keyCode == KeyEvent.VK_CONTROL) {
            CanvasManager.onMultiselection = false;
            return;
        }
        
        if (!controlActions(x, y, keyCode)) {
            if (CanvasManager.createFigure(x, y, keyChar));
            else if (keyCode == KeyEvent.VK_DELETE) {
                CanvasManager.deleteFigures();
            } else if (keyChar == 'f') {
                CanvasManager.propertyChangeType = 0;
            } else if (keyChar == 'o') {
                CanvasManager.propertyChangeType = 1;
            } else if (keyChar == 'p') {
                CanvasManager.propertyChangeType = 2;
            }
        }

        e.getComponent().repaint();
    }

    public void keyTyped(KeyEvent e) {

    }

    private boolean controlActions(int x, int y, int keyCode) {
        if (!CanvasManager.onMultiselection) return false;

        switch (keyCode) {
            case KeyEvent.VK_A:
                CanvasManager.selectAllFigures(); break;
            case KeyEvent.VK_S:
                CanvasManager.serializeCanvas(); break;
            case KeyEvent.VK_O:
                CanvasManager.deserializeCanvas(); break;
            case KeyEvent.VK_C:
                CanvasManager.copyFigures(); break;
            case KeyEvent.VK_V:
                CanvasManager.pasteFigures(x, y); break;
            case KeyEvent.VK_R:
                CanvasManager.toggleResize(); break;
            default:
                return false;
        }

        return true;
    }
}
