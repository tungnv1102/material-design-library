package com.blunderer.materialdesignlibrary.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.blunderer.materialdesignlibrary.R;

public abstract class ListViewFragment extends Fragment {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListViewFragment mFragment;

    public ListViewFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mFragment = this;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_listview, container, false);

        ViewStub stub = (ViewStub) view.findViewById(R.id.activity_listview_view);
        final ListView listView;
        try {
            stub.setLayoutResource(useCustomContentView()
                    ? getCustomContentView() : R.layout.listview);
            View inflatedView = stub.inflate();

            if (inflatedView instanceof ListView) listView = (ListView) inflatedView;
            else listView = (ListView) inflatedView.findViewById(android.R.id.list);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(
                    "CustomContentView must have a valid layoutResource");
        }

        mSwipeRefreshLayout = (SwipeRefreshLayout)
                view.findViewById(R.id.activity_listview_with_refresh_refresh);
        if (pullToRefreshEnabled()) {
            mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {

                @Override
                public void onRefresh() {
                    mFragment.onRefresh();
                }

            });
            mSwipeRefreshLayout.setColorSchemeResources(getPullToRefreshColorResources());
        } else mSwipeRefreshLayout.setEnabled(false);

        if (listView != null) {
            ListAdapter adapter = getListAdapter();
            if (adapter != null) {
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> adapterView,
                                            View view, int position, long id) {
                        mFragment.onItemClick(adapterView, view, position, id);
                    }

                });
                listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

                    @Override
                    public boolean onItemLongClick(AdapterView<?> adapterView,
                                                   View view, int position, long id) {
                        return mFragment.onItemLongClick(adapterView, view, position, id);
                    }

                });
            }
        }

        return view;
    }

    protected void setRefreshing(boolean refreshing) {
        if (mSwipeRefreshLayout != null) mSwipeRefreshLayout.setRefreshing(refreshing);
    }

    protected abstract ListAdapter getListAdapter();

    protected abstract boolean useCustomContentView();

    /**
     * Return a custom content view.
     * The layout MUST contain a ListView with android:id="@android:id/list".
     * The method "useCustomContentView()" MUST return true.
     *
     * @return a resource
     */
    protected abstract int getCustomContentView();

    protected abstract boolean pullToRefreshEnabled();

    protected abstract int[] getPullToRefreshColorResources();

    protected abstract void onRefresh();

    protected abstract void onItemClick(AdapterView<?> adapterView,
                                        View view,
                                        int position,
                                        long l);

    protected abstract boolean onItemLongClick(AdapterView<?> adapterView,
                                               View view,
                                               int position,
                                               long l);

}
