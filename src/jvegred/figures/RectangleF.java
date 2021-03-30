package jvegred.figures;

import java.awt.Graphics2D;
import java.awt.Paint;

/**
 * @author Pedro Gabriel do Valle Nogueira
 * @author pgvalle.nogueira@gmail.com
 */
public class RectangleF extends Geometric2DF {

    public RectangleF(int x, int y, float angle, Paint fill, Paint outline, int outlineThickness) {
        super(x, y, fill, outline, outlineThickness);
    }

    @Override
    public void paint(Graphics2D g2d) {
        // outer rectangle
        g2d.setPaint(this.outline);
        g2d.fillRect(this.x, this.y, this.w, this.h);
        // inner rectangle
        g2d.setPaint(this.fill);
        g2d.fillRect(
            this.x + this.outlineThickness, this.y + outlineThickness,
            this.w - 2 * this.outlineThickness, this.h - 2 * this.outlineThickness
        );
    }
}
