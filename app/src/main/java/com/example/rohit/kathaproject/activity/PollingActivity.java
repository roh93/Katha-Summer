package com.example.rohit.kathaproject.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

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
    private SortedSet<Integer> chosenPos = new TreeSet<>();
    SortedSet<String> voteChoiceSet = new TreeSet<>();

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
        if(voteChoiceSet.size()==3){
            ArrayList<Integer> chosenPosList = new ArrayList<>();
            final Dialog choiceDialog = new Dialog(PollingActivity.this);
            choiceDialog.setContentView(R.layout.poll_choice_confirm_dialog);
            choiceDialog.show();
            TextView ageTV = (TextView) choiceDialog.findViewById(R.id.age_choice_tv);
            TextView genderTV = (TextView) choiceDialog.findViewById(R.id.gender_choice_tv);
            TextView occupationTV = (TextView) choiceDialog.findViewById(R.id.occupation_choice_tv);
            TextView issueTV = (TextView) choiceDialog.findViewById(R.id.issue_choice_tv);
            ImageView issue1IV = (ImageView) choiceDialog.findViewById(R.id.issue1_selected_iv);
            ImageView issue2IV = (ImageView) choiceDialog.findViewById(R.id.issue2_selected_iv);
            ImageView issue3IV = (ImageView) choiceDialog.findViewById(R.id.issue3_selected_iv);
            ageTV.setText(ageEt.getText());
            String sex;
            if(maleButton.isChecked()){
                sex = maleButton.getText().toString();
            }else{
                sex = femaleButton.getText().toString();
            }
            genderTV.setText(sex);
            occupationTV.setText(getResources().getStringArray(R.array.occupation_array)[occupationSpinner.getSelectedItemPosition()]);
            chosenIssue = "";
            for (String aVoteChoiceSet : voteChoiceSet) {
                chosenIssue += aVoteChoiceSet + ',';
            }
            for (Integer index : chosenPos){
                chosenPosList.add(index);
            }
            issueTV.setText(chosenIssue);
            issue1IV.setImageBitmap(pollItems.get(chosenPosList.get(0)));
            issue2IV.setImageBitmap(pollItems.get(chosenPosList.get(1)));
            issue3IV.setImageBitmap(pollItems.get(chosenPosList.get(2)));
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
                    voteChoiceSet.clear();
                }
            });
        }else {
            String incompleteChoice="";
            for (String aVoteChoiceSet : voteChoiceSet) {
                incompleteChoice += aVoteChoiceSet + ',';
            }
            Toast.makeText(PollingActivity.this,"Must Select 3, Chosen so far:"+incompleteChoice,Toast.LENGTH_SHORT).show();
        }
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
        ArrayList<String> voteChoiceList = new ArrayList<>();
        for(String s: voteChoiceSet){
            voteChoiceList.add(s);
        }
        return new PollingDetail(sex,age,occupation,voteChoiceList.get(0),voteChoiceList.get(1),voteChoiceList.get(2));
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
        if(chosenIssueListPositions.get(position)<size) {
            chosenIssue = pollIssueNameList.get(chosenIssueListPositions.get(position));
        }else {
            chosenIssue = "New Issue -  "+(chosenIssueListPositions.get(position)%size);
        }
        Toast.makeText(PollingActivity.this,"You Selected : "+chosenIssue,Toast.LENGTH_LONG).show();
        if(voteChoiceSet.size()==3){
            voteChoiceSet.remove(voteChoiceSet.first());
            chosenPos.remove(chosenPos.first());
        }
        voteChoiceSet.add(chosenIssue);
        chosenPos.add(position);
    }

    @Override
    public List<Bitmap> getIssueImageList() {
        return null;
    }
}
