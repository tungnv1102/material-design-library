package vn.edu.benchmarking.app.viewpagers;

import vn.edu.benchmarking.handlers.ViewPagerHandler;
import vn.edu.benchmarking.app.MainFragment;
import vn.edu.benchmarking.app.R;

public class ViewPagerWithTabsFragment
        extends vn.edu.benchmarking.fragments.ViewPagerWithTabsFragment {

    @Override
    public ViewPagerHandler getViewPagerHandler() {
        return new ViewPagerHandler(getActivity())
                .addPage(R.string.title_item1,
                        MainFragment.newInstance("Material Design Fragment ViewPager with Tabs"))
                .addPage(R.string.title_item2,
                        MainFragment.newInstance("Material Design Fragment ViewPager with Tabs"));
    }

    @Override
    public boolean expandTabs() {
        return false;
    }

    @Override
    public int defaultViewPagerPageSelectedPosition() {
        return 0;
    }

}
