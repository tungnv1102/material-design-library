package vn.edu.benchmarking.app.viewpagers;

import vn.edu.benchmarking.handlers.ViewPagerHandler;
import vn.edu.benchmarking.app.MainFragment;
import vn.edu.benchmarking.app.R;

public class ViewPagerFragment
        extends vn.edu.benchmarking.fragments.ViewPagerFragment {

    @Override
    public ViewPagerHandler getViewPagerHandler() {
        return new ViewPagerHandler(getActivity())
                .addPage(R.string.title_item1, MainFragment.newInstance("Material Design Fragment ViewPager"))
                .addPage(R.string.title_item2, MainFragment.newInstance("Material Design Fragment ViewPager"));
    }

    @Override
    public int defaultViewPagerPageSelectedPosition() {
        return 0;
    }

    @Override
    public boolean showViewPagerIndicator() {
        return true;
    }

    @Override
    public boolean replaceActionBarTitleByViewPagerPageTitle() {
        return true;
    }

}
