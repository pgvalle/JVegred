package jvegred.figures;

import java.awt.*;

// this serves as a base for all geometric 2d shapes
public class Rectangl extends Figure {

    public Color fill;
    public Color outline;
    public int outlineThickness;

    // only base arguments. Otherwise it would be too big of a constructor call
    public Rectangl(int x, int y, int w, int h) {
        super(x, y, w, h);
        // reasonable defaults
        this.fill = Color.WHITE;
        this.outline = Color.BLACK;
        this.outlineThickness = 2;
    }

    @Override
    public Figure copy() {
        final int w = super.x2 - super.x1;
        final int h = super.y2 - super.y1;
        
        Rectangl rect = new Rectangl(this.x1, this.y1, w, h);
        rect.fill = this.fill;
        rect.outline = this.outline;
        rect.outlineThickness = this.outlineThickness;

        return rect;
    }

    @Override
    public void paint(Graphics2D g2d) {
        final int w = super.x2 - super.x1;
        final int h = super.y2 - super.y1;
        
        // drawing outline
        g2d.setPaint(this.outline);
        g2d.fillRect(super.x1, super.y1, w, h);

        // drawing interior
        g2d.setPaint(this.fill);
        g2d.fillRect(super.x1 + this.outlineThickness, super.y1 + this.outlineThickness,
            w - 2*this.outlineThickness, h - 2*this.outlineThickness);
    }
}