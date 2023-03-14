package jvegred.figures;

import java.awt.*;

public class Elipse extends Rectangl3 {

    public Elipse(int x, int y, int w, int h) {
        super(x, y, w, h);
    }

    @Override
    public void paint(Graphics2D g2d) {
        final int width = super.x2 - super.x1;
        final int height = super.y2 - super.y1;

        // drawing outline
        g2d.setPaint(super.outline);
        g2d.fillOval(super.x1, super.y1, width, height);

        // drawing interior
        g2d.setPaint(super.fill);
        g2d.fillOval(
            super.x1 + super.outlineThickness, super.y1 + super.outlineThickness,
            width - 2*super.outlineThickness, height - 2*super.outlineThickness
        );
    }
}
