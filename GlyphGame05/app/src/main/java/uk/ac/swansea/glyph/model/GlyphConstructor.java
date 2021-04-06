package uk.ac.swansea.glyph.model;

/**
 * Created by ric on 17/05/16.
 */
public abstract class GlyphConstructor {
    public abstract Glyph randomGlyph();
    public abstract GlyphConstructor nextGlyph();
}
