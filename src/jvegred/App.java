package jvegred;

import java.awt.*;
import javax.swing.*;

public class App extends JFrame {

    public static void main(String[] args) {
        new App();
    }

    public App() {
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setTitle("JVegred");
        super.setSize(500, 500);
        super.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
    }
}
