package uk.ac.swansea.glyph.model;

import android.graphics.Color;
import android.graphics.Point;
import android.util.Log;

import java.util.ArrayList;

import uk.ac.swansea.glyph.framework.util.Painter;
import uk.ac.swansea.glyph.glyphgame05.GameMainActivity;

/**
 * Created by ric on 16/05/16.
 */
public class Board {
    private Cell[][] cellGrid;
    private ArrayList<Cell> cells;

    private Point point;
    private int boardHeight, boardWidth;
    private int border;
    private int size;
    private GlyphConstructor glyphSet;

    public Board(int size, Point point, int boardHeight, int boardWidth,
                 int border, GlyphConstructor glyphSet) {
        this.point = point;
        this.boardHeight = boardHeight;
        this.boardWidth = boardWidth;
        this.border = border;
        this.size = size;
        this.glyphSet = glyphSet;

        Glyph.glyphSpaceHeight = (int) ((this.boardWidth * 1.0 / (size * 1.0)));
        Glyph.glyphSpaceWidth = (int) ((this.boardHeight * 1.0 / (size * 1.0)));
        Glyph.glyphSpaceHeightOffset = (int) ((Glyph.glyphSpaceHeight * 1.0) / 2.0);
        Glyph.glyphSpaceWidthOffset = (int) ((Glyph.glyphSpaceWidth * 1.0) / 2.0);

        Glyph.glyphWidth = (int) ((this.boardWidth * 1.0 / (size * 1.0)))
                - this.border;
        Glyph.glyphHeight = (int) ((this.boardHeight * 1.0 / (size * 1.0)))
                - this.border;
        this.cellGrid = new Cell[size][size];
        this.cells = new ArrayList<Cell>();
    }

    public void clickedBoard(int x, int y) {
        if ((x > point.x) && (x < point.x + this.boardWidth)) {
            if ((y > point.y) && (y < point.y + this.boardWidth)) {
                int xPos = (int) (6 * ((x - point.x * 1.0) / (double) this.boardWidth));
                int yPos = (int) (6 * ((y - point.y * 1.0) / (double) this.boardHeight));
                this.cellGrid[xPos][yPos].removeGlyph();
            }
        }
    }

    private boolean bounds(int v) {
        if (v < 0)
            return false;
        if (v >= this.size)
            return false;
        return true;
    }

    public void addGlyphs() {
        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                Glyph glyph = this.glyphSet.randomGlyph();
                this.cellGrid[i][j] =  new Cell(i, j);
                this.cellGrid[i][j].setOccupant(glyph);
                this.cells.add(this.cellGrid[i][j]);
                glyph.setOffset(-1.0);
            }
        }

        for (int i = 0; i < this.size; i++) {
            for (int j = 0; j < this.size; j++) {
                Glyph g = this.glyphSet.randomGlyph();
                if (bounds(j + 1)) {
                    this.cellGrid[i][j].setBelow(this.cellGrid[i][j + 1]);
                    this.cellGrid[i][j].addNeighbour(this.cellGrid[i][j + 1]);
                }
                if (bounds(j - 1)) {
                    this.cellGrid[i][j].setAbove(this.cellGrid[i][j - 1]);
                    this.cellGrid[i][j].addNeighbour(this.cellGrid[i][j - 1]);
                }
                if (bounds(i + 1)) {
                    this.cellGrid[i][j].addNeighbour(this.cellGrid[i + 1][j]);
                }
                if (bounds(i - 1)) {
                    this.cellGrid[i][j].addNeighbour(this.cellGrid[i - 1][j]);
                }
            }
        }
    }

    public int iterate() {
        // Remove selected glyphs
        int count = 0;
        for (Cell cell : this.cells) {
            if(cell.glyphTicked())count++;
            cell.move();
        }

        // Add random glyphs to the top of the board
        for (int i = 0; i < this.size; i++) {
            if (this.cellGrid[i][0].getOccupant() == null) {
                Glyph glyph = this.glyphSet.randomGlyph();
                this.cellGrid[i][0].setOccupant(glyph);
                glyph.setOffset(-1.0);
            }
        }
        return count;
    }

    public void draw(Painter g) {
        for (Cell cell : this.cells) {
            cell.newDraw(g, point);
        }
    }

}
