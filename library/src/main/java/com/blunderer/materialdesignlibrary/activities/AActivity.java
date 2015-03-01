package com.blunderer.materialdesignlibrary.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.AbsListView;

import com.blunderer.materialdesignlibrary.R;

public abstract class AActivity extends ActionBarActivity {

    private Toolbar mToolbar;

    public void onCreate(Bundle savedInstanceState, int contentView) {
        super.onCreate(savedInstanceState);
        setContentView(contentView);

        mToolbar = (Toolbar) findViewById(R.id.my_awesome_toolbar);
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    protected Toolbar getToolbar() {
        return mToolbar;
    }

}
