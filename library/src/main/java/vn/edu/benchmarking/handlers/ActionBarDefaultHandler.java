package vn.edu.benchmarking.handlers;

import android.content.Context;

import vn.edu.benchmarking.views.Toolbar;
import vn.edu.benchmarking.views.ToolbarDefault;

public class ActionBarDefaultHandler extends ActionBarHandler {

    public ActionBarDefaultHandler(Context context) {
        super(context);
    }

    /**
     * Build the Toolbar to be the Default Toolbar.
     *
     * @return The Toolbar.
     */
    @Override
    public Toolbar build() {
        return new ToolbarDefault(mContext);
    }

}
