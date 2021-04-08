package jvegred.figures;

import java.awt.Graphics2D;
import java.awt.Paint;

public class Ellipse extends Geometric2D {
	
	public Ellipse(int x, int y, Paint fill, Paint outline, int outlineThickness) {
		super(x, y, fill, outline, outlineThickness);
	}
    
	@Override
	public void paint(Graphics2D g2d) {
		super.paint(g2d);
		// drawing outline
		g2d.setPaint(super.outline);
		g2d.fillOval(super.x, super.y, super.w, super.h);
		// drawing interior
		g2d.setPaint(super.fill);
		g2d.fillOval(
			super.x + super.outlineThickness,
			super.y + super.outlineThickness,
			super.w - 2 * super.outlineThickness,
			super.h - 2 * super.outlineThickness
		);
	}
}
