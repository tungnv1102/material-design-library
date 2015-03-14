package com.blunderer.materialdesignlibrary.fragments;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.blunderer.materialdesignlibrary.R;
import com.blunderer.materialdesignlibrary.adapters.ViewPagerAdapter;
import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;
import com.blunderer.materialdesignlibrary.models.ViewPagerItem;

import java.util.List;

public abstract class ViewPagerWithTabsFragment extends Fragment {

    private List<ViewPagerItem> mViewPagerItems;

    public ViewPagerWithTabsFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewPagerHandler viewPagerHandler = getViewPagerHandler();
        if (viewPagerHandler == null) viewPagerHandler = new ViewPagerHandler(getActivity());
        mViewPagerItems = viewPagerHandler.getViewPagerItems();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_view_pager_with_tabs, container, false);

        if (mViewPagerItems != null && mViewPagerItems.size() > 0) {
            ViewPager pager = (ViewPager) view.findViewById(R.id.viewpager);
            pager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), mViewPagerItems));

            int defaultViewPagerPageSelectedPosition = defaultViewPagerPageSelectedPosition();
            if (defaultViewPagerPageSelectedPosition >= 0 &&
                    defaultViewPagerPageSelectedPosition < mViewPagerItems.size()) {
                pager.setCurrentItem(defaultViewPagerPageSelectedPosition);
            }

            showTabs(pager, view);
        }
        return view;
    }

    private void showTabs(ViewPager pager, View view) {
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        tabs.setTextColor(getResources().getColor(android.R.color.white));
        tabs.setViewPager(pager);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            tabs.setTabBackground(android.R.attr.selectableItemBackground);
        }
    }

    protected abstract ViewPagerHandler getViewPagerHandler();

    protected abstract int defaultViewPagerPageSelectedPosition();

}
