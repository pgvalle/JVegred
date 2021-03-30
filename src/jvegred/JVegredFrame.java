package jvegred;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import jvegred.figures.Figure;
import jvegred.figures.RectangleF;

public class JVegredFrame extends JFrame {

    private static final long serialVersionUID = 1L;
    private Figure fig;
    private boolean onResize;
    private boolean onRotate;
    private boolean onMoving;

    JVegredFrame(String title, int w, int h) {
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent event) {
                if (onRotate) {
                    if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                        fig.rotate(10);
                    } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                        fig.rotate(-10);
                    }
                } else if (onResize) {
                    if (event.getKeyCode() == KeyEvent.VK_UP) {
                        fig.resize(0, -10, 0, 0);
                    } else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                        fig.resize(0, 0, 0, 10);
                    } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                        fig.resize(-10, 0, 0, 0);
                    } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                        fig.resize(0, 0, 10, 0);
                    }else if (event.getKeyCode() == KeyEvent.VK_W) {
                        fig.resize(0, 0, 0, -10);
                    } else if (event.getKeyCode() == KeyEvent.VK_S) {
                        fig.resize(0, 10, 0, 0);
                    } else if (event.getKeyCode() == KeyEvent.VK_A) {
                        fig.resize(0, 0, -10, 0);
                    } else if (event.getKeyCode() == KeyEvent.VK_D) {
                        fig.resize(10, 0, 0, 0);
                    }
                } else if (onMoving) {
                    if (event.getKeyCode() == KeyEvent.VK_UP) {
                        fig.move(0, -10);
                    } else if (event.getKeyCode() == KeyEvent.VK_DOWN) {
                        fig.move(0, 10);
                    } else if (event.getKeyCode() == KeyEvent.VK_LEFT) {
                        fig.move(-10, 0);
                    } else if (event.getKeyCode() == KeyEvent.VK_RIGHT) {
                        fig.move(10, 0);
                    }
                }
                repaint();
            }
        });

        this.addKeyListener(new KeyAdapter() {
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_I) {
                    onResize = true; onRotate = false; onMoving = false;
                } else if (event.getKeyCode() == KeyEvent.VK_M) {
                    onMoving = true; onRotate = false; onResize = false;
                } else if (event.getKeyCode() == KeyEvent.VK_R) {
                    onRotate = true; onResize = false; onMoving = false;
                }
            }
        });

        this.fig = new RectangleF(60, 90, 0.0f, Color.WHITE, Color.BLACK, 2);
        this.fig.resize(0, 0, 60, 60);
        this.onResize = true;
        this.onRotate = false;
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
