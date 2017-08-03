package com.example.rohit.kathaproject.adapters;

import android.content.Context;
import android.support.annotation.ColorRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.rohit.kathaproject.R;
import com.example.rohit.kathaproject.helpers.CropColor;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Rohit on 24-07-2017.
 */

public class CalendarMonthAdapter extends RecyclerView.Adapter<CalendarMonthAdapter.MyViewHolder> {
    private Context mContext;
    private List<CalendarMonth> mCalendarMonthList;
    private String mCurrentActiveColor;

    public CalendarMonthAdapter(Context context, List<CalendarMonth> calendarMonthList, String activeColor) {
        this.mContext = context;
        this.mCalendarMonthList = calendarMonthList;
        this.mCurrentActiveColor = activeColor;
    }

    public void updateActiveColor(String activeColor) {
        this.mCurrentActiveColor = activeColor;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_month_member, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        CalendarMonth thisMonth = mCalendarMonthList.get(position);

        if (thisMonth.getMonthName() != null) {
            holder.mTextViewMonthName.setText(thisMonth.getMonthName());
        }

        if (thisMonth.getRow1() != null && thisMonth.getRow1().size() > 0) {
            for (int i = 0; i < thisMonth.getRow1().size(); i++) {
                if (thisMonth.getRow1().get(i).equalsIgnoreCase(CropColor.RED.toString())) {
                    if (i == 1) {
                        updateColumnColor(mContext, holder.mViewWeek1Row1, R.color.legend_red);
                    } else if (i == 2) {
                        updateColumnColor(mContext, holder.mViewWeek2Row1, R.color.legend_red);
                    } else if (i == 3) {
                        updateColumnColor(mContext, holder.mViewWeek3Row1, R.color.legend_red);
                    } else if (i == 4) {
                        updateColumnColor(mContext, holder.mViewWeek4Row1, R.color.legend_red);
                    }
                } else if (thisMonth.getRow1().get(i).equalsIgnoreCase(CropColor.ORANGE.toString())) {
                    if (i == 1) {
                        updateColumnColor(mContext, holder.mViewWeek1Row1, R.color.legend_orange);
                    } else if (i == 2) {
                        updateColumnColor(mContext, holder.mViewWeek2Row1, R.color.legend_orange);
                    } else if (i == 3) {
                        updateColumnColor(mContext, holder.mViewWeek3Row1, R.color.legend_orange);
                    } else if (i == 4) {
                        updateColumnColor(mContext, holder.mViewWeek4Row1, R.color.legend_orange);
                    }
                } else if (thisMonth.getRow1().get(i).equalsIgnoreCase(CropColor.BLUE.toString())) {
                    if (i == 1) {
                        updateColumnColor(mContext, holder.mViewWeek1Row1, R.color.legend_blue);
                    } else if (i == 2) {
                        updateColumnColor(mContext, holder.mViewWeek2Row1, R.color.legend_blue);
                    } else if (i == 3) {
                        updateColumnColor(mContext, holder.mViewWeek3Row1, R.color.legend_blue);
                    } else if (i == 4) {
                        updateColumnColor(mContext, holder.mViewWeek4Row1, R.color.legend_blue);
                    }
                } else if (thisMonth.getRow1().get(i).equalsIgnoreCase(CropColor.GREEN.toString())) {
                    if (i == 1) {
                        updateColumnColor(mContext, holder.mViewWeek1Row1, R.color.legend_green);
                    } else if (i == 2) {
                        updateColumnColor(mContext, holder.mViewWeek2Row1, R.color.legend_green);
                    } else if (i == 3) {
                        updateColumnColor(mContext, holder.mViewWeek3Row1, R.color.legend_green);
                    } else if (i == 4) {
                        updateColumnColor(mContext, holder.mViewWeek4Row1, R.color.legend_green);
                    }
                } else if (thisMonth.getRow1().get(i).equalsIgnoreCase(CropColor.YELLOW.toString())) {
                    if (i == 1) {
                        updateColumnColor(mContext, holder.mViewWeek1Row1, R.color.legend_yellow);
                    } else if (i == 2) {
                        updateColumnColor(mContext, holder.mViewWeek2Row1, R.color.legend_yellow);
                    } else if (i == 3) {
                        updateColumnColor(mContext, holder.mViewWeek3Row1, R.color.legend_yellow);
                    } else if (i == 4) {
                        updateColumnColor(mContext, holder.mViewWeek4Row1, R.color.legend_yellow);
                    }
                } else if (thisMonth.getRow1().get(i).equalsIgnoreCase(CropColor.VIOLET.toString())) {
                    if (i == 1) {
                        updateColumnColor(mContext, holder.mViewWeek1Row1, R.color.legend_violet);
                    } else if (i == 2) {
                        updateColumnColor(mContext, holder.mViewWeek2Row1, R.color.legend_violet);
                    } else if (i == 3) {
                        updateColumnColor(mContext, holder.mViewWeek3Row1, R.color.legend_violet);
                    } else if (i == 4) {
                        updateColumnColor(mContext, holder.mViewWeek4Row1, R.color.legend_violet);
                    }
                }
            }

            for (int i = 0; i < thisMonth.getRow2().size(); i++) {
                if (thisMonth.getRow2().get(i).equalsIgnoreCase(CropColor.RED.toString())) {
                    if (i == 1) {
                        updateColumnColor(mContext, holder.mViewWeek1Row2, R.color.legend_red);
                    } else if (i == 2) {
                        updateColumnColor(mContext, holder.mViewWeek2Row2, R.color.legend_red);
                    } else if (i == 3) {
                        updateColumnColor(mContext, holder.mViewWeek3Row2, R.color.legend_red);
                    } else if (i == 4) {
                        updateColumnColor(mContext, holder.mViewWeek4Row2, R.color.legend_red);
                    }
                } else if (thisMonth.getRow2().get(i).equalsIgnoreCase(CropColor.ORANGE.toString())) {
                    if (i == 1) {
                        updateColumnColor(mContext, holder.mViewWeek1Row2, R.color.legend_orange);
                    } else if (i == 2) {
                        updateColumnColor(mContext, holder.mViewWeek2Row2, R.color.legend_orange);
                    } else if (i == 3) {
                        updateColumnColor(mContext, holder.mViewWeek3Row2, R.color.legend_orange);
                    } else if (i == 4) {
                        updateColumnColor(mContext, holder.mViewWeek4Row2, R.color.legend_orange);
                    }
                } else if (thisMonth.getRow2().get(i).equalsIgnoreCase(CropColor.BLUE.toString())) {
                    if (i == 1) {
                        updateColumnColor(mContext, holder.mViewWeek1Row2, R.color.legend_blue);
                    } else if (i == 2) {
                        updateColumnColor(mContext, holder.mViewWeek2Row2, R.color.legend_blue);
                    } else if (i == 3) {
                        updateColumnColor(mContext, holder.mViewWeek3Row2, R.color.legend_blue);
                    } else if (i == 4) {
                        updateColumnColor(mContext, holder.mViewWeek4Row2, R.color.legend_blue);
                    }
                } else if (thisMonth.getRow2().get(i).equalsIgnoreCase(CropColor.GREEN.toString())) {
                    if (i == 1) {
                        updateColumnColor(mContext, holder.mViewWeek1Row2, R.color.legend_green);
                    } else if (i == 2) {
                        updateColumnColor(mContext, holder.mViewWeek2Row2, R.color.legend_green);
                    } else if (i == 3) {
                        updateColumnColor(mContext, holder.mViewWeek3Row2, R.color.legend_green);
                    } else if (i == 4) {
                        updateColumnColor(mContext, holder.mViewWeek4Row2, R.color.legend_green);
                    }
                } else if (thisMonth.getRow2().get(i).equalsIgnoreCase(CropColor.YELLOW.toString())) {
                    if (i == 1) {
                        updateColumnColor(mContext, holder.mViewWeek1Row2, R.color.legend_yellow);
                    } else if (i == 2) {
                        updateColumnColor(mContext, holder.mViewWeek2Row2, R.color.legend_yellow);
                    } else if (i == 3) {
                        updateColumnColor(mContext, holder.mViewWeek3Row2, R.color.legend_yellow);
                    } else if (i == 4) {
                        updateColumnColor(mContext, holder.mViewWeek4Row2, R.color.legend_yellow);
                    }
                } else if (thisMonth.getRow2().get(i).equalsIgnoreCase(CropColor.VIOLET.toString())) {
                    if (i == 1) {
                        updateColumnColor(mContext, holder.mViewWeek1Row2, R.color.legend_violet);
                    } else if (i == 2) {
                        updateColumnColor(mContext, holder.mViewWeek2Row2, R.color.legend_violet);
                    } else if (i == 3) {
                        updateColumnColor(mContext, holder.mViewWeek3Row2, R.color.legend_violet);
                    } else if (i == 4) {
                        updateColumnColor(mContext, holder.mViewWeek4Row2, R.color.legend_violet);
                    }
                }
                else if (thisMonth.getRow2().get(i).equalsIgnoreCase("Reset")) {
                    if (i == 1) {
                        updateColumnColor(mContext, holder.mViewWeek1Row2, R.color.light_grey);
                    } else if (i == 2) {
                        updateColumnColor(mContext, holder.mViewWeek2Row2, R.color.light_grey);
                    } else if (i == 3) {
                        updateColumnColor(mContext, holder.mViewWeek3Row2, R.color.light_grey);
                    } else if (i == 4) {
                        updateColumnColor(mContext, holder.mViewWeek4Row2, R.color.light_grey);
                    }
                }
            }
        }
    }

    public void updateColumnColor(Context context, View view, @ColorRes int resId) {
        if(resId == R.color.light_grey){
            view.setBackground(ContextCompat.getDrawable(context,R.drawable.drawable_week_light));
        }else {
            view.setBackgroundColor(ContextCompat.getColor(context, resId));
        }
    }

    public void updateSelectedColor(Context context, View view, String colorName) {
        if (colorName.equalsIgnoreCase(CropColor.RED.toString())) {
            view.setBackgroundColor(ContextCompat.getColor(context, R.color.legend_red));
        } else if (colorName.equalsIgnoreCase(CropColor.ORANGE.toString())) {
            view.setBackgroundColor(ContextCompat.getColor(context, R.color.legend_orange));
        } else if (colorName.equalsIgnoreCase(CropColor.BLUE.toString())) {
            view.setBackgroundColor(ContextCompat.getColor(context, R.color.legend_blue));
        } else if (colorName.equalsIgnoreCase(CropColor.GREEN.toString())) {
            view.setBackgroundColor(ContextCompat.getColor(context, R.color.legend_green));
        } else if (colorName.equalsIgnoreCase(CropColor.YELLOW.toString())) {
            view.setBackgroundColor(ContextCompat.getColor(context, R.color.legend_yellow));
        } else if (colorName.equalsIgnoreCase(CropColor.VIOLET.toString())) {
            view.setBackgroundColor(ContextCompat.getColor(context, R.color.legend_violet));
        }else if (colorName.equals("Reset")){
            view.setBackground(ContextCompat.getDrawable(context,R.drawable.drawable_week_light));
        }
    }

    @Override
    public int getItemCount() {
        return mCalendarMonthList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.text_month_name)
        TextView mTextViewMonthName;

        @BindView(R.id.view_week1_row1)
        View mViewWeek1Row1;

        @BindView(R.id.view_week1_row2)
        View mViewWeek1Row2;

        @BindView(R.id.view_week2_row1)
        View mViewWeek2Row1;

        @BindView(R.id.view_week2_row2)
        View mViewWeek2Row2;

        @BindView(R.id.view_week3_row1)
        View mViewWeek3Row1;

        @BindView(R.id.view_week3_row2)
        View mViewWeek3Row2;

        @BindView(R.id.view_week4_row1)
        View mViewWeek4Row1;

        @BindView(R.id.view_week4_row2)
        View mViewWeek4Row2;

        public MyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.view_week1_row1)
        public void week1Row1Clicked() {
            updateSelectedColor(mContext, mViewWeek1Row1, mCurrentActiveColor);
        }

        @OnClick(R.id.view_week2_row1)
        public void week2Row1Clicked() {
            updateSelectedColor(mContext, mViewWeek2Row1, mCurrentActiveColor);
        }

        @OnClick(R.id.view_week3_row1)
        public void week3Row1Clicked() {
            updateSelectedColor(mContext, mViewWeek3Row1, mCurrentActiveColor);
        }

        @OnClick(R.id.view_week4_row1)
        public void week4Row1Clicked() {
            updateSelectedColor(mContext, mViewWeek4Row1, mCurrentActiveColor);
        }

        @OnClick(R.id.view_week1_row2)
        public void week1Row2Clicked() {
            updateSelectedColor(mContext, mViewWeek1Row2, mCurrentActiveColor);
        }

        @OnClick(R.id.view_week2_row2)
        public void week2Row2Clicked() {
            updateSelectedColor(mContext, mViewWeek2Row2, mCurrentActiveColor);
        }

        @OnClick(R.id.view_week3_row2)
        public void week1Row3Clicked() {
            updateSelectedColor(mContext, mViewWeek3Row2, mCurrentActiveColor);
        }

        @OnClick(R.id.view_week4_row2)
        public void week4Row2Clicked() {
            updateSelectedColor(mContext, mViewWeek4Row2, mCurrentActiveColor);
        }
    }

    public interface saveClicked {
        public void saveClicked(CalendarMonth caledarMonth);
    }
}
