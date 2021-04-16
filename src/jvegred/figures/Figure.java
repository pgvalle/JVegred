package jvegred.figures;

import java.awt.Graphics2D;
import java.awt.Paint;

public abstract class Figure {

	protected boolean focused;

	protected int x, y;
	protected int w, h;

	protected Paint fill;

	protected Figure(int x, int y, int w, int h, Paint fill) {
		this.focused = true;

		this.x = x;
		this.y = y;
		this.w = w;
		this.h = h;

		this.fill = fill;
	}

	public boolean contains(int x, int y) {
		boolean inx = this.x <= x && x <= this.x + this.w;
		boolean iny = this.y <= y && y <= this.y + this.h;

		return inx && iny;
	}

	public abstract void draw(Graphics2D g2d);

	public void drag(int dx, int dy) {
		this.x += dx;
		this.y += dy;
	}
}
