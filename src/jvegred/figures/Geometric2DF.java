package jvegred.figures;

import java.awt.Paint;

/**
 * Base class for 2 dimentional geometric shapes
 * 
 * @author Pedro Gabriel do Valle Nogueira
 * @author pgvalle.nogueira@gmail.com
 */
public abstract class Geometric2DF extends Figure {

    protected Paint outline;
    protected int outlineThickness;

    protected Geometric2DF(int x, int y, Paint fill, Paint outline, int outlineThickness) {
        super(x, y, fill);
        this.outline = outline;
        this.outlineThickness = outlineThickness;
    }

    @Override
    public void resize(int dx, int dy, int dw, int dh) {
        this.x += (dx - dw) / 2;
        this.y += (dy - dh) / 2;
        //
        this.w += dw - dx;
        this.h += dh - dy;

        // (w, h) can't be less than (0, 0)
        // subtract (dx, dy) from (x, y) to stop downside-right moving behavior
        if (this.w < 0) {
            this.x -= (dx - dw) / 2;
            this.w = 0;
        }
        if (this.h < 0) {
            this.y -= (dy - dh) / 2;
            this.h = 0;
        }        
    }

    @Override
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public void setOutlinePaint(Paint outline) { this.outline = outline; }
    public Paint getOutlinePaint() { return this.outline; }

    public void setOutlineThickness(int thickness) { this.outlineThickness = thickness; }
    public int getOutlineThickness() { return this.outlineThickness; }    
}
