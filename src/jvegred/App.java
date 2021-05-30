package jvegred;

import java.awt.*;
import javax.swing.*;

public class App extends JFrame {

    public static void main(String[] args) {
        ToolBox.initButtons();
        App app = new App();
        app.setVisible(true);
    }

    public App() {
        super.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        super.setTitle("JVegred");
        super.setSize(500, 500);
        
        MouseManager mManager = new MouseManager();
        this.addMouseListener(mManager);
        this.addMouseMotionListener(mManager);
        this.addMouseWheelListener(mManager);

        KeyboardManager kManager = new KeyboardManager();
        this.addKeyListener(kManager);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;

        g2d.setPaint(Color.LIGHT_GRAY);
        g2d.fillRect(0, 0, this.getWidth(), this.getHeight());

        CanvasManager.paintCanvas(g2d);
        ToolBox.paintButtons(g2d);
    }
}
