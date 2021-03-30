package jvegred;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import jvegred.figures.*;

public class JVegredFrame extends JFrame {

    private static final long serialVersionUID = 1L;

    JVegredFrame(String title, int w, int h) {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle(title);
        this.setSize(w, h);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
    }
}
