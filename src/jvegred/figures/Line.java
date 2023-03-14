package jvegred.figures;

import java.awt.*;

public class Line extends Figure {

    public Color color;
    public int thickness;

    public Line(int x1, int y1, int x2, int y2) {
        super(x1, y1, x2 - x1, y2 - y1);
        this.color = Color.BLACK;
        this.thickness = 1;
    }

    @Override
    public Figure copy() {
        Line line = new Line(this.x1, this.y1, super.x2, super.y2);
        line.color = this.color;
        line.thickness = this.thickness;

        return line;
    }

    @Override
    public void paint(Graphics2D g2d) {
        g2d.setPaint(this.color);
        g2d.setStroke(new BasicStroke(this.thickness));
        g2d.drawLine(super.x1, super.y1, super.x2, super.y2);
    }
}