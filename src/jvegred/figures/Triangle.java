package jvegred.figures;

import java.awt.*;

public class Triangle extends Retangulo {

    private int[] xOutPoints, yOutPoints;
    private int[] xInPoints, yInPoints;

    public Triangle(int x, int y, int w, int h) {
        super(x, y, w, h);

        this.xOutPoints = new int[3];
        this.yOutPoints = new int[3];
        this.xInPoints = new int[3];
        this.yInPoints = new int[3];

        this.remapPoints();
    }

    private void remapPoints() {
        int w = super.x2 - super.x1;
        int h = super.y2 - super.y1;

        // outline points
        this.xOutPoints[0] = super.x1 + w / 2;
        this.yOutPoints[0] = super.y1;

        this.xOutPoints[1] = super.x2;
        this.yOutPoints[1] = super.y2;

        this.xOutPoints[2] = super.x1;
        this.yOutPoints[2] = super.y2;

        // inside points
        double angle = Math.atan(2.0 * h / w);
        int dx = (int) (super.outlineThickness / Math.tan(angle / 2.0));
        int dy = (int) (super.outlineThickness / Math.sin(Math.PI / 2.0 - angle));

        this.xInPoints[0] = super.x1 + w / 2;
        this.yInPoints[0] = super.y1 + dy;

        this.xInPoints[1] = super.x2 - dx;
        this.yInPoints[1] = super.y2 - super.outlineThickness;

        this.xInPoints[2] = super.x1 + dx;
        this.yInPoints[2] = super.y2 - super.outlineThickness;
    }

    @Override
    public Figure copy() {
        int w = super.x2 - super.x1;
        int h = super.y2 - super.y1;
        Triangle tria = new Triangle(super.x1, super.y1, w, h);
        System.arraycopy(this.xOutPoints, 0, tria.xOutPoints, 0, 3);
        System.arraycopy(this.yOutPoints, 0, tria.yOutPoints, 0, 3);
        System.arraycopy(this.xInPoints, 0, tria.xInPoints, 0, 3);
        System.arraycopy(this.yInPoints, 0, tria.yInPoints, 0, 3);

        return tria;
    }

    @Override
    public void paint(Graphics2D g2d) {
        // drawing outline
        g2d.setPaint(super.outline);
        g2d.fillPolygon(this.xOutPoints, this.yOutPoints, 3);

        // drawing interior
        g2d.setPaint(super.fill);
        g2d.fillPolygon(this.xInPoints, this.yInPoints, 3);
    }

    @Override
    public void drag(int dx, int dy) {
        super.drag(dx, dy);

        for (int i = 0; i < 3; i++) {
            // out triangle
            this.xOutPoints[i] += dx;
            this.yOutPoints[i] += dy;
            // inn triangle
            this.xInPoints[i] += dx;
            this.yInPoints[i] += dy;
        }
    }

    public void resize(int x, int y, int dx, int dy) {
        super.resize(x, y, dx, dy);
        this.remapPoints();
    }
}
