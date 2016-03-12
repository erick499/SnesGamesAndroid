package io.github.yagoazedias.snesgamesandroid;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class CustomView extends View implements Runnable{

    final Paint flog  = new Paint();
    final Paint border = new Paint();
    final int height = 100;
    final int width  = 100;

    public CustomView(Context context) {
        super(context);

    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int dCenter = 40;
        int centerX = (int)(getWidth()/2);
        int centerY = (int)(getHeight()/2);

        Paint paint = new Paint();
        paint.setColor(Color.BLUE);  //The square will draw in the color blue now

        Rect rect = new Rect(0, 0, 100, 200);
        canvas.drawRect(rect, paint);

    }

    @Override
    public void run() {

    }
}