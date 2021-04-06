package uk.ac.swansea.glyph.glyphgame05;

import android.app.Activity;
import android.content.res.AssetManager;
import android.os.Bundle;

public class GameMainActivity extends Activity {


    public static final int GAME_WIDTH = 450;
    public static final int GAME_HEIGHT = 800;
    public static GameView sGame;
    public static AssetManager assets;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assets = getAssets();
        sGame = new GameView(this,GAME_WIDTH,GAME_HEIGHT);
        setContentView(sGame);
    }
}
