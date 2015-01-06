package com.jacob.circleview;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


public class MainActivity extends FragmentActivity implements View.OnTouchListener {
    private ImageView myImageView;
    private int screenWidth;
    private int lastX, lastY;
    private int  startX,startY, height;
    CustomCircleView customCircleView;

    private void init() {
        this.myImageView = (ImageView) this.findViewById(R.id.image_view);
        this.myImageView.setOnTouchListener(this);
        myImageView.post(new Runnable() {
            @Override
            public void run() {
                startX = (int) myImageView.getX();
                startY = (int) myImageView.getY();
                height = myImageView.getMeasuredHeight();
            }
        });
        DisplayMetrics dm = getResources().getDisplayMetrics();
        screenWidth = dm.widthPixels;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.init();

         customCircleView = (CustomCircleView) findViewById(R.id.circle_view);
    }

    public boolean onTouch(View v, MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Toast.makeText
                        (MainActivity.this, "Down...", Toast.LENGTH_SHORT).show();
                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_MOVE:
                int dx = (int) event.getRawX() - lastX;
                int left = v.getLeft() + dx;
                int right = v.getRight() + dx;

                // 设置不能出界
                if (left < 0) {
                    left = 0;
                    right = left + v.getWidth();
                }
                if (right > screenWidth) {
                    right = screenWidth;
                    left = right - v.getWidth();
                }
                v.layout(left, startY, right, startY + height);
                customCircleView.setRadio(left-startX);
                customCircleView.invalidate();

                lastX = (int) event.getRawX();
                lastY = (int) event.getRawY();
                break;
            case MotionEvent.ACTION_UP:
                break;
        }
        return true;
    }
}
