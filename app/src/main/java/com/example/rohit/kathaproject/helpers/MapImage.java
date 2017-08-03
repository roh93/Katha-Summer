package com.example.rohit.kathaproject.helpers;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;

import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Rohit on 18-07-2017.
 */

public class MapImage extends SubsamplingScaleImageView {
    private PointF sPin;
    private Bitmap pin;
    Paint paint = new Paint();
    static HashMap<PointF,Bitmap> iconList = new HashMap<>();

    public MapImage(Context context) {
        this(context, null);
    }

    public MapImage(Context context, AttributeSet attr) {
        super(context, attr);
        //initialise(bitmap);
    }

    public void setPin(PointF sPin, Bitmap bitmap) {
        this.sPin = sPin;
        initialise(bitmap);
        iconList.put(sPin,this.pin);
        invalidate();
    }

    public PointF getPin() {
        return sPin;
    }

    private void initialise(Bitmap bitmap) {
        float density = getResources().getDisplayMetrics().densityDpi;
        pin = bitmap;
        float w = 120;
        float h = 120;
        pin = Bitmap.createScaledBitmap(pin, (int)w, (int)h, true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.setAntiAlias(true);

        for (Map.Entry<PointF,Bitmap> pair : iconList.entrySet() ) {
            if (pair.getKey() != null && pair.getValue() != null) {
                PointF vPin = sourceToViewCoord(pair.getKey());
                if(vPin!=null) {
                    float vX = vPin.x - (pair.getValue().getWidth() / 2);
                    float vY = vPin.y - pair.getValue().getHeight();
                    canvas.drawBitmap(pair.getValue(), vX, vY, paint);
                }
            }
        }
    }

    public void clearBitmaps(){
        iconList.clear();
    }

}
