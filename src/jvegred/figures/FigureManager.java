package jvegred.figures;

import java.awt.*;
import java.util.*;

public class FigureManager {

    private ArrayList<Figure> figures;
    private ArrayList<Integer> selection;

    // bounds of selection rectangle
    private int leftSelX, rightSelX, topSelY, bottomSelY;

    public FigureManager() {
        this.figures = new ArrayList<Figure>();
        this.selection = new ArrayList<Integer>();
        this.leftSelX = this.topSelY = Integer.MAX_VALUE;
        this.rightSelX = this.bottomSelY = Integer.MIN_VALUE;
    }

    private void calculateSelectionRect() {
        // reset rectangle bounds first
        this.leftSelX = this.topSelY = Integer.MAX_VALUE;
        this.rightSelX = this.bottomSelY = Integer.MIN_VALUE;

        for (Integer i : this.selection) {
            Figure figure = this.figures.get(i.intValue());
            // left
            if (figure.x1 < this.leftSelX)
                this.leftSelX = figure.x1;
            // right
            if (figure.x2 > this.rightSelX)
                this.rightSelX = figure.x2;
            // top
            if (figure.y1 < this.topSelY)
                this.topSelY = figure.y1;
            // bottom
            if (figure.y2 > this.bottomSelY)
                this.bottomSelY = figure.y2;
        }
    }

    protected int findFigureAt(int x, int y) {
        for (int i = 0; i < this.figures.size(); i++) {
            if (this.figures.get(i).intersectPoint(x, y))
                return i;
        }
        return -1;
    }

    public void toggleSelectionAt(int x, int y) {
        final int index = this.findFigureAt(x, y);
        // no figure at (x, y). So nothing is going on here
        if (index == -1)
            return;

        // find figure index inside selection
        for (int i = 0; i < selection.size(); i++) {
            // Found. Remove it from selection
            if (selection.get(i) == index) {
                selection.remove(i);
                return;
            }
        }

        this.selection.add(index); // Not found. Add it to selection
        this.calculateSelectionRect(); // selection rect should be updated
    }

    public void paintFigures(Graphics2D g2d) {
        for (Figure figure : this.figures) {
            figure.paint(g2d);
        }

        // render selection rect of each figure
        for (Integer i : this.selection) {
            this.figures.get(i.intValue()).paintFocusRect(g2d);
        }

        // big selection rect
        if (this.selection.size() > 0) {
            g2d.setColor(Color.RED);
            g2d.drawRect(this.leftSelX, this.topSelY, this.rightSelX - this.leftSelX,
                this.bottomSelY - this.topSelY);
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
        this.calculateSelectionRect(); // selection rect should be updated
    }

    public void dragSelected(int dx, int dy) {
        for (Integer index : this.selection) {
            this.figures.get(index.intValue()).drag(dx, dy);
        }
    }

    public void resizeSelected(int x, int y, int dx, int dy) {
        // resize big selection rect horizontally
        final int distanceToLeft = Math.abs(x - this.leftSelX);
        final int distanceToRight = Math.abs(x - this.rightSelX);
        if (distanceToLeft < distanceToRight) {
            this.leftSelX += dx;
        } else {
            this.rightSelX += dx;
        }

        // resizing big selection rect vertically
        final int distanceToTop = Math.abs(y - this.topSelY);
        final int distanceToBottom = Math.abs(y - this.bottomSelY);
        if (distanceToTop < distanceToBottom) {
            this.topSelY += dy;
        } else {
            this.bottomSelY += dy;
        }

        // resize each figure individually
        for (Integer i : this.selection) {
            this.figures.get(i.intValue()).resize(x, y, dx, dy);
        }
    }
}