package com.blunderer.materialdesignlibrary.sample.viewpagers;

import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;
import com.blunderer.materialdesignlibrary.sample.MainFragment;
import com.blunderer.materialdesignlibrary.sample.R;

public class ViewPagerFragment extends com.blunderer.materialdesignlibrary.fragments.ViewPagerFragment {

    @Override
    protected ViewPagerHandler getViewPagerHandler() {
        return new ViewPagerHandler(getActivity())
                .addPage(R.string.title_item1, MainFragment.newInstance("Material Design Fragment ViewPager"))
                .addPage(R.string.title_item2, MainFragment.newInstance("Material Design Fragment ViewPager"));
    }

    @Override
    protected boolean showViewPagerIndicator() {
        return true;
    }

    @Override
    protected boolean replaceActionBarTitleByViewPagerPageTitle() {
        return true;
    }

    @Override
    protected int defaultViewPagerPageSelectedPosition() {
        return 0;
    }

}
