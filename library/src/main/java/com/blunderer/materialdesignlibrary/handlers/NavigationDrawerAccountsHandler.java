package com.blunderer.materialdesignlibrary.handlers;

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
                                                      int pictureResource,
                                                      int backgroundResource) {
        Account item = new Account();
        item.setTitle(title);
        item.setDescription(description);
        item.setPictureResource(pictureResource);
        item.setBackgroundResource(backgroundResource);
        mItems.add(item);
        return this;
    }

    public List<Account> getNavigationDrawerAccounts() {
        return mItems;
    }

}
