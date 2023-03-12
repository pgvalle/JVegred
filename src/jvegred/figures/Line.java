package figures;

import java.awt.*;

public class Line extends Figure {

    public Color color;
    public int thickness;

    public Line(int x1, int y1, int x2, int y2) {
        final int w = Math.abs(x2 - x1);
        final int h = Math.abs(y2 - y1);

        super(x1, y1, w, h);
        this.color = Color.BLACK;
        this.thickness = 1;
    }

    public void paint(Graphics2D g2d) {
        g2d.setPaint(this.color);
        g2d.setStroke(new BasicStroke(this.thickness));
        g2d.drawLine(super.x1, super.y1, super.x2, super.y2);
    }
}