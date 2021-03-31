package jvegred.figures;

/**
 * @author Pedro Gabriel do Valle Nogueira
 * @author pgvalle.nogueira@gmail.com
 */
public class LineF extends Figure {

    private int thickness;

    public LineF(int x, int y, java.awt.Paint paint, int thickness) {
        super(x, y, paint);
        this.thickness = thickness;
    }

    @Override
    public void paint(java.awt.Graphics2D g2d) {
        g2d.setStroke(new java.awt.BasicStroke(this.thickness));
        g2d.setPaint(this.fill);
        g2d.drawLine(this.x, this.y, this.w, this.h);
    }

    @Override
    public void move(int dx, int dy) {
        this.x += dx;
        this.y += dy;
        // (w, h) is a point in space for lines so it must get moved too
        this.w += dx;
        this.h += dy;
    }

    @Override
    public void resize(int dx, int dy, int dw, int dh) {
        this.x += dx;
        this.y += dy;
        this.w += dw;
        this.h += dh;
    }
}
