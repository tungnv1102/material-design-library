package com.blunderer.materialdesignlibrary.sample.scrollviews;

import com.blunderer.materialdesignlibrary.sample.R;

public class ScrollViewActivity
        extends com.blunderer.materialdesignlibrary.activities.ScrollViewActivity {

    @Override
    public int getContentView() {
        return R.layout.activity_scrollview;
    }

    @Override
    public boolean pullToRefreshEnabled() {
        return false;
    }

    @Override
    public int[] getPullToRefreshColorResources() {
        return new int[0];
    }

    @Override
    public void onRefresh() {
    }

}
