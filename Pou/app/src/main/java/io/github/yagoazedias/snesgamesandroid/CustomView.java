package io.github.yagoazedias.snesgamesandroid;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;

public class CustomView extends View implements Runnable {

    public Handler handler = new Handler();

    // Player properties.
    float xpad = (float)(getPaddingLeft() + getPaddingRight()) + 300;
    float ypad = (float)(getPaddingTop() + getPaddingBottom()) + 1300;

    // Water properties.
    Rect whate = new Rect(getPaddingLeft(), (int)ypad + 300, getPaddingLeft() + 5000, (int)ypad + 2000);

    Board[] board = new Board[4];

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

        for(int i = 0; i < board.length; i++) {
            canvas.drawRect(board[i].xpad, board[i].ypad, board[i].xpad + 200, board[i].ypad + 200, new Paint(Paint.ANTI_ALIAS_FLAG));
        }
    }

    private void Update() {

    }

    @Override
    public void run() {
        handler.postDelayed(this, 30);
    }
}