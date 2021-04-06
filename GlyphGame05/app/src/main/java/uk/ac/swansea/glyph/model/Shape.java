package uk.ac.swansea.glyph.model;

import android.graphics.Color;
import android.graphics.Point;

import uk.ac.swansea.glyph.framework.util.Painter;

/**
 * Created by ric on 16/05/16.
 */
public abstract class Shape {
    protected int fill,line;
    protected Glyph glyph;
    protected double scale;
    protected int x,y,width,height;
    public Shape(Glyph glyph, int line , int fill, double scale){
        this.line = line;
        this.fill = fill;
        this.scale =scale;
        this.glyph= glyph;
    }

    public void set2DGraphics(Painter g,Point point){
        double dx = point.x+Glyph.glyphSpaceWidthOffset+(Glyph.glyphSpaceWidth*this.glyph.getXPos());
        double dy = point.y+Glyph.glyphSpaceHeightOffset+(Glyph.glyphSpaceHeight*this.glyph.getYPos());
        x = (int)(dx-(Glyph.glyphWidth/2.0)*scale*this.glyph.getScale());
        y = (int)(dy-(Glyph.glyphHeight/2.0)*scale*this.glyph.getScale());
        width =  (int)(Glyph.glyphWidth*scale*this.glyph.getScale());
        height = (int)(Glyph.glyphHeight*scale*this.glyph.getScale());
    }

    public abstract void newDraw(Painter g,Point point);
}
