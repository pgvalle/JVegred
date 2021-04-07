package jvegred.figures;

import java.awt.Graphics2D;
import java.awt.Paint;

public class Triangle extends Geometric2D {

    protected int[] xOutPoints, yOutPoints;
    protected int[] xInnPoints, yInnPoints;

    public Triangle(int x, int y, Paint fill, Paint outline, int outlineThickness) {
        super(x, y, fill, outline, outlineThickness);
        
        this.xOutPoints = new int[3]; this.yOutPoints = new int[3];
        this.xInnPoints = new int[3]; this.yInnPoints = new int[3];

        this.remapPoints();
    }

    private void remapPoints() {
        // outline triangle
        this.xOutPoints[0] = this.x + this.w / 2;
        this.yOutPoints[0] = this.y;
        
        this.xOutPoints[1] = this.x + this.w;
        this.yOutPoints[1] = this.y + this.h;

        this.xOutPoints[2] = this.x;
        this.yOutPoints[2] = this.y + this.h;

        // inside triangle
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
        super.paint(g2d);
        // applying rotations
		g2d.rotate(super.angle, super.x + super.w / 2.0, super.y + super.h / 2.0);
		// drawing outline
		g2d.setPaint(super.outline);
		g2d.fillPolygon(this.xOutPoints, yOutPoints, 3);
		// drawing interior
		g2d.setPaint(super.fill);
		g2d.fillPolygon(this.xInnPoints, yInnPoints, 3);
    }

    @Override
	public void move(int dx, int dy) {
		super.move(dx, dy);
        this.remapPoints();
	}
}
