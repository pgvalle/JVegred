package jvegred.figures;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;

public class FigureMan {
	
	public static ArrayList<Figure> figures = new ArrayList<Figure>(0);

	public static void addFigureToList(Figure f) {
		f.focused = true;
		// before adding, be sure no figure is focused
		FigureMan.unfocusFigures();
		FigureMan.figures.add(f);
	}

	public static void deleteFiguresOnFocus() {		
		for (int i = 0; i < FigureMan.figures.size(); i++) {
			if (FigureMan.figures.get(i).focused) {
				FigureMan.figures.remove(i--);
			}
		}
	}

	public static void drawFigures(Graphics2D g2d) {
		for (Figure f : FigureMan.figures) {
			if (f.focused) {
				g2d.setPaint(Color.RED);
				g2d.drawRect(f.x, f.y, f.w, f.h);
			}

			f.draw(g2d);
		}
	}
	
	public static void focusFigureAt(int x, int y, boolean unique) {
		int last = FigureMan.figures.size() - 1;
		boolean done = false; // no figure got selected "yet"

		if (unique) {
			FigureMan.unfocusFigures();
		}
		
		for (int i = last; i >= 0; i--) {
			Figure f = FigureMan.figures.get(i);

			if (f.contains(x, y)) {
				f.focused = true;
				
				FigureMan.figures.remove(i);
				FigureMan.figures.add(f);
				
				done = true; // a figure got selected 
				i = 0; // stop loop already
			}
		}

		// No figure got selected at the end
		if (last >= 0 && !done) {
			FigureMan.unfocusFigures();
		}
	}

	public static void unfocusFigures() {
		for (Figure f : FigureMan.figures) {
			f.focused = false;
		}
	}

	public static void moveFiguresOnFocus(int dx, int dy) {
		for (Figure f : FigureMan.figures) {
			if (f.focused) {
				f.move(dx, dy);
			}
		}
	}
}
