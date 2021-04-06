package uk.ac.swansea.glyph.state;

import android.view.MotionEvent;

import uk.ac.swansea.glyph.framework.util.Painter;
import uk.ac.swansea.glyph.glyphgame05.Assets;

/**
 * Created by ric on 12/05/16.
 */
public class LoadState extends State {
    public LoadState(String id) {
        super(id);
    }
    @Override
    public void init() {
        Assets.load();
    }

    @Override
    public void update(float delta) {
        setCurrentState(new MenuState("MenueState"));
    }

    @Override
    public void render(Painter g) {
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        return false;
    }
}