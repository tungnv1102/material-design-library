package com.blunderer.materialdesignlibrary.interfaces;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

public interface ListView {

    public void setRefreshing(boolean refreshing);

    public ListAdapter getListAdapter();

    public boolean useCustomContentView();

    public int getCustomContentView();

    public boolean pullToRefreshEnabled();

    public int[] getPullToRefreshColorResources();

    public void onRefresh();

    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l);

    public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l);

}
