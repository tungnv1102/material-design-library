package com.blunderer.materialdesignlibrary.handlers;

import android.graphics.drawable.Drawable;

import com.blunderer.materialdesignlibrary.models.Account;

import java.util.ArrayList;
import java.util.List;

public class NavigationDrawerAccountsHandler {

    private List<Account> mItems;

    public NavigationDrawerAccountsHandler() {
        mItems = new ArrayList<>();
    }

    public NavigationDrawerAccountsHandler addAccount(String title,
                                                      String description,
                                                      Drawable picture,
                                                      Drawable backgroundImage) {
        Account item = new Account();
        item.setTitle(title);
        item.setDescription(description);
        item.setPicture(picture);
        item.setBackgroundImage(backgroundImage);
        mItems.add(item);
        return this;
    }

    public NavigationDrawerAccountsHandler addAccount(String title,
                                                      String description,
                                                      Drawable picture,
                                                      int backgroundColor) {
        Account item = new Account();
        item.setTitle(title);
        item.setDescription(description);
        item.setPicture(picture);
        item.setBackgroundColor(backgroundColor);
        mItems.add(item);
        return this;
    }

    public List<Account> getNavigationDrawerAccounts() {
        return mItems;
    }

}
