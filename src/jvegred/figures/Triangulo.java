package jvegred.figures;

import java.awt.*;

public class Triangulo extends Geometric2D {

    private int[] xOutPoints, yOutPoints;
    private int[] xInnPoints, yInnPoints;

    public Triangulo(int x, int y, int w, int h, Paint fill, Paint outline, int outlineThickness) {
        super(x, y, w, h, fill, outline, outlineThickness);

        this.xOutPoints = new int[3];
        this.yOutPoints = new int[3];
        this.xInnPoints = new int[3];
        this.yInnPoints = new int[3];

        this.remapPoints();
    }

    private void remapPoints() {
        // outline points
        this.xOutPoints[0] = this.x + this.w / 2;
        this.yOutPoints[0] = this.y;

        this.xOutPoints[1] = this.x + this.w;
        this.yOutPoints[1] = this.y + this.h;

        this.xOutPoints[2] = this.x;
        this.yOutPoints[2] = this.y + this.h;

        // inside points
        double angle = Math.atan(2.0 * this.h / this.w);
        int dx = (int) (this.outlineThickness / Math.tan(angle / 2.0));
        int dy = (int) (this.outlineThickness / Math.sin(Math.PI / 2.0 - angle));

        this.xInnPoints[0] = this.x + this.w / 2;
        this.yInnPoints[0] = this.y + dy;

        this.xInnPoints[1] = this.x + this.w - dx;
        this.yInnPoints[1] = this.y + this.h - this.outlineThickness;

        this.xInnPoints[2] = this.x + dx;
        this.yInnPoints[2] = this.y + this.h - this.outlineThickness;
    }

    @Override
    public void paint(Graphics2D g2d) {
        // drawing outline
        g2d.setPaint(super.outline);
        g2d.fillPolygon(this.xOutPoints, this.yOutPoints, 3);

        // drawing interior
        g2d.setPaint(super.fill);
        g2d.fillPolygon(this.xInnPoints, this.yInnPoints, 3);
    }

    @Override
    public void drag(int dx, int dy) {
        super.drag(dx, dy);

        // out triangle
        this.xOutPoints[0] += dx;
        this.yOutPoints[0] += dy;

        this.xOutPoints[1] += dx;
        this.yOutPoints[1] += dy;

        this.xOutPoints[2] += dx;
        this.yOutPoints[2] += dy;

        // inn triangle
        this.xInnPoints[0] += dx;
        this.yInnPoints[0] += dy;

        this.xInnPoints[1] += dx;
        this.yInnPoints[1] += dy;

        this.xInnPoints[2] += dx;
        this.yInnPoints[2] += dy;
    }
    
    @Override
    public void resize(int dx, int dy, int dw, int dh) {
        super.resize(dx, dy, dw, dh);
        this.remapPoints();
    }
}
