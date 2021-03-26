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

    protected int x, y;
    protected int w, h; // will work as a second point for lines
    protected float angle;
    protected Paint fill;

    /**
     * All paremeters are self-explanatory. Important thing is that (w, h) will
     * always be initialized with (0, 0), because as soon as when the user creates
     * the figure they will have to "resize" it.
     */
    protected Figure(int x, int y, float angle, Paint fill) {
        this.x = x; this.y = y;
        this.w = 0; this.h = 0;
        this.angle = angle;
        this.fill = fill;
    }

    public void paint(Graphics2D g2d) {
        // common behavior of paint method for all figures
        // all figures should be able to rotate
        g2d.rotate(Math.toRadians(this.angle), this.x, this.y);
        // rest of painting behavior must be defined
        // call super.paint(g2d) or paste the code above before rendering code
    }

    public void setFill(Paint fill) { this.fill = fill; }
    public Paint getFill() { return this.fill; }

    public void move(int dx, int dy) {
        this.x += dx; this.y += dy;
    }

    public void setX(int x) { this.x = x; }
    public int getX() { return this.x; }

    public void setY(int y) { this.y = y; }
    public int getY() { return this.y;}

    public void resize(int dx, int dy, int dw, int dh) {
        // actual resize operation
        this.move(dx, dy);
        // when decreasing x and y, subtract the (dx, dy) from
        // (w, h) to make the resize effect.
        this.w += dw - dx;
        this.h += dh - dy;

        // (w, h) can't be less than (0, 0)
        // (x, y) can't increase when (w, h) are less than (0, 0)
        if (this.w < 0) {
            this.x -= dx; // rightside moving behavior if taken away
            this.w = 0;
        }

        if (this.h < 0) {
            this.y -= dy; // downside moving behavior if taken away
            this.h = 0;
        }
    }

    public void rotate(float dangle) { this.angle += dangle; }

    public void setRotationAngle(float angle) { this.angle = angle; }
    public float getRotationAngle() { return this.angle; }
}
