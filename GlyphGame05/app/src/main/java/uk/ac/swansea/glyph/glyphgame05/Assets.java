package uk.ac.swansea.glyph.glyphgame05;

import android.graphics.Bitmap;
import java.io.IOException;
import java.io.InputStream;

import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;

import java.io.InputStream;

/**
 * Created by ric on 12/05/16.
 */
public class Assets {
    public static Bitmap welcome;
    public static Bitmap play;
    public static Bitmap gameOver;

    public static void load(){
        gameOver = loadBitmap("gameover.png",false);
        welcome = loadBitmap("welcome.png",false);
        play =  loadBitmap("play.png",false);
    }
    private static Bitmap loadBitmap(String filename, boolean transparency) {
        InputStream inputStream = null;
        try {
            inputStream = GameMainActivity.assets.open(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Options options = new Options();
        if (transparency) {
            options.inPreferredConfig = Config.ARGB_8888;
        } else {
            options.inPreferredConfig = Config.RGB_565;
        }
        Bitmap bitmap = BitmapFactory.decodeStream(inputStream, null,
                options);
        return bitmap;
    }
}
