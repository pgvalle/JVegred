package jvegred.figures;

import java.awt.*;

public class Retangulo extends Figure {

    public Retangulo(int x, int y, int w, int h, Color fill, Color outline, int outlineThickness) {
        super(x, y, w, h, fill, outline, outlineThickness);
    }

    @Override
    public void paint(Graphics2D g2d, boolean hasfocus) {
        // drawing outline
        g2d.setPaint(super.outline);
        g2d.fillRect(super.x, super.y, super.w, super.h);

        // drawing interior
        g2d.setPaint(super.fill);
        g2d.fillRect(
            super.x + super.outlineThickness, super.y + super.outlineThickness,
            super.w - 2*super.outlineThickness, super.h - 2*super.outlineThickness
        );

        if (hasfocus) {
            g2d.setColor(Color.RED);
            g2d.drawRect(super.x, super.y, super.w, super.h);
        }
    }

    @Override
    public Figure clone() {
        return new Retangulo(x, y, w, h, fill, outline, outlineThickness);
    }
}
