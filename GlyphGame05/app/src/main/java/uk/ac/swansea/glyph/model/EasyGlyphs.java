package uk.ac.swansea.glyph.model;

import android.graphics.Color;

import java.util.Random;

/**
 * Created by ric on 17/05/16.
 */
public class EasyGlyphs extends GlyphConstructor{

    private Glyph glyphTypeA(){
        Glyph g= new Glyph("A",0.9);
        Circle c = new Circle(g,  Color.rgb(76,175,80),  Color.rgb(76,175,80),0.9);
        g.addShape(c);

        return g;
    }

    private Glyph glyphTypeB(){
        Glyph g= new Glyph("B",0.9);
        Circle c = new Circle(g,Color.rgb(244,67,54), Color.rgb(244,67,54),0.9);
        g.addShape(c);

        return g;
    }

    private Glyph glyphTypeC(){
        Glyph g= new Glyph("C",0.9);
        Circle c = new Circle(g, Color.rgb(33,150,243),  Color.rgb(33,150,243),0.9);
        g.addShape(c);

        return g;
    }

    private Glyph glyphTypeD(){
        Glyph g= new Glyph("D",0.9);
        Circle c = new Circle(g, Color.rgb(255,152,0),  Color.rgb(255,152,0),0.9);
        g.addShape(c);

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
        return new MediumGlyphs();
    }
}

