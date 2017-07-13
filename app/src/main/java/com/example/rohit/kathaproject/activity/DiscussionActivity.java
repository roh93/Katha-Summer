package com.example.rohit.kathaproject.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.example.rohit.kathaproject.R;
import com.example.rohit.kathaproject.Utils.Util;
import com.example.rohit.kathaproject.adapters.IssuesAdapter;
import com.example.rohit.kathaproject.helpers.NewIssueAddInterface;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Rohit on 16-06-2017.
 */

public class DiscussionActivity extends AppCompatActivity implements IssuesAdapter.ItemClickListener, NewIssueAddInterface {

    List<Bitmap> imageList = new ArrayList<>();
    int position=0,count =0;
    @BindView(R.id.main_topic_iv)
    ImageView mainTopicImage;
    @BindView(R.id.subtopic1_iv)
    ImageView subTopic1Image;
    @BindView(R.id.subtopic2_iv)
    ImageView subTopic2Image;
    @BindView(R.id.discussion_rv)
    RecyclerView issueRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_discussion);
        ButterKnife.bind(this);
        imageList = Util.getBitmapList(this);
        position = this.getIntent().getIntExtra("Position",0);
        mainTopicImage.setImageBitmap(imageList.get(position));
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        issueRecyclerView.setLayoutManager(layoutManager);
        IssuesAdapter issuesAdapter = new IssuesAdapter(this, imageList);
        issuesAdapter.setClickListener(this);
        issueRecyclerView.setAdapter(issuesAdapter);
    }

    @Override
    public void onItemClick(View view, int position) {
        if(this.position != position && count<2){
            if(count==0){
                subTopic1Image.setImageBitmap(imageList.get(position));
                count++;
            }
            else if(count==1){
                subTopic2Image.setImageBitmap(imageList.get(position));
                count++;
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    public List<Bitmap> getIssueImageList() {
        return null;
    }
}
