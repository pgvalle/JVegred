package jvegred;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import jvegred.figures.*;

class Button {

    public static final int SIZE = 32;
    public static final int FIGURE_SIZE = 24;
    // public static final int this.x = 10, this.y = 35;
    
    private Figure figure;

    public Button(Figure figure) {
        this.figure = figure;
    }

    public void paint(Graphics2D g2d) {    
        this.figure.paint(g2d);
    }

    public void paintFocused(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.drawRect(this.figure.x1, this.figure.y1, SIZE, SIZE);

        this.paint(g2d);
    }

    public void paintSelected(Graphics2D g2d) {
        g2d.setColor(Color.BLACK);
        g2d.drawRect(this.figure.x1, this.figure.y1, SIZE, SIZE);

        this.paint(g2d);
    }
}

public class ToolBox implements MouseListener, MouseMotionListener {
    
    public int x, y;
    public Button[] buttons;
    public int focused, selected;

    ToolBox(int x, int y) {
        int offset = (Button.SIZE - Button.FIGURE_SIZE) / 2;

        this.x = x;
        this.y = y;
        this.buttons = new Button[4];

        buttons[0] = new Button(new Elipse(this.x + offset, this.y + offset,
            Button.FIGURE_SIZE, Button.FIGURE_SIZE));
        buttons[1] = new Button(new Rectangl(this.x + offset + Button.SIZE,
            this.y + offset, Button.FIGURE_SIZE, Button.FIGURE_SIZE));
        buttons[2] = new Button(new Triangle(this.x + offset + 2*Button.SIZE,
            this.y + offset, Button.FIGURE_SIZE, Button.FIGURE_SIZE));
        buttons[3] = new Button(new Hexagon(this.x + offset + 3*Button.SIZE,
            this.y + offset, Button.FIGURE_SIZE, Button.FIGURE_SIZE));

        this.focused = this.selected = -1;
    }

    public void paint(Graphics2D g2d) {
        // toolbox background
        g2d.setColor(Color.GRAY);
        g2d.fillRect(this.x, this.y, 4*Button.SIZE, Button.SIZE);
        
        // painting inative buttons
        for (int i = 0; i < this.buttons.length; i++) {
            if (i != this.focused && i != this.selected)
                this.buttons[i].paint(g2d);
        }

        // button which mouse is over it
        if (this.focused != -1)
            this.buttons[this.focused].paintFocused(g2d);
        // current figure type selected
        if (this.selected != -1)
            this.buttons[this.selected].paintSelected(g2d);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        
    }

    @Override
    public void mouseExited(MouseEvent e) {
        
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        
    }

    @Override
    public void mousePressed(MouseEvent e) {
        
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        
    }
}
