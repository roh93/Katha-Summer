package com.example.rohit.kathaproject.activity;

import android.Manifest;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.rohit.kathaproject.R;
import com.example.rohit.kathaproject.constants.AppConsts;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

/**
 * Created by Rohit on 14-06-2017.
 */

public class DashboardActivity extends AppCompatActivity implements EasyPermissions.PermissionCallbacks{

    @BindView(R.id.villager_mode_btn)
    ImageView villagerGif;
    @BindView(R.id.researcher_mode_btn)
    ImageView researcherGif;

    private boolean isStoragePermissionAvailable = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        ButterKnife.bind(this);
        askForStoragePermission();
        if(isStoragePermissionAvailable){
            makeDirectories();
        }
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

    private void makeDirectories() {
        File MapPic = new File(Environment.getExternalStorageDirectory() + "/Sanlap/MapPic/");
        if (!MapPic.exists()) {
            MapPic.mkdirs();
        }
        File MapOutput = new File(Environment.getExternalStorageDirectory() + "/Sanlap/MapOutput/");
        if (!MapOutput.exists()) {
            MapOutput.mkdirs();
        }
        File MapInput = new File(Environment.getExternalStorageDirectory() + "/Sanlap/MapInput/");
        if (!MapInput.exists()) {
            MapInput.mkdirs();
        }
        File IssueSelected = new File(Environment.getExternalStorageDirectory() + "/Sanlap/IssueSelected/");
        if (!IssueSelected.exists()) {
            IssueSelected.mkdirs();
        }
        File Map1 = new File(Environment.getExternalStorageDirectory() + "/Sanlap/MapInput/map1");
        if (!Map1.exists()) {
            Map1.mkdirs();
        }
        File Map2 = new File(Environment.getExternalStorageDirectory() + "/Sanlap/MapInput/map2");
        if (!Map2.exists()) {
            Map2.mkdirs();
        }
        File Map3 = new File(Environment.getExternalStorageDirectory() + "/Sanlap/MapInput/map3");
        if (!Map3.exists()) {
            Map3.mkdirs();
        }
        File Map4 = new File(Environment.getExternalStorageDirectory() + "/Sanlap/MapInput/map4");
        if (!Map4.exists()) {
            Map4.mkdirs();
        }
    }

    @AfterPermissionGranted(200)
    public void askForStoragePermission() {
        String[] permission = {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE};
        if (!EasyPermissions.hasPermissions(this, permission)) {
            EasyPermissions.requestPermissions(this, getString(R.string.permission_external_storage), 200, permission);
        } else {
            this.isStoragePermissionAvailable = true;
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
}
