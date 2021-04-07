package jvegred.figures;

import java.awt.Graphics2D;
import java.awt.Paint;

public class Rectangle extends Geometric2D {

    protected Rectangle(int x, int y, Paint fill, Paint outline, int outlineThickness) {
        super(x, y, fill, outline, outlineThickness);
    }
    
    @Override
    public void paint(Graphics2D g2d) {
		super.paint(g2d);
        // applying rotations
		g2d.rotate(super.angle, super.x + super.w / 2.0, super.y + super.h / 2.0);
		// drawing outline
		g2d.setPaint(super.outline);
		g2d.fillRect(super.x, super.y, super.w, super.h);
		// drawing interior
		g2d.setPaint(super.fill);
		g2d.fillRect(
			super.x + super.outlineThickness,
			super.y + super.outlineThickness,
			super.w - 2 * super.outlineThickness,
			super.h - 2 * super.outlineThickness
		);
    }
}
