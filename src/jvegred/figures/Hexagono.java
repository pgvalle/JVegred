package jvegred.figures;

import java.awt.*;

public class Hexagono extends Figure {

    private int[] xOutPoints, yOutPoints;
    private int[] xInnPoints, yInnPoints;

    public Hexagono(int x, int y, int w, int h, Color fill, Color outline, int outlineThickness) {
        super(x, y, w, h, fill, outline, outlineThickness);

        this.xOutPoints = new int[6];
        this.yOutPoints = new int[6];
        this.xInnPoints = new int[6];
        this.yInnPoints = new int[6];

        this.remapPoints();
    }

    public void remapPoints() {
        // outline points
        this.xOutPoints[0] = super.x + super.w / 4;
        this.yOutPoints[0] = super.y;
        this.xOutPoints[1] = super.x + 3*super.w / 4;
        this.yOutPoints[1] = super.y;
        this.xOutPoints[2] = super.x + super.w;
        this.yOutPoints[2] = super.y + super.h / 2;
        this.xOutPoints[3] = super.x + 3*super.w / 4;
        this.yOutPoints[3] = super.y + super.h;
        this.xOutPoints[4] = super.x + super.w / 4;
        this.yOutPoints[4] = super.y + super.h;
        this.xOutPoints[5] = super.x;
        this.yOutPoints[5] = super.y + super.h / 2;

        // outline points
        this.xInnPoints[0] = super.x + super.w / 4;
        this.yOutPoints[0] = super.y + super.outlineThickness;
        this.xInnPoints[1] = super.x + 3*super.w / 4;
        this.yOutPoints[1] = super.y + super.outlineThickness;
        this.xInnPoints[2] = super.x + super.w;
        this.yOutPoints[2] = super.y + super.h / 2;
        this.xInnPoints[3] = super.x + 3*super.w / 4;
        this.yOutPoints[3] = super.y + super.h - super.outlineThickness;
        this.xInnPoints[4] = super.x + super.w / 4;
        this.yOutPoints[4] = super.y + super.h - super.outlineThickness;
        this.xInnPoints[5] = super.x;
        this.yOutPoints[5] = super.y + super.h / 2;
    }

    @Override
    public void paint(Graphics2D g2d, boolean hasfocus) {
        // drawing outline
        g2d.setPaint(super.outline);
        g2d.fillPolygon(this.xOutPoints, this.yOutPoints, 6);

        // drawing interior
        g2d.setPaint(super.fill);
        g2d.fillPolygon(this.xInnPoints, this.yInnPoints, 6);

        if (hasfocus) {
            g2d.setColor(Color.RED);
            g2d.drawRect(super.x, super.y, super.w, super.h);
        }
    }

    @Override
    public Figure clone() {
        return new Hexagono(x, y, w, h, fill, outline, outlineThickness);
    }

    @Override
    public void drag(int dx, int dy) {
        super.drag(dx, dy);

        for (int i = 0; i < 6; i++) {
            // out pentagon
            this.xOutPoints[i] += dx;
            this.yOutPoints[i] += dy;
            // inn pentagon
            this.xInnPoints[i] += dx;
            this.yInnPoints[i] += dy;
        }
    }
    
    @Override
    public void resize(int x1, int y1, int dx, int dy) {
        super.resize(x1, y1, dx, dy);
        this.remapPoints();
    }
}
