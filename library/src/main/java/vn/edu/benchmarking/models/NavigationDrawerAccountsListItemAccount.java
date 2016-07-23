package vn.edu.benchmarking.models;

import android.content.Context;

import vn.edu.benchmarking.listeners.OnMoreAccountClickListener;

public class NavigationDrawerAccountsListItemAccount extends NavigationDrawerAccountsListItem {

    private OnMoreAccountClickListener mOnClickListener;

    public NavigationDrawerAccountsListItemAccount(Context context) {
        super(context, CUSTOM);
    }

    public OnMoreAccountClickListener getOnClickListener() {
        return mOnClickListener;
    }

    public void setOnClickListener(OnMoreAccountClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }

}
