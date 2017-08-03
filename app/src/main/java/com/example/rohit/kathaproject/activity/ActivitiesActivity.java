package com.example.rohit.kathaproject.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.rohit.kathaproject.R;
import com.example.rohit.kathaproject.adapters.CalendarMonth;
import com.example.rohit.kathaproject.adapters.CalendarMonthAdapter;
import com.example.rohit.kathaproject.helpers.CropColor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Rohit on 24-07-2017.
 */

public class ActivitiesActivity extends AppCompatActivity{

        @BindView(R.id.activity_calendar)
        RecyclerView mListViewCalendar;

        private RecyclerView.LayoutManager mLayoutManager;
        private CalendarMonthAdapter mCalendarMonthAdapter;
        private List<CalendarMonth> mCalendarMonthList;
        private String mCurrentActiveColor = CropColor.RED.toString();

        @Override
        protected void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_activity_calendar);
            ButterKnife.bind(this);

            initlizeData();
            initilizeView();
        }

        public void initlizeData() {
            mCalendarMonthList = new ArrayList<>();

            CalendarMonth calendarMonth1 = new CalendarMonth();
            calendarMonth1.setMonthName(com.example.rohit.kathaproject.activity.CalendarActivity.Month.JANUARY.toString());
            calendarMonth1.setRow1(Arrays.asList("", "", "", ""));
            calendarMonth1.setRow2(Arrays.asList("", "", "", ""));

            mCalendarMonthList.add(calendarMonth1);

            CalendarMonth calendarMonth2 = new CalendarMonth();
            calendarMonth2.setMonthName(com.example.rohit.kathaproject.activity.CalendarActivity.Month.FEBRUARY.toString());
            calendarMonth2.setRow1(Arrays.asList("", "", "", ""));
            calendarMonth2.setRow2(Arrays.asList("", "", "", ""));
            mCalendarMonthList.add(calendarMonth2);

            CalendarMonth calendarMonth3 = new CalendarMonth();
            calendarMonth3.setMonthName(com.example.rohit.kathaproject.activity.CalendarActivity.Month.MARCH.toString());
            calendarMonth3.setRow1(Arrays.asList("", "", "", ""));
            calendarMonth3.setRow2(Arrays.asList("", "", "", ""));
            mCalendarMonthList.add(calendarMonth3);

            CalendarMonth calendarMonth4 = new CalendarMonth();
            calendarMonth4.setMonthName(com.example.rohit.kathaproject.activity.CalendarActivity.Month.APRIL.toString());
            calendarMonth4.setRow1(Arrays.asList("", "", "", ""));
            calendarMonth4.setRow2(Arrays.asList("", "", "", ""));
            mCalendarMonthList.add(calendarMonth4);

            CalendarMonth calendarMonth5 = new CalendarMonth();
            calendarMonth5.setMonthName(com.example.rohit.kathaproject.activity.CalendarActivity.Month.MAY.toString());
            calendarMonth5.setRow1(Arrays.asList("", "", "", ""));
            calendarMonth5.setRow2(Arrays.asList("", "", "", ""));
            mCalendarMonthList.add(calendarMonth5);

            CalendarMonth calendarMonth6 = new CalendarMonth();
            calendarMonth6.setMonthName(com.example.rohit.kathaproject.activity.CalendarActivity.Month.JUNE.toString());
            calendarMonth6.setRow1(Arrays.asList("", "", "", ""));
            calendarMonth6.setRow2(Arrays.asList("", "", "", ""));
            mCalendarMonthList.add(calendarMonth6);

            CalendarMonth calendarMonth7 = new CalendarMonth();
            calendarMonth7.setMonthName(com.example.rohit.kathaproject.activity.CalendarActivity.Month.JULY.toString());
            calendarMonth7.setRow1(Arrays.asList("", "", "", ""));
            calendarMonth7.setRow2(Arrays.asList("", "", "", ""));
            mCalendarMonthList.add(calendarMonth7);

            CalendarMonth calendarMonth8 = new CalendarMonth();
            calendarMonth8.setMonthName(com.example.rohit.kathaproject.activity.CalendarActivity.Month.AUGUST.toString());
            calendarMonth8.setRow1(Arrays.asList("", "", "", ""));
            calendarMonth8.setRow2(Arrays.asList("", "", "", ""));
            mCalendarMonthList.add(calendarMonth8);

            CalendarMonth calendarMonth9 = new CalendarMonth();
            calendarMonth9.setMonthName(com.example.rohit.kathaproject.activity.CalendarActivity.Month.SEPTEMBER.toString());
            calendarMonth9.setRow1(Arrays.asList("", "", "", ""));
            calendarMonth9.setRow2(Arrays.asList("", "", "", ""));
            mCalendarMonthList.add(calendarMonth9);

            CalendarMonth calendarMonth10 = new CalendarMonth();
            calendarMonth10.setMonthName(com.example.rohit.kathaproject.activity.CalendarActivity.Month.OCTOBER.toString());
            calendarMonth10.setRow1(Arrays.asList("", "", "", ""));
            calendarMonth10.setRow2(Arrays.asList("", "", "", ""));
            mCalendarMonthList.add(calendarMonth10);

            CalendarMonth calendarMonth11 = new CalendarMonth();
            calendarMonth11.setMonthName(com.example.rohit.kathaproject.activity.CalendarActivity.Month.NOVEMBER.toString());
            calendarMonth11.setRow1(Arrays.asList("", "", "", ""));
            calendarMonth11.setRow2(Arrays.asList("", "", "", ""));
            mCalendarMonthList.add(calendarMonth11);

            CalendarMonth calendarMonth12 = new CalendarMonth();
            calendarMonth12.setMonthName(com.example.rohit.kathaproject.activity.CalendarActivity.Month.DECEMBER.toString());
            calendarMonth12.setRow1(Arrays.asList("", "", "", ""));
            calendarMonth12.setRow2(Arrays.asList("", "", "", ""));
            mCalendarMonthList.add(calendarMonth12);

        }

        public void initilizeView() {
            mListViewCalendar.setHasFixedSize(true);
            mLayoutManager = new GridLayoutManager(this, 3);
            mListViewCalendar.setLayoutManager(mLayoutManager);
            mCalendarMonthAdapter = new CalendarMonthAdapter(this, mCalendarMonthList, mCurrentActiveColor);
            mListViewCalendar.setAdapter(mCalendarMonthAdapter);
            //mListViewCalendar.addItemDecoration(new GridSpacingItemDecorator(3, 50, false));
        }

        @OnClick(R.id.layout_farming)
        public void farmingClicked() {
            mCurrentActiveColor = CropColor.RED.toString();
            mCalendarMonthAdapter.updateActiveColor(mCurrentActiveColor);
        }

        @OnClick(R.id.layout_non_farming)
        public void nonFarmingClicked() {
            mCurrentActiveColor = CropColor.ORANGE.toString();
            mCalendarMonthAdapter.updateActiveColor(mCurrentActiveColor);
        }

        @OnClick(R.id.layout_festivals)
        public void festivalClicked() {
            mCurrentActiveColor = CropColor.BLUE.toString();
            mCalendarMonthAdapter.updateActiveColor(mCurrentActiveColor);
        }

        @OnClick(R.id.layout_others)
        public void othersClicked() {
            mCurrentActiveColor = CropColor.VIOLET.toString();
            mCalendarMonthAdapter.updateActiveColor(mCurrentActiveColor);
        }

        @OnClick(R.id.layout_reset)
        public void resetClicked(){
            mCurrentActiveColor = "Reset";
            mCalendarMonthAdapter.updateActiveColor(mCurrentActiveColor);
        }

        public enum Month {
            JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
        }

}
