package com.blunderer.materialdesignlibrary.activities;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;

import com.blunderer.materialdesignlibrary.R;

public abstract class AActivity extends ActionBarActivity {

    protected Toolbar mToolbar;

    public void onCreate(Bundle savedInstanceState, int contentView) {
        super.onCreate(savedInstanceState);
        setContentView(contentView);

        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        mToolbar.setTitleTextColor(getResources().getColor(android.R.color.white));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

}
