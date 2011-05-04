package com.rosehulman.android.fortune;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class Fortune extends Activity {
    
    private WebAdapter mWebAdapter;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        mWebAdapter = new WebAdapter();
        
        final TextView fortuneView = (TextView) findViewById(R.id.fortune);
        
        fortuneView.setOnClickListener(new OnClickListener() {
            
            public void onClick(View v) {
                fortuneView.setText(mWebAdapter.getRandomFortune());
            }
        });
    }
}