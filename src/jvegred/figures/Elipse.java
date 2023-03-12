package jvegred.figures;

import java.awt.*;

public class Elipse extends Rectangle {

    public Elipse(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void paint(Graphics2D g2d) {
        int w = super.x2 - super.x1;
        int h = super.y2 - super.y1;

        // drawing outline
        g2d.setPaint(super.outline);
        g2d.fillOval(super.x1, super.y1, w, h);

        // drawing interior
        g2d.setPaint(super.fill);
        g2d.fillOval(
            super.x1 + super.outlineThickness, super.y1 + super.outlineThickness,
            w - 2*super.outlineThickness, h - 2*super.outlineThickness
        );
    }
}
