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

public abstract class ViewPagerWithTabsActivity extends AActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_view_pager_with_tabs);

        List<ViewPagerItem> viewPagerItems = null;
        ViewPagerHandler handler = getViewPagerHandler();
        if (handler != null && handler.getViewPagerItems() != null) {
            viewPagerItems = handler.getViewPagerItems();
        }

        if (viewPagerItems != null && viewPagerItems.size() > 0) {
            ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
            pager.setAdapter(new ViewPagerAdapter(this,
                    getSupportFragmentManager(), viewPagerItems));

            int defaultViewPagerItemSelectedPosition = defaultViewPagerItemSelectedPosition();
            if (defaultViewPagerItemSelectedPosition >= 0 &&
                    defaultViewPagerItemSelectedPosition < viewPagerItems.size()) {
                pager.setCurrentItem(defaultViewPagerItemSelectedPosition);
            }

            showTabs(pager);
        }
    }

    private void showTabs(ViewPager pager) {
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        tabs.setTextColor(getResources().getColor(android.R.color.white));
        tabs.setViewPager(pager);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            tabs.setTabBackground(android.R.attr.selectableItemBackground);
        }
    }

    protected abstract ViewPagerHandler getViewPagerHandler();

    protected abstract int defaultViewPagerItemSelectedPosition();

}
