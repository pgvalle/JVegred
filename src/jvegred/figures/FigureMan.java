package jvegred.figures;

import java.awt.Graphics2D;
import java.awt.Color;

import java.util.ArrayList;

public class FigureMan {

	// z-order note: 0, ..., n <=> farthest, ..., closest
	public static ArrayList<Figure> figures = new ArrayList<Figure>(0);

	public static boolean focusedFiguresContain(int x, int y) {
		// l stands for lowest and g for greatest
		int lx = Integer.MAX_VALUE, ly = Integer.MAX_VALUE, gx = 0, gy = 0;

		// z-order doesn't matter here
		for (Figure f : FigureMan.figures) {
			if (f.focused) {
				// consider f to calculate bounds of selection
				lx = f.x < lx ? f.x : lx;
				ly = f.y < ly ? f.y : ly;
				gx = f.x + f.w > gx ? f.x + f.w : gx;
				gy = f.y + f.h > gy ? f.y + f.h : gy;
			}
		}

		return lx <= x && x <= gx && ly <= y && y <= gy; // (x, y) inside selection
	}

	public static int unfocusedFiguresContain(int x, int y) {
		// iterate backwards because z-order matters here
		for (int i = FigureMan.figures.size() - 1; i >= 0; i--) {
			Figure f = FigureMan.figures.get(i);

			if (!f.focused && f.contains(x, y)) {
				return i;
			}
		}

		return -1;
	}

	public static int anyFigureContains(int x, int y) {
		// iterate backwards because z-order matters here
		for (int i = FigureMan.figures.size() - 1; i >= 0; i--) {
			if (FigureMan.figures.get(i).contains(x, y)) {
				return i;
			}
		}

		return -1;
	}

	public static void addFigureToList(Figure f) {
		f.focused = true;
		// be sure no figure is focused
		FigureMan.setAllFiguresFocus(false);
		FigureMan.figures.add(f);
	}

	public static void deleteFocusedFigures() {
		// z-order doesn't matter here
		for (int i = 0; i < FigureMan.figures.size(); i++) {
			if (FigureMan.figures.get(i).focused) {
				// when removing, following items indexes decrease by one
				// i-- keeps the value in next iteration not to jump items
				FigureMan.figures.remove(i--);
			}
		}
	}

	public static void focusFigureAt(int x, int y, boolean multifocus) {
		if (!multifocus) {
			// reset focus of previously selected figures
			FigureMan.setAllFiguresFocus(false);
		}

		int findex = FigureMan.anyFigureContains(x, y);

		// found a figure that contains (x, y)
		if (findex != -1) {
			Figure f = FigureMan.figures.get(findex);
			
			f.focused = true;
			// insert selected figure in the end
			FigureMan.figures.remove(findex);
			FigureMan.figures.add(f);
		} else if (multifocus) {
			// reset focus of previously selected figures
			FigureMan.setAllFiguresFocus(false);
		}
	}

	public static void focusFigureAt(int i) {
		if (i >= 0 && i < FigureMan.figures.size()) {
			FigureMan.figures.get(i).focused = true;
		}
	}

	public static void setAllFiguresFocus(boolean focus) {
		// z-order doesn't matter here
		for (Figure f : FigureMan.figures) {
			f.focused = focus;
		}
	}

	public static void drawFigures(Graphics2D g2d) {
		// l stands for lowest and g for greatest
		int lx = Integer.MAX_VALUE, ly = Integer.MAX_VALUE, gx = 0, gy = 0;

		for (Figure f : FigureMan.figures) {
			f.draw(g2d);

			if (f.focused) {
				// consider f to calculate bounds of global selection
				lx = f.x < lx ? f.x : lx;
				ly = f.y < ly ? f.y : ly;
				gx = f.x + f.w > gx ? f.x + f.w : gx;
				gy = f.y + f.h > gy ? f.y + f.h : gy;

				// draw a local selection rectangle
				g2d.setPaint(Color.RED);
				g2d.drawRect(f.x, f.y, f.w, f.h);
			}
		}

		// draw global selection rectangle
		g2d.setPaint(Color.RED);
		g2d.drawRect(lx, ly, gx - lx, gy - ly);
	}

	public static void dragFocusedFigures(int dx, int dy) {
		// z-order doesn't matter here
		for (Figure f : FigureMan.figures) {
			if (f.focused) {
				f.drag(dx, dy);
			}
		}
	}
}
