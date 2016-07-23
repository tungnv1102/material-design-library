package vn.edu.benchmarking.interfaces;

public interface ScrollView {

    void setRefreshing(boolean refreshing);

    int getContentView();

    boolean pullToRefreshEnabled();

    int[] getPullToRefreshColorResources();

    void onRefresh();

}
