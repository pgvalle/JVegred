package jvegred;

import java.awt.*;
import javax.swing.*;
import jvegred.figures.Figure;

public class Button extends MouseListener {
    
    private Figure figure;
    private boolean onFocus, onSelection;

    public Button(Figure figure) {
        this.figure = figure;
        this.onFocus = this.onSelection = false;
    }

    public paint(Graphics2D g2d) {
        // gray background for button
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(focused*B_SIZE, TB_Y, B_SIZE, B_SIZE);

        if (this.onSelection) {
            g2d.setColor(Color.BLACK);
            g2d.drawRect(TB_X + focused*B_SIZE, TB_Y, B_SIZE, B_SIZE);
        } else if (this.onFocus) {
            g2d.setColor(Color.RED);
            g2d.drawRect(TB_X + focused*B_SIZE, TB_Y, B_SIZE, B_SIZE);
        }
    
        this.figure.paint();
    }

    public void mouseEntered(MouseEvent e) {
        this.onFocus = true;
    }

    public void mouseExited(MouseEvent e) {
        this.onFocus = false;
    }

    public void mouseClicked(MouseEvent e) {
        this.onSelection = !this.onSelection;
    }
}
