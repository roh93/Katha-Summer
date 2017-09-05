package com.example.rohit.kathaproject.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Rohit on 28-07-2017.
 */

public class TransectViewHelper extends SubsamplingScaleImageView implements View.OnTouchListener {

    private int strokeWidth;
    private List<PointF> sPoints;
    private Bitmap pin;
    private PointF sPin;
    private Paint paint;
    Path mPath = new Path();
    Paint mPaint = new Paint();
    private Bitmap  mBitmap;
    private Canvas  mCanvas;

    public TransectViewHelper(Context context, AttributeSet attr) {
        super(context, attr);
        initialise();
    }

    static HashMap<PointF,Bitmap> iconList = new HashMap<>();

    public TransectViewHelper(Context context) {
        this(context, null);
    }

    private void initialise() {
        setOnTouchListener(this);
        float density = getResources().getDisplayMetrics().densityDpi;
        strokeWidth = (int)(density/60f);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2);
        paint.setAntiAlias(true);
    }

    public void setPin(PointF sPin, Bitmap bitmap) {
        this.sPin = sPin;
        this.pin = Bitmap.createScaledBitmap(bitmap, 120, 120, true);
        iconList.put(sPin,this.pin);
        invalidate();
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                mPath.moveTo(event.getX(), event.getY());
                break;

            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(), event.getY());
                invalidate();
                break;

            case MotionEvent.ACTION_UP:
                break;
        }

        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Don't draw anything before image is ready.
        if (!isReady()) {
            return;
        }
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeWidth(10);
        canvas.drawPath(mPath, mPaint);

        for (Map.Entry<PointF, Bitmap> pair : iconList.entrySet()) {
            if (pair.getKey() != null && pair.getValue() != null) {
                PointF vPin = sourceToViewCoord(pair.getKey());
                float vX = vPin.x - (pair.getValue().getWidth() / 2);
                float vY = vPin.y - pair.getValue().getHeight();
                canvas.drawBitmap(pair.getValue(), vX, vY, paint);
            }
        }
    }

    public void reset() {
        this.sPoints = null;
        invalidate();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

}
