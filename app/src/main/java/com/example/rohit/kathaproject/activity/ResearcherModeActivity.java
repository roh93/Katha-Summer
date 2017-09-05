package com.example.rohit.kathaproject.activity;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.example.rohit.kathaproject.Database.PollingResultsCRUD;
import com.example.rohit.kathaproject.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;

import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.rohit.kathaproject.Utils.Util.getMostCommonString;

/**
 * Created by Rohit on 19-07-2017.
 */

public class ResearcherModeActivity extends AppCompatActivity{
    static int count=0;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_researcher_mode);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.map1)
    public void mainMapActivity(){
        Intent resourceMappingIntent = new Intent(this,ResourceMappingActivity.class);
        resourceMappingIntent.putExtra("MapChoice",1);
        startActivity(resourceMappingIntent);
    }

    @OnClick(R.id.map2)
    public void secondMapActivity(){
        Intent resourceMappingIntent = new Intent(this,ResourceMappingActivity.class);
        resourceMappingIntent.putExtra("MapChoice",2);
        startActivity(resourceMappingIntent);
    }

    @OnClick(R.id.map3)
    public void thirdMapActivity(){
        Intent resourceMappingIntent = new Intent(this,ResourceMappingActivity.class);
        resourceMappingIntent.putExtra("MapChoice",3);
        startActivity(resourceMappingIntent);
    }

    @OnClick(R.id.map4)
    public void fourthMapActivity(){
        startActivity(new Intent(this,TransectActivity.class));
    }

    @OnClick(R.id.seasonal_cal)
    public void calendarActivity(){
        startActivity(new Intent(this,CalendarActivity.class));
    }

    @OnClick(R.id.activity_cal)
    public void activityCalendar(){
        startActivity(new Intent(this,ActivitiesActivity.class));
    }

    @OnClick(R.id.picture_btn)
    public void takePicture(){
        if(count<11) {
            Intent camera = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
            startActivityForResult(camera, 1);
        } else {
            Toast.makeText(ResearcherModeActivity.this,"Restricted to take more pictures",Toast.LENGTH_LONG).show();
        }
    }



    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 1:
                if(resultCode == RESULT_OK) {
                    Uri selectedImage = imageReturnedIntent.getData();
                    String[] filePathColumn = {MediaStore.Images.Media.DATA};
                    Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
                    assert cursor != null;
                    cursor.moveToFirst();
                    int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                    String filePath = cursor.getString(columnIndex);
                    File f = new File(filePath);
                    String filename = f.getName();
                    cursor.close();
                    saveFile(filename,filePath);
                    count++;
                }
        }
    }

    public void saveFile(String name, String path) {
        File direct = new File(Environment.getExternalStorageDirectory() + "/Sanlap/MapPic/");
        File file = new File(Environment.getExternalStorageDirectory() + "/Sanlap/MapPic/"+name+count+".png");

        if(!direct.exists()) {
            direct.mkdir();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
                FileChannel src = new FileInputStream(path).getChannel();
                FileChannel dst = new FileOutputStream(file).getChannel();
                dst.transferFrom(src, 0, src.size());
                src.close();
                dst.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @OnClick(R.id.poll_result_btn)
    public void getPollResults() {
        Dialog pollResultDialog = new Dialog(ResearcherModeActivity.this);
        pollResultDialog.setTitle("Poll Results");
        pollResultDialog.setContentView(R.layout.poll_result_dialog);
        pollResultDialog.show();
        TextView totalTV = (TextView) pollResultDialog.findViewById(R.id.total_result_tv);
        TextView maleTV = (TextView) pollResultDialog.findViewById(R.id.male_result_tv);
        TextView femaleTV = (TextView) pollResultDialog.findViewById(R.id.female_result_tv);
        PollingResultsCRUD pr = new PollingResultsCRUD(ResearcherModeActivity.this);
        pr.open();
        if (!pr.getPolledIssueListBySex("Male").isEmpty()) {
            maleTV.setText(getMostCommonString(pr.getPolledIssueListBySex("Male")));
        }
        if (!pr.getPolledIssueListBySex("Female").isEmpty()) {
            femaleTV.setText(getMostCommonString(pr.getPolledIssueListBySex("Female")));
        }
        if (!pr.getPolledIssueListTotal().isEmpty()){
            totalTV.setText(getMostCommonString(pr.getPolledIssueListTotal()));
        }
        pr.close();
    }
}
