package vn.edu.benchmarking.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewStub;

import vn.edu.benchmarking.R;
import vn.edu.benchmarking.views.ToolbarSearch;

public abstract class Activity extends AActivity {

    private View mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.mdl_activity);

        ViewStub stub = (ViewStub) findViewById(R.id.view_stub);
        stub.setLayoutResource(getContentView());
        mView = stub.inflate();
    }

    @Override
    public View findViewById(int id) {
        if (mView != null) return mView.findViewById(id);
        return super.findViewById(id);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ToolbarSearch.SEARCH_REQUEST_CODE) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    protected abstract int getContentView();

}