package jvegred.figures;

/**
 * Base Class for Figures
 * 
 * @author Pedro Gabriel do Valle Nogueira
 * @author pgvalle.nogueira@gmail.com
 */
public abstract class Figure {

    protected int x, y;
    protected int w, h;
    protected java.awt.Paint fill;

    /**
     * All paremeters are self-explanatory. Important thing is (w, h) will
     * always be initialized with (0, 0), because as soon as when the user
     * creates the figure they will have to "resize" it.
     */
    protected Figure(int x, int y, java.awt.Paint fill) {
        this.x = x;
        this.y = y;
        this.w = 0;
        this.h = 0;
        this.fill = fill;
    }

    public abstract void paint(java.awt.Graphics2D g2d);
    public abstract void move(int dx, int dy);
    public abstract void resize(int dx, int dy, int dw, int dh);

    public void setFill(java.awt.Paint fill) { this.fill = fill; }
    public java.awt.Paint getFill() { return this.fill; }
}