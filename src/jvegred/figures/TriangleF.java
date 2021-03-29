package jvegred.figures;

import java.awt.Graphics2D;
import java.awt.Paint;

public class TriangleF extends Geometric2DF {

    private int[] xOutline, xFill;
    private int[] yOutline, yFill;

    public TriangleF(int x, int y, float angle, Paint fill, Paint outline, int outlineThickness) {
        super(x, y, angle, fill, outline, outlineThickness);
        this.xOutline = new int[3];
        this.xFill = new int[3];
        this.yOutline = new int[3];
        this.yFill = new int[3];

        this.setOutlinePoints();
        this.setFillPoints();
    }

    private void setFillPoints() {
        // using Math.atan to get bottom corner angle
        double ba = Math.atan(this.h / (this.w / 2.0)) / 2.0;
        // using bottom corner angle to get top angle
        double ta = (Math.PI - 4.0 * ba) / 2.0;

        // offset for x and y coordinates of fill triangle
        int dx = (int) (this.outlineThickness / Math.tan(ba));
        int dy = (int) (this.outlineThickness / Math.sin(ta));

        this.xFill[0] = this.x + this.w / 2;
        this.yFill[0] = this.y + dy;

        this.xFill[1] = this.x + this.w - dx;
        this.yFill[1] = this.y + this.h - this.outlineThickness;

        this.xFill[2] = this.x + dx;
        this.yFill[2] = this.y + this.h - this.outlineThickness;

        if (this.yFill[1] - this.yFill[0] < 0) {
            this.yFill[2] = this.yFill[1] = this.yFill[0];
        }

        if (this.xFill[1] - this.xFill[2] < 0) {
            this.xFill[1] = this.xFill[2];
        }
    }

    private void setOutlinePoints() {
        this.xOutline[0] = this.x + this.w / 2;
        this.yOutline[0] = this.y;

        this.xOutline[1] = this.x + this.w;
        this.yOutline[1] = this.y + this.h;
        
        this.xOutline[2] = this.x;
        this.yOutline[2] = this.y + this.h;

        if (this.yOutline[1] - this.yOutline[0] < 0) {
            this.yOutline[2] = this.yOutline[1] = this.yOutline[0];
        }

        if (this.xOutline[1] - this.xOutline[2] < 0) {
            this.xOutline[1] = this.xOutline[2];
        }
    }

    @Override
    public void paint(Graphics2D g2d) {
        // applying rotations
        super.paint(g2d);

        // draw outline
        g2d.setPaint(this.outline);
        g2d.fillPolygon(this.xOutline, this.yOutline, 3);

        // drawing inner part
        g2d.setPaint(this.fill);
        g2d.fillPolygon(this.xFill, this.yFill, 3);
    }

    @Override
    public void resize(int dx, int dy, int dw, int dh) {
        super.resize(dx, dy, dw, dh);

        this.setOutlinePoints();
        this.setFillPoints();
    }
}
