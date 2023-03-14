package jvegred.figures;

public class Triangle extends Complex2D {

    public Triangle(int x, int y, int w, int h) {
        super(x, y, w, h, 3);
    }

    @Override
    protected void updatePoints() {
        final int width = super.x2 - super.x1;
        final int height = super.y2 - super.y1;

        // outside points
        super.outsideXs[0] = super.x1 + width/2;
        super.outsideYs[0] = super.y1;
        super.outsideXs[1] = super.x2;
        super.outsideYs[1] = super.y2;
        super.outsideXs[2] = super.x1;
        super.outsideYs[2] = super.y2;

        // inside points
        final double angle = Math.atan((2.0*height)/width);
        final double dx = super.outlineThickness/Math.tan(angle/2.0);
        final double dy = super.outlineThickness/Math.sin(Math.PI/2.0 - angle);

        super.insideXs[0] = super.x1 + width/2;
        super.insideYs[0] = super.y1 + (int)dy;
        super.insideXs[1] = super.x2 - (int)dx;
        super.insideYs[1] = super.y2 - super.outlineThickness;
        super.insideXs[2] = super.x1 + (int)dx;
        super.insideYs[2] = super.y2 - super.outlineThickness;
    }
}
