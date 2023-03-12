package jvegred.figures;

import java.awt.*;

// this serves as a base for all geometric 2d shapes
public class Rectangle extends Figure {

    public Color fill;
    public Color outline;
    public int outlineThickness;

    // only base arguments. Otherwise it would be too big of a constructor call
    public Rectangle(int x, int y, int w, int h) {
        super(x, y, w, h);
        // reasonable defaults
        this.fill = Color.WHITE;
        this.outline = Color.BLACK;
        this.outlineThickness = 1;
    }

    public Figure copy() {
        int w = super.x2 - super.x1;
        int h = super.y2 - super.y1;
        
        Rectangle rect = new Rectangle(this.x1, this.y1, w, h);
        rect.fill = new Color(this.fill);
        rect.outline = new Color(this.outline);
        rect.outlineThickness = new Color(this.outlineThickness);

        return rect;
    }

    public void paint(Graphics2D g2d) {
        int w = super.x2 - super.x1;
        int h = super.y2 - super.y1;
        
        // drawing outline
        g2d.setPaint(this.outline);
        g2d.fillRect(super.x1, super.y1, w, h);

        // drawing interior
        g2d.setPaint(super.fill);
        g2d.fillRect(
            super.x1 + this.outlineThickness, super.y1 + this.outlineThickness,
            w - 2*this.outlineThickness, h - 2*this.outlineThickness
        );
    }

    public abstract void paintFocused(Graphics2D g2d) {
        int w = super.x2 - super.x1;
        int h = super.y2 - super.y1;

        // focus rectangle
        g2d.setColor(Color.RED);
        g2d.drawRect(super.x1, super.y1, w, h);

        this.paint(g2d);
    }
}