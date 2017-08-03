package com.example.rohit.kathaproject.adapters;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by sandeep on 22-07-2017.
 */

public class CalendarMonth implements Parcelable {
    private String monthName;
    private List<String> row1;
    private List<String> row2;

    public String getMonthName() {
        return monthName;
    }

    public void setMonthName(String monthName) {
        this.monthName = monthName;
    }

    public List<String> getRow1() {
        return row1;
    }

    public void setRow1(List<String> row1) {
        this.row1 = row1;
    }

    public List<String> getRow2() {
        return row2;
    }

    public void setRow2(List<String> row2) {
        this.row2 = row2;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.monthName);
        dest.writeStringList(this.row1);
        dest.writeStringList(this.row2);
    }

    public CalendarMonth() {
    }

    protected CalendarMonth(Parcel in) {
        this.monthName = in.readString();
        this.row1 = in.createStringArrayList();
        this.row2 = in.createStringArrayList();
    }

    public static final Parcelable.Creator<CalendarMonth> CREATOR = new Parcelable.Creator<CalendarMonth>() {
        @Override
        public CalendarMonth createFromParcel(Parcel source) {
            return new CalendarMonth(source);
        }

        @Override
        public CalendarMonth[] newArray(int size) {
            return new CalendarMonth[size];
        }
    };
}
