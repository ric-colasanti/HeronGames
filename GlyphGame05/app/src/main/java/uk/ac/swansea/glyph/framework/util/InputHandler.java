package uk.ac.swansea.glyph.framework.util;

import android.view.MotionEvent;
import android.view.View;

import uk.ac.swansea.glyph.state.State;
import uk.ac.swansea.glyph.glyphgame05.GameMainActivity;

/**
 * Created by ric on 12/05/16.
 */
public class InputHandler implements View.OnTouchListener {


    private State currentState;

    public void setCurrentState(State currentState){
        this.currentState = currentState;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int scaledX = (int) ((event.getX()/v.getWidth())* GameMainActivity.GAME_WIDTH);
        int scaledY = (int) ((event.getY()/v.getHeight())* GameMainActivity.GAME_HEIGHT);
        return currentState.onTouch(event,scaledX,scaledY);
    }
}
