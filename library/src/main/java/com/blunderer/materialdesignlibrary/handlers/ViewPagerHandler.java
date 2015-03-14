package com.blunderer.materialdesignlibrary.handlers;

import android.content.Context;
import android.support.v4.app.Fragment;

import com.blunderer.materialdesignlibrary.models.ViewPagerItem;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerHandler {

    private Context mContext;
    private List<ViewPagerItem> mItems;

    public ViewPagerHandler(Context context) {
        mContext = context;
        mItems = new ArrayList<>();
    }

    public ViewPagerHandler addPage(int titleResource, Fragment fragment) {
        ViewPagerItem item = new ViewPagerItem();
        item.setTitle(mContext, titleResource);
        item.setFragment(fragment);
        mItems.add(item);
        return this;
    }

    public List<ViewPagerItem> getViewPagerItems() {
        return mItems;
    }

}
