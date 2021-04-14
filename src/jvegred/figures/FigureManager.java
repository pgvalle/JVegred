package jvegred.figures;

import java.awt.Graphics2D;
import java.util.ArrayList;

public class FigureManager {
	
	public static ArrayList<Figure> figures = new ArrayList<Figure>(0);

	public static void addFigureToList(Figure f) {
		// before adding, be sure last figure isn't focused
		int last = FigureManager.figures.size() - 1;

		if (last >= 0) {
			FigureManager.figures.get(last).onFocus = false;
		}

		FigureManager.figures.add(f);
	}

	public static void deleteFigureOnFocus() {
		// before deleting, be sure last figure is focused
		int last = FigureManager.figures.size() - 1;

		if (last >= 0) {
			FigureManager.figures.remove(last);
		}
	}
	
	public static void focusFigureAt(int x, int y) {
		int last = FigureManager.figures.size() - 1;
		boolean found = false;

		// iterating backwards because of z-order feature
		for (int i = last; i >= 0; i--) {
			// if figure at index i is the one clicked by the user, then ...
			if (FigureManager.figures.get(i).bounds.contains(x, y)) {
				// changing focus to selected figure
				FigureManager.figures.get(last).onFocus = false;
				FigureManager.figures.get(i).onFocus = true;

				// moving focused figure to end of list
				Figure tmp = FigureManager.figures.get(i);
				FigureManager.figures.remove(i);
				FigureManager.figures.add(tmp);

				// user indeed clicked inside a figure
				found = true;
				// stop the loop
				i = 0;
			}
		}

		// if user clicked where there is no figure, then remove focus
		if (last >= 0 && !found) {
			FigureManager.figures.get(last).onFocus = false;
		}
	}
	
	public static void paintFigures(Graphics2D g2d) {
		for (Figure f : FigureManager.figures) {	
			f.paint(g2d);
		}
	}

	public static void moveFigureOnFocus(int dx, int dy) {
		// before moving, be sure last figure is focused
		int last = FigureManager.figures.size() - 1;

		if (last >= 0) {
			FigureManager.figures.get(last).move(dx, dy);
		}
	}

	public static void resizeFigureOnFocus(int x1, int y1, int x2, int y2) {
		// before resizing, be sure last figure is focused
		int last = FigureManager.figures.size() - 1;

		if (last >= 0) {
			FigureManager.figures.get(last).resize(x1, y1, x2, y2);
		}
	}

	public static void spinFigureOnFocus(double angle) {
		// before spining, be sure last figure is focused
		int last = FigureManager.figures.size() - 1;

		if (last >= 0) {
			FigureManager.figures.get(last).spin(angle);
		}
	}
}
