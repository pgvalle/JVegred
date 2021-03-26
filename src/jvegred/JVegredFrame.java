package jvegred;

import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import java.util.Vector;

import javax.swing.*;
import javax.swing.text.JTextComponent.KeyBinding;
import javax.xml.crypto.dsig.keyinfo.KeyInfo;

import jvegred.figures.Figure;
import jvegred.figures.RectangleF;

public class JVegredFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private RectangleF fig;
    private boolean increase;

    JVegredFrame(String title, int w, int h) {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent event) {
                if (increase) {
                    if (event.getKeyCode() == KeyEvent.VK_UP) {
                        fig.resize(0, -2, 0, 0);
                    } else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                        fig.resize(0, 0, 0, 2);
                    } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                        fig.resize(-2, 0, 0, 0);
                    } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                        fig.resize(0, 0, 2, 0);
                    }
                } else {
                    if (event.getKeyCode() == KeyEvent.VK_UP) {
                        fig.resize(0, 0, 0, -2);
                    } else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                        fig.resize(0, 2, 0, 0);
                    } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                        fig.resize(0, 0, -2, 0);
                    } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                        fig.resize(2, 0, 0, 0);
                    }
                }
                repaint();
            }
        });

        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_I) {
                    increase = true;
                } else if (event.getKeyCode() == KeyEvent.VK_J) {
                    increase = false;
                }
            }
        });

        this.fig = new RectangleF(60, 90, 0.0f, Color.WHITE, Color.BLACK, 0);
        this.fig.resize(0, 0, 60, 60);
        this.increase = true;
        this.setTitle(title);
        this.setSize(w, h);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.LIGHT_GRAY);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());
        this.fig.paint(g2d);
    }
}
