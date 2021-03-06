package vn.edu.benchmarking.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import vn.edu.benchmarking.R;
import vn.edu.benchmarking.listeners.OnMoreAccountClickListener;
import vn.edu.benchmarking.models.Account;
import vn.edu.benchmarking.models.NavigationDrawerAccountsListItemAccount;

public class NavigationDrawerAccountsLayoutSmall extends ANavigationDrawerAccountsLayout {

    public NavigationDrawerAccountsLayoutSmall(Context context) {
        this(context, null);
    }

    public NavigationDrawerAccountsLayoutSmall(Context context, AttributeSet attrs) {
        super(context, attrs);

        init(R.layout.mdl_navigation_drawer_accounts_small);
    }

    @Override
    protected void initAccounts() {
        ViewGroup dataLayout = (ViewGroup) mMainLayout.getChildAt(1);
        ViewGroup dataTextViewsLayout = (ViewGroup) dataLayout.getChildAt(1);
        mBackground = (ImageView) mMainLayout.getChildAt(0);
        mPicture = (RoundedImageView) ((ViewGroup) dataLayout.getChildAt(0)).getChildAt(1);
        mPictureProgressBar = (ProgressBar) ((ViewGroup) dataLayout.getChildAt(0)).getChildAt(0);
        mTitle = (TextView) dataTextViewsLayout.getChildAt(0);
        mDescription = (TextView) dataTextViewsLayout.getChildAt(1);

        changeAccount();
    }

    @Override
    protected void initAccountsMenuSwitch() {
        ViewGroup switchLayout = (ViewGroup) mMainLayout.getChildAt(1);
        mAccountsMenuSwitch = (ImageView) switchLayout.getChildAt(2);
        mAccountsMenuSwitch.setImageResource(R.drawable.ic_arrow_drop_down);
        mAccountsMenuSwitch.setVisibility(VISIBLE);
    }

    @Override
    protected void updateMoreAccountsList() {
        if (mAccounts != null && mAccounts.size() > 1 &&
                mAccountsMenuItems != null && mAccountsMenuAdapter != null) {
            NavigationDrawerAccountsListItemAccount moreAccount;
            for (int i = (mAccounts.size() - 1); i >= 1; --i) {
                Account account = mAccounts.get(i);
                moreAccount = new NavigationDrawerAccountsListItemAccount(getContext());
                moreAccount.setTitle(account.getTitle());
                if (account.usePictureUrl()) moreAccount.setIcon(account.getPictureUrl());
                else moreAccount.setIcon(account.getPictureResource());
                moreAccount.setOnClickListener(generateAccountClickListener(i));
                mAccountsMenuItems.add(0, moreAccount);
            }
            mAccountsMenuAdapter.notifyDataSetChanged();
        }
    }

    @Override
    protected OnMoreAccountClickListener generateAccountClickListener(final int accountId) {
        return new OnMoreAccountClickListener() {

            @Override
            public void onMoreAccountClick(View v, int i) {
                Account firstAccount = getAccount(0);
                int firstId = mAccountsPositions[0];

                if (i > 1) System.arraycopy(mAccountsPositions, 0, mAccountsPositions, 1, i);

                mAccountsPositions[0] = accountId;
                mAccountsPositions[1] = firstId;

                NavigationDrawerAccountsListItemAccount moreAccount =
                        new NavigationDrawerAccountsListItemAccount(getContext());
                moreAccount.setTitle(firstAccount.getTitle());
                if (firstAccount.usePictureUrl()) moreAccount.setIcon(firstAccount.getPictureUrl());
                else moreAccount.setIcon(firstAccount.getPictureResource());
                moreAccount.setOnClickListener(generateAccountClickListener(
                        mAccountsPositions[1]));

                mAccountsMenuItems.remove(i - 1);
                mAccountsMenuItems.add(0, moreAccount);
                mAccountsMenuAdapter.notifyDataSetChanged();

                changeAccount();
                if (mOnAccountChangeListener != null) {
                    mOnAccountChangeListener
                            .onAccountChange(mAccounts.get(mAccountsPositions[0]));
                }
            }

        };
    }

}
