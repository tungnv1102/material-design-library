package com.blunderer.materialdesignlibrary.sample.cardviews;

import android.os.Bundle;
import android.view.View;

import com.blunderer.materialdesignlibrary.sample.R;
import com.blunderer.materialdesignlibrary.views.CardView;

public class CardViewNormalActivity
        extends com.blunderer.materialdesignlibrary.activities.Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final CardView cardView = (CardView) findViewById(R.id.cardview);
        cardView.setOnNormalButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardView.setDescription("test");
            }
        });
        cardView.setOnHighlightButtonClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cardView.setImagePosition(CardView.POSITION_LEFT);
                cardView.setImageDrawable(null);
                cardView.setTitle("Title");
                cardView.setDescription("Description");
                cardView.setNormalButtonText("");
                cardView.setHighlightButtonText("");
            }
        });
    }

    @Override
    protected int getContentView() {
        return R.layout.activity_cardview_normal;
    }

}
