package vn.edu.benchmarking.app.cardviews;

import vn.edu.benchmarking.handlers.ActionBarDefaultHandler;
import vn.edu.benchmarking.handlers.ActionBarHandler;
import vn.edu.benchmarking.app.R;

import vn.edu.benchmarking.activities.Activity;

public class CardViewWithTopImageActivity
        extends Activity {

    @Override
    protected int getContentView() {
        return R.layout.activity_cardview_with_top_image;
    }

    @Override
    protected boolean enableActionBarShadow() {
        return false;
    }

    @Override
    protected ActionBarHandler getActionBarHandler() {
        return new ActionBarDefaultHandler(this);
    }

}
