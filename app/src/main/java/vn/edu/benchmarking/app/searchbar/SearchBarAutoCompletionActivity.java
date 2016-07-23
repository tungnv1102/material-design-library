package vn.edu.benchmarking.app.searchbar;

import android.widget.Toast;

import vn.edu.benchmarking.activities.Activity;
import vn.edu.benchmarking.handlers.ActionBarHandler;
import vn.edu.benchmarking.handlers.ActionBarSearchHandler;
import vn.edu.benchmarking.listeners.OnSearchListener;
import vn.edu.benchmarking.app.R;
import vn.edu.benchmarking.views.ToolbarSearch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchBarAutoCompletionActivity extends Activity {

    private final static List<String> mItems = new ArrayList<>(
            Arrays.asList("one", "two", "three", "four", "five", "six", "seven", "eight", "nine",
                    "ten", "eleven", "twelve", "thirteen", "fourteen", "fifteen", "sixteen",
                    "seventeen", "eighteen", "nineteen", "twenty"));

    @Override
    protected int getContentView() {
        return R.layout.activity_searchbar_autocompletion;
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

        })
                .enableAutoCompletion()
                .setAutoCompletionSuggestions(mItems)
                .setAutoCompletionMode(ToolbarSearch.AutoCompletionMode.CONTAINS);
    }

}
