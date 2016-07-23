package vn.edu.benchmarking.models;

import android.content.Intent;

public class NavigationDrawerListItemTopIntent extends ListItem {

    private Intent mIntent;

    public Intent getIntent() {
        return mIntent;
    }

    public void setIntent(Intent intent) {
        this.mIntent = intent;
    }

}
