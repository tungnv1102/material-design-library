package com.blunderer.materialdesignlibrary.activities;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.blunderer.materialdesignlibrary.R;
import com.blunderer.materialdesignlibrary.adapters.NavigationDrawerAdapter;
import com.blunderer.materialdesignlibrary.adapters.NavigationDrawerBottomAdapter;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerAccountsHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerAccountsMenuHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerBottomHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerTopHandler;
import com.blunderer.materialdesignlibrary.listeners.OnAccountChangeListener;
import com.blunderer.materialdesignlibrary.listeners.OnMoreAccountClickListener;
import com.blunderer.materialdesignlibrary.models.Account;
import com.blunderer.materialdesignlibrary.models.ListItem;
import com.blunderer.materialdesignlibrary.models.NavigationDrawerAccountsListItemAccount;
import com.blunderer.materialdesignlibrary.models.NavigationDrawerAccountsListItemIntent;
import com.blunderer.materialdesignlibrary.models.NavigationDrawerAccountsListItemOnClick;
import com.blunderer.materialdesignlibrary.models.NavigationDrawerListItemBottom;
import com.blunderer.materialdesignlibrary.models.NavigationDrawerListItemTopFragment;
import com.blunderer.materialdesignlibrary.models.NavigationDrawerListItemTopIntent;
import com.blunderer.materialdesignlibrary.views.NavigationDrawerAccountsLayout;

import java.util.ArrayList;
import java.util.List;

public abstract class NavigationDrawerActivity extends AActivity implements OnAccountChangeListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private View mDrawerLeft;
    private NavigationDrawerListItemTopFragment mCurrentItem;
    private int mCurrentItemPosition = 0;
    private ListView mDrawerListView;
    private List<ListItem> mNavigationDrawerItems;
    private NavigationDrawerAccountsHandler mNavigationDrawerAccountsHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.activity_navigation_drawer);

        mDrawerLeft = findViewById(R.id.left_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerToggle = new ActionBarDrawerToggle(
                this,
                mDrawerLayout,
                getToolbar(),
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {

            @Override
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);

                if (mCurrentItem != null) replaceTitle(mCurrentItem.getTitle());
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);

                replaceTitle(getTitle().toString());
            }

        };
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        NavigationDrawerTopHandler navigationDrawerTopHandler = getNavigationDrawerTopHandler();
        if (navigationDrawerTopHandler == null ||
                navigationDrawerTopHandler.getNavigationDrawerTopItems() == null) {
            mNavigationDrawerItems = new ArrayList<>();
        } else mNavigationDrawerItems = navigationDrawerTopHandler.getNavigationDrawerTopItems();

        NavigationDrawerAdapter adapter = new NavigationDrawerAdapter(
                this,
                R.layout.navigation_drawer_row,
                mNavigationDrawerItems);
        mDrawerListView = (ListView) findViewById(R.id.left_drawer_listview);
        mDrawerListView.setAdapter(adapter);
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onListItemTopClick(adapterView, view, i);
            }

        });

        showAccountsLayout();

        List<NavigationDrawerListItemBottom> navigationDrawerItemsBottom;
        NavigationDrawerBottomHandler navigationDrawerBottomHandler =
                getNavigationDrawerBottomHandler();
        if (navigationDrawerBottomHandler == null
                || navigationDrawerBottomHandler.getNavigationDrawerBottomItems() == null) {
            navigationDrawerItemsBottom = new ArrayList<>();
        } else {
            navigationDrawerItemsBottom = navigationDrawerBottomHandler
                    .getNavigationDrawerBottomItems();
        }

        NavigationDrawerBottomAdapter bottomAdapter = new NavigationDrawerBottomAdapter(
                this,
                R.layout.navigation_drawer_row,
                navigationDrawerItemsBottom);
        final ListView mDrawerBottomListView = (ListView)
                findViewById(R.id.left_drawer_bottom_listview);
        mDrawerBottomListView.setAdapter(bottomAdapter);
        mDrawerBottomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onListItemBottomClick(adapterView, view, i);
            }

        });
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            mDrawerBottomListView.setBackgroundResource(R.drawable.navigation_drawer_bottom_shadow);
        }

        initFragment(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("current_fragment_position", mDrawerListView.getCheckedItemPosition());
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public void onAccountChange(Account account) {
        onNavigationDrawerAccountChange(account);
    }

    private void initFragment(Bundle savedInstanceState) {
        if (mNavigationDrawerItems.size() <= 0) return;

        int fragmentPosition;
        boolean isSavedInstanceState = false;
        if (savedInstanceState != null) {
            isSavedInstanceState = true;
            fragmentPosition = savedInstanceState.getInt("current_fragment_position", 0);
        } else fragmentPosition = defaultNavigationDrawerItemSelectedPosition();

        if (fragmentPosition < 0 || fragmentPosition >= mNavigationDrawerItems.size()) {
            fragmentPosition = 0;
        }

        ListItem item = mNavigationDrawerItems.get(fragmentPosition);
        if (item instanceof NavigationDrawerListItemTopFragment) {
            if (!isSavedInstanceState) {
                getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.fragment_container,
                                ((NavigationDrawerListItemTopFragment) item).getFragment())
                        .commit();
                mCurrentItem = (NavigationDrawerListItemTopFragment) item;
                mCurrentItemPosition = (getNavigationDrawerAccountsHandler() == null ? 0 : 1) +
                        fragmentPosition;
            }
            mDrawerListView.setItemChecked(mCurrentItemPosition, true);
            replaceTitle(item.getTitle());
        } else {
            for (int i = 0; i < mNavigationDrawerItems.size(); ++i) {
                if (mNavigationDrawerItems.get(i) instanceof NavigationDrawerListItemTopFragment) {
                    NavigationDrawerListItemTopFragment fragment = (NavigationDrawerListItemTopFragment)
                            mNavigationDrawerItems.get(i);

                    if (!isSavedInstanceState) {
                        getSupportFragmentManager().beginTransaction()
                                .add(R.id.fragment_container, fragment.getFragment())
                                .commit();
                        mCurrentItem = fragment;
                        mCurrentItemPosition = (getNavigationDrawerAccountsHandler() == null ?
                                0 : 1) + i;
                    }
                    mDrawerListView.setItemChecked(mCurrentItemPosition, true);
                    replaceTitle(fragment.getTitle());
                    break;
                }
            }
        }
    }

    private void onListItemTopClick(AdapterView<?> adapterView, View view, int i) {
        ListItem item = (ListItem) adapterView.getAdapter().getItem(i);

        if (item instanceof NavigationDrawerListItemTopFragment) {
            Fragment fragment = ((NavigationDrawerListItemTopFragment) item).getFragment();
            if (mCurrentItem == null || mCurrentItem.getFragment() != fragment) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, fragment).commit();
                mCurrentItem = (NavigationDrawerListItemTopFragment) item;
                mCurrentItemPosition = i;
            }
            replaceTitle(item.getTitle());
            mDrawerListView.setItemChecked(mCurrentItemPosition, true);
            mDrawerLayout.closeDrawer(mDrawerLeft);
        } else if (item instanceof NavigationDrawerListItemTopIntent) {
            mDrawerListView.setItemChecked(mCurrentItemPosition, true);
            startActivity(((NavigationDrawerListItemTopIntent) item).getIntent());
        } else if (item instanceof NavigationDrawerAccountsListItemIntent) {
            startActivity(((NavigationDrawerAccountsListItemIntent) item).getIntent());
        } else if (item instanceof NavigationDrawerAccountsListItemOnClick) {
            View.OnClickListener onClickListener = ((NavigationDrawerAccountsListItemOnClick) item)
                    .getOnClickListener();

            if (onClickListener != null) onClickListener.onClick(view);
        } else if (item instanceof NavigationDrawerAccountsListItemAccount) {
            OnMoreAccountClickListener onClickListener = ((NavigationDrawerAccountsListItemAccount) item)
                    .getOnClickListener();

            if (onClickListener != null) onClickListener.onMoreAccountClick(view, i);
        }
    }

    private void onListItemBottomClick(AdapterView<?> adapterView, View view, int i) {
        View.OnClickListener onClickListener = ((NavigationDrawerListItemBottom)
                adapterView.getAdapter().getItem(i)).getOnClickListener();

        if (onClickListener != null) onClickListener.onClick(view);
    }

    private void showAccountsLayout() {
        mNavigationDrawerAccountsHandler = getNavigationDrawerAccountsHandler();
        if (!isNavigationDrawerAccountHandlerEmpty()) {
            NavigationDrawerAccountsLayout accountsLayout = new NavigationDrawerAccountsLayout(
                    getApplicationContext());
            accountsLayout.setListView(mDrawerListView);
            accountsLayout.setAccounts(mNavigationDrawerAccountsHandler
                    .getNavigationDrawerAccounts());
            accountsLayout.setOnAccountChangeListener(this);
            if (getNavigationDrawerAccountsMenuHandler() != null) {
                accountsLayout.setNavigationDrawerAccountsMenuItems(
                        getNavigationDrawerAccountsMenuHandler()
                                .getNavigationDrawerAccountsMenuItems());
            }

            mDrawerListView.addHeaderView(accountsLayout, null, false);
        }
    }

    private void replaceTitle(int titleResource) {
        if (replaceActionBarTitleByNavigationDrawerItemTitle()) {
            getSupportActionBar().setTitle(titleResource);
        }
    }

    private void replaceTitle(String title) {
        if (replaceActionBarTitleByNavigationDrawerItemTitle()) {
            getSupportActionBar().setTitle(title);
        }
    }

    private boolean isNavigationDrawerAccountHandlerEmpty() {
        return mNavigationDrawerAccountsHandler == null ||
                mNavigationDrawerAccountsHandler.getNavigationDrawerAccounts().size() <= 0;
    }

    protected abstract NavigationDrawerAccountsHandler getNavigationDrawerAccountsHandler();

    protected abstract NavigationDrawerAccountsMenuHandler getNavigationDrawerAccountsMenuHandler();

    protected abstract void onNavigationDrawerAccountChange(Account account);

    protected abstract NavigationDrawerTopHandler getNavigationDrawerTopHandler();

    protected abstract NavigationDrawerBottomHandler getNavigationDrawerBottomHandler();

    protected abstract boolean replaceActionBarTitleByNavigationDrawerItemTitle();

    protected abstract int defaultNavigationDrawerItemSelectedPosition();

}
