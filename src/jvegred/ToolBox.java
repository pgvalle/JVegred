package jvegred;

import java.awt.*;

import jvegred.figures.*;

public class ToolBox {

    public static final int B_SIZE = 32;
    public static final int F_SIZE = 24;
    public static final int TB_X = 10, TB_Y = 35;
    
    public static Button[] fbuttons = new Button[4];

    public static int focused;

    public static void initButtons() {
        int offset = (B_SIZE-F_SIZE) / 2;

        fbuttons[0] = new Button(
            new Elipse(TB_X + offset, TB_Y + offset,
            F_SIZE, F_SIZE, Color.WHITE, Color.BLACK, 2),
                'e'
        );

        fbuttons[1] = new Button(
            new Retangulo(TB_X + B_SIZE + offset, TB_Y + offset,
            F_SIZE, F_SIZE, Color.WHITE, Color.BLACK, 2),
                'r'
        );

        fbuttons[2] = new Button(
            new Triangulo(TB_X + 2*B_SIZE + offset, TB_Y + offset,
            F_SIZE, F_SIZE, Color.WHITE, Color.BLACK, 2),
                't'
        );

        fbuttons[3] = new Button(
            new Hexagono(TB_X + 3*B_SIZE + offset, TB_Y + offset,
            F_SIZE, F_SIZE, Color.WHITE, Color.BLACK, 2),
                'h'
        );

        focused = -1;
    }

    public static void paintButtons(Graphics2D g2d) {
        g2d.setColor(Color.GRAY);
        g2d.fillRect(TB_X, TB_Y, 4*B_SIZE, B_SIZE);

        if (focused > -1) {
            g2d.setColor(Color.DARK_GRAY);
            g2d.fillRect(TB_X + focused*B_SIZE, TB_Y, B_SIZE, B_SIZE);
            g2d.setColor(Color.RED);
            g2d.drawRect(TB_X + focused*B_SIZE, TB_Y, B_SIZE, B_SIZE);
        }
        
        for (Button b : fbuttons) b.innerfig.paint(g2d, false);
    }

    public static boolean clickFigureButton(int x, int y) {
        for (int i = 0; i < fbuttons.length; i++) {
            boolean inX = x > TB_X + i*B_SIZE && x < TB_X + (i+1)*B_SIZE;
            boolean inY = y > TB_Y && y < TB_Y + B_SIZE;

            if (inX && inY) {
                focused = (focused == i ? -1 : i);
                return true;
            }
        }

        focused = -1;
        return false;
    }

    public static char getFigureType() {
        if (focused == -1) return '\n';
        return fbuttons[focused].ftype;
    }
}
