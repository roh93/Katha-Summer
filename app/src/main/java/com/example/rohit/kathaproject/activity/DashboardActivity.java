package com.example.rohit.kathaproject.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.rohit.kathaproject.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

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
        Glide.with(this).asGif().load(R.drawable.tree).into(villagerGif);
        Glide.with(this).asGif().load(R.drawable.science).into(researcherGif);
    }

    @OnClick(R.id.villager_mode_btn)
    public void launchVillagerScreen(){
        Toast.makeText(this,"VillagerClicked",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this,DataCollectionActivity.class));
    }

    @OnClick(R.id.researcher_mode_btn)
    public void launchResearcherScreen(){
        Toast.makeText(this,"ResearcherClicked",Toast.LENGTH_LONG).show();
        startActivity(new Intent(this,ResearcherModeActivity.class));
    }

}
