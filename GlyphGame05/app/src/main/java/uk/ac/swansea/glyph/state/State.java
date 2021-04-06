package uk.ac.swansea.glyph.state;

/**
 * Created by ric on 12/05/16.
 */

import android.app.usage.UsageEvents;
import android.view.MotionEvent;

import uk.ac.swansea.glyph.framework.util.Painter;
import uk.ac.swansea.glyph.glyphgame05.GameMainActivity;

public abstract class State {
    protected String id;
    public State(String id){
        this.id = id;
    }

    public  void setCurrentState(State newState){
        GameMainActivity.sGame.setCurrentState(newState);
    }
    public abstract void init();

    public abstract void update(float delta);

    public abstract void render(Painter g);

    public abstract boolean onTouch(MotionEvent event , int scaledX, int scaledY);
}

