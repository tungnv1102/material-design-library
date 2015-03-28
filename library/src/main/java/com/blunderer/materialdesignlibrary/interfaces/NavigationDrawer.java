package com.blunderer.materialdesignlibrary.interfaces;

import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerAccountsHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerAccountsMenuHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerBottomHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerTopHandler;
import com.blunderer.materialdesignlibrary.models.Account;

public interface NavigationDrawer {

    public void updateNavigationDrawerTopHandler(
            NavigationDrawerTopHandler navigationDrawerTopHandler,
            int defaultNavigationDrawerItemSelectedPosition);

    public void updateNavigationDrawerBottomHandler(
            NavigationDrawerBottomHandler navigationDrawerBottomHandler);

    public void closeNavigationDrawer();

    public void openNavigationDrawer();

    public void performNavigationDrawerItemClick(int position);

    public NavigationDrawerAccountsHandler getNavigationDrawerAccountsHandler();

    public NavigationDrawerAccountsMenuHandler getNavigationDrawerAccountsMenuHandler();

    public void onNavigationDrawerAccountChange(Account account);

    public NavigationDrawerTopHandler getNavigationDrawerTopHandler();

    public NavigationDrawerBottomHandler getNavigationDrawerBottomHandler();

    public boolean overlayActionBar();

    public boolean replaceActionBarTitleByNavigationDrawerItemTitle();

    public int defaultNavigationDrawerItemSelectedPosition();

}
