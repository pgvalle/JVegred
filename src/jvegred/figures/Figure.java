package jvegred.figures;

import java.awt.*;
import java.io.Serializable;

public abstract class Figure implements Serializable {

    public int x, y;
    public int w, h;
    public Color fill;
    public Color outline;
    public int outlineThickness;
    
    protected Figure(int x, int y, int w, int h, Color fill, Color outline, int outlineThickness) {
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        this.fill = fill;
        this.outline = outline;
        this.outlineThickness = outlineThickness;
    }

    public abstract Figure clone();

    public abstract void paint(Graphics2D g2d, boolean hasfocus);

    public boolean click(int x, int y) {
        return this.x <= x && x <= this.x + this.w &&
            this.y <= y && y <= this.y + this.h;
    }

    public void drag(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
    
    public void resize(int x1, int y1, int dx, int dy) {
        if (x1 - this.x < this.x + this.w - x1) {
            this.x += dx;
            this.w -= dx;
        } else if (this.x + this.w - x1 < x1 - this.x) {
            this.w += dx;
        }

        if (y1 - this.y < this.y + this.h - y1) {
            this.y += dy;
            this.h -= dy;
        } else if (this.y + this.h - y1 < y1 - this.y) {
            this.h += dy;
        }
    }
}
