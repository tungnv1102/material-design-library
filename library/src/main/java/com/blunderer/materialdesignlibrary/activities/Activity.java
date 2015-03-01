package com.blunderer.materialdesignlibrary.activities;

import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

import com.blunderer.materialdesignlibrary.R;

public abstract class Activity extends AActivity {

    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity);

        ViewStub stub = (ViewStub) findViewById(R.id.view_stub);
        stub.setLayoutResource(getContentView());
        mView = stub.inflate();
    }

    @Override
    public View findViewById(int id) {
        if (mView != null)
            return mView.findViewById(id);
        return super.findViewById(id);
    }

    protected abstract int getContentView();

}