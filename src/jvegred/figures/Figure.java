package jvegred.figures;

import java.awt.*;

public abstract class Figure {

	public boolean active;

	public int x, y;
	public int w, h;

	public double angle; // in radians
	
	public Paint fill;

	protected Figure(int x, int y, Paint fill) {
		this.x = x; this.y = y;
		this.w = 50; this.h = 50;

		this.active = true;

		this.angle = 0.0;

		this.fill = fill;
	}

	public void paint(Graphics2D g2d) {
		if (this.active) {
			g2d.setPaint(Color.RED);
			g2d.drawRect(
				(int) (this.x + Math.cos(this.angle) - 1.0),
 				(int) (this.y + Math.sin(this.angle) - 1.0),			
 				(int) (this.w + Math.cos(this.angle) + 1.0),
 				(int) (this.h + Math.sin(this.angle) + 1.0)
			);
		}
	}

	public void move(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}

	public void resize(int x1, int y1, int x2, int y2) {
		// do stuff
	}
}
