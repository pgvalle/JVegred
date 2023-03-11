package jvegred.figures;

import java.awt.*;
import java.io.Serializable;

public abstract class Figure implements Serializable {

    public int x1, x2;
    public int y1, y2;
    
    protected Figure(int x, int y, int w, int h) {
        this.x1 = x;     // left
        this.x2 = x + w; // right
        this.y1 = y;     // top
        this.y2 = y + h; // bottom
    }

    public abstract Figure copy();

    public abstract void paint(Graphics2D g2d);
    public abstract void paintFocused(Graphics2D g2d);

    public boolean inBounds(int x, int y) {
        return this.x1 <= x && x <= this.x2 && this.y1 <= y && y <= this.y2;
    }

    public void drag(int dx, int dy) {
        // moving left and right
        this.x1 += dx;
        this.x2 += dx;
        // moving top and bottom
        this.y1 += dy;
        this.y2 += dy;
    }
    
    public void resize(int x, int y, int dx, int dy) {
        // resizing horizontally
        int distanceToLeft = Math.abs(x - this.x1);
        int distanceToRight = Math.abs(x - this.x2);
        if (distanceToLeft < distanceToRight) {
            this.x1 += dx;
        } else {
            this.x2 += dx;
        }

        // resizing vertically
        int distanceToTop = Math.abs(y - this.y1);
        int distanceToBottom = Math.abs(y - this.y2);
        if (distanceToTop < distanceToBottom) {
            this.y1 += dy;
        } else {
            this.y2 += dy;
        }
    }
}
