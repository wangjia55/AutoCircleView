package com.jacob.circleview;

/**
 * Package : com.jacob.circleview
 * Author : jacob
 * Date : 15-1-6
 * Description : 这个类是用来xxx
 */

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * 自定义的view，需要覆盖onDraw()方法绘制控件，覆盖onTouchEvent()接收触摸消息
 */
public class CustomCircleView extends View {

    private static final int WIDTH = 40;
    private final Context context;
    private Paint paint = new Paint();//画笔
    private int radio = 20;

    public CustomCircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.paint = new Paint();
        paint.setFlags(Paint.ANTI_ALIAS_FLAG);// 帮助消除锯齿
        this.paint.setAntiAlias(true); //消除锯齿
        this.paint.setStyle(Paint.Style.STROKE); //绘制空心圆
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;

        //绘制内圆
        this.paint.setColor(Color.parseColor("#3FFF9E00"));
        this.paint.setStyle(Paint.Style.FILL); //绘制空心圆
        canvas.drawCircle(centerX, centerY, radio, this.paint);

        //绘制外圆
        this.paint.setColor(Color.parseColor("#FF9E00"));
        this.paint.setStrokeWidth(2);
        this.paint.setStyle(Paint.Style.STROKE); //绘制空心圆
        canvas.drawCircle(centerX, centerY, radio, this.paint);

        //绘制直线
        canvas.drawLine(centerX, centerY, centerX + radio, centerY, paint);


        //字符串 以字符串下面为基准
        String text = "半径:" + radio;
        Paint paint1 = new Paint();
        Rect rect = new Rect();

        //返回包围整个字符串的最小的一个Rect区域
        paint1.getTextBounds(text, 0, 1, rect);
        this.paint.setStyle(Paint.Style.FILL);
        int stringW = rect.width();
        int stringH = rect.height();

        paint.setTextSize(24);
        canvas.drawText(text, centerX + (radio - stringW - dip2px(getContext(),30)) / 2, centerY - stringH, paint);

    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    public void setRadio(int radio) {
        this.radio = radio;
    }


}