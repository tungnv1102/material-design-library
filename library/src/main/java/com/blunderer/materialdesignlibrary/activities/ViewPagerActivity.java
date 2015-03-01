package com.blunderer.materialdesignlibrary.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.blunderer.materialdesignlibrary.R;
import com.blunderer.materialdesignlibrary.adapters.ViewPagerWithTabsAdapter;
import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;
import com.blunderer.materialdesignlibrary.models.ViewPagerItem;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

public abstract class ViewPagerActivity extends AActivity {

    private List<ViewPagerItem> viewPagerItems;
    private final ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            replaceTitle(viewPagerItems.get(position).getTitleResource());
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_view_pager);

        ViewPagerHandler handler = getViewPagerHandler();
        if (handler != null && handler.getViewPagerItems() != null)
            viewPagerItems = handler.getViewPagerItems();

        if (viewPagerItems != null && viewPagerItems.size() > 0) {
            ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
            pager.setAdapter(new ViewPagerWithTabsAdapter(this, getSupportFragmentManager(), viewPagerItems));
            int defaultViewPagerItemSelectedPosition = defaultViewPagerItemSelectedPosition();
            if (defaultViewPagerItemSelectedPosition >= 0 && defaultViewPagerItemSelectedPosition < viewPagerItems.size())
                pager.setCurrentItem(defaultViewPagerItemSelectedPosition);

            if (!showViewPagerIndicator()) {
                pager.setOnPageChangeListener(mOnPageChangeListener);
            } else {
                CirclePageIndicator viewPagerIndicator = (CirclePageIndicator) findViewById(R.id.viewpagerindicator);
                viewPagerIndicator.setViewPager(pager);
                viewPagerIndicator.setVisibility(View.VISIBLE);
                viewPagerIndicator.setOnPageChangeListener(mOnPageChangeListener);
            }

            replaceTitle(viewPagerItems.get(0).getTitleResource());
        }
    }

    private void replaceTitle(int titleResource) {
        if (replaceActionBarTitleByViewPagerItemTitle())
            getSupportActionBar().setTitle(titleResource);
    }

    protected abstract ViewPagerHandler getViewPagerHandler();

    protected abstract boolean showViewPagerIndicator();

    protected abstract boolean replaceActionBarTitleByViewPagerItemTitle();

    protected abstract int defaultViewPagerItemSelectedPosition();

}
