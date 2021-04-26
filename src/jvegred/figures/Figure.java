package jvegred.figures;

import java.awt.*;

public abstract class Figure {
    
    public boolean onFocus;
    
    public int x, y;
    public int w, h;
    
    public Paint fill;
    
    protected Figure(int x, int y, int w, int h, Paint fill) {
        this.onFocus = true;

        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
        
        this.fill = fill;
    }
    
    public boolean contains(int x, int y) {
        return this.x <= x && x <= this.x + this.w && this.y <= y && y <= this.y + this.h;
    }
    
    public abstract void paint(Graphics2D g2d);

    public void drag(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }
    
    public void resize(int dx, int dy, int dw, int dh) {
        this.x += dx;
        this.y += dy;
        this.w += dw - dx;
        this.h += dh - dy;
    }
}
