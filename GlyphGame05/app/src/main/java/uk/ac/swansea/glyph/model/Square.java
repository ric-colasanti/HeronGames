package uk.ac.swansea.glyph.model;

import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;

import uk.ac.swansea.glyph.framework.util.Painter;

/**
 * Created by ric on 16/05/16.
 */
public class Square extends Shape {


    public Square(Glyph glyph, int line, int fill, double scale) {
        super(glyph,line, fill,scale);
        // TODO Auto-generated constructor stub
    }



    public void newDraw(Painter g, Point point) {
        this.set2DGraphics(g,point);
        g.setColor(fill);
        g.fillRect(x,y,width,height);
    }
}