package uk.ac.swansea.glyph.framework.util;

/**
 * Created by ric on 12/05/16.
 */
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Typeface;

public class Painter {
    private Canvas canvas;
    private Paint paint;
    private Rect srcRect;
    private Rect dstRect;
    private RectF dstRectF;

    public Painter(Canvas canvas) {
        this.canvas = canvas;
        paint = new Paint();
        paint.setAntiAlias(true);
        srcRect = new Rect();
        dstRect = new Rect();
        dstRectF = new RectF();
    }

    public void setColor(int color) {
        paint.setColor(color);
    }

    public void setFont(Typeface typeface, float textSize) {
        paint.setTypeface(typeface);
        paint.setTextSize(textSize);
    }

    public void drawString(String str, int x, int y) {
        canvas.drawText(str, x, y, paint);
    }

    public void fillRect(int x, int y, int width, int height) {
        dstRect.set(x, y, x + width, y + height);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRect(dstRect, paint);
    }


    public void drawImage(Bitmap bitmap, int x, int y) {
        canvas.drawBitmap(bitmap, x, y, paint);
    }

    public void drawImage(Bitmap bitmap, int x, int y, int width, int height) {
        srcRect.set(0, 0, bitmap.getWidth(), bitmap.getHeight());
        dstRect.set(x, y, x + width, y + height);
        canvas.drawBitmap(bitmap, srcRect, dstRect, paint);
    }

    public void setStrokeWidth(Float width){
        paint.setStrokeWidth(width);
    }

    public void fillArc(int x, int y, int width, int height,int start,  int end) {
        paint.setStyle(Paint.Style.FILL);
        dstRectF.set(x, y, x + width, y + height);
        canvas.drawArc(dstRectF,start,end,true, paint);
    }


    public void fillOval(int x, int y, int width, int height) {
        paint.setStyle(Paint.Style.FILL);
        dstRectF.set(x, y, x + width, y + height);
        canvas.drawOval(dstRectF, paint);
    }
    public void drawOval(int x, int y, int width, int height) {
        paint.setStyle(Paint.Style.STROKE);
        dstRectF.set(x, y, x + width, y + height);
        canvas.drawOval(dstRectF, paint);
    }
}
