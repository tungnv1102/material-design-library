package vn.edu.benchmarking.app.navigationdrawers;

import android.content.Intent;

import vn.edu.benchmarking.handlers.ActionBarDefaultHandler;
import vn.edu.benchmarking.handlers.ActionBarHandler;
import vn.edu.benchmarking.handlers.NavigationDrawerAccountsHandler;
import vn.edu.benchmarking.handlers.NavigationDrawerAccountsMenuHandler;
import vn.edu.benchmarking.handlers.NavigationDrawerBottomHandler;
import vn.edu.benchmarking.handlers.NavigationDrawerStyleHandler;
import vn.edu.benchmarking.handlers.NavigationDrawerTopHandler;
import vn.edu.benchmarking.models.Account;
import vn.edu.benchmarking.app.listviews.ListViewFragment;
import vn.edu.benchmarking.app.scrollviews.ScrollViewFragment;
import vn.edu.benchmarking.app.viewpagers.ViewPagerActivity;
import vn.edu.benchmarking.app.viewpagers.ViewPagerFragment;
import vn.edu.benchmarking.app.viewpagers.ViewPagerWithTabsFragment;

import vn.edu.benchmarking.app.R;

public class NavigationDrawerActivity
        extends vn.edu.benchmarking.activities.NavigationDrawerActivity {

    @Override
    public NavigationDrawerStyleHandler getNavigationDrawerStyleHandler() {
        return null;
    }

    @Override
    public NavigationDrawerAccountsHandler getNavigationDrawerAccountsHandler() {
        return null;
    }

    @Override
    public NavigationDrawerAccountsMenuHandler getNavigationDrawerAccountsMenuHandler() {
        return null;
    }

    @Override
    public void onNavigationDrawerAccountChange(Account account) {
    }

    @Override
    public NavigationDrawerTopHandler getNavigationDrawerTopHandler() {
        return new NavigationDrawerTopHandler(this)
                .addSection(R.string.fragment)
                .addItem(R.string.fragment_listview, new ListViewFragment())
                .addItem(R.string.fragment_scrollview, new ScrollViewFragment())
                .addItem(R.string.fragment_viewpager, new ViewPagerFragment())
                .addItem(R.string.fragment_viewpager_with_tabs, new ViewPagerWithTabsFragment())
                .addSection(R.string.activity)
                .addItem(R.string.start_activity, R.mipmap.ic_github,
                        new Intent(getApplicationContext(), ViewPagerActivity.class));
    }

    @Override
    public NavigationDrawerBottomHandler getNavigationDrawerBottomHandler() {
        return new NavigationDrawerBottomHandler(this)
                .addSettings(null)
                .addHelpAndFeedback(null);
    }

    @Override
    public boolean overlayActionBar() {
        return false;
    }

    @Override
    public boolean replaceActionBarTitleByNavigationDrawerItemTitle() {
        return true;
    }

    @Override
    public int defaultNavigationDrawerItemSelectedPosition() {
        return 1;
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
