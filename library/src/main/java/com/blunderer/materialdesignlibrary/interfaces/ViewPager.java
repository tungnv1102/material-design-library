package com.blunderer.materialdesignlibrary.interfaces;

import com.blunderer.materialdesignlibrary.handlers.ViewPagerHandler;

public interface ViewPager {

    public abstract ViewPagerHandler getViewPagerHandler();

    public abstract int defaultViewPagerPageSelectedPosition();

}
