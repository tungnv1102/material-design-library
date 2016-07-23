package vn.edu.benchmarking.interfaces;

import vn.edu.benchmarking.handlers.NavigationDrawerAccountsHandler;
import vn.edu.benchmarking.handlers.NavigationDrawerAccountsMenuHandler;
import vn.edu.benchmarking.handlers.NavigationDrawerBottomHandler;
import vn.edu.benchmarking.handlers.NavigationDrawerStyleHandler;
import vn.edu.benchmarking.handlers.NavigationDrawerTopHandler;
import vn.edu.benchmarking.models.Account;

public interface NavigationDrawer {

    void updateNavigationDrawerStyleHandler(
            NavigationDrawerStyleHandler navigationDrawerStyleHandler);

    void updateNavigationDrawerTopHandler(
            NavigationDrawerTopHandler navigationDrawerTopHandler,
            int defaultNavigationDrawerItemSelectedPosition);

    void updateNavigationDrawerBottomHandler(
            NavigationDrawerBottomHandler navigationDrawerBottomHandler);

    void closeNavigationDrawer();

    void openNavigationDrawer();

    boolean isNavigationDrawerOpen();

    void performNavigationDrawerItemClick(int position);

    NavigationDrawerStyleHandler getNavigationDrawerStyleHandler();

    NavigationDrawerAccountsHandler getNavigationDrawerAccountsHandler();

    NavigationDrawerAccountsMenuHandler getNavigationDrawerAccountsMenuHandler();

    void onNavigationDrawerAccountChange(Account account);

    NavigationDrawerTopHandler getNavigationDrawerTopHandler();

    NavigationDrawerBottomHandler getNavigationDrawerBottomHandler();

    boolean overlayActionBar();

    boolean replaceActionBarTitleByNavigationDrawerItemTitle();

    int defaultNavigationDrawerItemSelectedPosition();

}
