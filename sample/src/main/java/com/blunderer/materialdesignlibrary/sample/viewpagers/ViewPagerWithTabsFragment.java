package com.blunderer.materialdesignlibrary.sample.viewpagers;

import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;
import com.blunderer.materialdesignlibrary.sample.MainFragment;
import com.blunderer.materialdesignlibrary.sample.R;

public class ViewPagerWithTabsFragment extends com.blunderer.materialdesignlibrary.fragments.ViewPagerWithTabsFragment {

    @Override
    protected ViewPagerHandler getViewPagerHandler() {
        return new ViewPagerHandler(getActivity())
                .addPage(R.string.title_item1,
                        MainFragment.newInstance("Material Design Fragment ViewPager with Tabs"))
                .addPage(R.string.title_item2,
                        MainFragment.newInstance("Material Design Fragment ViewPager with Tabs"));
    }

    @Override
    protected int defaultViewPagerPageSelectedPosition() {
        return 0;
    }

}
