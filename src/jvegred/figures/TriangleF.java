package jvegred.figures;

import java.awt.Graphics2D;
import java.awt.Paint;

/**
 * @author Pedro Gabriel do Valle Nogueira
 * @author pgvalle.nogueira@gmail.com
 */
public class TriangleF extends Geometric2DF {

    private int[] xOuter, yOuter;
    private int[] xInner, yInner;

    public TriangleF(int x, int y, float angle, Paint fill, Paint outline, int outlineThickness) {
        super(x, y, angle, fill, outline, outlineThickness);
        this.xOuter = new int[3];
        this.yOuter = new int[3];
        this.xInner = new int[3];
        this.yInner = new int[3];

        this.setOuterPoints();
        this.setInnerPoints();
    }

    private void setOuterPoints() {
        // setup outer triangle points
        this.xOuter[0] = this.x + this.w / 2;
        this.yOuter[0] = this.y;
        this.xOuter[1] = this.x + this.w;
        this.yOuter[1] = this.y + this.h;
        this.xOuter[2] = this.x;
        this.yOuter[2] = this.y + this.h;

        // check if outer triangle points "passed" one another
        if (this.xOuter[1] - this.xOuter[2] < 0) {
            this.xOuter[1] = this.xOuter[2];
        }
        if (this.yOuter[1] - this.yOuter[0] < 0) {
            this.yOuter[2] = this.yOuter[1] = this.yOuter[0];
        }
    }

    private void setInnerPoints() {
        // use arctan to get bottom corner angle
        double ba = Math.atan(this.h / (this.w / 2.0)) / 2.0;
        // use bottom corner angle to get top angle
        double ta = (Math.PI - 4.0 * ba) / 2.0;
        // offset of x and y coordinates for innter triangle
        int dx = (int) (this.outlineThickness / Math.tan(ba));
        int dy = (int) (this.outlineThickness / Math.sin(ta));

        // setup inner triangle points
        this.xInner[0] = this.x + this.w / 2;
        this.yInner[0] = this.y + dy;
        this.xInner[1] = this.x + this.w - dx;
        this.yInner[1] = this.y + this.h - this.outlineThickness;
        this.xInner[2] = this.x + dx;
        this.yInner[2] = this.y + this.h - this.outlineThickness;

        // check if inner triangle points "passed" one another
        if (this.xInner[1] - this.xInner[2] < 0) {
            this.xInner[1] = this.xInner[2];
        }
        if (this.yInner[1] - this.yInner[0] < 0) {
            this.yInner[2] = this.yInner[1] = this.yInner[0];
        }
    }

    @Override
    public void paint(Graphics2D g2d) {
        // rotations
        super.paint(g2d);
        // outer triangle
        g2d.setPaint(this.outline);
        g2d.fillPolygon(this.xOuter, this.yOuter, 3);
        // inner triangle
        g2d.setPaint(this.fill);
        g2d.fillPolygon(this.xInner, this.yInner, 3);
    }

    @Override
    public void move(int dx, int dy) {
        // global coordinates
        this.x += dx;
        this.y += dy;
        // outer triangle points
        this.xOuter[0] += dx;
        this.yOuter[0] += dy;
        this.xOuter[1] += dx;
        this.yOuter[1] += dy;
        this.xOuter[2] += dx;
        this.yOuter[2] += dy;
        // inner triangle points
        this.xInner[0] += dx;
        this.yInner[0] += dy;
        this.xInner[1] += dx;
        this.yInner[1] += dy;
        this.xInner[2] += dx;
        this.yInner[2] += dy;
    }

    @Override
    public void resize(int dx, int dy, int dw, int dh) {
        super.resize(dx, dy, dw, dh);
        // update inner and outer triangle points
        this.setOuterPoints();
        this.setInnerPoints();
    }
}
