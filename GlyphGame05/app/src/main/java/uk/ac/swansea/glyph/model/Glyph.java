package uk.ac.swansea.glyph.model;

import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;

import java.util.ArrayList;

import uk.ac.swansea.glyph.framework.util.Painter;

/**
 * Created by ric on 16/05/16.
 */
public class Glyph {
    public static int glyphSpaceHeight, glyphSpaceWidth, glyphHeight,
            glyphWidth;
    public static int glyphSpaceHeightOffset, glyphSpaceWidthOffset;

    private ArrayList<Shape> shapes;
    private String id;
    private double scale;
    private boolean taged;
    private int countDown;
    private Outline hilight;
    private Cell home;
    private double offset;

    public double getOffset() {
        return offset;
    }

    public void setOffset(double offset) {
        this.offset = offset;
    }

    public Cell getHome() {
        return home;
    }

    public void setHome(Cell home) {
        this.home = home;
    }

    public Glyph(String id, double scale) {
        this.id = id;
        this.scale = scale;
        this.shapes = new ArrayList<Shape>();
        this.taged = false;
        this.countDown = 20;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public void addShape(Shape s) {
        this.shapes.add(s);
    }

    public String getId() {
        return this.id;
    }

    public void remove() {
        this.taged = true;
    }

    public boolean ticked() {
        if (this.taged == true) {
            this.countDown--;
            if (this.countDown < 0) {
                home.clearOccupant();
                return true;
            }
        }
        return false;
    }

    public boolean isTaged() {
        return this.taged;
    }

    public double getXPos(){
        return home.getxPos();
    }

    public double getYPos(){
        return home.getyPos()+offset;
    }

    public void newDraw(Painter g,Point point) {
        if(this.offset<0)this.offset+=0.2;
        if(this.offset>0.01)this.offset=0.0;
        for (Shape s : this.shapes) {
            s.newDraw(g, point);
        }
        if (this.taged) {
            if (this.hilight == null) {
                this.hilight = new Outline(this, Color.YELLOW, Color.YELLOW, 1.0);
            }
            this.hilight.zoom((this.countDown) / 20.0);
            this.hilight.newDraw(g, point);
        }
    }

}

