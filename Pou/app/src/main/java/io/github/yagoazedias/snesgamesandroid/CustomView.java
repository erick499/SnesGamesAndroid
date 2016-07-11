package io.github.yagoazedias.snesgamesandroid;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CustomView extends View implements Runnable {

    public Handler handler = new Handler();

    // Player properties.
    float xpad = (float)(getPaddingLeft() + getPaddingRight()) + 300;
    float ypad = (float)(getPaddingTop() + getPaddingBottom()) + 1300;

    // Water properties.
    Rect whate = new Rect(getPaddingLeft(), getPaddingBottom() +  400, getPaddingLeft() + 5000, 100);

    public void OnStart(){

    }

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }


    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(xpad, ypad, 100, new Paint(Paint.ANTI_ALIAS_FLAG));
        canvas.drawRect(whate, new Paint(Paint.DEV_KERN_TEXT_FLAG));
    }

    private void Update() {

    }

    @Override
    public void run() {
        handler.postDelayed(this, 30);
    }
}