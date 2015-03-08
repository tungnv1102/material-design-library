package com.blunderer.materialdesignlibrary.activities;

import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.blunderer.materialdesignlibrary.R;
import com.blunderer.materialdesignlibrary.adapters.NavigationDrawerAdapter;
import com.blunderer.materialdesignlibrary.adapters.NavigationDrawerBottomAdapter;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerBottomHandler;
import com.blunderer.materialdesignlibrary.handlers.NavigationDrawerTopHandler;
import com.blunderer.materialdesignlibrary.models.NavigationDrawerItem;
import com.blunderer.materialdesignlibrary.models.NavigationDrawerItemBottom;
import com.blunderer.materialdesignlibrary.models.NavigationDrawerItemTopFragment;
import com.blunderer.materialdesignlibrary.models.NavigationDrawerItemTopIntent;

import java.util.ArrayList;
import java.util.List;

public abstract class NavigationDrawerActivity extends AActivity {

    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private View mDrawerLeft;
    private NavigationDrawerItemTopFragment mCurrentItem;
    private int mCurrentItemPosition = 0;
    private ListView mDrawerListView;
    private List<NavigationDrawerItem> mNavigationDrawerItems;

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

            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);

                if (mCurrentItem != null) {
                    replaceTitle(mCurrentItem.getTitleResource());
                }
            }

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
        } else {
            mNavigationDrawerItems = navigationDrawerTopHandler.getNavigationDrawerTopItems();
        }

        NavigationDrawerAdapter adapter = new NavigationDrawerAdapter(
                this,
                R.layout.navigation_drawer_row,
                mNavigationDrawerItems);
        mDrawerListView = (ListView) findViewById(R.id.left_drawer_listview);
        mDrawerListView.setAdapter(adapter);
        mDrawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                NavigationDrawerItem item = (NavigationDrawerItem)
                        adapterView.getAdapter().getItem(i);

                if (item instanceof NavigationDrawerItemTopFragment) {
                    Fragment fragment = ((NavigationDrawerItemTopFragment) item).getFragment();
                    if (mCurrentItem == null || mCurrentItem.getFragment() != fragment) {
                        FragmentManager fragmentManager = getSupportFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.fragment_container, fragment).commit();
                        mCurrentItem = (NavigationDrawerItemTopFragment) item;
                        mCurrentItemPosition = i;
                    }
                    replaceTitle(item.getTitleResource());
                    mDrawerListView.setItemChecked(mCurrentItemPosition, true);
                    mDrawerLayout.closeDrawer(mDrawerLeft);
                } else if (item instanceof NavigationDrawerItemTopIntent) {
                    mDrawerListView.setItemChecked(mCurrentItemPosition, true);
                    startActivity(((NavigationDrawerItemTopIntent) item).getIntent());
                }
            }

        });

        List<NavigationDrawerItemBottom> navigationDrawerItemsBottom;
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
                View.OnClickListener onClickListener = ((NavigationDrawerItemBottom)
                        adapterView.getAdapter().getItem(i)).getOnClickListener();

                if (onClickListener != null) onClickListener.onClick(view);
            }

        });
        if (android.os.Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
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

    private void initFragment(Bundle savedInstanceState) {
        if (mNavigationDrawerItems.size() <= 0) return;

        int fragmentPosition;
        if (savedInstanceState != null) {
            fragmentPosition = savedInstanceState.getInt("current_fragment_position", 0);
        } else {
            fragmentPosition = defaultNavigationDrawerItemSelectedPosition();
        }

        if (fragmentPosition < 0 || fragmentPosition >= mNavigationDrawerItems.size()) {
            fragmentPosition = 0;
        }

        NavigationDrawerItem item = mNavigationDrawerItems.get(fragmentPosition);
        if (item instanceof NavigationDrawerItemTopFragment) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.fragment_container,
                            ((NavigationDrawerItemTopFragment) item).getFragment())
                    .commit();
            mCurrentItem = (NavigationDrawerItemTopFragment) item;
            mCurrentItemPosition = fragmentPosition;
            mDrawerListView.setItemChecked(mCurrentItemPosition, true);
            replaceTitle(item.getTitleResource());
        } else {
            for (int i = 0; i < mNavigationDrawerItems.size(); ++i) {
                if (mNavigationDrawerItems.get(i) instanceof NavigationDrawerItemTopFragment) {
                    NavigationDrawerItemTopFragment fragment = (NavigationDrawerItemTopFragment)
                            mNavigationDrawerItems.get(i);

                    getSupportFragmentManager()
                            .beginTransaction().add(R.id.fragment_container, fragment.getFragment())
                            .commit();
                    mCurrentItem = fragment;
                    mCurrentItemPosition = i;
                    mDrawerListView.setItemChecked(mCurrentItemPosition, true);
                    replaceTitle(fragment.getTitleResource());
                    break;
                }
            }
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

    protected abstract NavigationDrawerTopHandler getNavigationDrawerTopHandler();

    protected abstract NavigationDrawerBottomHandler getNavigationDrawerBottomHandler();

    protected abstract boolean replaceActionBarTitleByNavigationDrawerItemTitle();

    protected abstract int defaultNavigationDrawerItemSelectedPosition();

}
