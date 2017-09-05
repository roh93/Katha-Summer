package com.example.rohit.kathaproject.activity;

import android.graphics.Bitmap;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.example.rohit.kathaproject.R;
import com.example.rohit.kathaproject.Utils.Util;
import com.example.rohit.kathaproject.adapters.IssuesAdapter;
import com.example.rohit.kathaproject.helpers.NewIssueAddInterface;
import com.example.rohit.kathaproject.helpers.TransectViewHelper;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Rohit on 28-07-2017.
 */

public class TransectActivity extends AppCompatActivity implements IssuesAdapter.ItemClickListener, NewIssueAddInterface {

    private TransectViewHelper mapImage;
    String loadPath = Environment.getExternalStorageDirectory()+"/Sanlap/MapInput/map4/map1.png";
    @BindView(R.id.rm_options_panel)
    RecyclerView optionsRV;
    private IssuesAdapter optionsAdapter;
    private List<Bitmap> optionImages;
    Bitmap optionImage = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transect);
        ButterKnife.bind(this);
        optionImages = Util.getAllMapImages(this);
        mapImage = (TransectViewHelper) findViewById(R.id.rm_map_iv);
        mapImage.setImage(ImageSource.uri(loadPath));
        mapImage.setZoomEnabled(true);
        optionsRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        optionsAdapter = new IssuesAdapter(this, optionImages);
        optionsAdapter.setClickListener(this);
        optionsRV.setAdapter(optionsAdapter);
        setListener();
    }

    private void setListener() {
        final GestureDetector gestureDetect = new GestureDetector(this,new GestureDetector.SimpleOnGestureListener(){
            @Override
            public boolean onSingleTapConfirmed(MotionEvent e) {
                if (mapImage.isReady()) {
                    PointF sCoord = mapImage.viewToSourceCoord(e.getX(), e.getY());
                    Toast.makeText(getApplicationContext(), "Long press: " + ((int)sCoord.x) + ", " + ((int)sCoord.y), Toast.LENGTH_SHORT).show();
                    if(optionImage!=null){
                        mapImage.setPin(sCoord,optionImage);
                        optionImage = null;
                    }
                }
                return true;
            }
        });
        mapImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return gestureDetect.onTouchEvent(event);
            }
        });
    }

    @Override
    public List<Bitmap> getIssueImageList() {
        return null;
    }

    @Override
    public void onItemClick(View view, int position) {
        optionImage = optionImages.get(position);
    }

    @OnClick(R.id.undo_btn)
    public void undoIconAddition(){
        mapImage.undo();
    }

}
