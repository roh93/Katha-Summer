package com.example.rohit.kathaproject.activity;

import android.Manifest;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rohit.kathaproject.R;
import com.example.rohit.kathaproject.constants.AppConsts;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Rohit on 14-06-2017.
 */

public class DashboardActivity extends AppCompatActivity{

    @BindView(R.id.villager_mode_btn)
    ImageView villagerGif;
    @BindView(R.id.researcher_mode_btn)
    ImageView researcherGif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        //Glide.with(this).asGif().load(R.drawable.tree).into(villagerGif);
        //Glide.with(this).asGif().load(R.drawable.science).into(researcherGif);
    }

    @OnClick(R.id.villager_mode_btn)
    public void launchVillagerScreen(){
        //Toast.makeText(this,"VillagerClicked",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this,DataCollectionActivity.class));
    }

    @OnClick(R.id.researcher_mode_btn)
    public void launchResearcherScreen(){
        //Toast.makeText(this,"ResearcherClicked",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this,ResearcherModeActivity.class));
    }

}
