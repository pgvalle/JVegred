package jvegred.figures;

import java.awt.Graphics2D;
import java.awt.Paint;

/**
 * Base Class for Figures
 * 
 * @author Pedro Gabriel do Valle Nogueira
 * @author pgvalle.nogueira@gmail.com
 */
public abstract class Figure {

    protected int x, y; // position
    // for lines dimensions will work as the second point
    protected int w, h; // dimensions

    protected float angle;

    protected Paint fill;

    /**
     * All paremeters are self-explanatory. Important thing is that w and h will
     * always be initialized with zero, because as soon as when the user creates the
     * figure they will have to resize it.
     */
    protected Figure(int x, int y, float angle, Paint fill) {
        this.x = x;
        this.y = y;
        this.w = 0;
        this.h = 0;

        this.angle = angle;

        this.fill = fill;
    }

    public void paint(Graphics2D g2d) {
        // all figures should be able to rotate
        g2d.rotate(Math.toRadians(this.angle), this.x, this.y);
        // rest of painting behaviour defined in each class
    }

    public void setFill(Paint fill) {
        this.fill = fill;
    }

    public Paint getFill() {
        return this.fill;
    }

    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getX() {
        return this.x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getY() {
        return this.y;
    }

    public void resize(int dx, int dy, int dw, int dh) {
        this.move(dx, dy);
        this.w += this.w + dw - dx >= 0 ? dw - dx : 0;
        this.h += this.h + dh - dy >= 0 ? dh - dy : 0;
    }

    public void rotate(float dangle) {
        this.angle += dangle;
    }

    public void setRotationAngle(float angle) {
        this.angle = angle;
    }

    public float getRotationAngle() {
        return this.angle;
    }
}
