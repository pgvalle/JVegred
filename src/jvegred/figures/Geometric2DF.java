package jvegred.figures;

import java.awt.Paint;

/**
 * 2 Dimentional Geometric shapes
 * 
 * @author Pedro Gabriel Nogueira
 */
public abstract class Geometric2DF extends Figure {

    protected Paint outline;
    protected int outlineThickness;

    protected Geometric2DF(int x, int y, float angle, Paint fill, Paint outline, int outlineThickness) {
        super(x, y, angle, fill);

        this.outline = outline;
        this.outlineThickness = outlineThickness;
    }

    public void setOutlinePaint(Paint outline) {
        this.outline = outline;
    }

    public Paint getOutlinePaint() {
        return this.outline;
    }

    public void setOutlineThickness(int thickness) {
        this.outlineThickness = thickness;
    }

    public int getOutlineThickness() {
        return this.outlineThickness;
    }
}
