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

    private PointF vPrevious;
    private PointF vStart;
    private boolean drawing = false;
    private int strokeWidth;
    private List<PointF> sPoints;
    private Bitmap pin;
    private PointF sPin;

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
    public boolean onTouchEvent(@NonNull MotionEvent event) {
/*        if (sPoints != null && !drawing) {
            return super.onTouchEvent(event);
        }*/
        boolean consumed = false;
        int touchCount = event.getPointerCount();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_1_DOWN:
                vStart = new PointF(event.getX(), event.getY());
                vPrevious = new PointF(event.getX(), event.getY());
                break;
            case MotionEvent.ACTION_POINTER_2_DOWN:
                // Abort any current drawing, user is zooming
                vStart = null;
                vPrevious = null;
                break;
            case MotionEvent.ACTION_MOVE:
                PointF sCurrentF = viewToSourceCoord(event.getX(), event.getY());
                if(sCurrentF != null){
                    PointF sCurrent = new PointF(sCurrentF.x, sCurrentF.y);
                    PointF sStart = vStart == null ? null : new PointF(viewToSourceCoord(vStart).x, viewToSourceCoord(vStart).y);

                    if (touchCount == 1 && vStart != null) {
                        float vDX = Math.abs(event.getX() - vPrevious.x);
                        float vDY = Math.abs(event.getY() - vPrevious.y);
                        if (vDX >= strokeWidth * 5 || vDY >= strokeWidth * 5) {
                            if (sPoints == null) {
                                sPoints = new ArrayList<PointF>();
                            }
                            sPoints.add(sStart);
                            sPoints.add(sCurrent);
                            sPoints.add(new PointF(11111,11111));
                            vPrevious.x = event.getX();
                            vPrevious.y = event.getY();
                            drawing = true;
                        }
                        consumed = true;
                        invalidate();

                    } else if (touchCount == 1) {
                        // Consume all one touch drags to prevent odd panning effects handled by the superclass.
                        consumed = true;
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                invalidate();
                drawing = false;
                vPrevious = null;
                vStart = null;
        }
        // Use parent to handle pinch and two-finger pan.
        return consumed || super.onTouchEvent(event);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Don't draw anything before image is ready.
        if (!isReady()) {
            return;
        }

        Paint paint = new Paint();
        paint.setAntiAlias(true);

        if (sPoints != null && sPoints.size() >= 2) {
            Path vPath = new Path();
            PointF vPrev = sourceToViewCoord(sPoints.get(0).x, sPoints.get(0).y);
            vPath.moveTo(vPrev.x, vPrev.y);
            for (int i = 1; i < sPoints.size() - 1; i++) {
                if (sPoints.get(i).x == 11111 && sPoints.get(i).y == 11111) {
                    vPrev = sourceToViewCoord(sPoints.get(i + 1).x, sPoints.get(i + 1).y);
                    vPath.moveTo(vPrev.x, vPrev.y);
                } else {
                    PointF vPoint = sourceToViewCoord(sPoints.get(i).x, sPoints.get(i).y);
                    vPath.quadTo(vPrev.x, vPrev.y, (vPoint.x + vPrev.x) / 2, (vPoint.y + vPrev.y) / 2);
                    vPrev = vPoint;
                }
            }
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeCap(Paint.Cap.ROUND);
            paint.setStrokeWidth(strokeWidth * 2);
            paint.setColor(Color.BLACK);
            canvas.drawPath(vPath, paint);
            paint.setStrokeWidth(strokeWidth);
            paint.setColor(Color.argb(255, 51, 181, 229));
            canvas.drawPath(vPath, paint);


            for (Map.Entry<PointF, Bitmap> pair : iconList.entrySet()) {
                if (pair.getKey() != null && pair.getValue() != null) {
                    PointF vPin = sourceToViewCoord(pair.getKey());
                    float vX = vPin.x - (pair.getValue().getWidth() / 2);
                    float vY = vPin.y - pair.getValue().getHeight();
                    canvas.drawBitmap(pair.getValue(), vX, vY, paint);
                }
            }

        }
    }

    public void reset() {
        this.sPoints = null;
        invalidate();
    }

}
