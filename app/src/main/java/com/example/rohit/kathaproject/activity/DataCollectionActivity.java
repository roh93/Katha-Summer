package com.example.rohit.kathaproject.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.rohit.kathaproject.R;
import com.example.rohit.kathaproject.adapters.IssuesAdapter;
import com.example.rohit.kathaproject.constants.AppConsts;
import com.example.rohit.kathaproject.helpers.NewIssueAddInterface;
import com.example.rohit.kathaproject.services.AudioService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Rohit on 20-05-2017.
 */

public class DataCollectionActivity extends AppCompatActivity implements IssuesAdapter.ItemLongClickListener
        ,NewIssueAddInterface, IssuesAdapter.ItemClickListener {

    @BindView(R.id.issues_gridView)
    RecyclerView issuesGridView;
    IssuesAdapter issuesAdapter;

    private List<String> issueList = new ArrayList<>(Arrays.asList(AppConsts.ANIMAL_HUSBANDRY,
            AppConsts.DRINKING_WATER, AppConsts.ELECTRICITY, AppConsts.ELEPHANT, AppConsts.FOOD,
            AppConsts.GENERAL_WATER, AppConsts.IRRIGATION, AppConsts.ROAD, AppConsts.SANITATION,
            AppConsts.SHELTER));
    static final int REQUEST_IMAGE_CAPTURE = 1;
    private ArrayList<Bitmap> issueImageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_collection);
        ButterKnife.bind(this);
        issuesGridView.setLayoutManager(new GridLayoutManager(this, AppConsts.GRID_COLUMNS));
        issuesAdapter = new IssuesAdapter(this, issueList);
        issuesAdapter.setLongClickListener(this);
        issuesAdapter.setClickListener(this);
        issuesGridView.setAdapter(issuesAdapter);
    }


    @OnClick(R.id.stop_audio_rec_btn)
    public void stopAudioRecording() {
        stopService(new Intent(this, AudioService.class));
        Intent pollingIntent = new Intent(this,PollingActivity.class);
        startActivity(pollingIntent);
    }


    @Override
    public void onItemLongClick(View view, final int position) {
        Toast.makeText(this, "Long tapped on: " + issueList.get(position), Toast.LENGTH_LONG).show();
        new MaterialDialog.Builder(this)
                .title("Confirm Removal")
                .content("Do you want to remove this issue?")
                .positiveText("Agree").onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                issueList.remove(position % issueList.size());
                issuesAdapter.notifyDataSetChanged();
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
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            issueList.add("New Item");
            issueImageList.add(imageBitmap);
            issuesAdapter.notifyDataSetChanged();
        }
    }

    public List<Bitmap> getIssueImageList() {
        return issueImageList;
    }

    @Override
    public void onItemClick(View view, int position) {
        Intent discussionIntent = new Intent(this,DiscussionActivity.class);
        discussionIntent.putParcelableArrayListExtra("ImageList", issueImageList);
        discussionIntent.putExtra("Position",position);
        startActivity(discussionIntent);
    }

}
