package jvegred.figures;

import java.awt.Graphics2D;
import java.awt.Paint;

public class Texto extends Figure {

    private String text;

    protected Texto(int x, int y, int w, int h, Paint fill, String text) {
        super(x, y, w, h, fill);
        
        this.text = text;
    }

    @Override
    public void draw(Graphics2D g2d) {
        // TODO Implementar Texto.draw
    }
}
