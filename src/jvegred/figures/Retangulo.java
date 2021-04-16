package jvegred.figures;

import java.awt.Graphics2D;
import java.awt.Paint;

public class Retangulo extends Geometric2D {

	public Retangulo(int x, int y, int w, int h, Paint fill, Paint outline, int outlineThickness) {
		super(x, y, w, h, fill, outline, outlineThickness);
	}

	@Override
	public void draw(Graphics2D g2d) {
		// drawing outline
		g2d.setPaint(super.outline);
		g2d.fillRect(super.x, super.y, super.w, super.h);

		// drawing interior
		g2d.setPaint(super.fill);
		g2d.fillRect(super.x + super.outlineThickness, super.y + super.outlineThickness,
				super.w - 2 * super.outlineThickness, super.h - 2 * super.outlineThickness);
	}
}
