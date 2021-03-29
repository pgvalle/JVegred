package jvegred.figures;

import java.awt.Graphics2D;
import java.awt.Paint;

public class EllypseF extends Geometric2DF {

    public EllypseF(int x, int y, float angle, Paint fill, Paint outline, int outlineThickness) {
        super(x, y, angle, fill, outline, outlineThickness);
    }

    @Override
    public void paint(Graphics2D g2d) {
        // applying rotations
        super.paint(g2d);

        // draw outline
        g2d.setPaint(this.outline);
        g2d.fillOval(this.x, this.y, this.w, this.h);

        // drawing inner part
        g2d.setPaint(this.fill);
        g2d.fillOval(
            this.x + this.outlineThickness, this.y + outlineThickness,
            this.w - 2 * this.outlineThickness, this.h - 2 * this.outlineThickness
        );
    }
}
