package vn.edu.benchmarking.app.scrollviews;

import android.os.Handler;

import vn.edu.benchmarking.app.R;

public class ScrollViewFragment extends vn.edu.benchmarking.fragments.ScrollViewFragment {

    @Override
    public int getContentView() {
        return R.layout.fragment_scrollview;
    }

    @Override
    public boolean pullToRefreshEnabled() {
        return true;
    }

    @Override
    public int[] getPullToRefreshColorResources() {
        return new int[]{R.color.color_primary};
    }

    @Override
    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                setRefreshing(false);
            }

        }, 2000);
    }

}
