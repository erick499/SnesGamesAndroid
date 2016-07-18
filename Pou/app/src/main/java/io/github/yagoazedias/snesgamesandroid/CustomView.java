package io.github.yagoazedias.snesgamesandroid;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.provider.Settings;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

public class CustomView extends View implements Runnable {

    public Handler handler = new Handler();

    // Player properties.
    float xpad  = (float)(getPaddingLeft() + getPaddingRight()) + 300;
    float ypad = (float)(getPaddingTop() + getPaddingBottom()) + 1300;
    float screenW;
    float screenH;

    float xplayer;
    float yplayer;

    int canAnim = 2;

    // Water properties.
    //Rect water = new Rect((int)screenW/12, (int)screenH/3, (int)screenW, (int)screenH);

    Board[] board = new Board[100];

    public void OnStart(){
        // Init board positions.
        for(int i = 0; i < board.length; i++) {
            board[i] = new Board();
            board[i].ypad = ypad + 100;

            switch (i) {
                case 0:  board[i].xpad = xpad - 100; break;
                default: board[i].xpad = board[i - 1].xpad + 300; break;
            }
        }
    }

    public CustomView(Context context) {
        super(context);
        screenW = context.getResources().getDisplayMetrics().widthPixels;
        screenH = context.getResources().getDisplayMetrics().heightPixels;
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        screenW = context.getResources().getDisplayMetrics().widthPixels;
        screenH = context.getResources().getDisplayMetrics().heightPixels;
    }

    public CustomView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        screenW = context.getResources().getDisplayMetrics().widthPixels;
        screenH = context.getResources().getDisplayMetrics().heightPixels;
    }

    public void pouAnim() {

        if(canAnim == 0) {
            yplayer -= screenH/20;

            //for(int i = 0; i < board.length; i++) {
              //  board[i].xpad -= 60;
            //}

        }

        if(canAnim == 1) {
            yplayer += screenH/20;
        }

        if(ypad <= (getPaddingTop() + getPaddingBottom()) + 1000) {
            canAnim = 1;
        }

        if(ypad >= (getPaddingTop() + getPaddingBottom()) + 1300) {
            canAnim = 2;
        }

    }

    public boolean onTouchEvent(MotionEvent event) {
        if(canAnim == 2) {
            canAnim = 0;
            System.out.println("CanAnim: " + canAnim);
            System.out.println(ypad);
        }
        return super.onTouchEvent(event);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        xplayer = screenW/11;
        yplayer = screenH/2;

        canvas.drawCircle(xplayer, yplayer, 100, new Paint(Paint.ANTI_ALIAS_FLAG));
        //canvas.drawRect(screenW/30,screenH ,screenW,screenH+1, new Paint(Paint.DEV_KERN_TEXT_FLAG));

        pouAnim();

       // board[1].isVoid = true;

      //  for(int i = 0; i < board.length; i++) {
      //      if(!board[i].isVoid) {
       //         canvas.drawRect(board[i].xpad, board[i].ypad, board[i].xpad + 200, board[i].ypad + 200, new Paint(Paint.ANTI_ALIAS_FLAG));
       //     }
       // }

        try {
            Thread.sleep(30);
        } catch (InterruptedException e) { }

        invalidate();
    }

    private void Update() {

    }

    @Override
    public void run() {
        handler.postDelayed(this, 30);
    }
}