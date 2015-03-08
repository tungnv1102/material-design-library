package com.blunderer.materialdesignlibrary.handlers;

import android.content.Intent;
import android.support.v4.app.Fragment;

import com.blunderer.materialdesignlibrary.models.NavigationDrawerItem;
import com.blunderer.materialdesignlibrary.models.NavigationDrawerItemHeader;
import com.blunderer.materialdesignlibrary.models.NavigationDrawerItemTopFragment;
import com.blunderer.materialdesignlibrary.models.NavigationDrawerItemTopIntent;

import java.util.ArrayList;
import java.util.List;

public class NavigationDrawerTopHandler {

    private List<NavigationDrawerItem> mItems;

    public NavigationDrawerTopHandler() {
        mItems = new ArrayList<>();
    }

    public NavigationDrawerTopHandler addSection(int titleResource) {
        NavigationDrawerItemHeader item = new NavigationDrawerItemHeader();
        item.setTitleResource(titleResource);
        mItems.add(item);
        return this;
    }

    public NavigationDrawerTopHandler addItem(int titleResource,
                                              int iconResource,
                                              Fragment fragment) {
        NavigationDrawerItemTopFragment item = new NavigationDrawerItemTopFragment();
        item.setTitleResource(titleResource);
        item.setIconResource(iconResource);
        item.setFragment(fragment);
        mItems.add(item);
        return this;
    }

    public NavigationDrawerTopHandler addItem(int titleResource, Fragment fragment) {
        NavigationDrawerItemTopFragment item = new NavigationDrawerItemTopFragment();
        item.setTitleResource(titleResource);
        item.setFragment(fragment);
        mItems.add(item);
        return this;
    }

    public NavigationDrawerTopHandler addItem(int titleResource, int iconResource, Intent intent) {
        NavigationDrawerItemTopIntent item = new NavigationDrawerItemTopIntent();
        item.setTitleResource(titleResource);
        item.setIconResource(iconResource);
        item.setIntent(intent);
        mItems.add(item);
        return this;
    }

    public NavigationDrawerTopHandler addItem(int titleResource, Intent intent) {
        NavigationDrawerItemTopIntent item = new NavigationDrawerItemTopIntent();
        item.setTitleResource(titleResource);
        item.setIntent(intent);
        mItems.add(item);
        return this;
    }

    public List<NavigationDrawerItem> getNavigationDrawerTopItems() {
        return mItems;
    }

}
