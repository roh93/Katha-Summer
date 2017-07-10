package com.example.rohit.kathaproject.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.rohit.kathaproject.R;

import butterknife.BindView;

import static android.R.attr.x;
import static android.R.attr.y;

/**
 * Created by Rohit on 31-05-2017.
 */

public class ResearcherModeActivity extends AppCompatActivity{

    //@BindView(R.id.rm_map_iv)
    private ImageView mapImage;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_researcher_mode);
        mapImage = (ImageView) findViewById(R.id.rm_map_iv);
        setListeners();
    }

    private void setListeners() {
        mapImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Log.v("touched x val >>", event.getX() + "");
                Log.v("touched y val >>", event.getY() + "");

                int[] viewCoords = new int[2];
                mapImage.getLocationOnScreen(viewCoords);

                int imageX = (int) (event.getX()- viewCoords[0]); // viewCoods[0] is the X coordinate
                int imageY = (int) (event.getY() - viewCoords[1]); // viewCoods[1] is the y coordinate
                Log.v("Real x >>>",imageX+"");
                Log.v("Real y >>>",imageY+"");

                RelativeLayout rl = (RelativeLayout) findViewById(R.id.map_screen_rv);
                ImageView iv = new ImageView(getApplicationContext());
                Bitmap bm = BitmapFactory.decodeResource(getResources(),R.drawable.namaste);
                iv.setImageBitmap(bm);
                RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
                params.width = 50;
                params.height= 50;
                params.leftMargin = imageX;
                params.topMargin = imageY;
                rl.addView(iv, params);
                return false;
            }
        });
    }

}
