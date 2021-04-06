package uk.ac.swansea.glyph.model;

import android.support.graphics.Point;

import java.util.ArrayList;

import uk.ac.swansea.glyph.framework.util.Painter;

/**
 * Created by ric on 16/05/16.
 */
public class Cell {

    private ArrayList<Cell> neighbours;
    private Cell below, above;
    private int xPos, yPos;
    private Glyph occupant;

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public void setBelow(Cell below) {
        this.below = below;
    }

    public void setAbove(Cell above) {
        this.above = above;
    }

    public void move() {
        if (this.occupant != null) {
            if (this.below != null) {
                if (this.below.getOccupant() == null) {
                    this.below.setOccupant(occupant);
                    occupant.setOffset(-1.0);
                    occupant = null;
                    if (this.above != null) {
                        this.above.move();
                    }
                }
            }
        }
    }

    public Glyph getOccupant() {
        return occupant;
    }

    public void clearOccupant() {
        this.occupant = null;
    }

    public void setOccupant(Glyph occupant) {
        this.occupant = occupant;
        this.occupant.setHome(this);
    }

    public void addNeighbour(Cell neighbour) {
        this.neighbours.add(neighbour);
    }

    public void removeGlyph() {
        if (this.occupant != null) {
            remove(this.occupant.getId());
        }
    }

    public boolean glyphTicked() {
        if (this.occupant != null) {
            return this.occupant.ticked();
        }
        return false;
    }

    private void remove(String mId) {
        if (occupant == null)
            return;
        if (occupant.isTaged())
            return;
        if (mId != occupant.getId())
            return;
        occupant.remove();
        for (Cell c : neighbours) {
            c.remove(mId);
        }
    }

    public void newDraw(Painter g, Point p) {
        if (this.occupant != null) {
            this.occupant.newDraw(g, p);
        }
    }

    public Cell(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
        this.neighbours = new ArrayList<Cell>();
        this.occupant = null;
        this.below = null;
    }

}
