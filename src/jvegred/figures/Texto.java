package jvegred.figures;

import java.awt.*;
import java.awt.font.*;

// TODO Finish implementing Texto class
public class Texto extends Figure {

    private String text;

    public Texto(int x, int y, int w, int h, Paint fill, String text) {
        super(x, y, w, h, fill);
        
        this.text = text;
    }

    @Override
    public void paint(Graphics2D g2d) {
        // TODO Implement Texto.draw

        Font font = g2d.getFont();
        FontRenderContext context = g2d.getFontRenderContext();
        LineMetrics lineMetrics = font.getLineMetrics(text, context);

        int w = (int) font.getStringBounds(text, context).getWidth();
        int h = (int) (lineMetrics.getAscent() + lineMetrics.getDescent());
        int x = this.x + (this.w - w) / 2;
        int y = (int) (this.y + (this.h + h) / 2 - lineMetrics.getDescent());

        g2d.setPaint(this.fill);
        g2d.drawString(this.text, x, y);
    }
}
