package jvegred.figures;

import java.awt.*;
import java.io.Serializable;

public abstract class Figure implements Serializable {

    protected int x1, x2, y1, y2;
    
    public Figure(int x, int y, int w, int h) {
        this.x1 = x;
        this.x2 = x + w;
        this.y1 = y;
        this.y2 = y + h;
    }

    public void setBoundsPoints(int x1, int y1, int x2, int y2) {
        this.x1 = x1;
        this.x2 = x2;
        this.y1 = y1;
        this.y2 = y2;
    }

    public boolean intersectPoint(int x, int y) {
        return this.x1 <= x && x <= this.x2 && this.y1 <= y && y <= this.y2;
    }

    public abstract Figure copy();

    public abstract void paint(Graphics2D g2d);

    public void paintFocusRect(Graphics2D g2d) {
        g2d.setColor(Color.RED);
        g2d.drawRect(this.x1, this.y1, this.x2 - this.x1, this.y2 - this.y1);
    }

    public void drag(int dx, int dy) {
        // moving x1 and x2
        this.x1 += dx;
        this.x2 += dx;
        // moving y1 and y2
        this.y1 += dy;
        this.y2 += dy;
    }
    
    public void resize(int x, int y, int dx, int dy) {
        // resizing horizontally
        final int distanceTox1 = Math.abs(x - this.x1);
        final int x2 = Math.abs(x - this.x2);
        if (distanceTox1 < x2) {
            this.x1 += dx;
        } else {
            this.x2 += dx;
        }

        // resizing vertically
        final int distanceToy1 = Math.abs(y - this.y1);
        final int distanceToy2 = Math.abs(y - this.y2);
        if (distanceToy1 < distanceToy2) {
            this.y1 += dy;
        } else {
            this.y2 += dy;
        }
    }
}
