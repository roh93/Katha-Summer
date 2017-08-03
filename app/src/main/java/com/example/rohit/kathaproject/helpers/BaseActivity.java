package com.example.rohit.kathaproject.helpers;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.rohit.kathaproject.R;

/**
 * Created by sandeep on 15-07-2017.
 */

public class BaseActivity extends AppCompatActivity {
    private CoordinatorLayout mCoordinatorLayout;
    private Toolbar mToolbar;
    private FrameLayout mFrameLayoutContainer;
    private TextView mTextViewTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        mCoordinatorLayout = (CoordinatorLayout) getLayoutInflater().inflate(R.layout.activity_base, null);
        mFrameLayoutContainer = (FrameLayout) mCoordinatorLayout.findViewById(R.id.activity_content);
        getLayoutInflater().inflate(layoutResID, mFrameLayoutContainer, true);
        super.setContentView(mCoordinatorLayout);

        mToolbar = (Toolbar) mCoordinatorLayout.findViewById(R.id.toolbar);
        mTextViewTitle = (TextView) mToolbar.findViewById(R.id.text_screen_title);
    }

    public void setScreenTitle(@StringRes int resId) {
        mTextViewTitle.setText(getString(resId));
    }
}
