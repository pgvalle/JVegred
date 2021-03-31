package jvegred.figures;

/**
 * @author Pedro Gabriel do Valle Nogueira
 * @author pgvalle.nogueira@gmail.com
 */
public class EllypseF extends Geometric2DF {

    public EllypseF(int x, int y, java.awt.Paint fill, java.awt.Paint outline, int outlineThickness) {
        super(x, y, fill, outline, outlineThickness);
    }

    @Override
    public void paint(java.awt.Graphics2D g2d) {
        // outer ellypse
        g2d.setPaint(this.outline);
        g2d.fillOval(this.x, this.y, this.w, this.h);
        // inner ellypse
        g2d.setPaint(this.fill);
        g2d.fillOval(
            this.x + this.outlineThickness, this.y + outlineThickness,
            this.w - 2 * this.outlineThickness, this.h - 2 * this.outlineThickness
        );
    }
}
