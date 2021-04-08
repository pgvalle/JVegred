package jvegred.figures;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.geom.Rectangle2D;

public abstract class Figure {

	public boolean active;

	public int x, y;
	public int w, h;
	public Rectangle2D selectionRect;

	public double angle; // in radians
	
	public Paint fill;

	protected Figure(int x, int y, Paint fill) {
		this.x = x; this.y = y;
		this.w = 50; this.h = 50;
		this.selectionRect = new Rectangle2D.Double(
			this.x - Math.cos(this.angle) - 2.0,
			this.y - Math.sin(this.angle) - 2.0,
			this.w + Math.cos(this.angle) + 4.0,
			this.h + Math.sin(this.angle) + 4.0
		);

		this.active = true;

		this.angle = 0.0;

		this.fill = fill;
	}

	protected void updateSelectionRect() {
		this.selectionRect.setFrame(
			this.x - Math.cos(this.angle) - 2.0,
			this.y - Math.sin(this.angle) - 2.0,
			this.w + Math.cos(this.angle) + 4.0,
			this.h + Math.sin(this.angle) + 4.0
		);
	}

	public void paint(Graphics2D g2d) {
		// applying rotations
		g2d.rotate(this.angle, this.x + this.w / 2.0, this.y + this.h / 2.0);
	}

	public void move(int dx, int dy) {
		this.x += dx;
		this.y += dy;
		this.updateSelectionRect();
	}

	public void resize(int x1, int y1, int x2, int y2) {
		// do stuff
		this.updateSelectionRect();
	}

	public void rotate(double angle) {
		this.angle += angle;
		this.updateSelectionRect();
	} 
}
