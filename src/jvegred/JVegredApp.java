package jvegred;

import java.awt.*;
import javax.swing.*;

import java.awt.event.*;
import jvegred.figures.*;

public class JVegredApp extends JFrame {

    public static void main(String[] args) {
        JVegredApp app = new JVegredApp();
        app.setVisible(true);
    }

    public JVegredApp() {
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setTitle("JVegred App");
        super.setSize(800, 600);

        MouseMan mouseHandler = new MouseMan();

        super.addMouseListener(mouseHandler);
        super.addMouseMotionListener(mouseHandler);
        super.addMouseWheelListener(mouseHandler);

        this.initKeyBindings();
    }

    public void initKeyBindings() {
        JPanel pane = new JPanel();
        this.add(pane);

        pane.getInputMap().put(KeyStroke.getKeyStroke("control released R"), "resize toggle");
        pane.getActionMap().put("resize toggle", new AnAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (FigureHandler.onResizeMode = !FigureHandler.onResizeMode) {
                    for (Figure f : FigureHandler.figures) {
                        f.onFocus = false;
                    }
                    System.out.println("Multiple Selection Off");
                    FigureHandler.onMultipleSelectMode = false;
                }
                System.out.format("Resize mode %s.\n", FigureHandler.onResizeMode ? "On" : "Off");

                repaint();
            }
        });

        pane.getInputMap().put(KeyStroke.getKeyStroke("control CONTROL"), "multiple selection on");
        pane.getActionMap().put("multiple selection on", new AnAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!FigureHandler.onResizeMode) {
                    System.out.println("Multiple Selection On");
                    FigureHandler.onMultipleSelectMode = true;
                }
            }
        });

        pane.getInputMap().put(KeyStroke.getKeyStroke("released CONTROL"), "multiple selection off");
        pane.getActionMap().put("multiple selection off", new AnAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (FigureHandler.onMultipleSelectMode) {
                    System.out.println("Multiple Selection Off");
                    FigureHandler.onMultipleSelectMode = false;
                }
            }
        });

        pane.getInputMap().put(KeyStroke.getKeyStroke("released DELETE"), "delete focused");
        pane.getActionMap().put("delete focused", new AnAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FigureHandler.removeFiguresOnFocus();
                repaint();
            }
        });

        pane.getInputMap().put(KeyStroke.getKeyStroke("E"), "new ellipse");
        pane.getActionMap().put("new ellipse", new AnAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FigureHandler.x = getMousePosition().x - FigureHandler.w / 2;
                FigureHandler.y = getMousePosition().y - FigureHandler.h / 2;
                FigureHandler.addFigure('e');
                repaint();
            }
        });

        pane.getInputMap().put(KeyStroke.getKeyStroke("R"), "new rectangle");
        pane.getActionMap().put("new rectangle", new AnAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FigureHandler.x = getMousePosition().x - FigureHandler.w / 2;
                FigureHandler.y = getMousePosition().y - FigureHandler.h / 2;
                FigureHandler.addFigure('r');
                repaint();
            }
        });

        pane.getInputMap().put(KeyStroke.getKeyStroke("T"), "new triangle");
        pane.getActionMap().put("new triangle", new AnAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FigureHandler.x = getMousePosition().x - FigureHandler.w / 2;
                FigureHandler.y = getMousePosition().y - FigureHandler.h / 2;
                FigureHandler.addFigure('t');
                repaint();
            }
        });

        pane.getInputMap().put(KeyStroke.getKeyStroke("S"), "new string");
        pane.getActionMap().put("new string", new AnAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FigureHandler.x = getMousePosition().x - FigureHandler.w / 2;
                FigureHandler.y = getMousePosition().y - FigureHandler.h / 2;
                FigureHandler.addFigure('s');
                repaint();
            }
        });

        pane.getInputMap().put(KeyStroke.getKeyStroke("F"), "set fill");
        pane.getActionMap().put("set fill", new AnAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FigureHandler.changingPropertyType = 0;
            }
        });

        pane.getInputMap().put(KeyStroke.getKeyStroke("O"), "set outline");
        pane.getActionMap().put("set outline", new AnAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FigureHandler.changingPropertyType = 1;
            }
        });

        pane.getInputMap().put(KeyStroke.getKeyStroke("P"), "set outlineThickness");
        pane.getActionMap().put("set outlineThickness", new AnAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FigureHandler.changingPropertyType = 2;
            }
        });
    }
    
    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        // refreshing background
        g2d.setPaint(super.getBackground());
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        FigureHandler.paintFigures(g2d);
    }
}

