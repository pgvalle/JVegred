package jvegred;

import java.awt.*;
import javax.swing.*;

import jvegred.figures.*;

public class App extends JFrame {

    public FigureManager figureManager;

    public static void main(String[] args) {
        new App();
    }

    public App() {
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setTitle("JVegred");
        super.setSize(500, 500);
        super.setVisible(true);
        this.figureManager = new FigureManager();
        this.figureManager.addFigure(new Elipse(10, 50, 60, 30));
        this.figureManager.addFigure(new Triangle(70, 100, 100, 100));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.DARK_GRAY);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        this.figureManager.paintFigures(g2d);
    }
}
