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
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

public class CustomView extends View implements Runnable{

    public Flog flog = new Flog();
    public Handler handler = new Handler();

    public void InitFlog() {
        flog.x = getMeasuredWidth() / 2;
        flog.y = getMeasuredHeight() - 50;

        System.out.println(getMeasuredHeight());

        flog.width  = flog.x + 100;
        flog.height = flog.y + 50;
    }

    public void UpdateFlogProprietes() {
        flog.width  = flog.x + 100;
        flog.height = flog.y + 50;

        if(flog.y < 0)
        {
            InitFlog();
        }
    }

    public void Move(String path){

        if(path.equals("left")){
            flog.x -= getMeasuredWidth() / 5;
        }
        if(path.equals("right")){
            flog.x += getMeasuredWidth() / 5;
        }
        if(path.equals("up")){
            flog.y -= getMeasuredHeight() / 11;
        }
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

    public boolean onTouchEvent(MotionEvent event) {
        UpdateFlogProprietes();

        int x = (int) event.getX();
        int y = (int) event.getY();

        if(x < (getWidth() / 2) - 300)
        {
            Move("left");
        }
        else if(x > (getWidth() / 2) + 300)
        {
            Move("right");
        }
        else{
            Move("up");
        }


        invalidate();
        return super.onTouchEvent(event);
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
        UpdateFlogProprietes();
    }

    @Override
    public void run() {
        handler.postDelayed(this, 30);
    }
}