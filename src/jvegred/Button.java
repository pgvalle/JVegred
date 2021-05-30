package jvegred;

import jvegred.figures.Figure;

public class Button {
    
    public Figure innerfig;
    public char ftype;

    public Button(Figure f, char ftype) {
        this.innerfig = f;
        this.ftype = ftype;
    }
}
