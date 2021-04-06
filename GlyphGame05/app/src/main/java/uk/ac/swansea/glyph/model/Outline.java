package uk.ac.swansea.glyph.model;

import android.graphics.Color;
import android.graphics.Point;

import uk.ac.swansea.glyph.framework.util.Painter;

/**
 * Created by ric on 17/05/16.
 */
public class Outline extends Shape{


    public Outline(Glyph glyph, int line, int fill, double scale) {
        super(glyph,line, fill,scale);
    }

    public void zoom(double factor){
        this.scale*=factor;
    }

    @Override
    public void newDraw(Painter g, Point point) {
        this.set2DGraphics(g,point);
        g.setColor(line);
        g.setStrokeWidth((float)2.0);
        g.drawOval(x,y, width, height);
        g.setStrokeWidth((float)0.0);
    }

}