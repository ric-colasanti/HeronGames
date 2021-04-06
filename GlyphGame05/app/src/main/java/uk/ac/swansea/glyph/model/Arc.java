package uk.ac.swansea.glyph.model;

import android.graphics.Point;

import uk.ac.swansea.glyph.framework.util.Painter;

/**
 * Created by ric on 17/05/16.
 */
public class Arc extends Shape{
    private int startAngle;
    private int endAngle;
    public Arc(Glyph glyph, int line, int fill, Double scale, int startAngle, int endAngle) {
        super(glyph, line, fill, scale);
        this.startAngle = startAngle;
        this.endAngle = endAngle;
    }



    @Override
    public void newDraw(Painter g, Point point) {
        this.set2DGraphics(g,point);
        g.setColor(fill);
        g.fillArc(x,y,width,height,this.startAngle,this.endAngle);
    }

}
