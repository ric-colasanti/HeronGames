package uk.ac.swansea.glyph.model;

import android.graphics.Point;

import uk.ac.swansea.glyph.framework.util.Painter;

/**
 * Created by ric on 17/05/16.
 */
public class Circle extends Shape{


    public Circle(Glyph glyph, int line, int fill, double scale) {
        super(glyph,line, fill, scale);
    }



    @Override
    public void newDraw(Painter g, Point point) {
        this.set2DGraphics(g,point);
        g.setColor(fill);
        g.fillOval(x,y, width,height);
        g.setColor(line);
        g.drawOval(x,y, width,height);
    }

}
