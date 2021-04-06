package uk.ac.swansea.glyph.model;

import android.content.res.Resources;
import android.graphics.Color;

import java.util.Random;

/**
 * Created by ric on 17/05/16.
 */
public class HardGlyphs extends GlyphConstructor{

    private Glyph glyphTypeA(){
        Glyph g= new Glyph("A",0.9);
        Circle c = new Circle(g, Color.rgb(76,175,80),  Color.rgb(76,175,80),0.9);
        g.addShape(c);
        Circle mc = new Circle(g, Color.rgb(244,67,54),  Color.rgb(244,67,54),0.5);
        g.addShape(mc);
        Arc a = new Arc(g, Color.rgb(33,150,243),Color.rgb(33,150,243),0.7,15,25);
        g.addShape(a);
        return g;
    }

    private Glyph glyphTypeB(){
        Glyph g= new Glyph("B",0.9);
        Circle c = new Circle(g,Color.rgb(76,175,80), Color.rgb(76,175,80),0.9);
        g.addShape(c);
        Circle mc = new Circle(g,Color.rgb(244,67,54),Color.rgb(244,67,54),0.5);
        g.addShape(mc);
        Arc a = new Arc(g,Color.rgb(33,150,243),Color.rgb(33,150,243),0.7,25,35);
        g.addShape(a);
        return g;
    }

    private Glyph glyphTypeC(){
        Glyph g= new Glyph("C",0.9);
        Circle c = new Circle(g,Color.rgb(244,67,54),Color.rgb(244,67,54),0.9);
        g.addShape(c);
        Circle mc = new Circle(g,Color.rgb(76,175,80), Color.rgb(76,175,80),0.5);
        g.addShape(mc);
        Arc a = new Arc(g,Color.rgb(33,150,243),Color.rgb(33,150,243),0.7,15,25);
        g.addShape(a);
        return g;
    }

    private Glyph glyphTypeD(){
        Glyph g= new Glyph("D",0.9);
        Circle c = new Circle(g,Color.rgb(76,175,80), Color.rgb(76,175,80),0.9);
        g.addShape(c);
        Circle mc = new Circle(g,Color.rgb(33,150,243), Color.rgb(33,150,243),0.5);
        g.addShape(mc);
        Arc a = new Arc(g,Color.rgb(244,67,54), Color.rgb(76,175,80),0.7,25,35);
        g.addShape(a);
        return g;
    }

    public Glyph randomGlyph(){
        Random rand = new Random();
        switch(rand.nextInt(4)){
            case 0: return this.glyphTypeA();
            case 1: return this.glyphTypeB();
            case 2: return this.glyphTypeC();
            case 3: return this.glyphTypeD();
        }
        return null;
    }

    @Override
    public GlyphConstructor nextGlyph() {
        // TODO Auto-generated method stub
        return null;
    }
}