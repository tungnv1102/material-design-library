package vn.edu.benchmarking.app.searchbar;

import android.widget.Toast;

import vn.edu.benchmarking.activities.Activity;
import vn.edu.benchmarking.handlers.ActionBarHandler;
import vn.edu.benchmarking.handlers.ActionBarSearchHandler;
import vn.edu.benchmarking.listeners.OnSearchListener;
import vn.edu.benchmarking.app.R;

public class SearchBarActivity extends Activity {

    @Override
    protected int getContentView() {
        return R.layout.activity_searchbar;
    }

    @Override
    protected boolean enableActionBarShadow() {
        return false;
    }

    @Override
    protected ActionBarHandler getActionBarHandler() {
        return new ActionBarSearchHandler(this, new OnSearchListener() {

            @Override
            public void onSearched(String text) {
                Toast.makeText(getApplicationContext(),
                        "Searching \"" + text + "\"", Toast.LENGTH_SHORT).show();
            }

        });
    }

}
