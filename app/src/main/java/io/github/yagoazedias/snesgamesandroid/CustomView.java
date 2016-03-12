package io.github.yagoazedias.snesgamesandroid;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CustomView extends View implements Runnable{

    public Flog flog = new Flog();

    Handler handler = new Handler();

    public void InitFlog() {
        flog.x = 450;
        flog.y = getHeight() - 50;

        flog.width  = flog.x + 100;
        flog.height = flog.y + 50;
    }

    public void UpdateFlogPropites() {
        flog.width  = flog.x + 100;
        flog.height = flog.y + 50;
    }

    public CustomView(Context context) {
        super(context);
        InitFlog();
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public boolean onTouchEvent(MotionEvent event)
    {
        flog.y -= 100;
        UpdateFlogPropites();
        return false;
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        flog.paint.setColor(Color.GREEN);

        canvas.drawRect(flog.x, flog.y, flog.width, flog.height, flog.paint);

        Update();

        try {
            Thread.sleep(30);
        } catch (InterruptedException e) { }

        invalidate();
    }

    private void Update() {
        UpdateFlogPropites();
    }

    @Override
    public void run() {
        handler.postDelayed(this, 30);
    }
}