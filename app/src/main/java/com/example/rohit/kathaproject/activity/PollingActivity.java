package com.example.rohit.kathaproject.activity;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.example.rohit.kathaproject.Database.PollingDetail;
import com.example.rohit.kathaproject.Database.PollingResultsCRUD;
import com.example.rohit.kathaproject.R;
import com.example.rohit.kathaproject.Utils.Util;
import com.example.rohit.kathaproject.adapters.IssuesAdapter;
import com.example.rohit.kathaproject.constants.AppConsts;
import com.example.rohit.kathaproject.helpers.NewIssueAddInterface;
import com.example.rohit.kathaproject.helpers.WrapContentGridLayoutManager;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Rohit on 22-06-2017.
 */

public class PollingActivity extends AppCompatActivity implements IssuesAdapter.ItemClickListener, NewIssueAddInterface{

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
    public ArrayList<Integer> chosenIssueListPositions = new ArrayList<>();
    List<Bitmap> pollItems = new ArrayList<>();
    List<String> pollIssueNameList = new ArrayList<>();
    String chosenIssue;int size;
    private List<Bitmap> newIssueList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_polling);
        ButterKnife.bind(this);
        pollingResultsCRUD = new PollingResultsCRUD(this);
        pollingResultsCRUD.open();
        pollingIssueList.setLayoutManager(new WrapContentGridLayoutManager(this, AppConsts.GRID_COLUMNS));
        pollItems= Util.getBitmapList(this);
        if(getIntent().getParcelableArrayListExtra("NewImage")!= null){
            newIssueList = getIntent().getParcelableArrayListExtra("NewImage");
        }
        chosenIssueListPositions = getIntent().getIntegerArrayListExtra("ChosenItems");
        Log.d("chosen size", chosenIssueListPositions.size()+"");
        pollItems = createNewList();
        pollIssueNameList = Util.getIssueListName();
        pollingAdapter = new IssuesAdapter(this, pollItems);
        pollingAdapter.setClickListener(this);
        pollingIssueList.setAdapter(pollingAdapter);
        saveIssuesToFile();
    }

    private void saveIssuesToFile() {
        List<String> newList = new ArrayList<>();
        for (int indexChosen : chosenIssueListPositions) {
            if (indexChosen < size) {
                newList.add(pollIssueNameList.get(indexChosen));
            } else {
                chosenIssue = "New Issue - " + (indexChosen % size);
            }
        }
        Util.saveArrayList((ArrayList<String>) newList, Environment.getExternalStorageDirectory() + "/Sanlap/IssueSelected/issues.txt");
    }

    private List<Bitmap> createNewList() {
        size = pollItems.size();
        List<Bitmap> newList = new ArrayList<>();
        for (int indexChosen : chosenIssueListPositions){
            if(indexChosen<size) {
                newList.add(pollItems.get(indexChosen));
            }else {
                newList.add(newIssueList.get(indexChosen%size));
            }
        }
        return newList;
    }

    @OnClick(R.id.btn_vote)
    public void confirmSelection(){
        /*new MaterialDialog.Builder(this)
                .title("Confirm Selection")
                .content("Do you want to Confirm the Selection?")
                .positiveText("Yes").onPositive(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                if(ageEt.getText()!= null) {
                    pollingResultsCRUD.insertPollDetail(getPollingDTO());
                    clearAllFields();
                    dialog.dismiss();
                }
            }
        }).negativeText("No").onNegative(new MaterialDialog.SingleButtonCallback() {
            @Override
            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                dialog.dismiss();
            }
        }).show();*/

        final Dialog choiceDialog = new Dialog(PollingActivity.this);
        choiceDialog.setContentView(R.layout.poll_choice_confirm_dialog);
        choiceDialog.show();
        TextView ageTV = (TextView) choiceDialog.findViewById(R.id.age_choice_tv);
        TextView genderTV = (TextView) choiceDialog.findViewById(R.id.gender_choice_tv);
        TextView occupationTV = (TextView) choiceDialog.findViewById(R.id.occupation_choice_tv);
        TextView issueTV = (TextView) choiceDialog.findViewById(R.id.issue_choice_tv);
        ImageView issueIV = (ImageView) choiceDialog.findViewById(R.id.issue_selected_iv);
        ageTV.setText(ageEt.getText());
        String sex;
        if(maleButton.isChecked()){
            sex = maleButton.getText().toString();
        }else{
            sex = femaleButton.getText().toString();
        }
        genderTV.setText(sex);
        occupationTV.setText(String.valueOf(occupationSpinner.getSelectedItem()));
        issueTV.setText(chosenIssue);
        //issueIV.setImageBitmap();
        Button confirmButton = (Button) choiceDialog.findViewById(R.id.confirm_btn);
        Button cancelButton = (Button) choiceDialog.findViewById(R.id.cancel_btn);
        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ageEt.getText()!= null) {
                    pollingResultsCRUD.insertPollDetail(getPollingDTO());
                    clearAllFields();
                    choiceDialog.dismiss();
                }
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choiceDialog.dismiss();
            }
        });
    }

    @OnClick(R.id.btn_finish_vote)
    public void finishVoting(){
        startActivity(new Intent(this,DashboardActivity.class));
    }

    private void clearAllFields() {
        ageEt.setText("");
        occupationSpinner.setSelection(0);
        maleButton.setChecked(true);
        chosenIssue = null;
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
        String issue = chosenIssue;
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
        if(position<size) {
            chosenIssue = pollIssueNameList.get(chosenIssueListPositions.get(position));
        }else {
            chosenIssue = "New Issue - "+(position%size);
        }
        Toast.makeText(PollingActivity.this,"You Selected : "+chosenIssue,Toast.LENGTH_LONG).show();
    }

    @Override
    public List<Bitmap> getIssueImageList() {
        return null;
    }
}
