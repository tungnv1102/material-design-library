package vn.edu.benchmarking.app.viewpagers;

import vn.edu.benchmarking.handlers.ActionBarDefaultHandler;
import vn.edu.benchmarking.handlers.ActionBarHandler;
import vn.edu.benchmarking.handlers.ViewPagerHandler;
import vn.edu.benchmarking.app.MainFragment;
import vn.edu.benchmarking.app.R;

public class ViewPagerActivity
        extends vn.edu.benchmarking.activities.ViewPagerActivity {

    @Override
    public ViewPagerHandler getViewPagerHandler() {
        return new ViewPagerHandler(this)
                .addPage(R.string.title_section1,
                        MainFragment.newInstance("Material Design ViewPager"))
                .addPage(R.string.title_section2,
                        MainFragment.newInstance("Material Design ViewPager"));
    }

    @Override
    public int defaultViewPagerPageSelectedPosition() {
        return 0;
    }

    @Override
    public boolean showViewPagerIndicator() {
        return false;
    }

    @Override
    public boolean replaceActionBarTitleByViewPagerPageTitle() {
        return true;
    }

    @Override
    protected boolean enableActionBarShadow() {
        return false;
    }

    @Override
    protected ActionBarHandler getActionBarHandler() {
        return new ActionBarDefaultHandler(this);
    }

}
