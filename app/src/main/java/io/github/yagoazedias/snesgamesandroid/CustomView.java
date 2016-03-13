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
    public Plant[] plants1 = new Plant[3];
    public Plant[] plants2 = new Plant[3];
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

    public void UpdatePlants() {
        InitSurfacesLengths();

        for(int i = 0; i < plants1.length; i++) {
            plants1[i].x -= 5;

            if(plants1[i].x < -139) {
                plants1[i].x = getMeasuredWidth();
                InitSurfacesLengths();
            }
        }

        for(int i = 0; i < plants2.length; i ++) {
            plants2[i].x += 5;

            if(plants2[i].x > getMeasuredWidth()) {
                plants2[i].x = -139;
                InitSurfacesLengths();
            }
        }
    }

    public void InitSurfaces(){
        for(int i = 0; i < plants1.length; i++) {
            plants1[i] = new Plant();

            if(i == 0) {
                plants1[i].x = 20;
                plants1[i].y = 20;
                plants1[i].height = plants1[i].y + 139;
                plants1[i].width  = plants1[i].x + 139;
            }else{
                plants1[i].x = plants1[i - 1].x + 400;
                plants1[i].y = 20;
                plants1[i].height = plants1[i].y + 139;
                plants1[i].width  = plants1[i].x + 139;
            }
        }

        for(int i = 0; i < plants2.length; i ++) {
            plants2[i] = new Plant();

            if(i == 0) {
                plants2[i].x = 20;
                plants2[i].y = 169;
                plants2[i].height = plants2[i].y + 149;
                plants2[i].width  = plants2[i].x + 149;
            }else{
                plants2[i].x = plants2[i - 1].x + 400;
                plants2[i].y = 169;
                plants2[i].height = plants2[i].y + 199;
                plants2[i].width  = plants2[i].x + 199;
            }
        }
    }

    public void InitSurfacesLengths() {
        for(int i = 0; i < plants1.length; i++) {

            if(i == 0) {
                plants1[i].height = plants1[i].y + 139;
                plants1[i].width  = plants1[i].x + 139;
            }else{
                plants1[i].height = plants1[i].y + 139;
                plants1[i].width  = plants1[i].x + 139;
            }


            if(i == 0) {
                plants2[i].height = plants2[i].y + 139;
                plants2[i].width  = plants2[i].x + 139;
            }else{
                plants2[i].height = plants2[i].y + 159;
                plants2[i].width  = plants2[i].x + 159;
            }
        }
    }

    public void InitFlog() {
        //flog.x = 450;
        flog.y = 1749 - ((1749 / 11) / 2);

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

        if(flog.y < 0) {
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

        canvas.drawRect(water.x, water.y, water.width, water.height, water.paint);

        for(int i = 0; i < plants1.length; i++) {
            canvas.drawRect(plants1[i].x, plants1[i].y, plants1[i].width, plants1[i].height, plants1[i].paint);
        }

        for(int i = 0; i < plants2.length; i++) {
            canvas.drawRect(plants2[i].x, plants2[i].y, plants2[i].width, plants2[i].height, plants2[i].paint);
        }

        canvas.drawRect(flog.x, flog.y, flog.width, flog.height, flog.paint);

        Update();

        try {
            Thread.sleep(30);
        } catch (InterruptedException e) { }

        invalidate();
    }

    private void Update() {
        UpdateFlogProprietes();
        UpdatePlants();
    }

    @Override
    public void run() {
        handler.postDelayed(this, 30);
    }
}