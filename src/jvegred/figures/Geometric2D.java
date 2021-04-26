package jvegred.figures;

import java.awt.*;

public abstract class Geometric2D extends Figure {

    protected Paint outline;
    protected int outlineThickness;

    protected Geometric2D(int x, int y, int w, int h, Paint fill, Paint outline, int outlineThickness) {
        super(x, y, w, h, fill);

        this.outline = outline;
        this.outlineThickness = outlineThickness;
    }
}
