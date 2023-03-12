package jvegred.figures;

import java.awt.*;

public class Hexagon extends Rectangl {

    private int[] xOutPoints, yOutPoints;
    private int[] xInPoints, yInPoints;

    public Hexagon(int x, int y, int w, int h) {
        super(x, y, w, h);

        this.xOutPoints = new int[6];
        this.yOutPoints = new int[6];
        this.xInPoints = new int[6];
        this.yInPoints = new int[6];

        this.remapPoints();
    }

    public void remapPoints() {
        int w = super.x2 - super.x1;
        int h = super.y2 - super.y1;

        // outline points
        this.xOutPoints[0] = super.x1 + w / 4;
        this.yOutPoints[0] = super.y1;
        this.xOutPoints[1] = super.x1 + 3*w / 4;
        this.yOutPoints[1] = super.y1;
        this.xOutPoints[2] = super.x2;
        this.yOutPoints[2] = super.y1 + h / 2;
        this.xOutPoints[3] = super.x1 + 3*w / 4;
        this.yOutPoints[3] = super.y2;
        this.xOutPoints[4] = super.x1 + w / 4;
        this.yOutPoints[4] = super.y2;
        this.xOutPoints[5] = super.x1;
        this.yOutPoints[5] = super.y1 + h / 2;

        // outline points
        this.xInPoints[0] = super.x1 + w / 4;
        this.yInPoints[0] = super.y1 + super.outlineThickness;
        this.xInPoints[1] = super.x1 + 3*w / 4;
        this.yInPoints[1] = super.y1 + super.outlineThickness;
        this.xInPoints[2] = super.x2;
        this.yInPoints[2] = super.y1 + h / 2;
        this.xInPoints[3] = super.x1 + 3*w / 4;
        this.yInPoints[3] = super.y2 - super.outlineThickness;
        this.xInPoints[4] = super.x1 + w / 4;
        this.yInPoints[4] = super.y2 - super.outlineThickness;
        this.xInPoints[5] = super.x1;
        this.yInPoints[5] = super.y1 + h / 2;
    }

    @Override
    public Figure clone() {
        int w = super.x2 - super.x1;
        int h = super.y2 - super.y1;
        Hexagon hexa = new Hexagon(super.x1, super.y1, w, h);
        System.arraycopy(this.xOutPoints, 0, hexa.xOutPoints, 0, 6);
        System.arraycopy(this.yOutPoints, 0, hexa.yOutPoints, 0, 6);
        System.arraycopy(this.xInPoints, 0, hexa.xInPoints, 0, 6);
        System.arraycopy(this.yInPoints, 0, hexa.yInPoints, 0, 6);

        return hexa;
    }

    @Override
    public void paint(Graphics2D g2d) {
        // drawing outline
        g2d.setPaint(super.outline);
        g2d.fillPolygon(this.xOutPoints, this.yOutPoints, 6);

        // drawing interior
        g2d.setPaint(super.fill);
        g2d.fillPolygon(this.xInPoints, this.yInPoints, 6);
    }

    @Override
    public void drag(int dx, int dy) {
        super.drag(dx, dy);

        for (int i = 0; i < 6; i++) {
            // out pentagon
            this.xOutPoints[i] += dx;
            this.yOutPoints[i] += dy;
            // inn pentagon
            this.xInPoints[i] += dx;
            this.yInPoints[i] += dy;
        }
    }
    
    @Override
    public void resize(int x, int y, int dx, int dy) {
        super.resize(x, y, dx, dy);
        this.remapPoints();
    }
}
