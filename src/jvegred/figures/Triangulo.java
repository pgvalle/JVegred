package jvegred.figures;

import java.awt.*;

public class Triangulo extends Figure {

    private int[] xOutPoints, yOutPoints;
    private int[] xInnPoints, yInnPoints;

    public Triangulo(int x, int y, int w, int h, Color fill, Color outline, int outlineThickness) {
        super(x, y, w, h, fill, outline, outlineThickness);

        this.xOutPoints = new int[3];
        this.yOutPoints = new int[3];
        this.xInnPoints = new int[3];
        this.yInnPoints = new int[3];

        this.remapPoints();
    }

    public void remapPoints() {
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
    public void paint(Graphics2D g2d, boolean hasfocus) {
        // drawing outline
        g2d.setPaint(super.outline);
        g2d.fillPolygon(this.xOutPoints, this.yOutPoints, 3);

        // drawing interior
        g2d.setPaint(super.fill);
        g2d.fillPolygon(this.xInnPoints, this.yInnPoints, 3);

        if (hasfocus) {
            g2d.setColor(Color.RED);
            g2d.drawRect(super.x, super.y, super.w, super.h);
        }
    }

    @Override
    public Figure clone() {
        return new Triangulo(x, y, w, h, fill, outline, outlineThickness);
    }

    @Override
    public void drag(int dx, int dy) {
        super.drag(dx, dy);

        for (int i = 0; i < 3; i++) {
            // out triangle
            this.xOutPoints[i] += dx;
            this.yOutPoints[i] += dy;
            // inn triangle
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
