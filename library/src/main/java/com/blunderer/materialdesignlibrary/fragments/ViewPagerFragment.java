package com.blunderer.materialdesignlibrary.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blunderer.materialdesignlibrary.R;
import com.blunderer.materialdesignlibrary.adapters.ViewPagerAdapter;
import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;
import com.blunderer.materialdesignlibrary.models.ViewPagerItem;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.List;

public abstract class ViewPagerFragment extends Fragment {

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

    public ViewPagerFragment() {
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
        View view = inflater.inflate(R.layout.fragment_view_pager, container, false);

        if (mViewPagerItems != null && mViewPagerItems.size() > 0) {
            ViewPager pager = (ViewPager) view.findViewById(R.id.viewpager);
            pager.setAdapter(new ViewPagerAdapter(getChildFragmentManager(), mViewPagerItems));

            int defaultViewPagerItemSelectedPosition = defaultViewPagerPageSelectedPosition();
            if (defaultViewPagerItemSelectedPosition >= 0 &&
                    defaultViewPagerItemSelectedPosition < mViewPagerItems.size()) {
                pager.setCurrentItem(defaultViewPagerItemSelectedPosition);
            }

            showIndicator(view, pager);

            replaceTitle(mViewPagerItems
                    .get(defaultViewPagerItemSelectedPosition).getTitle());
        }
        return view;
    }

    private void showIndicator(View view, ViewPager pager) {
        if (!showViewPagerIndicator()) {
            pager.setOnPageChangeListener(mOnPageChangeListener);
        } else {
            CirclePageIndicator viewPagerIndicator = (CirclePageIndicator)
                    view.findViewById(R.id.viewpagerindicator);
            viewPagerIndicator.setViewPager(pager);
            viewPagerIndicator.setVisibility(View.VISIBLE);
            viewPagerIndicator.setOnPageChangeListener(mOnPageChangeListener);
        }
    }

    private void replaceTitle(String title) {
        if (replaceActionBarTitleByViewPagerPageTitle()) {
            ((ActionBarActivity) getActivity()).getSupportActionBar().setTitle(title);
        }
    }

    protected abstract ViewPagerHandler getViewPagerHandler();

    protected abstract boolean showViewPagerIndicator();

    protected abstract boolean replaceActionBarTitleByViewPagerPageTitle();

    protected abstract int defaultViewPagerPageSelectedPosition();

}
