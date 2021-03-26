package jvegred.figures;

import java.awt.Graphics2D;
import java.awt.Paint;

public class RectangleF extends Geometric2DF {

    public RectangleF(int x, int y, float angle, Paint fill, Paint outline, int outlineThickness) {
        super(x, y, angle, fill, outline, outlineThickness);
    }

    @Override
    public void paint(Graphics2D g2d) {
        // must call to apply rotations
        super.paint(g2d);

        // drawing inner part
        g2d.setPaint(this.fill);
        g2d.fillRect(this.x, this.y, this.w, this.h);

        // draw outline
        g2d.setPaint(this.outline);
        g2d.drawRect(this.x, this.y, this.w, this.h);
    }
}
