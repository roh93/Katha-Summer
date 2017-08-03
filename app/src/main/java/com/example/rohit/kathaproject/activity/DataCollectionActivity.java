package com.example.rohit.kathaproject.activity;

import android.Manifest;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.rohit.kathaproject.R;
import com.example.rohit.kathaproject.Utils.Util;
import com.example.rohit.kathaproject.adapters.IssuesAdapter;
import com.example.rohit.kathaproject.constants.AppConsts;
import com.example.rohit.kathaproject.helpers.NewIssueAddInterface;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Rohit on 20-05-2017.
 */

public class DataCollectionActivity extends AppCompatActivity implements IssuesAdapter.ItemLongClickListener
        ,NewIssueAddInterface, IssuesAdapter.ItemClickListener, EasyPermissions.PermissionCallbacks {

    @BindView(R.id.issues_gridView)
    RecyclerView issuesGridView;
    IssuesAdapter issuesAdapter;
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ArrayList<Bitmap> issueImageList = new ArrayList<>();
    public  ArrayList<Integer> chosenIssueListPositions = new ArrayList<>();
    private boolean isStoragePermissionAvailable = false;
    private List<Bitmap> newIssueImageList = new ArrayList<>();
    static int removalCount=0, additionCount=0;
    int originalSize = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collection);
        ButterKnife.bind(this);
        askForStoragePermission();
        if (isStoragePermissionAvailable) {
            startRecording();
        }
        issueImageList = (ArrayList<Bitmap>) Util.getBitmapList(this);
        originalSize = issueImageList.size();
        issuesGridView.setLayoutManager(new GridLayoutManager(this, AppConsts.GRID_COLUMNS));
        issuesAdapter = new IssuesAdapter(this, issueImageList);
        issuesAdapter.setLongClickListener(this);
        issuesAdapter.setClickListener(this);
        issuesGridView.setAdapter(issuesAdapter);
        issuesGridView.getRecycledViewPool().clear();
    }


    @OnClick(R.id.stop_audio_rec_btn)
    public void stopAudioRecording() {
        Intent pollingIntent = new Intent(this,PollingActivity.class);
        if(!newIssueImageList.isEmpty()){
            pollingIntent.putParcelableArrayListExtra("NewImage", (ArrayList<? extends Parcelable>) newIssueImageList);
            //newIssueImageList.removeAll(newIssueImageList);
        }
        pollingIntent.putIntegerArrayListExtra("ChosenItems",chosenIssueListPositions);
        startActivity(pollingIntent);
    }


    @Override
    public void onItemLongClick(View view, final int position) {
        new MaterialDialog.Builder(this)
                .title("Confirm Removal")
                .content("Do you want to remove this issue?")
                .positiveText("Agree").onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                issueImageList.remove(position % issueImageList.size());
                issuesGridView.getRecycledViewPool().clear();
                issuesAdapter.notifyDataSetChanged();
                removalCount++;
                dialog.dismiss();
            }
        }).negativeText("Disagree").onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
            }
        }).show();
    }

    @OnClick(R.id.new_issue_btn)
    public void addIssue() {
        final Dialog newIssueDialog = new Dialog(DataCollectionActivity.this);
        newIssueDialog.setTitle("New Issue");
        newIssueDialog.setContentView(R.layout.new_issue_dialog);
        newIssueDialog.show();
        Button picButton = (Button) newIssueDialog.findViewById(R.id.pic_btn);
        Button textButton = (Button) newIssueDialog.findViewById(R.id.text_btn);
        picButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
                    startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
                }
                newIssueDialog.dismiss();
            }
        });
        textButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText textView = (EditText) newIssueDialog.findViewById(R.id.text_input_tv);
                try {
                    if (!textView.getText().toString().isEmpty()) {
                        Bitmap bmp = textToBmp(textView.getText().toString());
                        issueImageList.add(bmp);
                        issuesGridView.getRecycledViewPool().clear();
                        issuesAdapter.notifyDataSetChanged();
                        newIssueImageList.add(bmp);
                        additionCount++;
                        newIssueDialog.dismiss();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            issueImageList.add(imageBitmap);
            issuesGridView.getRecycledViewPool().clear();
            issuesAdapter.notifyDataSetChanged();
            newIssueImageList.add(imageBitmap);
            additionCount++;
        }
    }

    public List<Bitmap> getIssueImageList() {
        return issueImageList;
    }

    @Override
    public void onItemClick(View view, int position) {
        chosenIssueListPositions.add(position);
        Intent discussionIntent = new Intent(this,DiscussionActivity.class);
        discussionIntent.putExtra("Position",position);
        if(!newIssueImageList.isEmpty()){
            discussionIntent.putParcelableArrayListExtra("NewImage", (ArrayList<? extends Parcelable>) newIssueImageList);
        }
        if(position>=originalSize){
            discussionIntent.putParcelableArrayListExtra("new",(ArrayList<? extends Parcelable>)newIssueImageList);
            discussionIntent.putExtra("Position",position%originalSize);
        }
        //issueImageList.removeAll(issueImageList);
        startActivity(discussionIntent);
    }


    @AfterPermissionGranted(AppConsts.AUDIO_REQUEST_CODE)
    public void askForStoragePermission() {
        String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.RECORD_AUDIO, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(this, permission)) {
            EasyPermissions.requestPermissions(this, getString(R.string.permission_external_storage), AppConsts.AUDIO_REQUEST_CODE, permission);
        } else {
            //Allowed to record
            this.isStoragePermissionAvailable = true;
        }
    }


    public void startRecording() {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy_MM_dd_HH_mm");
        String mAudioFilePath = dateFormat.format(currentDate) + ".mp3";

        File completePath = new File(Environment.getExternalStorageDirectory() + "/Sanlap/IssueRecording/");
        completePath.mkdirs();

        File outputFile = new File(completePath, mAudioFilePath);
        try {
            MediaRecorder mMediaRecorder = new MediaRecorder();
            mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
            mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);
            mMediaRecorder.setAudioEncoder(MediaRecorder.OutputFormat.AMR_NB);
            mMediaRecorder.setOutputFile(outputFile.getAbsolutePath());
            mMediaRecorder.prepare();
            mMediaRecorder.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPermissionsGranted(int requestCode, List<String> perms) {
        this.isStoragePermissionAvailable = true;
    }

    @Override
    public void onPermissionsDenied(int requestCode, List<String> perms) {
        this.isStoragePermissionAvailable = true;
    }

    private Bitmap textToBmp(final String text) throws IOException{
        final Paint textPaint = new Paint() {
            {
                setColor(Color.RED);
                setTextAlign(Paint.Align.LEFT);
                setTextSize(30f);
                setAntiAlias(true);
            }
        };
        Rect bounds = new Rect();
        textPaint.getTextBounds(text, 0, text.length(), bounds);

        final Bitmap bmp = Bitmap.createBitmap(bounds.width(), bounds.height() + 15, Bitmap.Config.ARGB_8888); //use ARGB_8888 for better quality
        final Canvas canvas = new Canvas(bmp);
        canvas.drawText(text, 0, 20f, textPaint);
        FileOutputStream stream = new FileOutputStream(new File(Environment.getExternalStorageDirectory() + "/Sanlap/MapOutput/new.png")); //create your FileOutputStream here
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        stream.close();
        return bmp;
    }
}
