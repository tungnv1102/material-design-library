package com.blunderer.materialdesignlibrary.activities;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.astuetz.PagerSlidingTabStrip;
import com.blunderer.materialdesignlibrary.R;
import com.blunderer.materialdesignlibrary.adapters.ViewPagerAdapter;
import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;
import com.blunderer.materialdesignlibrary.models.ViewPagerItem;

import java.util.List;

public abstract class ViewPagerWithTabsActivity extends AActivity
        implements com.blunderer.materialdesignlibrary.interfaces.ViewPager {

    protected ViewPager mViewPager;
    protected PagerSlidingTabStrip mViewPagerTabs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_view_pager_with_tabs);

        List<ViewPagerItem> viewPagerItems = null;
        ViewPagerHandler handler = getViewPagerHandler();
        if (handler != null && handler.getViewPagerItems() != null) {
            viewPagerItems = handler.getViewPagerItems();
        }

        if (viewPagerItems != null && viewPagerItems.size() > 0) {
            mViewPager = (ViewPager) findViewById(R.id.viewpager);
            mViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), viewPagerItems));

            int defaultViewPagerPageSelectedPosition = defaultViewPagerPageSelectedPosition();
            if (defaultViewPagerPageSelectedPosition >= 0 &&
                    defaultViewPagerPageSelectedPosition < viewPagerItems.size()) {
                mViewPager.setCurrentItem(defaultViewPagerPageSelectedPosition);
            }

            showTabs(mViewPager);
        }
    }

    private void showTabs(ViewPager pager) {
        mViewPagerTabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        mViewPagerTabs.setTextColor(getResources().getColor(android.R.color.white));
        mViewPagerTabs.setViewPager(pager);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            mViewPagerTabs.setTabBackground(android.R.attr.selectableItemBackground);
        }
    }

}
