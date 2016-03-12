package io.github.yagoazedias.snesgamesandroid;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class CustomView extends View implements Runnable {

    public Flog flog   = new Flog();
    public Water water = new Water();
    public Handler handler = new Handler();
    public List<Plant> plants;
    public int[] waterObjPos = {0, 159, 318, 477};

    public void OnStart(){
        InitFlog();
        InitSurfaces();
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

    public void InitSurfaces(){
        plants[0].x = 0;
        plants[0].y = waterObjPos[0];
    }

    public void InitFlog() {
        flog.x = 450;
        flog.y = getMeasuredHeight() - ((getMeasuredHeight() / 11) / 2);

        System.out.println(getMeasuredWidth());

        flog.width  = flog.x + 100;
        flog.height = flog.y + 50;
    }

    public void UpdateWaterProprietes(){
        water.width = water.x + getMeasuredWidth();
        water.height = water.y + 636;
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

    public boolean onTouchEvent(MotionEvent event) {
        UpdateFlogProprietes();
        UpdateWaterProprietes();

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
        water.paint.setColor(Color.BLUE);
        plants[0].paint.setColor(Color.GREEN);

        canvas.drawRect(water.x, water.y, water.width, water.height, water.paint);
        canvas.drawRect(plants[0].x, plants[0].y, plants[0].width, plants[0].height, plants[0].paint);
        canvas.drawRect(flog.x, flog.y, flog.width, flog.height, flog.paint);

        Update();

        try {
            Thread.sleep(30);
        } catch (InterruptedException e) { }

        invalidate();
    }

    private void Update() {
        UpdateFlogProprietes();
        System.out.println(getMeasuredWidth());
    }

    @Override
    public void run() {
        handler.postDelayed(this, 30);
    }
}