package jvegred.figures;

import java.awt.*;

public class Complex2D extends Rectangl3 {
    
    protected int[] insideXs, outsideXs, insideYs, outsideYs;
    private final int noPoints;

    protected Complex2D(int x, int y, int w, int h, int noPoints) {
        super(x, y, w, h);
        this.insideXs  = new int[noPoints];
        this.outsideXs = new int[noPoints];
        this.insideYs  = new int[noPoints];
        this.outsideYs = new int[noPoints];
        this.noPoints = noPoints;

        this.updatePoints();
    }

    protected void updatePoints() {}

    @Override
    public void setBoundsPoints(int x1, int y1, int x2, int y2) {
        super.setBoundsPoints(x1, y1, x2, y2);
        this.updatePoints();
    }

    @Override
    public Figure copy() {        
        Complex2D c2d = new Complex2D(super.x1, super.y1, super.x2 - super.x1,
            super.y2 - super.y1, this.noPoints);
        System.arraycopy(this.outsideXs, 0, c2d.outsideXs, 0, this.noPoints);
        System.arraycopy(this.outsideYs, 0, c2d.outsideYs, 0, this.noPoints);
        System.arraycopy(this.insideXs, 0, c2d.insideXs, 0, this.noPoints);
        System.arraycopy(this.insideYs, 0, c2d.insideYs, 0, this.noPoints);

        return c2d;
    }

    @Override
    public void paint(Graphics2D g2d) {
        // drawing outline
        g2d.setPaint(super.outline);
        g2d.fillPolygon(this.outsideXs, this.outsideYs, this.noPoints);

        // drawing interior
        g2d.setPaint(super.fill);
        g2d.fillPolygon(this.insideXs, this.insideYs, this.noPoints);
    }

    @Override
    public void drag(int dx, int dy) {
        super.drag(dx, dy);

        for (int i = 0; i < this.noPoints; i++) {
            // outside (outline)
            this.outsideXs[i] += dx;
            this.outsideYs[i] += dy;
            // inside (fill)
            this.insideXs[i] += dx;
            this.insideYs[i] += dy;
        }
    }
    
    @Override
    public void resize(int x, int y, int dx, int dy) {
        super.resize(x, y, dx, dy);
        this.updatePoints();
    }
}
