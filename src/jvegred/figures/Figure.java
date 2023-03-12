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

    public void paintFocus(Graphics2D g2d) {
        final int w = this.x2 - this.x1;
        final int h = this.y2 - this.y1;

        // focus Rectangle
        g2d.setColor(Color.RED);
        g2d.drawRect(this.x1, this.y1, w, h);
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
        final int distanceToLeft = Math.abs(x - this.x1);
        final int distanceToRight = Math.abs(x - this.x2);
        if (distanceToLeft < distanceToRight) {
            this.x1 += dx;
        } else {
            this.x2 += dx;
        }

        // resizing vertically
        final int distanceToTop = Math.abs(y - this.y1);
        final int distanceToBottom = Math.abs(y - this.y2);
        if (distanceToTop < distanceToBottom) {
            this.y1 += dy;
        } else {
            this.y2 += dy;
        }
    }
}
