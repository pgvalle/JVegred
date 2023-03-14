package jvegred.figures;

import java.awt.*;
import java.util.*;

public class FigureManager {

    private ArrayList<Figure> figures;
    private ArrayList<Integer> selection;
    // bounds of selection rectangle
    private int selectionLeft, selectionRight, selectionTop, selectionBottom;

    private FigureManager() {
        this.figures = new ArrayList<Figure>();
        this.selection = new ArrayList<Integer>();
        this.selectionLeft = this.selectionTop = Integer.MAX_VALUE;
        this.selectionRight = this.selectionBottom = Integer.MIN_VALUE;
    }

    public static final FigureManager instance = new FigureManager();

    private void updateSelectionRect() {
        // reset rectangle bounds first
        this.selectionLeft = this.selectionTop = Integer.MAX_VALUE;
        this.selectionRight = this.selectionBottom = Integer.MIN_VALUE;

        for (Integer i : this.selection) {
            final Figure figure = this.figures.get(i.intValue());
            // left
            if (figure.x1 < this.selectionLeft)
                this.selectionLeft = figure.x1;
            // right
            if (figure.x2 > this.selectionRight)
                this.selectionRight = figure.x2;
            // top
            if (figure.y1 < this.selectionTop)
                this.selectionTop = figure.y1;
            // bottom
            if (figure.y2 > this.selectionBottom)
                this.selectionBottom = figure.y2;
        }
    }

    protected int findFigureAt(int x, int y) {
        for (int i = 0; i < this.figures.size(); i++) {
            if (this.figures.get(i).intersectPoint(x, y)) {
                return i;
            }
        }
        return -1;
    }

    public void toggleSelectionAt(int x, int y) {
        final int index = this.findFigureAt(x, y);
        // no figure at (x, y). So nothing is going on here
        if (index == -1) {
            return;
        }

        // find figure index inside selection
        for (int i = 0; i < selection.size(); i++) {
            // Found. Remove it from selection
            if (selection.get(i) == index) {
                selection.remove(i);
                return;
            }
        }

        this.selection.add(index); // Not found. Add it to selection
        this.updateSelectionRect(); // selection rect should be updated
    }

    public void paintFigures(Graphics2D g2d) {
        for (Figure figure : this.figures) {
            figure.paint(g2d);
        }

        // render selection rect of each figure
        for (Integer i : this.selection) {
            this.figures.get(i.intValue()).paintFocusRect(g2d);
        }

        // big selection rect.
        // Only valid if there's at least one figure selected
        if (this.selection.size() > 0) {
            g2d.setColor(Color.RED);
            g2d.drawRect(
                this.selectionLeft, this.selectionTop,
                this.selectionRight - this.selectionLeft,
                this.selectionBottom - this.selectionTop
            );
        }
    }

    public void addFigure(Figure figure) {
        this.figures.add(figure);
    }

    public void removeSelected() {
        // To avoid messing up figure deletion, sort indices in reverse order
        selection.sort(null);
        Collections.reverse(this.selection);

        for (Integer index : this.selection) {
            this.figures.remove(index.intValue());
        }

        this.selection.clear();
        this.updateSelectionRect(); // selection rect should be updated
    }

    public void dragSelected(int dx, int dy) {
        for (Integer index : this.selection) {
            this.figures.get(index.intValue()).drag(dx, dy);
        }
    }

    public void resizeSelected(int x, int y, int dx, int dy) {
        // resize big selection rect horizontally
        final int distanceToLeft = Math.abs(x - this.selectionLeft);
        final int distanceToRight = Math.abs(x - this.selectionRight);
        if (distanceToLeft < distanceToRight) {
            x = this.selectionLeft += dx;
        } else {
            x = this.selectionRight += dx;
        }

        // resizing big selection rect vertically
        final int distanceToTop = Math.abs(y - this.selectionTop);
        final int distanceToBottom = Math.abs(y - this.selectionBottom);
        if (distanceToTop < distanceToBottom) {
            y = this.selectionTop += dy;
        } else {
            y = this.selectionBottom += dy;
        }

        // changed (x, y) to avoid different resizing behavior between figures
        // now resize each figure individually
        for (Integer i : this.selection) {
            this.figures.get(i.intValue()).resize(x, y, dx, dy);
        }
    }
}