package jvegred.figures;

import java.awt.*;

// this serves as a base for all geometric 2d shapes
public class Rectangl3 extends Figure {

    public Color fill, outline;
    public int outlineThickness;

    // only base arguments. Otherwise it would be too big of a constructor call
    public Rectangl3(int x, int y, int w, int h) {
        super(x, y, w, h);
        // reasonable defaults
        this.fill = Color.WHITE;
        this.outline = Color.BLACK;
        this.outlineThickness = 2;
    }

    @Override
    public Figure copy() {
        final int width = super.x2 - super.x1;
        final int height = super.y2 - super.y1;
        
        Rectangl3 rect = new Rectangl3(this.x1, this.y1, width, height);
        rect.fill = this.fill;
        rect.outline = this.outline;
        rect.outlineThickness = this.outlineThickness;

        return rect;
    }

    @Override
    public void paint(Graphics2D g2d) {
        final int width = super.x2 - super.x1;
        final int height = super.y2 - super.y1;
        
        // drawing outline
        g2d.setPaint(this.outline);
        g2d.fillRect(super.x1, super.y1, width, height);

        // drawing interior
        g2d.setPaint(this.fill);
        g2d.fillRect(
            super.x1 + this.outlineThickness, super.y1 + this.outlineThickness,
            width - 2*this.outlineThickness, height - 2*this.outlineThickness
        );
    }
}