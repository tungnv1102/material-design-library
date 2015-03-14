package com.blunderer.materialdesignlibrary.models;

public class Account {

    private String mTitle;
    private String mDescription;
    private int mPictureResource = -1;
    private int mBackgroundResource;

    private boolean mUseBackground = false;

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

    public int getPictureResource() {
        return mPictureResource;
    }

    public void setPictureResource(int pictureResource) {
        this.mPictureResource = pictureResource;
    }

    public int getBackgroundResource() {
        return mBackgroundResource;
    }

    public void setBackgroundResource(int backgroundImageResource) {
        this.mBackgroundResource = backgroundImageResource;
        mUseBackground = true;
    }

    public boolean useBackgroundResource() {
        return mUseBackground;
    }

}
