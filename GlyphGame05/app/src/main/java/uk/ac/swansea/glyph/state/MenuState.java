package uk.ac.swansea.glyph.state;

import android.util.Log;
import android.view.MotionEvent;

import uk.ac.swansea.glyph.framework.util.Painter;
import uk.ac.swansea.glyph.glyphgame05.Assets;
import uk.ac.swansea.glyph.model.EasyGlyphs;

/**
 * Created by ric on 12/05/16.
 */
public class MenuState extends State {
    private boolean clicked;
    public MenuState(String id) {
        super(id);
        this.clicked = false;
    }
    @Override
    public void init() {
    }

    @Override
    public void update(float delta) {
        if(this.clicked){
            setCurrentState(new PlayState("PlayState",new EasyGlyphs()));
        }
    }

    @Override
    public void render(Painter g) {
        g.drawImage(Assets.welcome, 0, 0);
    }

    @Override
    public boolean onTouch(MotionEvent e, int scaledX, int scaledY) {
        Log.i("info","Touched");
        this.clicked = true;
        return false;
    }
}