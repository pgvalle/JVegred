package jvegred.figures;

public class Hexagon extends Complex2D {

    public Hexagon(int x, int y, int w, int h) {
        super(x, y, w, h, 6);
    }

    @Override
    protected void updatePoints() {
        final int quartWidth = (super.x2 - super.x1)/4;
        final int halfHeight = (super.y2 - super.y1)/2;

        // outline points
        super.outsideXs[0] = super.x1 +   quartWidth;
        super.outsideYs[0] = super.y1;
        super.outsideXs[1] = super.x1 + 3*quartWidth;
        super.outsideYs[1] = super.y1;
        super.outsideXs[2] = super.x2;
        super.outsideYs[2] = super.y1 +   halfHeight;
        super.outsideXs[3] = super.x1 + 3*quartWidth;
        super.outsideYs[3] = super.y2;
        super.outsideXs[4] = super.x1 +   quartWidth;
        super.outsideYs[4] = super.y2;
        super.outsideXs[5] = super.x1;
        super.outsideYs[5] = super.y1 +   halfHeight;

        // inside points
        super.insideXs[0] = super.x1;
        super.insideYs[0] = super.y1 + super.outlineThickness;
        super.insideXs[1] = super.x2;
        super.insideYs[1] = super.y1 + super.outlineThickness;
        super.insideXs[2] = super.x2;
        super.insideYs[2] = super.y1 + halfHeight;
        super.insideXs[3] = super.x2;
        super.insideYs[3] = super.y2 - super.outlineThickness;
        super.insideXs[4] = super.x1;
        super.insideYs[4] = super.y2 - super.outlineThickness;
        super.insideXs[5] = super.x1;
        super.insideYs[5] = super.y1 + halfHeight;
    }
}
