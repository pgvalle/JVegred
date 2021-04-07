package jvegred.figures;

import java.awt.Paint;

public abstract class Geometric2D extends Figure {

	public Paint outline;
	
	public int outlineThickness;

	protected Geometric2D(int x, int y, Paint fill, Paint outline, int outlineThickness) {
		super(x, y, fill);

		this.outline = outline;
		this.outlineThickness = outlineThickness;
	}
}
