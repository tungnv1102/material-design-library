package com.blunderer.materialdesignlibrary.models;

import android.graphics.drawable.Drawable;

public class Account {

    private String mTitle;
    private String mDescription;
    private Drawable mPicture;
    private Drawable mBackgroundImage;
    private int mBackgroundColor;

    private boolean mUseBackgroundImage = false;
    private boolean mUseBackgroundColor = false;

    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String name) {
        this.mTitle = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String email) {
        this.mDescription = email;
    }

    public Drawable getPicture() {
        return mPicture;
    }

    public void setPicture(Drawable picture) {
        this.mPicture = picture;
    }

    public Drawable getBackgroundImage() {
        return mBackgroundImage;
    }

    public void setBackgroundImage(Drawable backgroundImage) {
        this.mBackgroundImage = backgroundImage;
        mUseBackgroundImage = true;
        mUseBackgroundColor = false;
    }

    public int getBackgroundColor() {
        return mBackgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.mBackgroundColor = backgroundColor;
        mUseBackgroundColor = true;
        mUseBackgroundImage = false;
    }

    public boolean useBackgroundImage() {
        return mUseBackgroundImage;
    }

    public boolean useBackgroundColor() {
        return mUseBackgroundColor;
    }

}
