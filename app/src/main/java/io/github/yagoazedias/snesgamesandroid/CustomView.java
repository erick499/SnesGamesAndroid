package io.github.yagoazedias.snesgamesandroid;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.os.Handler;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CustomView extends View implements Runnable {

    public Flog    flog    = new Flog();
    public Water   water   = new Water();
    public Handler handler = new Handler();
    public Plant[] plants1 = new Plant[3];
    public Plant[] plants2 = new Plant[3];
    public Tree[]  trees1  = new Tree[2];
    public Tree    tree2   = new Tree();
    public int[]   LineObjPos = { 20, 169, 318, 477, 646, 815, 1010 };
    public Car[]   carsLine1 = new Car[3];
    public Car[]   carsLine2 = new Car[3];
    public Car[]   carsLine3 = new Car[3];
    public Car[]   carsLine4 = new Car[3];
    public Car[]   carsLine5 = new Car[3];
    public Car[]   carsLine6 = new Car[3];

    public void OnStart(){
        InitFlog();
        InitSurfaces();
        InitCars();
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

    public void InitCars() {
        for (int i = 0; i < carsLine1.length; i++) {
            carsLine1[i] = new Car();

            if(i == 0) {
                carsLine1[i].x = 20;
                carsLine1[i].y = LineObjPos[4];
                carsLine1[i].width  = carsLine1[i].x + 229;
                carsLine1[i].height = carsLine1[i].y + 129;
            }else{
                carsLine1[i].x = carsLine1[i - 1].width + 400;
                carsLine1[i].y = LineObjPos[4];
                carsLine1[i].width  = carsLine1[i].x + 229;
                carsLine1[i].height = carsLine1[i].y + 129;
            }
        }
    }

    public void UpdateCarsLength() {
        for (int i = 0; i < carsLine1.length; i++) {
                carsLine1[i].width  = carsLine1[i].x + 229;
                carsLine1[i].height = carsLine1[i].y + 129;
        }
    }

    public void UpdateCars(){
        for (int i = 0; i < carsLine1.length; i++) {
            carsLine1[i].x -= 7.5;

            if(carsLine1[i].x < -229){
                carsLine1[i].x = getMeasuredWidth();
            }
        }
    }

    public void InitSurfaces(){
        for(int i = 0; i < plants1.length; i++) {
            plants1[i] = new Plant();

            if(i == 0) {
                plants1[i].x = 20;
                plants1[i].y = LineObjPos[0];
                plants1[i].height = plants1[i].y + 139;
                plants1[i].width  = plants1[i].x + 139;
            }else{
                plants1[i].x = plants1[i - 1].x + 400;
                plants1[i].y = LineObjPos[0];
                plants1[i].height = plants1[i].y + 139;
                plants1[i].width  = plants1[i].x + 139;
            }

            tree2.y = LineObjPos[3];
        }

        for(int i = 0; i < plants2.length; i ++) {
            plants2[i] = new Plant();

            if(i == 0) {
                plants2[i].x = 20;
                plants2[i].y = LineObjPos[1];
                plants2[i].height = plants2[i].y + 139;
                plants2[i].width  = plants2[i].x + 139;
            }else{
                plants2[i].x = plants2[i - 1].x + 400;
                plants2[i].y = LineObjPos[1];
                plants2[i].height = plants2[i].y + 139;
                plants2[i].width  = plants2[i].x + 139;
            }
        }

        for(int i = 0; i < trees1.length; i ++) {
            trees1[i] = new Tree();

            switch (i)
            {
                case 0:
                    trees1[i].x = 20;
                    trees1[i].y = LineObjPos[2];
                    trees1[i].width = trees1[i].x + 429;
                    trees1[i].height = trees1[i].y + 129;
                    break;
                default:
                    trees1[i].x = trees1[i -1].x + 729;
                    trees1[i].y = LineObjPos[2];
                    trees1[i].width = trees1[i].x + 429;
                    trees1[i].height = trees1[i].y + 129;
                    break;
            }
        }

        tree2 = new Tree();

        tree2.x = 450;
        tree2.y = LineObjPos[3];
        tree2.width  = tree2.x + 429;
        tree2.height = tree2.y + 129;
    }

    public void UpdateSurfacesLengths() {
        for(int i = 0; i < plants1.length; i++) {

            if(i == 0) {
                plants1[i].height = plants1[i].y + 139;
                plants1[i].width  = plants1[i].x + 159;
            }else{
                plants1[i].height = plants1[i].y + 139;
                plants1[i].width  = plants1[i].x + 159;
            }


            if(i == 0) {
                plants2[i].height = plants2[i].y + 139;
                plants2[i].width  = plants2[i].x + 169;
            }else{
                plants2[i].height = plants2[i].y + 139;
                plants2[i].width  = plants2[i].x + 169;
            }
        }

        for(int i = 0; i < trees1.length; i ++) {
            if(i == 0) {
                trees1[i].width = trees1[i].x + 429;
                trees1[i].height = trees1[i].y + 129;
            }
            else {
                trees1[i].width  = trees1[i].x + 429;
                trees1[i].height = trees1[i].y + 129;
            }
        }

        tree2.width  = tree2.x + 429;
        tree2.height = tree2.y + 129;
    }

    public void InitFlog() {
        //flog.x = 450;
        flog.y = 1749 - ((1749 / 11) / 2);
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

    public void UpdatePlants() {

        for(int i = 0; i < plants1.length; i++) {
            plants1[i].x -= 5;

            if(plants1[i].x < -159) {
                plants1[i].x = getMeasuredWidth();
                UpdateSurfacesLengths();
            }
        }

        for(int i = 0; i < plants2.length; i ++) {
            plants2[i].x += 5;

            if(plants2[i].x > getMeasuredWidth()) {
                plants2[i].x = -159;
                UpdateSurfacesLengths();
            }
        }
    }

    public void UpdateTrees() {
        for(int i = 0; i < trees1.length; i ++) {
            trees1[i].x -= 5;

            if(trees1[i].x < -429) {
                trees1[i].x = getMeasuredWidth();
                UpdateSurfacesLengths();
            }
        }

        tree2.x += 10;

        if(tree2.x > getMeasuredWidth() + 429){
            tree2.x = -429;
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

        if(x < (getWidth() / 2) - 300) {
            Move("left");
        }
        else if(x > (getWidth() / 2) + 300) {
            Move("right");
        }
        else {
            Move("up");
        }

        invalidate();
        return super.onTouchEvent(event);
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        flog.paint.setColor(Color.GREEN);
        water.paint.setColor(Color.BLUE);
        water.paint.setColor(Color.BLUE);
        tree2.paint.setColor(Color.RED);

        for(int i = 0; i < plants1.length; i++) {
            plants1[i].paint.setColor(Color.YELLOW);
        }
        for(int i = 0; i < plants2.length; i++) {
            plants2[i].paint.setColor(Color.YELLOW);
        }
        for(int i = 0; i < trees1.length; i++) {
            trees1[i].paint.setColor(Color.RED);
        }
        for(int i = 0; i < carsLine1.length; i++){
            carsLine1[i].paint.setColor(Color.CYAN);
        }

            canvas.drawRect(water.x, water.y, water.width, water.height, water.paint);

        for(int i = 0; i < plants1.length; i++) {
            canvas.drawRect(plants1[i].x, plants1[i].y, plants1[i].width, plants1[i].height, plants1[i].paint);
        }

        for(int i = 0; i < plants2.length; i++) {
            canvas.drawRect(plants2[i].x, plants2[i].y, plants2[i].width, plants2[i].height, plants2[i].paint);
        }

        for(int i = 0; i < trees1.length; i++) {
            canvas.drawRect(trees1[i].x, trees1[i].y, trees1[i].width, trees1[i].height, trees1[i].paint);
        }

        for(int i = 0; i < carsLine1.length; i++){
            canvas.drawRect(carsLine1[i].x, carsLine1[i].y, carsLine1[i].width, carsLine1[i].height, carsLine1[i].paint);
        }

        canvas.drawRect(tree2.x, tree2.y, tree2.width, tree2.height, tree2.paint);
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
        UpdateTrees();
        UpdateSurfacesLengths();
        UpdateCarsLength();
        UpdateCars();
    }

    @Override
    public void run() {
        handler.postDelayed(this, 30);
    }
}