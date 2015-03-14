package com.blunderer.materialdesignlibrary.activities;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.blunderer.materialdesignlibrary.R;
import com.blunderer.materialdesignlibrary.adapters.ViewPagerAdapter;
import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;
import com.blunderer.materialdesignlibrary.models.ViewPagerItem;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

public abstract class ViewPagerActivity extends AActivity {

    private List<ViewPagerItem> mViewPagerItems;
    private final ViewPager.OnPageChangeListener mOnPageChangeListener = new ViewPager
            .OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            replaceTitle(mViewPagerItems.get(position).getTitle());
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_view_pager);

        ViewPagerHandler handler = getViewPagerHandler();
        if (handler != null && handler.getViewPagerItems() != null) {
            mViewPagerItems = handler.getViewPagerItems();
        }

        if (mViewPagerItems != null && mViewPagerItems.size() > 0) {
            ViewPager pager = (ViewPager) findViewById(R.id.viewpager);
            pager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager(), mViewPagerItems));

            int defaultViewPagerItemSelectedPosition = defaultViewPagerPageSelectedPosition();
            if (defaultViewPagerItemSelectedPosition >= 0 &&
                    defaultViewPagerItemSelectedPosition < mViewPagerItems.size()) {
                pager.setCurrentItem(defaultViewPagerItemSelectedPosition);
            } else defaultViewPagerItemSelectedPosition = 0;

            showIndicator(pager);

            replaceTitle(mViewPagerItems
                    .get(defaultViewPagerItemSelectedPosition).getTitle());
        }
    }

    private void showIndicator(ViewPager pager) {
        if (!showViewPagerIndicator()) {
            pager.setOnPageChangeListener(mOnPageChangeListener);
        } else {
            CirclePageIndicator viewPagerIndicator = (CirclePageIndicator)
                    findViewById(R.id.viewpagerindicator);
            viewPagerIndicator.setViewPager(pager);
            viewPagerIndicator.setVisibility(View.VISIBLE);
            viewPagerIndicator.setOnPageChangeListener(mOnPageChangeListener);
        }
    }

    private void replaceTitle(String title) {
        if (replaceActionBarTitleByViewPagerPageTitle()) getSupportActionBar().setTitle(title);
    }

    protected abstract ViewPagerHandler getViewPagerHandler();

    protected abstract boolean showViewPagerIndicator();

    protected abstract boolean replaceActionBarTitleByViewPagerPageTitle();

    protected abstract int defaultViewPagerPageSelectedPosition();

}
