package jvegred.figures;

import java.awt.*;
import java.util.ArrayList;

public class FigureHandler {
    
    public static ArrayList<Figure> figures = new ArrayList<Figure>();

    // context variables
    public static int x = 0, y = 0;
    public static int w = 50, h = 50;
    
    public static Paint fill = Color.WHITE, outline = Color.BLACK;

    public static int outlineThickness = 2;
    
    // modes
    public static boolean onResizeMode = false;
    public static boolean onMultipleSelectMode = false;

    public static int changingPropertyType = 0;

    public static void refreshFigures() {
        for (Figure f : figures) {
            if (!f.onFocus) {
                continue;
            }
            f.resize(0, 0, 0, 0);
            f.fill = fill;

            if (f instanceof Geometric2D) {
                Geometric2D g = (Geometric2D) f;
                g.outline = outline;
                g.outlineThickness = outlineThickness;
            } else {

            }
        } 
    }

    public static void addFigure(char type) {
        for (Figure f : figures) {
            f.onFocus = false;
        }

        if (type == 'e') {
            figures.add(new Elipse(
                x, y, w, h, fill, outline, outlineThickness
            ));
        } else if (type == 't') {
            figures.add(new Triangulo(
                x, y, w, h, fill, outline, outlineThickness
            ));
        } else if (type == 'r') {
            figures.add(new Retangulo(
                x, y, w, h, fill, outline, outlineThickness
            ));
        } else if (type == 's') {
            figures.add(new Texto(x, y, w, h, outline, "Mansabling"));
        }
    }

    public static void swapFocusAtXY() {
        for (int i = figures.size() - 1; i >= 0; i--) {
            Figure f = figures.get(i);

            if (f.contains(x, y)) {
                if (f.onFocus = !f.onFocus) {
                    figures.remove(i);
                    figures.add(f);
                }
                
                for (int j = i - 1; j >= 0; j--) {
                    figures.get(j).onFocus = false;
                }

                return;
            }

            f.onFocus = false;
        }
    }

    public static void multipleSwapFocusAtXY() {
        for (int i = figures.size() - 1; i >= 0; i--) {
            Figure f = figures.get(i);

            if (f.contains(x, y)) {
                if (f.onFocus = !f.onFocus) {
                    figures.remove(i);
                    figures.add(f);
                }
                
                i = 0;
            }
        }
    }

    public static void removeFiguresOnFocus() {
        for (int i = 0; i < figures.size(); i++) {
            if (figures.get(i).onFocus) {
                figures.remove(i--);
            }
        }
    }
    
    public static void paintFigures(Graphics2D g2d) {
        int lx = Integer.MAX_VALUE, ly = Integer.MAX_VALUE, gx = 0, gy = 0;
        
        for (Figure f : figures) {
            if (f.onFocus) {
                lx = f.x < lx ? f.x : lx;
                ly = f.y < ly ? f.y : ly;
                gx = f.x + f.w > gx ? f.x + f.w : gx;
                gy = f.y + f.h > gy ? f.y + f.h : gy;
                
                g2d.setPaint(Color.RED);
                g2d.drawRect(f.x, f.y, f.w, f.h);
            }
            
            f.paint(g2d);
        }
        
        g2d.setPaint(Color.RED);
        g2d.drawRect(lx, ly, gx - lx, gy - ly);

        g2d.setPaint(fill);
        g2d.fillRect(0, 0, 20, 20);
        g2d.setPaint(outline);
        g2d.fillRect(20, 0, 20, 20);
        (new Texto(40, 0, 20, 20, outline, Integer.toString(outlineThickness))).paint(g2d);
    }
    
    public static void dragFiguresOnFocus(int dx, int dy) {
        int lx = Integer.MAX_VALUE, ly = Integer.MAX_VALUE, gx = 0, gy = 0;
        ArrayList<Figure> tmp = new ArrayList<Figure>();

        for (Figure f : figures) {
            if (f.onFocus) {
                lx = f.x < lx ? f.x : lx;
                ly = f.y < ly ? f.y : ly;
                gx = f.x + f.w > gx ? f.x + f.w : gx;
                gy = f.y + f.h > gy ? f.y + f.h : gy;

                tmp.add(f);
            }
        }

        if (lx <= x && x <= gx && ly <= y && y <= gy) {
            for (Figure f : tmp) {
                if (f.onFocus) {
                    f.drag(dx, dy);
                }
            }
        }
    }
    
    public static void resizeFigure(int dx, int dy) {
        Figure f = null;

        for (int i = figures.size() - 1; i >= 0; i--) {
            Figure g = figures.get(i);
            g.onFocus = false;

            if (g.contains(x, y)) {
                for (int j = i - 1; j >= 0; j--) {
                    figures.get(j).onFocus = false;
                }

                g.onFocus = true;
                figures.remove(i);
                figures.add(g);

                f = g;
                i = 0;
            }
        }

        if (f == null) {
            return;
        }

        double xx = Math.pow(x - f.x, 2), yy = Math.pow(y - f.y, 2);
        double wx = Math.pow(x - f.x - f.w, 2), wy = Math.pow(y - f.y - f.h, 2);
        
        int tl = (int) Math.sqrt(xx*xx + yy*yy);
        int tr = (int) Math.sqrt(wx*wx + yy*yy);
        int br = (int) Math.sqrt(wx*wx + wy*wy);
        int bl = (int) Math.sqrt(xx*xx + wy*wy);
        
        int min = Math.min(tl, Math.min(tr, Math.min(br, bl)));

        if (min == tl) { // case topleft
            f.resize(dx, dy, 0, 0);
        } else if (min == tr) { // case topright
            f.resize(0, dy, dx, 0);
        } else if (min == br) { // case bottomright
            f.resize(0, 0, dx, dy);
        } else if (min == bl) { // case bottomleft
            f.resize(dx, 0, 0, dy);
        }
    }
}
