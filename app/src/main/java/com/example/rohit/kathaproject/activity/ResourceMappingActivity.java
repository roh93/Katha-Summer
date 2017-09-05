package com.example.rohit.kathaproject.activity;

import android.app.Dialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.davemorrissey.labs.subscaleview.ImageSource;
import com.example.rohit.kathaproject.R;
import com.example.rohit.kathaproject.Utils.Util;
import com.example.rohit.kathaproject.adapters.IssuesAdapter;
import com.example.rohit.kathaproject.helpers.MapImage;
import com.example.rohit.kathaproject.helpers.NewIssueAddInterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.rohit.kathaproject.helpers.BitmapHelper.loadBitmap;


/**
 * Created by Rohit on 31-05-2017.
 */

public class ResourceMappingActivity extends AppCompatActivity implements IssuesAdapter.ItemClickListener, NewIssueAddInterface{

    private MapImage mapImage;
    @BindView(R.id.rm_options_panel)
    RecyclerView optionsRV;
    @BindView(R.id.myFAB)
    FloatingActionButton fab;
    IssuesAdapter optionsAdapter;
    Bitmap optionImage = null;
    List<Bitmap> optionImages = new ArrayList<>();
    String storePath = Environment.getExternalStorageDirectory()+"/Sanlap/MapOutput/";
    String loadPathPng = Environment.getExternalStorageDirectory()+"/Sanlap/MapInput/";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_map);
        ButterKnife.bind(this);
        InitMapChoice();
        optionsRV.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        optionsAdapter = new IssuesAdapter(this,optionImages);
        optionsAdapter.setClickListener(this);
        optionsRV.setAdapter(optionsAdapter);
        setListener();
    }

    private void InitMapChoice() {
        mapImage = (MapImage) findViewById(R.id.rm_map_iv);
        switch (getIntent().getIntExtra("MapChoice",0)){
            case 1:
                storePath = storePath +"/map1.png";
                loadPathPng = loadPathPng + "/map1/map1.png";
                optionImages = Util.getAllMapImages(this);
                optionsRV.setBackgroundColor(Color.argb(108,244,154,84));
                break;
            case 2:
                storePath = storePath +"/map2.png";
                loadPathPng = loadPathPng + "/map2/map1.png";
                fab.setVisibility(View.VISIBLE);
                optionImages = Util.getBasicAmenitiesMapImages(this);
                optionsRV.setBackgroundColor(Color.argb(108,84,156,246));
                break;
            case 3:
                storePath = storePath +"/map1.png";
                loadPathPng = loadPathPng + "/map3/map1.png";
                optionImages = loadImageFromStorage(Environment.getExternalStorageDirectory() + "/Sanlap/MapPic/");
                optionsRV.setBackgroundColor(Color.argb(108,66,44,86));
                break;
        }
        mapImage.setImage(ImageSource.uri(loadPathPng));
        mapImage.setZoomEnabled(true);
    }

    private List<Bitmap> loadImageFromStorage(String loadPath) {
        final List<Bitmap> capturedImageList = new ArrayList<>();
        File dir = new File(loadPath);
        ArrayList<File> files = new ArrayList<File>(Arrays.asList(dir.listFiles()));
        for(File f : files) {
            capturedImageList.add(loadBitmap(f.getAbsolutePath(),75,75));
        }
        return capturedImageList;
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
    public void onItemClick(View view, int position) {
        optionImage = optionImages.get(position);
    }

    @Override
    public List<Bitmap> getIssueImageList() {
        return null;
    }

/*    @OnClick(R.id.take_screenshot_btn)
    public void storeMap(){
        View rootView = getWindow().getDecorView().findViewById(android.R.id.content);
        Util.saveBitmap(Util.takeScreenshot(rootView), storePath);
    }*/


    @OnClick(R.id.myFAB)
    public void chooseImages(){
        Log.d("something", "click");
        final Dialog dialog = new Dialog(ResourceMappingActivity.this);
        dialog.setContentView(R.layout.layout_map_dialog);
        dialog.setTitle("Issue Choice Dialog");
        dialog.show();
        Button basicAmenitiesBtn = (Button) dialog.findViewById(R.id.basic_amenities_btn);
        Button waterBodiesBtn = (Button) dialog.findViewById(R.id.water_bodies_btn);
        Button transportationsBtn = (Button) dialog.findViewById(R.id.transportations_btn);
        Button defecationBtn = (Button) dialog.findViewById(R.id.defecations_btn);
        basicAmenitiesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionImages = Util.getBasicAmenitiesMapImages(ResourceMappingActivity.this);
                optionsAdapter = new IssuesAdapter(ResourceMappingActivity.this,optionImages);
                optionsAdapter.setClickListener(ResourceMappingActivity.this);
                optionsRV.setAdapter(optionsAdapter);
                dialog.dismiss();
            }
        });
        waterBodiesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionImages = Util.getWaterSourcesMapImages(ResourceMappingActivity.this);
                optionsAdapter = new IssuesAdapter(ResourceMappingActivity.this,optionImages);
                optionsAdapter.setClickListener(ResourceMappingActivity.this);
                optionsRV.setAdapter(optionsAdapter);
                dialog.dismiss();

            }
        });
        transportationsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionImages = Util.getTransportationMapImages(ResourceMappingActivity.this);
                optionsAdapter = new IssuesAdapter(ResourceMappingActivity.this,optionImages);
                optionsAdapter.setClickListener(ResourceMappingActivity.this);
                optionsRV.setAdapter(optionsAdapter);
                dialog.dismiss();
            }
        });
        defecationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                optionImages = Util.getDefecationMapImages(ResourceMappingActivity.this);
                optionsAdapter = new IssuesAdapter(ResourceMappingActivity.this,optionImages);
                optionsAdapter.setClickListener(ResourceMappingActivity.this);
                optionsRV.setAdapter(optionsAdapter);
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        mapImage.clearBitmaps();
        this.finish();
    }

    @OnClick(R.id.btn_remove_icons)
    public void undoIconAddition(){
        mapImage.undo();
    }

}
