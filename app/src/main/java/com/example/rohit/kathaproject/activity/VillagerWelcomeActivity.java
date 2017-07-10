package com.example.rohit.kathaproject.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.rohit.kathaproject.R;
import com.example.rohit.kathaproject.services.AudioService;

import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by Rohit on 20-05-2017.
 */

public class VillagerWelcomeActivity extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.v_welcome_screen);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.start_audio_btn)
        public void startDataRecord(){

        }

}

