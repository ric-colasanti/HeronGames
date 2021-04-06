package uk.ac.swansea.glyph.state;

import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Typeface;
import android.view.MotionEvent;

import uk.ac.swansea.glyph.glyphgame05.Assets;
import uk.ac.swansea.glyph.glyphgame05.GameMainActivity;
import uk.ac.swansea.glyph.model.Board;
import uk.ac.swansea.glyph.framework.util.Painter;
import uk.ac.swansea.glyph.model.GlyphConstructor;

/**
 * Created by ric on 16/05/16.
 */
public class PlayState extends State {

    private Board board;
    private long timeS;
    private GlyphConstructor glyphSet;
    private int total;
    private int clicked;
    private boolean timeup;
    private static final int MAX_TIME = 30000;

    public PlayState(String id, GlyphConstructor glyphSet) {
        super(id);
        this.glyphSet = glyphSet;
        this.clicked = 0;
        this.timeup = false;
        // TODO Auto-generated constructor stub
    }

    public void init() {
        int x = (int) (GameMainActivity.GAME_WIDTH / 8.0);
        int y = (int) (GameMainActivity.GAME_HEIGHT / 3.0);
        System.out.format("%d , %d \n", x, y);
        Point p = new Point(x, y);
        System.out.format("%d,%d \n", p.x, p.y);
        this.board = new Board(6, new Point(x, y), 6 * x, 6 * x, 2,
                this.glyphSet);
        this.board.addGlyphs();
        this.timeS = System.currentTimeMillis();
        this.total = 0;
    }

    public void update(float delta) {
        if (System.currentTimeMillis() - this.timeS < MAX_TIME) {
            this.total += this.board.iterate();
        }else{
            this.timeup = true;
        }
    }

    public void drawClock(Painter g) {

        int x = (int) (GameMainActivity.GAME_WIDTH - 140);
        int y = GameMainActivity.GAME_HEIGHT - 130;
        g.setColor(Color.rgb(33,150,243));
        g.fillOval(x, y, 80, 80);
        g.setColor(Color.rgb(33,150,243));
        g.drawOval(x, y, 80, 80);
        g.setColor(Color.rgb(76,175,80));
        g.fillArc(x, y, 80, 80, 90, (int) (-360
                * (System.currentTimeMillis() - this.timeS) / MAX_TIME));
        g.setColor(Color.rgb(33,150,243));
        g.fillOval(x + 20, y + 20, 40, 40);
        g.setColor(Color.rgb(244,67,54));
        g.fillArc(x + 20, y + 20, 40, 40, 90,
                (int) (360 * (System.currentTimeMillis() - this.timeS) / MAX_TIME));
    }

    public void drawScore(Painter g) {
        g.setFont(Typeface.DEFAULT_BOLD, 60);
        g.setColor( Color.rgb(33,150,243));
        g.drawString(Integer.toString(this.total), 60,
                GameMainActivity.GAME_HEIGHT - 60);
    }

    public void drawDone(Painter g) {
        g.setFont(Typeface.DEFAULT_BOLD, 44);
        g.setColor(Color.rgb(33,150,243));
        String s = " Time's up !";
        g.drawString(s, 160,GameMainActivity.GAME_HEIGHT-(GameMainActivity.GAME_HEIGHT - 150));
        g.setFont(Typeface.DEFAULT_BOLD, 20);
        s = "Double click for next level";
        g.drawString(s, 90,GameMainActivity.GAME_HEIGHT-(GameMainActivity.GAME_HEIGHT - 220));
    }

    public void drawHello(Painter g) {
        g.setFont(Typeface.DEFAULT_BOLD, 44);
        g.setColor(Color.rgb(33,150,243));
        String s = " Game on!";
        g.drawString(s, 160,GameMainActivity.GAME_HEIGHT-(GameMainActivity.GAME_HEIGHT - 150));
    }


    public void render(Painter g) {
        g.drawImage(Assets.play, 0, 0);
         this.board.draw(g);

        this.drawClock(g);
        this.drawScore(g);
        if (this.timeup) {
            this.drawDone(g);
        }else{
            this.drawHello(g);
        }
    }

    public boolean onTouch(MotionEvent event , int scaledX, int scaledY){
        if(this.timeup){
            this.clicked++;
            if (this.clicked>= 2) {
                GlyphConstructor nextSet = this.glyphSet.nextGlyph();
                if (nextSet == null) {
                    setCurrentState(new GameOverState("GameOver"));
                } else {
                    setCurrentState(new PlayState("PlayState", nextSet));
                }
                this.timeup=false;
                this.clicked=0;
            }
        }else  {
            this.board.clickedBoard(scaledX,scaledY);
        }
        return false;
    }

}