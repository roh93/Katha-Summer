package com.example.rohit.kathaproject.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.rohit.kathaproject.Database.PollingDetail;
import com.example.rohit.kathaproject.Database.PollingResultsCRUD;
import com.example.rohit.kathaproject.R;
import com.example.rohit.kathaproject.Utils.Util;
import com.example.rohit.kathaproject.adapters.IssuesAdapter;
import com.example.rohit.kathaproject.constants.AppConsts;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Rohit on 22-06-2017.
 */

public class PollingActivity extends AppCompatActivity implements IssuesAdapter.ItemClickListener{

    PollingResultsCRUD pollingResultsCRUD;

    @BindView(R.id.polling_input_age)
    EditText ageEt;

    @BindView(R.id.male_radio_btn)
    RadioButton maleButton;

    @BindView(R.id.female_radio_btn)
    RadioButton femaleButton;

    @BindView(R.id.occupation_spinner)
    Spinner occupationSpinner;

    @BindView(R.id.polling_rv)
    RecyclerView pollingIssueList;
    private IssuesAdapter pollingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polling);
        ButterKnife.bind(this);
        pollingResultsCRUD = new PollingResultsCRUD(this);
        pollingResultsCRUD.open();
        pollingIssueList.setLayoutManager(new GridLayoutManager(this, AppConsts.GRID_COLUMNS));
        List<Bitmap> pollItems= Util.getBitmapList(this);
        pollingAdapter = new IssuesAdapter(this, pollItems);
        pollingAdapter.setClickListener(this);
        pollingIssueList.setAdapter(pollingAdapter);
    }

    @OnClick(R.id.btn_vote)
    public void confirmSelection(){
        new MaterialDialog.Builder(this)
                .title("Confirm Selection")
                .content("Do you want to Confirm the Selection?")
                .positiveText("Yes").onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                //ADD TO DATABASE
                pollingResultsCRUD.insertPollDetail(getPollingDTO());
                clearAllFields();
                dialog.dismiss();
            }
        }).negativeText("No").onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
            }
        }).show();
    }

    @OnClick(R.id.btn_finish_vote)
    public void finishVoting(){
        startActivity(new Intent(this,DashboardActivity.class));
    }

    private void clearAllFields() {
        ageEt.setText("");
        occupationSpinner.setSelection(0);
        maleButton.setChecked(true);
    }

    private PollingDetail getPollingDTO() {
        String sex;
        if(maleButton.isChecked()){
            sex = maleButton.getText().toString();
        }else{
            sex = femaleButton.getText().toString();
        }
        int age = Integer.parseInt(ageEt.getText().toString());
        String occupation = String.valueOf(occupationSpinner.getSelectedItem());
        String issue = AppConsts.ELECTRICITY;
        return new PollingDetail(sex,age,occupation,issue);
    }


    @Override
    protected void onPause() {
        pollingResultsCRUD.close();
        super.onPause();
    }

    @Override
    protected void onResume() {
        pollingResultsCRUD.open();
        super.onResume();
    }

    @Override
    public void onItemClick(View view, int position) {

    }
}
