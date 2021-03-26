package jvegred.figures;

import java.awt.Graphics2D;
import java.awt.Paint;

public class TriangleF extends Geometric2DF {

    private int[] xlist, ylist;

    public TriangleF(int x, int y, float angle, Paint fill, Paint outline, int outlineThickness) {
        super(x, y, angle, fill, outline, outlineThickness);

        this.xlist = new int[3];
        this.ylist = new int[3];
    }

    @Override
    public void paint(Graphics2D g2d) {
        // TODO Auto-generated method stub
    }
}
