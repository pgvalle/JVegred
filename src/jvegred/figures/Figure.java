package jvegred.figures;

import java.awt.Graphics2D;
import java.awt.Paint;
import java.awt.Color;
import java.awt.Rectangle;

public abstract class Figure {

	public boolean onFocus;

	public int x, y;
	public int w, h;
	public Rectangle bounds; // selection rect

	public double angle; // in radians
	
	public Paint fill;

	protected Figure(int x, int y, Paint fill) {
		this.onFocus = true;

		this.x = x; this.y = y;
		this.w = 50; this.h = 50;
		this.bounds = new Rectangle();
		this.updateBounds();
		
		this.angle = 0.0;
		
		this.fill = fill;
	}

	protected void updateBounds() {
		int sin = (int) Math.sin(this.angle);
		int cos = (int) Math.cos(this.angle);

		this.bounds.setFrame(
			this.x - cos - 1, this.y - sin - 1, this.w + cos + 2, this.h - sin + 2
		);
	}

	public void paint(Graphics2D g2d) {
		if (this.onFocus) {
			g2d.setColor(Color.RED);
			g2d.draw(this.bounds);
		}

		g2d.rotate(this.angle, this.x + this.w / 2.0, this.y + this.h / 2.0);
	}

	public void move(int dx, int dy) {
		this.x += dx;
		this.y += dy;

		this.updateBounds();
	}

	public void resize(int x1, int y1, int x2, int y2) {
		// do stuff

		this.updateBounds();
	}

	public void spin(double angle) {
		this.angle += angle;

		this.updateBounds();
	} 
}
