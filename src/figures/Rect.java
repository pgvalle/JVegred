package figures;

import java.awt.*;

public class Rect {

    private int x, y;
    private int ox = 0, oy = 0;

    private int w, h;

    private Color background = Color.WHITE;

    private Color outline = Color.BLACK;
    private int outlineThickness = 1;

    public Rect(int x, int y, int w, int h) {
        this.x = x;
        this.y = y;

        this.w = w;
        this.h = h;
    }


    public void paint(Graphics g) {
        // painting outline
        g.setColor(this.outline);
        g.fillRect(this.x + this.ox, this.y + this.oy, this.w, this.h);

        // painting background
        g.setColor(this.background);
        g.fillRect(
            this.x + this.ox + this.outlineThickness,
            this.y + this.oy + this.outlineThickness,
            this.w - 2 * this.outlineThickness,
            this.h - 2 * this.outlineThickness
        );
    }


    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void drag(int dx, int dy) {
        this.x += dx;
        this.y += dy;
    }

    public void setOrigin(int ox, int oy) {
        this.ox = ox;
        this.oy = oy;
    }

    // public int getXPos() { return this.x + this.ox; }
    // public int getYPos() { return this.y + this.oy; }


    public void setSize(int w, int h) {
        this.w = w >= 0 ? w : this.w;
        this.h = h >= 0 ? h : this.h;
    }

    public void resize(int dw, int dh) {
        this.w = this.w + dw >= 0 ? this.w + dw : 0;
        this.h = this.h + dh >= 0 ? this.h + dh : 0;
    }

    // public int getWidth() { return this.w; }
    // public int getHeight() { return this.h; }


    public void setBackgroundColor(Color c) {
        this.background = c;
    }

    public void setOutlineColor(Color c) {
        this.outline = c;
    }


    public void setOutlineThickness(int i) {
        this.outlineThickness = i >= 0 ? i: -i;
    }
}
