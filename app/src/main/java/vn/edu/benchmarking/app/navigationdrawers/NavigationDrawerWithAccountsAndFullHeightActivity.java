package vn.edu.benchmarking.app.navigationdrawers;

import android.accounts.AccountManager;
import android.content.Intent;
import android.util.Patterns;
import android.view.View;

import java.util.regex.Pattern;

import vn.edu.benchmarking.app.fragments.AdvisingFragment;
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

public class NavigationDrawerWithAccountsAndFullHeightActivity
        extends vn.edu.benchmarking.activities.NavigationDrawerActivity {

    @Override
    public NavigationDrawerStyleHandler getNavigationDrawerStyleHandler() {
        return null;
    }

    @Override
    public NavigationDrawerAccountsHandler getNavigationDrawerAccountsHandler() {
        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        android.accounts.Account[] accounts = AccountManager.get(getApplicationContext()).getAccounts();
        String possibleEmail = "";
        for (android.accounts.Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                possibleEmail = account.name;
            }
        }
        return new NavigationDrawerAccountsHandler(this)
                .addAccount("Thí sinh", possibleEmail,
                        R.drawable.profile4, R.drawable.profile1_background);
    }

    @Override
    public NavigationDrawerAccountsMenuHandler getNavigationDrawerAccountsMenuHandler() {
        return new NavigationDrawerAccountsMenuHandler(this)
                .addAddAccount(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {

                    }

                })
                .addManageAccounts(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                    }

                });
    }

    @Override
    public void onNavigationDrawerAccountChange(Account account) {
    }

    @Override
    public NavigationDrawerTopHandler getNavigationDrawerTopHandler() {
        return new NavigationDrawerTopHandler(this)
                .addSection(R.string.fragment)
                .addItem(R.string.fragment_advising, new AdvisingFragment())
                .addItem(R.string.fragment_diemchuan, new ScrollViewFragment());
//                .addItem(R.string.fragment_viewpager, new ViewPagerFragment())
//                .addItem(R.string.fragment_viewpager_with_tabs, new ViewPagerWithTabsFragment());
//                .addSection(R.string.activity)
//                .addItem(R.string.start_activity,new Intent(getApplicationContext(), ViewPagerActivity.class));
    }

    @Override
    public NavigationDrawerBottomHandler getNavigationDrawerBottomHandler() {
        return new NavigationDrawerBottomHandler(this)
                .addSettings(null)
                .addHelpAndFeedback(null);
    }

    @Override
    public boolean overlayActionBar() {
        return true;
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
