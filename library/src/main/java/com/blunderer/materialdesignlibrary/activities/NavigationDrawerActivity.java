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

public abstract class NavigationDrawerActivity extends AActivity
        implements OnAccountChangeListener {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private View mDrawerLeft;
    private NavigationDrawerListItemTopFragment mCurrentItem;
    private NavigationDrawerAccountsLayout mAccountsLayout;
    private int mCurrentItemPosition = 0;
    private ListView mTopListView;
    private ListView mBottomListView;
    private NavigationDrawerAdapter mListTopAdapter;
    private NavigationDrawerBottomAdapter mListBottomAdapter;
    private List<ListItem> mNavigationDrawerItemsTop;
    private List<NavigationDrawerListItemBottom> mNavigationDrawerItemsBottom;
    private NavigationDrawerAccountsHandler mNavigationDrawerAccountsHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, overlayActionBar() ?
                R.layout.activity_navigation_drawer_full : R.layout.activity_navigation_drawer);

        defineDrawerLayout();
        defineListTop();
        defineListBottom();

        initFragment(savedInstanceState);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("current_fragment_position", mTopListView.getCheckedItemPosition());
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

    /**
     * Will update the Top ListView items.
     *
     * @param navigationDrawerTopHandler                  The handler that contains the new Top ListView items.
     * @param defaultNavigationDrawerItemSelectedPosition The position of the item that is going to be selected.
     */
    protected void updateNavigationDrawerTopHandler(
            NavigationDrawerTopHandler navigationDrawerTopHandler,
            int defaultNavigationDrawerItemSelectedPosition) {
        replaceTopItems(navigationDrawerTopHandler);
        selectDefaultItemPosition(defaultNavigationDrawerItemSelectedPosition, false, true);
    }


    /**
     * Will update the Bottom ListView items.
     *
     * @param navigationDrawerBottomHandler The handler that contains the new Top ListView items.
     */
    protected void updateNavigationDrawerBottomHandler(
            NavigationDrawerBottomHandler navigationDrawerBottomHandler) {
        replaceBottomItems(navigationDrawerBottomHandler);
    }

    private void defineListTop() {
        mNavigationDrawerItemsTop = new ArrayList<>();
        mListTopAdapter = new NavigationDrawerAdapter(this,
                R.layout.navigation_drawer_row, mNavigationDrawerItemsTop);
        mTopListView = (ListView) findViewById(R.id.left_drawer_listview);
        mTopListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onListItemTopClick(adapterView, view, i);
            }

        });
        showAccountsLayout();
    }

    private void defineListBottom() {
        mNavigationDrawerItemsBottom = new ArrayList<>();
        mListBottomAdapter = new NavigationDrawerBottomAdapter(
                this,
                R.layout.navigation_drawer_row,
                mNavigationDrawerItemsBottom);
        mBottomListView = (ListView)
                findViewById(R.id.left_drawer_bottom_listview);
        mBottomListView.setAdapter(mListBottomAdapter);
        mBottomListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                onListItemBottomClick(adapterView, view, i);
            }

        });
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            mBottomListView.setBackgroundResource(R.drawable.navigation_drawer_bottom_shadow);
        }
    }

    private void defineDrawerLayout() {
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
    }

    private void initFragment(Bundle savedInstanceState) {
        int fragmentPosition;
        boolean isSavedInstanceState = false;
        if (savedInstanceState != null) {
            isSavedInstanceState = true;
            fragmentPosition = savedInstanceState.getInt("current_fragment_position", 0);
        } else fragmentPosition = defaultNavigationDrawerItemSelectedPosition();

        replaceTopItems(getNavigationDrawerTopHandler());
        replaceBottomItems(getNavigationDrawerBottomHandler());
        selectDefaultItemPosition(fragmentPosition, isSavedInstanceState, false);
    }

    private void replaceTopItems(NavigationDrawerTopHandler navigationDrawerTopHandler) {
        mNavigationDrawerItemsTop.clear();

        if (navigationDrawerTopHandler != null &&
                navigationDrawerTopHandler.getNavigationDrawerTopItems() != null) {
            mNavigationDrawerItemsTop
                    .addAll(navigationDrawerTopHandler.getNavigationDrawerTopItems());
        }

        if (mCurrentItem != null) {
            getSupportFragmentManager().beginTransaction()
                    .remove(mCurrentItem.getFragment()).commit();
            mTopListView.setItemChecked(mCurrentItemPosition, false);
        }

        mListTopAdapter.notifyDataSetChanged();
    }

    private void replaceBottomItems(NavigationDrawerBottomHandler navigationDrawerBottomHandler) {
        mNavigationDrawerItemsBottom.clear();

        if (navigationDrawerBottomHandler != null &&
                navigationDrawerBottomHandler.getNavigationDrawerBottomItems() != null) {
            mNavigationDrawerItemsBottom.addAll(navigationDrawerBottomHandler
                    .getNavigationDrawerBottomItems());
        }

        mListBottomAdapter.notifyDataSetChanged();
    }

    private void selectDefaultItemPosition(int fragmentPosition,
                                           boolean isSavedInstanceState,
                                           boolean isUpdatingTopHandler) {
        if (mNavigationDrawerItemsTop.size() <= 0) return;

        if (fragmentPosition < 0 || fragmentPosition >= mNavigationDrawerItemsTop.size()) {
            fragmentPosition = 0;
        }

        ListItem item = mNavigationDrawerItemsTop.get(fragmentPosition);
        if (item instanceof NavigationDrawerListItemTopFragment) {
            if (!isSavedInstanceState) {
                getSupportFragmentManager().beginTransaction()
                        .add(R.id.fragment_container,
                                ((NavigationDrawerListItemTopFragment) item).getFragment())
                        .commit();
                mCurrentItem = (NavigationDrawerListItemTopFragment) item;
                mCurrentItemPosition = (isNavigationDrawerAccountHandlerEmpty() ? 0 : 1) +
                        fragmentPosition;
            } else if (isUpdatingTopHandler) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container,
                                ((NavigationDrawerListItemTopFragment) item).getFragment())
                        .commit();
                mCurrentItem = (NavigationDrawerListItemTopFragment) item;
                mCurrentItemPosition = (isNavigationDrawerAccountHandlerEmpty() ? 0 : 1) +
                        fragmentPosition;
            }

            mTopListView.setItemChecked(mCurrentItemPosition, true);
            replaceTitle(item.getTitle());
        } else {
            for (int i = 0; i < mNavigationDrawerItemsTop.size(); ++i) {
                if (mNavigationDrawerItemsTop.get(i) instanceof
                        NavigationDrawerListItemTopFragment) {
                    NavigationDrawerListItemTopFragment fragment =
                            (NavigationDrawerListItemTopFragment) mNavigationDrawerItemsTop.get(i);

                    if (!isSavedInstanceState) {
                        getSupportFragmentManager().beginTransaction()
                                .add(R.id.fragment_container, fragment.getFragment())
                                .commit();
                        mCurrentItem = fragment;
                        mCurrentItemPosition = (isNavigationDrawerAccountHandlerEmpty() ?
                                0 : 1) + i;
                    } else if (isUpdatingTopHandler) {
                        getSupportFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, fragment.getFragment())
                                .commit();
                        mCurrentItem = fragment;
                        mCurrentItemPosition = (isNavigationDrawerAccountHandlerEmpty() ?
                                0 : 1) + i;
                    }

                    mTopListView.setItemChecked(mCurrentItemPosition, true);
                    replaceTitle(fragment.getTitle());
                    break;
                }
            }
        }

        if (!isNavigationDrawerAccountHandlerEmpty()) {
            mAccountsLayout.notifyListTopItemSelectedChanged();
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
            mTopListView.setItemChecked(mCurrentItemPosition, true);
            mDrawerLayout.closeDrawer(mDrawerLeft);
        } else if (item instanceof NavigationDrawerListItemTopIntent) {
            mTopListView.setItemChecked(mCurrentItemPosition, true);
            startActivity(((NavigationDrawerListItemTopIntent) item).getIntent());
        } else if (item instanceof NavigationDrawerAccountsListItemIntent) {
            startActivity(((NavigationDrawerAccountsListItemIntent) item).getIntent());
        } else if (item instanceof NavigationDrawerAccountsListItemOnClick) {
            View.OnClickListener onClickListener = ((NavigationDrawerAccountsListItemOnClick) item)
                    .getOnClickListener();

            if (onClickListener != null) onClickListener.onClick(view);
        } else if (item instanceof NavigationDrawerAccountsListItemAccount) {
            OnMoreAccountClickListener onClickListener =
                    ((NavigationDrawerAccountsListItemAccount) item).getOnClickListener();

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
            mAccountsLayout = new NavigationDrawerAccountsLayout(
                    getApplicationContext());
            mAccountsLayout.setListView(mTopListView);
            mAccountsLayout.setListViewAdapter(mListTopAdapter);
            mAccountsLayout.setAccounts(mNavigationDrawerAccountsHandler
                    .getNavigationDrawerAccounts());
            mAccountsLayout.setOnAccountChangeListener(this);
            if (getNavigationDrawerAccountsMenuHandler() != null) {
                mAccountsLayout.setNavigationDrawerAccountsMenuItems(
                        getNavigationDrawerAccountsMenuHandler()
                                .getNavigationDrawerAccountsMenuItems());
            }

            mTopListView.addHeaderView(mAccountsLayout, null, false);
        }
        mTopListView.setAdapter(mListTopAdapter);
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

    protected abstract boolean overlayActionBar();

    protected abstract boolean replaceActionBarTitleByNavigationDrawerItemTitle();

    protected abstract int defaultNavigationDrawerItemSelectedPosition();

}
