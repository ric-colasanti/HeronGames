package uk.ac.swansea.glyph.state;

import android.util.Log;
import android.view.MotionEvent;

import uk.ac.swansea.glyph.framework.util.Painter;
import uk.ac.swansea.glyph.glyphgame05.Assets;
import uk.ac.swansea.glyph.model.EasyGlyphs;

/**
 * Created by ric on 17/05/16.
 */
public class GameOverState extends State{

    public GameOverState(String id) {
        super(id);
    }
    @Override
    public void init() {
    }

    @Override
    public void update(float delta) {

    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.gameOver, 0, 0);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        return false;
    }
}
