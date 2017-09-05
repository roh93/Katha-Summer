package com.example.rohit.kathaproject.helpers;

/**
 * Created by Rohit on 04-09-2017.
 */

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;


public class BitmapHelper
{
    public static int getScale(int originalWidth,int originalHeight, final int requiredWidth,final int requiredHeight)
    {
        int scale=1;
        if((originalWidth>requiredWidth) || (originalHeight>requiredHeight)) {
            if(originalWidth<originalHeight) {
                scale = Math.round((float) originalWidth / requiredWidth);
            } else {
                scale = Math.round((float) originalHeight / requiredHeight);
            }
        }
        return scale;
    }

    private static BitmapFactory.Options getOptions(String filePath, int requiredWidth, int requiredHeight)
    {
        BitmapFactory.Options options=new BitmapFactory.Options();
        options.inJustDecodeBounds=true;
        BitmapFactory.decodeFile(filePath,options);
        options.inSampleSize=getScale(options.outWidth, options.outHeight, requiredWidth, requiredHeight);
        options.inJustDecodeBounds=false;
        return options;
    }



    public static Bitmap loadBitmap(String filePath, int requiredWidth,int requiredHeight){
        BitmapFactory.Options options= getOptions(filePath, requiredWidth, requiredHeight);
        return BitmapFactory.decodeFile(filePath,options);
    }
}
