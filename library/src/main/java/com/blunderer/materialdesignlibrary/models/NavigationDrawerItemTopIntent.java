package com.blunderer.materialdesignlibrary.models;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;

public class NavigationDrawerItemTopIntent extends NavigationDrawerItem {

    private int mIconResource;
    private Intent mIntent;
    private boolean mUseIconResource = false;

    public int getIconResource() {
        return mIconResource;
    }

    public void setIconResource(int iconResource) {
        mUseIconResource = true;
        mIconResource = iconResource;
    }

    public Intent getIntent() {
        return mIntent;
    }

    public void setIntent(Intent intent) {
        this.mIntent = intent;
    }

    public boolean useIconResource() {
        return mUseIconResource;
    }

}
